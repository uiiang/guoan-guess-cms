package com.uiiang;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.ConfigurationManager;
import org.json.JSONException;

/**
 * Created by fuliqiang on 2017/5/2.
 */
public class QCloud {
    public static void setupSDK() {
        try {
            String configFilePath = getConfigFilePath();
            System.out.println("QCloud SDK 配置文件路径：" + configFilePath);
            ConfigurationManager.setupFromFile(configFilePath);
            System.out.println("QCloud SDK 已成功配置！");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static String getConfigFilePath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String defaultConfigFilePath = null;
        System.out.println("os name = " + osName);
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
