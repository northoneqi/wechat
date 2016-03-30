<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="refresh" content="4;url=/lime/index"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>支付成功</title>
<style>
.main{text-align:center; padding-top:20%;}
.main1{text-align:center; height:40px; width:20%; background-color:#2e8f02;margin:0 auto;padding-top:1.5%;}
.a{text-decoration:none;}
.a div{color:#333;font-size:20px;}
</style>

<link href="css/index/login.css" type="text/css" rel="stylesheet" />

</head>

<body>
<c:import url="../public/top.jsp"></c:import>
<br/>
<br/>
<br/>
<div class="main"><img src="images\search\success.jpg"/></div>
<div class="wen">
          <div class="wen_left">&nbsp;</div>
          <div class="wen_center">
                    <div class="wen_center_left">
                    	<a href="/lime/index">返回首页</a>
                    </div>
                    <br/>
                    <div class="wen_center_left" style="margin-top: 30px;">
                    	<a href="<c:url value="/orderlist?orderCodes=${wxCode}"/>">返回订单明细</a>
                    </div>
          </div>
          <div class="wen_right"></div>
</div>
</body>
</html>
