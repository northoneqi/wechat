package org.qishi.micromark.main;


import org.qishi.micromark.pojo.AccessToken;
import org.qishi.micromark.pojo.Button;
import org.qishi.micromark.pojo.CommonButton;
import org.qishi.micromark.pojo.CommonurlButton;
import org.qishi.micromark.pojo.ComplexButton;
import org.qishi.micromark.pojo.Menu;
import org.qishi.micromark.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qishi.util.ServerConfig;





/**
 * @author dhzhanghailong
 *
 *@date 2014年6月17日 上午10:19:04 
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	public static void main(String[] args) {
		try {
			ServerConfig.initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 第三方用户唯一凭证
		String appId =ServerConfig.getAPP_ID();
		//第三方用户唯一凭证密钥
		String appSecret =ServerConfig.getAPP_SECRET();
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId,appSecret);
		System.out.println(at.getToken());
		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());
			//int result = WeixinUtil.deleteMenu(at.getToken());
			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
		
		

		
		
	}

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		CommonurlButton btn11 = new CommonurlButton();
		btn11.setName("骑士特价");
		btn11.setType("view");
		btn11.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"yijiatejia?ColumnId=77");

		CommonurlButton btn12 = new CommonurlButton();
		btn12.setName("新品上架");
		btn12.setType("view");
		btn12.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"xinpinshangjia?ColumnId=79");
		
		CommonurlButton btn13 = new CommonurlButton();
		btn13.setName("限时抢购");
		btn13.setType("view");
		btn13.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"xianshiqianggou?ColumnId=80");
		
		CommonurlButton btn14 = new CommonurlButton();
		btn14.setName("骑士商城");
		btn14.setType("view");
		btn14.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"index");

		CommonurlButton btn15 = new CommonurlButton();
		btn15.setName("水果盒子");
		btn15.setType("view");
		btn15.setUrl("http://weixin.yijia360.com/LuckPrize/grabbox");
		

		CommonButton btn21 = new CommonButton();
		btn21.setName("联系客服");
		btn21.setType("click");
		btn21.setKey("key-2");

		CommonurlButton btn22 = new CommonurlButton();
		btn22.setName("会员中心");
		btn22.setType("view");
		btn22.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"memberCenter");
		
		CommonurlButton btn23 = new CommonurlButton();
		btn23.setName(" 购物车");
		btn23.setType("view");
		btn23.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"ordercartlist");
		
		CommonurlButton btn24 = new CommonurlButton();
		btn24.setName("我的订单");
		btn24.setType("view");
		btn24.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"orderlist");
		
		CommonurlButton btn25 = new CommonurlButton();
		btn25.setName("维权");
		btn25.setType("view");
		btn25.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"rights");

		CommonButton btn31 = new CommonButton();
		btn31.setName("营养课堂");
		btn31.setType("click");
		btn31.setKey("key-3");

		CommonurlButton btn32 = new CommonurlButton();
		btn32.setName("健康套餐");
		btn32.setType("view");
		btn32.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"yingyang?ColumnId=81");
		
		CommonurlButton btn33 = new CommonurlButton();
		btn33.setName("健康社区");
		btn33.setType("view");
		btn33.setUrl("http://wsq.qq.com/reflow/260989977");
		
		CommonurlButton btn34 = new CommonurlButton();
		btn34.setName("我的红包");
		btn34.setType("view");
		btn34.setUrl("http://"+ServerConfig.getBaseUrl()+"/LuckPrize/promotion?activityId=4");
		
		CommonurlButton btn35 = new CommonurlButton();
		btn35.setName("我的众筹");
		btn35.setType("view");
		btn35.setUrl("http://"+ServerConfig.getBaseUrl()+ServerConfig.getContext()+"orderlist");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("购物专区");
		mainBtn1.setSub_button(new Button[] {btn15,btn11,btn12,btn13,btn14});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("我的骑士");
		mainBtn2.setSub_button(new Button[] { btn34, btn22, btn23, btn24,btn25 });
//		mainBtn2.setSub_button(new Button[] {  btn22, btn23, btn24,btn25 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("健康专区");
		mainBtn3.setSub_button(new Button[] {btn31 ,btn32, btn33});

		/*CommonurlButton mainBtn3 = new CommonurlButton();
		mainBtn3.setName("爱家公社");
		mainBtn3.setType("view");
		mainBtn3.setUrl("http://weixin.yijia360.com/Vitamin/index");*/
		
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}