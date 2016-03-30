<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>
<head>
<link href="<c:url value='css/index/index.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/index/carscroll.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/index/top_footer.css'/>" type="text/css"
	rel="stylesheet" />
	<script src="<c:url value="/js/index/top.js"/>"
	type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>购物车（空）</title>
</head>

<body>
<div style="text-align:center; padding-top:20%;"><img height="80px";width="80px"; src="images/ordercart/shopping.png" /></div>
<div style="color:#797979; font-size:18px; text-align:center; padding-top:4%;">购物车还是空的，去挑几件<br />中意的商品吧</div>
<br/>
<br/>
<br/>
小编为您精选了以下热销商品，=^_^=看看吧
<br/>

 <div class="scroll">
	<div class="slide_01" id="slide_01" >
	
	<c:forEach items="${goodcha }" var="goodcha">
		<div class="mod_01"><a class="mod_a" href="<c:url value="/goodsku?proskuid=${goodcha.ProSKUId }"/>" id="/lime${goodcha.ProSKUId }"><img  src="http://images.yijia360.com${goodcha.ListPagePic }" alt="${goodcha.ProName }"></a></div>
		
		</c:forEach>
	</div>
	<div class="dotModule_new">
		<div id="slide_01_dot"></div>
	</div>
</div>
<br/>
<div style="text-align:center; padding-top:5%;"><a href="<c:url value="/index"/>"><img src="images/ordercart/buttom3.png" /></a></div>
<script src="<c:url value="/js/index/shopcarscroll.js"/>"
	type="text/javascript" charset="utf-8"></script>

</body>
</html>
