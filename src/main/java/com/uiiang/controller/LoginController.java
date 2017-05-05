package com.uiiang.controller;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.authorization.LoginService;
import com.qcloud.weapp.authorization.LoginServiceException;
import com.qcloud.weapp.authorization.UserInfo;
import com.uiiang.biz.PlayerInfoService;
import com.uiiang.entity.PlayerInfo;
import com.uiiang.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fuliqiang on 2017/5/2.
 */
@Controller
public class LoginController {
    PlayerInfoService crudRepository;

    public LoginController(PlayerInfoService crudRepository) {
        this.crudRepository = crudRepository;
    }

    @GetMapping("/login")
    public void loginServer(HttpServletRequest request, HttpServletResponse response) {
        LogUtils.i("========= LoginSuccess, loginServer: ==========");
        LoginService loginService = new LoginService(request, response);
        try {
            // 调用登录接口，如果登录成功可以获得登录信息
            UserInfo userInfo = loginService.login();
            LogUtils.i("========= LoginSuccess, UserInfo: ==========");
            LogUtils.i(userInfo.toString());

            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setAvatarUrl(userInfo.getAvatarUrl());
            playerInfo.setCity(userInfo.getCity());
            playerInfo.setCountry(userInfo.getCountry());
            playerInfo.setGender(userInfo.getGender());
            playerInfo.setLanguage(userInfo.getLanguage());
            playerInfo.setNickName(userInfo.getNickName());
            playerInfo.setOpenId(userInfo.getOpenId());
            playerInfo.setProvince(userInfo.getProvince());

            crudRepository.save(playerInfo);
        } catch (LoginServiceException | ConfigurationException e) {
            // 登录失败会抛出登录失败异常
            LogUtils.ex(e);
            e.printStackTrace();
        }
    }


    @GetMapping("/checkuser")
    public void checkUser(HttpServletRequest request, HttpServletResponse response) {
        LoginService service = new LoginService(request, response);
        try {
            // 调用检查登录接口，成功后可以获得用户信息，进行正常的业务请求
            UserInfo userInfo = service.check();
            LogUtils.i("user info = " + userInfo.getOpenId() + "  " + userInfo.getNickName());
            // 获取会话成功，输出获得的用户信息
            JSONObject result = new JSONObject();
            JSONObject data = new JSONObject();
            data.put("userInfo", new JSONObject(userInfo));
            result.put("code", 0);
            result.put("message", "OK");
            result.put("data", data);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(result.toString());

        } catch (LoginServiceException | JSONException | ConfigurationException | IOException e) {
            LogUtils.ex(e);
            e.printStackTrace();
        }
    }
}
