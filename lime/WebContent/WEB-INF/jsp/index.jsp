<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@page import="java.net.URLEncoder"%>
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>首页</title>
<link href="<c:url value='css/index/index.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/index/scroll.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/index/top_footer.css'/>" type="text/css"
	rel="stylesheet" />
	<script src="<c:url value="/js/index/top.js"/>"
	type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
</head>

<body >
	<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			$(".mod_a").click(function(){
				if ($(this).attr("id").indexOf("Vitamin")>=0) {
					window.location="http://weixin.yijia360.com/Vitamin/index";
				} else {
					window.location=encodeURI(encodeURI($(this).attr("id")));
					
				}
			});	
		});
	</script>
<a name="top"></a>
<div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/index/logo.png"></a>
          </div>
          <div class="header_right">
                    <div class="header_right_1"><a href="#"><img src="images/index/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="<c:url value="/memberCenter"/>"><img src="images/index/person.png"></a></div>
                    <div class="header_right_3"><a href="#"><img src="images/index/drive.png"></a></div>
          </div>
</div>
<c:import url="public/top.jsp"></c:import>
<div class="scroll">
	<div class="slide_01" id="slide_01">
	
	<c:forEach items="${indexbanner }" var="indexbanner">
		<div class="mod_01"><a class="mod_a" href="#" id="/lime${indexbanner.Href }"><img src="http://images.yijia360.com${indexbanner.HrefImg }" alt="${indexbanner.Title }"></a></div>
		
		</c:forEach>
	</div>
	<div class="dotModule_new">
		<div id="slide_01_dot"></div>
	</div>
</div>

<div  style="clear:both;"></div>
<!--
<div class="nav">
          <div class="nav1"><a href="#"><img src="images/index/nav1.png"><br>特价专场</a></div>
          <div class="nav2"><a href="#"><img src="images/index/nav2.png"><br>新品上市</a></div>
          <div class="nav3"><a href="#"><img src="images/index/nav3.png"><br>随便逛逛</a></div>
</div>
<div  style="clear:both;"></div>
<div class="nav" style="padding-top:5px;">
          <div class="nav1"><a href="#"><img src="images/index/nav4.png"><br>制定套餐</a></div>
          <div class="nav2"><a href="#"><img src="images/index/nav5.png"><br>有奖竞猜</a></div>
          <div class="nav3"><a href="#"><img src="images/index/nav6.png"><br>健康套餐</a></div>
</div>-->
<div class="nav">
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<td class="nav1"><a href="<c:url value="/indexhref?ColumnId=77"/>"><img src="images/index/nav1.png"><br>骑士特价</a></td>
<td class="nav2"><a href="<c:url value="/indexhref?ColumnId=79"/>"><img src="images/index/nav2.png"><br>新品上架</a></td>
<td class="nav3"><a href="<c:url value="/indexhref?ColumnId=78"/>"><img src="images/index/nav3.png"><br>销量排行</a></td>
</tr>
</table>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<td class="nav1"><a href="<c:url value="/indexhref?ColumnId=80"/>"><img src="images/index/nav4.png"><br>限时抢购</a></td>
<td class="nav2"><a href="<c:url value="/good"/>"><img src="images/index/nav5.png"><br>商品分类</a></td>
<td class="nav3"><a href="<c:url value="/indexhref?ColumnId=81"/>"><img src="images/index/nav6.png"><br>健康套餐</a></td>
</tr>
</table>
</div>
<a name="tejia"></a>


<!--
<div class="main">特价专场</div>
<div class="main_1">
          <div class=" main_1_a"><a href="#"><img src="images/index/main1.png"><br>尖椒骑士特价：<span style="color:#cc0000;">2.38元</span>/份</a></div>
          <div class=" main_1_b"><a href="#"><img src="images/index/main2.png"><br>黄瓜骑士特价：<span style="color:#cc0000;">2.48元</span>/份</a></div>
          <div class=" main_1_c"><a href="#"><img src="images/index/main3.png"><br>油菜骑士特价：<span style="color:#cc0000;">1.58元</span>/份</a></div>
