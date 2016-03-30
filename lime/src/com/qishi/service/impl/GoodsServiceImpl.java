package com.qishi.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qishi.dao.GoodsDao;
import com.qishi.entity.OrderCart;
import com.qishi.entity.ProductBasicSkuInfo;
import com.qishi.entity.ProductClass;
import com.qishi.service.GoodsService;
import com.qishi.util.StringUtil;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao gooddao;

	public List<ProductClass> findgoodclass() {
		// TODO Auto-generated method stub
		return gooddao.findclass();
	}

	public List findgoods(String classid, String SkuFlag, String indexyingyang) {
		// TODO Auto-generated method stub

		return gooddao.findgoods(classid, SkuFlag, indexyingyang);
	}

	// 获取banner把list作为参传
	public List indexbanner() {
		// TODO Auto-generated method stub
		List list = gooddao.indexbanner();
		System.out.println("banner1." + list.toString());
		return getlist(list);
	}

	public String indexgoods(String ColumnId) {
		List list = gooddao.indexgoods(ColumnId);
		String result = "";
		if (list.size() > 0 || list != null) {
			for (int i = 0; i < list.size(); i++) {
				Map map = (Map) list.get(i);
				String Href = map.get("Href").toString();
				if (Href.contains(".html")) {
					Href = Href.substring(0, Href.indexOf(".html"));
					String regEx = "[^0-9]";
					Pattern p = Pattern.compile(regEx);
					Matcher m = p.matcher(Href);

					Href = m.replaceAll("").trim();
					result += Href + ",";
				}
			}
//			result = result.substring(0, result.length() - 1);
			System.out.println("查看结果" + result);
		}

		/*
		 * if (StringUtil.isNotEmpty(result)) { //过滤取的值是否为prowxid List
		 * wxisshowlist =gooddao.wxProSKUId(result);
		 * 
		 * }
		 */
		return result;
	}

	public List getlist(List list) {
		List newlist = new ArrayList();
		if (list.size() > 0 || list != null) { // 先判断这个值不为空
			for (int i = 0; i < list.size(); i++) {
				try {
					Map map = (Map) list.get(i);
					String Href = map.get("Href").toString();
					String proskuid = null;
					String HrefImg = map.get("HrefImg").toString();
					if (Href.contains("CustomerIndex.aspx")) {
						Href = "http://weixin.yijia360.com/Vitamin/index";
					} else if (Href.contains("0_0_")
							&& Href.contains("/Shucai/")) { // 判断以/Shucai/0_0_黑森.html这个格式取值
						Href = Href.substring(Href.indexOf("0_0_") + 4,
								Href.indexOf(".html"));
						Href = "/searchGood?searchName=" + Href; // 商品列表
						System.out.println("hrd00===" + Href);
					} else if (Href.contains("/Shucai/")) { // 判断以/Shucai/1136.html这个格式取值
						Href = Href.substring(Href.indexOf("/Shucai/") + 8,
								Href.indexOf(".html"));
						String regEx = "^[0-9]*[1-9][0-9]*$";
						Pattern p = Pattern.compile(regEx);
						Matcher m = p.matcher(Href);
						System.out.println(Href + "href======");
						System.out.println(m.matches() + "m.matches()======");
						if (!m.matches()) { // 判断匹配
							Href = "/index";
						} else {
							System.out.println("hrd11===" + Href);
							proskuid = Href;
							Href = "/goodsku?proskuid=" + Href; // 商品详细
						}
					}else {
						Href = "/index";
					}
					Map newmap = new HashMap();
					if (!Href.equals("/index")) {
						newmap.put("Href", Href);
						newmap.put("HrefImg", HrefImg);

						if (StringUtil.isNotEmpty(proskuid)) { // 过滤取的值是否为prowxid
							List wxisshowlist = gooddao.wxProSKUId(proskuid);
							if (wxisshowlist.size() > 0) {
								newlist.add(newmap); // 添加数字的href
							}
						} else {
							newlist.add(newmap); // 添加文字的href
						}
					}
				} catch (Exception ex) {
					continue;
				}

			}
		}

		return newlist;

	}

	public List indexGetSku(String skuids) {

		return gooddao.indexGetSku(skuids);
	}

	public List findgoodsku(String proskuid, String param1, String param2) {
		// TODO Auto-generated method stub
		return gooddao.findgoodsku(proskuid, param1, param2);
	}
	
	public List findgoodInfo(String proskuid) {
		// TODO Auto-generated method stub
		return gooddao.findgoodInfo(proskuid);
	}

	@Override
	public List findgoodclassid(String classid) {
		// TODO Auto-generated method stub
		return gooddao.findclassid(classid);
	}

	@Override
	public List findHealthGood() {
		return gooddao.findHealthGood();
	}

}
