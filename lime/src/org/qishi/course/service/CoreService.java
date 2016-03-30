package org.qishi.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.qishi.course.message.resp.Article;
import org.qishi.course.message.resp.BaseMessage;
import org.qishi.course.message.resp.NewsMessage;
import org.qishi.course.message.resp.TextMessage;
import org.qishi.course.util.MessageUtil;

import com.qishi.controller.UserLotteryController;
import com.qishi.util.Constants;
import com.qishi.util.ServerConfig;






/**
 * 核心服务类
 * @author dhzhanghailong
 *
 *@date 2014年6月12日 上午11:57:44 
 */
public class CoreService {
	
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		String textOrnew="0";//0文本消息  1图文消息
		// 默认返回的文本消息内容
		String respContent = null;
		try {
			

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			//输入内容
			 String content = requestMap.get("Content");  
			 System.out.println("content---"+content);
			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			
			//回复图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);
			List<Article>  articleList = new ArrayList<Article>();

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//respContent = "您发送的是文本消息！";
				if (content.equals("1")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"index\">骑士商城首页</a>";
				}else if (content.equals("2")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"yijiatejia\">骑士特价</a>";
				}else if (content.equals("3")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"xinpinshangjia\">新品上架</a>";
				}else if (content.equals("4")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"xianshiqianggou\">限时抢购</a>";
				}else if (content.equals("5")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"memberCenter\">会员中心</a>";
				}else if (content.equals("6")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"ordercartlist\">购物车</a>";
				}else if (content.equals("7")) {
					respContent = "<a href=\""+"http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"orderlist\">我的订单</a>";
				}else if (content.equals("8")) {
					respContent = "客服电话：96008";
				}else if (content.equals("#") || content.equals("菜单")) {
					respContent = "你好，欢迎关注京华骑士！\n "
							+ "回复以下数字查看信息,回复“菜单”或”#“返回菜单\n"
							+ "1.骑士商城首页\n"
							+ "2.骑士特价\n"
							+ "3.新品上架\n"
							+ "4.限时抢购\n"
							+ "5.会员中心\n"
							+ "6.购物车\n"
							+ "7.我的订单\n"
							+ "8.联系客服"
							;
//					respContent = "你好，欢迎关注京华骑士！\n "
//							+ "关注骑士商城，抽取菜卡红包\n"
//							+ "点击链接抽奖：http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"/"+UserLotteryController.LotteryUrl
//							;
				}else if (content.equals("真相盒子")) {
					textOrnew = "1";
//					Article article = new Article();
//					article.setTitle("抢水果盒子啦——第一波魏大叔梨");
//					article.setDescription("小确幸盒子水果季，每天送出1000个水果礼盒，连送5天！！");
//					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/VXfHCX7L2lq0OMXPM0uatmrXH20w97XFhc6Pg4rjWG6cSWDU6AQS5Yar0icbdZS5DODib0HROqJ33BhoqcfUUHBw/0");
//					article.setUrl("http://weixin.yijia360.com/LuckPrize/grabbox");
//					articleList.add(article);
					
					
					
					Article article = new Article();
					article.setTitle("京华骑士倾情巨献 小苹果免费领");
					article.setDescription("转发就送骑士直采【脆甜直率 英雄之果】");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/VXfHCX7L2lp4Sxvu3CqrdFmYicgXnEb39eygzLiccOJ9gSRtJib7eHibxEwZ5ESjw6h2BOGu8Jubxsm7LaZj73Wg4A/0");
					article.setUrl("http://weixin.yijia360.com/LuckPrize/grabbox");
					articleList.add(article);
				}else{
					textOrnew = "2";
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				//respContent = "您发送的是图片消息！";
				textOrnew = "2";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				//respContent = "您发送的是地理位置消息！";
				textOrnew = "2";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				//respContent = "您发送的是链接消息！";
				textOrnew = "2";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				//respContent = "您发送的是音频消息！";
				textOrnew = "2";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					textOrnew = "1";
					Article article = new Article();
					article.setTitle("抢水果盒子啦——第一波魏大叔梨");
					article.setDescription("小确幸盒子水果季，每天送出1000个水果礼盒，连送5天！！");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/VXfHCX7L2lq0OMXPM0uatmrXH20w97XFhc6Pg4rjWG6cSWDU6AQS5Yar0icbdZS5DODib0HROqJ33BhoqcfUUHBw/0");
					article.setUrl("http://weixin.yijia360.com/LuckPrize/grabbox");
					articleList.add(article);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				/*//扫描带参数二维码
				else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					
				}
				//上报地理位置
				else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					
				}*/
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("key-1")) {
						//respContent = "有奖竞猜！";
					}else if (eventKey.equals("key-2")) {
						respContent = "客服电话：96008";
					}else if (eventKey.equals("key-3")) {
						respContent = "正在建设中。。。";
						textOrnew = "1";
						
							List list = YingYangMenu.returnRessage();
							
							Map map = (Map) list.get(0);
							Article article = new Article();
							article.setTitle(map.get("title").toString());
							article.setDescription(map.get("description").toString());
							article.setPicUrl(map.get("picurl").toString());
							article.setUrl(map.get("url").toString());
							articleList.add(article);
						
						
						
					}else if (eventKey.equals("key-4")) {
						//respContent = "正在建设中。。。";
					}else if (eventKey.equals("key-5")) {
						//respContent = "正在建设中。。。";
					}
					
				}else {
					respContent = "请通过菜单使用服务！";
				}
			}
			if (textOrnew.equals("0")) {
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);

			}else if (textOrnew.equals("1")) {
				newsMessage.setArticleCount(articleList.size());
				newsMessage.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsMessage);
			}else if (textOrnew.equals("2")) {
				BaseMessage cutomer = new BaseMessage();
				cutomer.setToUserName(fromUserName);
				cutomer.setFromUserName(toUserName);
				cutomer.setCreateTime(new Date().getTime());
				cutomer.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_CUSTOMER);
				respMessage = MessageUtil.textMessageToXml(cutomer);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}