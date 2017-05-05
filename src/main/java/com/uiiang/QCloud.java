package com.uiiang;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.ConfigurationManager;
import com.uiiang.utils.LogUtils;
import org.json.JSONException;

/**
 * Created by fuliqiang on 2017/5/2.
 */
public class QCloud {
    public static void setupSDK() {
        try {
            String configFilePath = getConfigFilePath();
            LogUtils.i("QCloud SDK 配置文件路径：" + configFilePath);
            ConfigurationManager.setupFromFile(configFilePath);
            LogUtils.i("QCloud SDK 已成功配置！");
        } catch (JSONException | ConfigurationException e) {
            LogUtils.ex(e);
            e.printStackTrace();
        }
    }

    private static String getConfigFilePath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String defaultConfigFilePath = null;
        LogUtils.i("os name = " + osName);
        boolean isWindows = osName.indexOf("windows") > -1;
        boolean isLinux = osName.indexOf("linux") > -1;

        if (isWindows) {
            defaultConfigFilePath = "C:\\qcloud\\sdk.config";
        } else if (isLinux) {
            defaultConfigFilePath = "/etc/qcloud/sdk.config";
        } else {
            defaultConfigFilePath = "/etc/qcloud/sdk.config";
        }
        return defaultConfigFilePath;
    }
}
