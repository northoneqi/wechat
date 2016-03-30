package com.qishi.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.qishi.entity.ProductBasicInfo;
import com.qishi.entity.ProductBasicSkuInfo;
import com.qishi.entity.ProductClass;
import com.qishi.service.GoodsService;
/**
 * 
 * @author chenghao
 *
 */
@Controller
public class GoodsController {
	//商品基本实体类
	private ProductBasicInfo goods;
	//商品服务类
	@Autowired
	private GoodsService goodsservice;
	List<ProductClass> classlist =null;
	
	/**
	 * 显示商品分类列表与分类下展示的商品
	 * @param classid 分类id
	 * @param SkuFlag 商品sku
	 * @param indexyingyang 首页营养套餐需要的参数（不为空判断执行查询营养套餐）
	 * @param request
	 * @return 商品列表页
	 * @author chenghao
	 */
	@RequestMapping(value="/good",method=RequestMethod.GET)
	public String load(String classid,String SkuFlag,String indexyingyang, HttpServletRequest request ){
		//获取商品分类列表商品分类id
		classlist = goodsservice.findgoodclass();
		//获取用户选择的的商品分类处于商品列表的位置
		int where=0;
		if(classid==null){
			if(classlist.size()!=0){
			classid = classlist.get(0).getClassId();	
			where=1;
			}else{
				return "/good/list";  
			}
		}else{
			for(;where<classlist.size();where++){
				if(classlist.get(where).getClassId().equals(classid)){
					break;
				}
			}
		}
		//根据商品分类Id或商品sku，参数yingyang获取对应的商品
		List goodslist =  goodsservice.findgoods(classid, SkuFlag, indexyingyang);
		//商品数量
		int size = goodslist.size();
		//如果商品长度为单数查询骑士卡信息取第一张骑士卡添加到商品列表中
		if(size%2!=0){
		List goodslist2 =  goodsservice.findgoods("428", SkuFlag, indexyingyang);
		if(goodslist2.size()>0){
		goodslist.add(goodslist2.get(0));
		}
		}
		//商品分类列表
		request.setAttribute("list", classlist);
		//商品分类下的商品
		request.setAttribute("good", goodslist);
		//商品数量
		request.setAttribute("size", size);
		//商品分类数量
		request.setAttribute("listsize", classlist.size());
		//商品分类Id
		request.setAttribute("classid",classid);
		//商品分类处于商品列表的位置
		request.setAttribute("where",where);	
		return "/good/list";  

		} 
	/**
	 * 显示商品详细页面
	 * @param proskuid 商品sku
	 * @param param1 预留参数（未用）
	 * @param param2 预留参数（未用）
	 * @param request
	 * @return 商品详细页
	 * @author chenghao
	 */
	@RequestMapping(value="/goodsku",method=RequestMethod.GET)
	public String loadfenlei(String proskuid,String param1,String param2, HttpServletRequest request ){ 
		//根据商品sku获取商品详细信息List长度为一
		List goodskulist =  goodsservice.findgoodsku(proskuid, param1, param2);
		//获取商品详细map
		Map map=(Map) goodskulist.get(0);
		//为商品详细中的图片地址增加服务器域名以便显示
		String detail=map.get("ProDescri").toString().replace("src=\"","src=\"http://images.yijia360.com");
		//商品图片地址
		request.setAttribute("detail", detail);
		//商品详细信息
		request.setAttribute("gooddet", goodskulist);
		return "/good/gooddet";  

		} 

}
