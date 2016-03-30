<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
	<!-- <meta http-equiv="refresh" content="6;url=/lime/index"/> -->
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
		<title>抽奖类型</title>
		<style>
.main{width:98%; margin:1%;border-radius:6px; background:#fff;text-align: center;margin-top: 15%;}

.main1 {
	text-align: center;
	height: 40px;
	width: 20%;
	background-color: #2e8f02;
	margin: 0 auto;
	padding-top: 1.5%;
}

.box {
	width: 100%;
	margin: 0 auto;
	background: #2e8f02;
	height: 60px;
	font-size: 16px;
	line-height: 60px;
	color: #fff;
}

.box2{
	width: 50%;
	margin: 0 auto;
	height: 30px;
	font-size: 16px;
	line-height: 30px;
	color: #fff;
}
</style>
</head>
<body>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#div").click(function(){
			//alert("---")
			window.location="index";
		});
	
	});
	
</script>
	<c:import url="../public/top.jsp"></c:import>
	<br />
	<div class="main">
		<img src="images/prize/prize6.jpg" /> <br>
			<c:if test="${remind !=null || remind !='' }"><div class="box2"><a href="showUserInf?url=lottery" style="">${remind}</a></div>
			</c:if>	
		您的分享链接URL${url}<br/>	
		URL分享了${urlNumber}次<br/>	
		获得的 奖品数${prizeNumber}<br/>
		再分享${noPrizeNum} 次给以再次获得奖品<br/>
	
	
		 <div class="box">
		 去商城逛逛
		</div>
	</div>
	
</body>
</html>
