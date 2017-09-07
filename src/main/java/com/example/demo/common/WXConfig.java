package com.example.demo.common;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WXConfig {

    public static final String WX_WEB_APP_ID = "wxd4b3d4bd675361f2";

    public static final String WX_WEB_APP_SECRET = "wxd4b3d4bd675361f2";

    public static final String WX_WEB_REDIRECT_URI = "https://www.acyone.top/demo/test";

    public static final String TAKEN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("https://www.superprism.cn/test/wxlogin","UTF-8"));
    }

}
