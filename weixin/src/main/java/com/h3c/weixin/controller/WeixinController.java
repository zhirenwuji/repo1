package com.h3c.weixin.controller;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.h3c.weixin.utils.Sample;
import com.h3c.weixin.utils.UrlUtil;
import com.h3c.weixin.vo.*;
import com.sun.org.apache.xpath.internal.objects.XString;
import org.apache.tomcat.util.buf.CharsetUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class WeixinController {
private String APPID="wxc225d3a4763cef60";
//private String APPID="wx8c708762cf3b029c";
private String APPSECRET="1f37808ebde35227b019ce65c99920de";
//private String APPSECRET="7a1d69038ed1bbff0c75b40e4a13d305";
    @GetMapping("wx")
    public void connect(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getParameter("signature"));
        String echostr = request.getParameter("echostr");
        try {
            response.getWriter().write(echostr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    String picUrl="http://mmbiz.qpic.cn/mmbiz_jpg/pajHAcxQO2RKtcIic2f6tUU187SicCOXTxFLWhVgDxrmicoMISibDeFGcBcuuDvUoVtPZ8mfh14aLGiac9VJMicOx45Q/0]";
    String MediaId="RL_JigIc4-f3hQumdfANYyBPc9DhMQEu_Kx6bdyKPGGSDQo6HSqYqCm5PTKocqB3";
    @PostMapping("wx")
    public void receiveMsg(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = inputStream.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            System.out.println(sb.toString());
            SAXReader reader = new SAXReader();
            String content = null;
                Document document = reader.read(new ByteArrayInputStream(sb.toString().getBytes()));
                Element rootElement = document.getRootElement();
                String msgType = rootElement.elementText("MsgType");

                content = rootElement.elementText("Content");
                String toUserName = rootElement.elementText("ToUserName");
                String fromUserName = rootElement.elementText("FromUserName");
            String event = rootElement.elementText("Event");
            String eventKey = rootElement.elementText("EventKey");
            response.setCharacterEncoding("utf-8");
            if("登录".equals(content)){
                String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=http://goodfriend.free.idcfengye.com/templateUrl&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
                response.getWriter().write("<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[gh_5f4fddede062]]></FromUserName>\n" +
                        "<CreateTime>1592056670</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[点击<a href=\""+url+"\"/>登录</a>]]></Content>\n" +
                        "<MsgId>22792770726491684</MsgId>\n" +
                        "</xml>");
            }
            if("event".equals(msgType) && "CLICK".equals(event) && eventKey.equals("1")){
                response.getWriter().write("<xml><ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                        "<FromUserName><![CDATA[gh_5f4fddede062]]></FromUserName>\n" +
                        "<CreateTime>1592056670</CreateTime>\n" +
                        "<MsgType><![CDATA[text]]></MsgType>\n" +
                        "<Content><![CDATA[菜单一]]></Content>\n" +
                        "<MsgId>22792770726491684</MsgId>\n" +
                        "</xml>");
            }
            if("支付".equals(content)){
                    Map<String, Object> map = new HashMap<>();
                    map.put("touser", fromUserName);
                    map.put("template_id", "PLl-f_kBvTtOnQZxFX0jKoEIkDVytoWlnL2XT4u4QPY");
                    map.put("url", "http://goodfriend.free.idcfengye.com/templateUrl");
                    Map<String, Object> data = new HashMap<>();
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("value", "一号食堂");
                    jsonObject.addProperty("color", "#173177");
                    data.put("keyword1", jsonObject);
                    JsonObject jsonObject1 = new JsonObject();
                    jsonObject1.addProperty("value", "10快");
                    jsonObject1.addProperty("color", "#173177");
                    data.put("keyword2", jsonObject1);
                    JsonObject jsonObject3 = new JsonObject();
                    jsonObject3.addProperty("value", "2030-6-13");
                    jsonObject3.addProperty("color", "#173177");
                    data.put("keyword3", jsonObject3);
                    JsonObject jsonObject4 = new JsonObject();
                    jsonObject4.addProperty("value", "dsfasdfasfe3432");
                    jsonObject4.addProperty("color", "#173177");
                    data.put("keyword4", jsonObject4);
                    JsonObject jsonObject5 = new JsonObject();
                    jsonObject5.addProperty("value", "花的开心");
                    jsonObject5.addProperty("color", "#173177");
                    data.put("remark", jsonObject5);
                    map.put("data", data);
                    String msg = new Gson().toJson(map);


                    UrlUtil.get("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + UrlUtil.getToken(), msg);
                }




                if ("图文".equals(content)) {
                    response.getWriter().write("<xml>\n" +
                            "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                            "  <FromUserName><![CDATA[gh_5f4fddede062]]></FromUserName>\n" +
                            "  <CreateTime>1591893846</CreateTime>\n" +
                            "  <MsgType><![CDATA[news]]></MsgType>\n" +
                            "  <ArticleCount>3</ArticleCount>\n" +
                            "  <Articles>\n" +
                            "    <item>\n" +
                            "      <Title><![CDATA[万众期待的《乘风破浪的姐姐》开播了]]></Title>\n" +
                            "      <Description><![CDATA[女团成长综艺类节目《乘风破浪的姐姐》在开始官宣的时候,就引来了很大的热度,官宣定档后也如期播出,这个节目一出后,直接秒杀了很多的综艺类节目,比如《创造2020》因...]]></Description>\n" +
                            "      <PicUrl><![CDATA[https://pics4.baidu.com/feed/f9dcd100baa1cd1133c40728cfcf06fac2ce2daf.jpeg?token=eac652f1e4b42432d46d21e0e2fe31ba]]></PicUrl>\n" +
                            "      <Url><![CDATA[https://baijiahao.baidu.com/s?id=1669284428810469733&wfr=spider&for=pc]]></Url>\n" +
                            "    </item>\n" +
                            "    <item>\n" +
                            "      <Title><![CDATA[作为评委，打分环节首先就要强调的是要有水准、要公平]]></Title>\n" +
                            "      <Description><![CDATA[杜华这么出名的原因和杨天真差不多，她的做事风格和态度时常被公司旗下艺人的粉丝和网友们骂的狗血淋头，这次在《乘风破浪的姐姐》中，她一如既往的谜之操作包揽了本期节目的最大槽点！]]></Description>\n" +
                            "      <PicUrl><![CDATA[https://pics3.baidu.com/feed/18d8bc3eb13533fae8a2a4e1dd0e331943345bc8.jpeg?token=40e17160ff597b903c4f008dbe2ef76f]]></PicUrl>\n" +
                            "      <Url><![CDATA[http://www.baidu.com]]></Url>\n" +
                            "    </item>\n" +
                            "    <item>\n" +
                            "      <Title><![CDATA[标题3]]></Title>\n" +
                            "      <Description><![CDATA[可爱的小鸡3]]></Description>\n" +
                            "      <PicUrl><![CDATA[http://mmbiz.qpic.cn/mmbiz_jpg/pajHAcxQO2S5qHvXKcTWnwMMZIKibw47IIZefLvVr4jXNUMcRbPm4JJAhKaFj7EFv2yaNM4hrmKicvhsud39Qaqg/0]]></PicUrl>\n" +
                            "      <Url><![CDATA[http://www.baidu.com]]></Url>\n" +
                            "    </item>\n" +
                            "  </Articles>\n" +
                            "</xml>");
                }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String url=" https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+UrlUtil.getToken();
        Button button=new Button();
        button.getButton().add(new ClickButton("click菜单一","1"));
        button.getButton().add(new ViewButton("view菜单二","http://baidu.com"));
        SubButton subButton = new SubButton("sub菜单");
        List<AbstractButton> sub_button = subButton.getSub_button();
        sub_button .add(new CustomButton("系统拍照发图","pic_sysphoto","2"));
        sub_button .add(new CustomButton("发送位置","location_select","3"));
        sub_button .add(new CustomButton("微信相册发图","pic_weixin","4"));
        sub_button .add(new CustomButton("拍照或者相册发图","pic_photo_or_album","4"));

        button.getButton().add(subButton);
        String param=new Gson().toJson(button);
        UrlUtil.get(url,param);
    }
    @GetMapping("/sendMsg")
    public void sendMsg(HttpServletRequest request,HttpServletResponse response){
String url="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+UrlUtil.getToken();
        String param="{\n" +
                "    \"touser\":\"omgjHwVFIiY5XeqsAcbzbnDyKi34\",\n" +
                "    \"msgtype\":\"text\",\n" +
                "    \"text\":\n" +
                "    {\n" +
                "         \"content\":\"Hello World\"\n" +
                "    }\n" +
                "}\n";
        String s = UrlUtil.get(url, param);
        System.out.println(s+"***********");

    }

    @GetMapping("/templateUrl")
    public String templateUrl(HttpServletRequest request){
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println("code:=="+code);
        System.out.println("state: "+state);
String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        String result = UrlUtil.get(url, null);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(result).getAsJsonObject();
        String access_token = jsonObject.get("access_token").getAsString();
        String openid = jsonObject.get("openid").getAsString();
        String url2="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        String result2=UrlUtil.get(url2, null);
        System.out.println("======>"+result2);
        return "callback";
    }
}
