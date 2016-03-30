package com.qishi.util;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.qishi.micromark.util.WeixinUtil;

/**
 * @author JackZhang
 *
 */
public class OAuthAPI {

//    public static final String APP_ID = "wxdf56a63c63a3f762";
//    public static final String APP_SECRET = "f8b67cb49c896422b8694605251676b6";
//    public static final String DOMAIN =ServerConfig.getBaseUrl();
    

    public static String OAuthIfNesscary(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        boolean isValidCode = true;
        
        String serviceUrl = URLEncoder.encode(
               "http://" + ServerConfig.getBaseUrl()+ request.getRequestURI(), "utf-8");
       
        //检查是否已验证或者验证是否通过
        if (code == null || code.equals("authdeny")) {
            isValidCode = false;
        }
        
        String openid = "";
        //如果session未空或者取消授权，重定向到授权页面
        if ((!isValidCode)) {
            StringBuilder oauth_url = new StringBuilder();
            oauth_url.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
            oauth_url.append("appid=").append(ServerConfig.getAPP_ID());
            oauth_url.append("&redirect_uri=").append(serviceUrl);
            oauth_url.append("&response_type=code");
            oauth_url.append("&scope=snsapi_base");
            oauth_url.append("&state=1#wechat_redirect");
            response.sendRedirect(oauth_url.toString());
            return openid;
        }
        if (isValidCode) {
               
                JSONObject obj = OAuthAPI.getAccessToken(ServerConfig.getAPP_ID(),ServerConfig.getAPP_SECRET(), code);
                String token = obj.getString("access_token");
                openid = obj.getString("openid");
                System.out.println(openid+"----------"+token);
//                JSONObject user = OAuthAPI.getUserInfo(token, openid);
//                MemberService memberService = (MemberService) WebAppContext.getObject("memberService");
//                member = memberService.saveOrUpdateIfNesscary(user);
        }
        return openid;     
    }


    /**
     * 获取授权令牌
     * */
    public static JSONObject getAccessToken(String appid, String secret,
            String code) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
        url.append("appid=" + appid);
        url.append("&secret=").append(secret);
        url.append("&code=").append(code);
        url.append("&grant_type=authorization_code");
        return WeixinUtil.httpRequest(url.toString(), "GET", null);
    }

//    //获取用户信息
//    public static JSONObject getUserInfo(String token, String openid) {
//        StringBuilder url = new StringBuilder();
//        url.append("https://api.weixin.qq.com/sns/userinfo?");
//        url.append("access_token=" + token);
//        url.append("&openid=").append(openid);
//        url.append("&lang=zh_CN");
//        return HttpClientUtils.getJson(url.toString());
//    }

}
