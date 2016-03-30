<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>搜索结果</title>
<link href="<c:url value='css/search/index.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/search/scroll.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/search/top_footer.css'/>" type="text/css"
	rel="stylesheet" />
	
		<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	
function goPay(SellPrice,sku,split){
	var buynum = 1;
	//alert(buynum);
	window.location.href = "/lime/goPay?SellPrice="+SellPrice+"&ProNum="+buynum+"&sku="+sku+"&split="+split;
}
</script>
	
</head>

<body >

<a name="top"></a>
<c:import url="../public/top.jsp"></c:import>
<div  style="clear:both;"></div>
<a name="tejia"></a>

<div class="main11" style="font-size: 14px;line-height:25px; ">抱歉，没有找到与“<c:out value="${search}"/>”相关的商品</div>
<a name="yingyangtaocan"></a>


<div class="main">新品上架</div>
<div  style="clear:both;"></div>
<div class="main_2">
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${xinpin}" var="xinpin" begin="0" end="1" >
<td class="main_2_a"><a href="<c:url value="/goodsku?proskuid=${xinpin.ProSKUId }"/>"><img width="150px" height="150px" src="http://images.yijia360.com${xinpin.ListPagePic }"><br>${fn:substring(xinpin.ProName,0,8) }<br/>${fn:substring(xinpin.ProName,8,16) }
												<c:if test="${fn:length(xinpin.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${xinpin.SellPrice }</span><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${xinpin.ProSKUId }','1');"><img src="images/goodlist/buttom22.png"></a></span></a>
</td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${xinpin }" var="xinpin" begin="2" end="3" >
<td class="main_2_a"><a href="<c:url value="/goodsku?proskuid=${xinpin.ProSKUId }"/>"><img width="150px" height="150px" src="http://images.yijia360.com${xinpin.ListPagePic }"><br>${fn:substring(xinpin.ProName,0,8)}<br/>${fn:substring(xinpin.ProName,8,16)}
												<c:if test="${fn:length(xinpin.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${xinpin.SellPrice }</span><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${xinpin.ProSKUId }','1');"><img src="images/goodlist/buttom22.png"></a></span></a>
</a></td>

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
<td class="main_2_a" ><a href="<c:url value="/goodsku?proskuid=${tejia.ProSKUId }"/>"><img width="150px" height="150px"src="http://images.yijia360.com${tejia.ListPagePic }"><br>${fn:substring(tejia.ProName,0,8)}<br/>${fn:substring(tejia.ProName,8,16)}
												<c:if test="${fn:length(tejia.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${tejia.ProSKUId}','1');"><img src="images/goodlist/buttom22.png"></a></span></a>
</a></td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%; border-bottom:1px solid #ccc;" cellpadding="0" cellspacing="0">
<tr >
<c:forEach items="${tejia }" var="tejia" begin="2" end="3" >
<td class="main_2_a" ><a href="<c:url value="/goodsku?proskuid=${tejia.ProSKUId }"/>"><img width="150px" height="150px"src="http://images.yijia360.com${tejia.ListPagePic }"><br>${fn:substring(tejia.ProName,0,8)}<br/>${fn:substring(tejia.ProName,8,16)}
												<c:if test="${fn:length(tejia.ProName)>16 }">...</c:if><br>
<span style="color:#cc0000;">骑士价：${tejia.SellPrice }</span><br>
<span width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${tejia.ProSKUId}','1');"><img src="images/goodlist/buttom22.png"></a></span></a>
</a></td>

</c:forEach>
</tr>
</table>
</div>

<div class="footer" >
          <a href="tel:96008"><img src="images/index/phone.png"></a>
</div>
<div id="page"></div>
	<script src="<c:url value="js/index/gototop.js"/>" type="text/javascript" charset="utf-8"></script>
</body>
</html>
