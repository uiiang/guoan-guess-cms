package com.uiiang.controller;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.authorization.LoginService;
import com.qcloud.weapp.authorization.LoginServiceException;
import com.qcloud.weapp.authorization.UserInfo;
import com.uiiang.biz.PlayerInfoService;
import com.uiiang.entity.PlayerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        LoginService loginService = new LoginService(request, response);
        try {
            // 调用登录接口，如果登录成功可以获得登录信息
            UserInfo userInfo = loginService.login();
            System.out.println("========= LoginSuccess, UserInfo: ==========");
            System.out.println(userInfo.toString());

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
        } catch (LoginServiceException e) {
            // 登录失败会抛出登录失败异常
            e.printStackTrace();
        } catch (ConfigurationException e) {
            // SDK 如果还没有配置会抛出配置异常
            e.printStackTrace();
        }
    }
}
