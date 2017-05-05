package com.uiiang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by fuliqiang on 2017/5/5.
 */
public class LogUtils {
    private static Logger logger = LoggerFactory.getLogger("guoan1992");

    public static void i(String msg) {
        if (logger != null) {
            logger.info(msg);
        }
    }

    public static void d(String msg) {
        if (logger != null) {
            logger.debug(msg);
        }
    }

    public static void e(String msg) {
        if (logger != null) {
            logger.error(msg);
        }
    }

    public static void ex(Exception e) {
        if (logger != null) {
            logger.error(e.getMessage(), e.getCause());
        }
    }
}