</div>
<div  style="clear:both;"></div>
<div class="main_1">
          <div class=" main_1_a"><a href="#"><img src="images/index/main4.png"><br>蒜薹骑士特价：<span style="color:#cc0000;">3.58元</span>/份</a></div>
          <div class=" main_1_b" ><a href="#"><img src="images/index/main5.png"><br>武夷山特级正山小种红茶骑士特价：<span style="color:#cc0000;">338元</span>/份</a></div>
          <div class=" main_1_c"><a href="#"><img src="images/index/main6.png"><br>武夷山百岁香岩茶骑士特价：<span style="color:#cc0000;">380元</span>/份</a></div>
</div>-->

<%-- <div class="main">特价专场</div>
<div  style="clear:both;"></div>
<div class="main_1">
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${tejia }" var="tejia" begin="0" end="2" >
<td class="main_1_a"><a href="#"><img src="http://images.yijia360.com${tejia.ListPagePic }"><br>${tejia.ProName}<br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span>/份</a></td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${tejia }" var="tejia" begin="3" end="5" >
<td class="main_1_a"><a href="#"><img src="http://images.yijia360.com${tejia.ListPagePic }"><br>${tejia.ProName}<br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span>/份</a></td>

</c:forEach>
</tr>
</table>
</div> --%>
<a name="yingyangtaocan"></a>


<div class="main">新品上架</div>
<div  style="clear:both;"></div>
<div class="main_2">
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${xinpin }" var="xinpin" begin="0" end="1" >
<td class="main_2_a" height="150px" width="150px"><a href="<c:url value="/goodsku?proskuid=${xinpin.ProSKUId }"/>"><img src="http://images.yijia360.com${xinpin.ListPagePic }"><br>${fn:substring(xinpin.ProName ,0,8)}<br/>${fn:substring(xinpin.ProName ,8,16)}
												<c:if test="${fn:length(xinpin.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${xinpin.SellPrice }</span></a><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${xinpin.ProSKUId }','1');"><img src="images/goodlist/buttom22.png"></a></span></td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${xinpin }" var="xinpin" begin="2" end="3" >
<td class="main_2_a" height="150px" width="150px"><a href="<c:url value="/goodsku?proskuid=${xinpin.ProSKUId }"/>"><img src="http://images.yijia360.com${xinpin.ListPagePic }"><br>${fn:substring(xinpin.ProName ,0,8)}<br/>${fn:substring(xinpin.ProName ,8,16)}
												<c:if test="${fn:length(xinpin.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${xinpin.SellPrice }</span></a><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${xinpin.ProSKUId }','1');"><img src="images/goodlist/buttom22.png"></a></span>
</td>

</c:forEach>
</tr>
</table>
</div>


<div  style="clear:both;"></div>
<div class="main">骑士特价</div>
<div  style="clear:both;"></div>
<div class="main_2">
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${tejia }" var="tejia" begin="0" end="1" >
<td class="main_2_a" height="150px" width="150px"><a href="<c:url value="/goodsku?proskuid=${tejia.ProSKUId }"/>"><img  src="http://images.yijia360.com${tejia.ListPagePic }"><br>${fn:substring(tejia.ProName ,0,8)}<br/>${fn:substring(tejia.ProName ,8,16)}
												<c:if test="${fn:length(tejia.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span></a><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${tejia.ProSKUId}','1');"><img src="images/goodlist/buttom22.png"></a></span>
</td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${tejia }" var="tejia" begin="2" end="3" >
<td class="main_2_a" height="150px" width="150px"><a href="<c:url value="/goodsku?proskuid=${tejia.ProSKUId }"/>"><img  src="http://images.yijia360.com${tejia.ListPagePic }"><br>${fn:substring(tejia.ProName ,0,8)}<br/>${fn:substring(tejia.ProName ,8,16)}
												<c:if test="${fn:length(tejia.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span></a><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${tejia.ProSKUId}','1');"><img src="images/goodlist/buttom22.png"></a></span>
</td>

</c:forEach>
</tr>
</table>
</div>

<div class="footer" style="position:fixed;" >
          <a href="tel:96008"><img src="images/index/phone.png"></a>
</div>
<!-- 
<A id=top-link onclick="goTop();return false;" 
href="javascript:void(0);">Top of Page</A>   -->
<!-- <div class="top-link"  >
          <a href="javascript:void(0);" onclick="goTop();return false;"><img src="images/index/top.png"></a>
</div> -->
<div id="page"></div>
	<script src="<c:url value="/js/index/gototop.js"/>" type="text/javascript" charset="utf-8"></script>
</body>
</html>
<script src="<c:url value="/js/index/scroll.js"/>"
	type="text/javascript" charset="utf-8"></script>