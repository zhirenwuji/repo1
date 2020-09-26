package com.h3c.weixin.controller;

import com.h3c.weixin.utils.UrlUtil;
import sun.net.util.URLUtil;

public class Test {
    public static void main(String[] args) {

        String url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+ UrlUtil.getToken();
        String paramStr="{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"haha\"}}}";
        String result = UrlUtil.get(url, paramStr);
        System.out.println(result);

    }
}
