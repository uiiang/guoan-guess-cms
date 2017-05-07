package com.uiiang.controller;

/**
 * Created by fuliqiang on 2017/5/5.
 */
public class ErrorCodeManager {
    public static final int ERROR_CODE_SUCCESS = 0;
    public static final String ERROR_MSG_SUCCESS = "SUCCESS";


    public static final int ERROR_CODE_GUESS_NOTSTART = 100;//竞猜比赛未开始
    public static final String ERROR_MSG_GUESS_NOTSTART = "竞猜比赛未开始";//竞猜比赛未开始

    public static final int ERROR_CODE_PLAYER_GUESS_NOT_FOUND = 101;
    public static final String ERROR_MSG_PLAYER_GUESS_NOT_FOUND = "未找到该玩家的竞猜记录";

    public static final int ERROR_CODE_NUMBER_FORMAT = 200;
    public static final String ERROR_MSG_NUMBER_FORMAT = "数字格式错误";
    public static final int ERROR_CODE_USER_LOGIN = 201;
    public static final String ERROR_MSG_USER_LOGIN = "玩家登录错误";
}
