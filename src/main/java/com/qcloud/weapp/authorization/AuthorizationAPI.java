package com.qcloud.weapp.authorization;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.ConfigurationManager;
import com.qcloud.weapp.HttpRequest;
import com.uiiang.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class AuthorizationAPI {

    private String getAPIUrl() throws ConfigurationException {
        return ConfigurationManager.getCurrentConfiguration().getAuthServerUrl();
    }

    public JSONObject login(String code, String encryptedData, String iv) throws AuthorizationAPIException, ConfigurationException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("encrypt_data", encryptedData);
        params.put("iv", iv);
        return request("qcloud.cam.id_skey", params);
    }

    public JSONObject checkLogin(String id, String skey) throws AuthorizationAPIException, ConfigurationException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("skey", skey);
        return request("qcloud.cam.auth", params);
    }

    public JSONObject request(String apiName, Map<String, Object> apiParams) throws AuthorizationAPIException, ConfigurationException {
        String requestBody = null;
        String responseBody = null;

        try {
            String APIURL = getAPIUrl();
            if (!APIURL.endsWith("/")) {
                APIURL = APIURL + "/";
            }
            HttpRequest request = new HttpRequest(getAPIUrl());

            requestBody = buildRequestBody(apiName, apiParams);
            LogUtils.i("==============Auth Request=============");
            LogUtils.i(requestBody);

            responseBody = request.post(requestBody);
            LogUtils.i("==============Auth Response=============");
            LogUtils.i(requestBody);
        } catch (IOException e) {
            LogUtils.ex(e);
            throw new AuthorizationAPIException("连接鉴权服务错误，请检查网络状态" + getAPIUrl() + e.getMessage());
        }

        JSONObject body = null;
        int returnCode = 0;
        String returnMessage = null;

        try {
            body = new JSONObject(responseBody);
            returnCode = body.getInt("returnCode");
            returnMessage = body.getString("returnMessage");
        } catch (JSONException e) {
            LogUtils.ex(e);
            throw new AuthorizationAPIException("调用鉴权服务失败：返回了非法的 JSON 字符串", e);
        }

        if (returnCode != 0) {
            AuthorizationAPIException error = new AuthorizationAPIException(String.format("调用鉴权服务失败：#%d - %s " + getAPIUrl(), returnCode, returnMessage));
            error.setCode(returnCode);
            throw error;
        }
        JSONObject returnData = null;
        try {
            returnData = body.getJSONObject("returnData");
        } catch (JSONException e) {
            LogUtils.ex(e);
        }

        return returnData;
    }

    private String buildRequestBody(String apiName, Map<String, Object> apiParams) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject interfaceJson = new JSONObject();
            interfaceJson.put("interfaceName", apiName);
            interfaceJson.put("para", apiParams);

            jsonObject.put("version", 1);
            jsonObject.put("componentName", "MA");
            jsonObject.put("interface", interfaceJson);
        } catch (JSONException e) {
            LogUtils.ex(e);
            e.printStackTrace();
        }

        return jsonObject.toString();
    }
}
