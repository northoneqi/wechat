<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>骑士卡绑定-绑定新卡</title>
<link type="text/css" rel="stylesheet" href="css/card/card.css">
<link type="text/css" rel="stylesheet" href="css/card/top_footer.css">
</head>

<body>

<c:import url="../public/top.jsp"></c:import>

<div class="bottom" style="margin-top:15%">
	<a href="/lime/addCard">
          <div class="bottom_left"><img src="images/card/jiahao1.png"></div>
          <div class="bottom_right">绑定新卡</div>
	</a>
</div>
<c:forEach items="${qwe}" var="sr">
		<div class="main">
		
		   <div class="main1">
		                    卡号：<span>${sr.CardNo}</span>
		                  <div style="float:right"><c:if test="${sr.cardstate==1}">状态：<span style="color:blue">正常&nbsp&nbsp</span></c:if></div>
		                  <div style="float:right"><c:if test="${sr.cardstate==2}">状态：<span style="color: red ">注销&nbsp&nbsp</span></c:if></div>
		                  <div style="float:right"><c:if test="${sr.cardstate==3}">状态：<span style="color: red ">退卡&nbsp&nbsp</span></c:if></div>
		                  <div style="float:right"><c:if test="${sr.cardstate==4}">状态：<span style="color: red ">锁定&nbsp&nbsp</span></c:if></div>
		                  <div style="float:right"><c:if test="${sr.cardstate==5}">状态：<span style="color: red ">过期&nbsp&nbsp</span></c:if></div>
		   </div>
		          <div class="main1" style="border-bottom:0px;">
		                    余额：<span>${sr.Balance}</span>
		                    
		          </div>
		</div>
</c:forEach>		
          <div  style="clear:both;"></div>
<!-- <a href="#"><div class="button">解除绑定</div></a> -->

</body>
</html>
