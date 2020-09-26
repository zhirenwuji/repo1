package com.h3c.weixin.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class UrlUtil {
    private static String APPID="wxc225d3a4763cef60";
    private static String APPSECRET="1f37808ebde35227b019ce65c99920de";
//private  static String APPID="wx8c708762cf3b029c";
//    private static String APPSECRET="7a1d69038ed1bbff0c75b40e4a13d305";
    public static String  get(String urlStr,String paramStr){
        //https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
        URL url = null;
        try {
            url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if (paramStr!=null){
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(paramStr.getBytes(Charset.forName("utf-8")));
            }
            InputStream inputStream = connection.getInputStream();
            byte[] bytes=new byte[1024];
            int len=0;
            StringBuffer sb = new StringBuffer();
            while ((len=inputStream.read(bytes))!=-1){
                sb.append(new String(bytes,0,len));
            }
            System.out.println("====>"+sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getToken(){
        String urlStr = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET + "";
        String result = UrlUtil.get(urlStr, null);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonO = jsonParser.parse(result).getAsJsonObject();
        String access_token = jsonO.get("access_token").getAsString();
        System.out.println("access_token===>" + access_token);
        return access_token;
    }
    public static String  writeToLocal(String urlStr){
        //https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
        URL url = null;
        String path="e:/aaaa";
        try {
            url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            FileOutputStream outputStream=new FileOutputStream(path);

            byte[] bytes=new byte[1024];
            int len=0;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
