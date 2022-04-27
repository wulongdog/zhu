package com.zhu.zhupro.utils.pathutils;


public class UserUrlPath {

    public static String setUserUrl(long userId) {
        String path = "/user/user"+userId+"-home";
        return path;
    }
}
