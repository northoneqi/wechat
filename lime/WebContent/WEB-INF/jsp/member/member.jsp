<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士首页</title>
<link type="text/css" rel="stylesheet"
	href="css/member/personal_index.css">
<link type="text/css" rel="stylesheet" href="css/member/top_footer.css">
</head>

<body>
	<!-- <div class="header">
		<div class="header_left">
			<a href="index1.html"><img src="images/member/logo.png"></a>
		</div>
		<div class="header_right">
			<div class="header_right_1">
				<a href="#"><img src="images/member/sousuo.png"></a>
			</div>
			<div class="header_right_2">
				<a href="#"><img src="images/member/person.png"></a>
			</div>
			<div class="header_right_3">
				<a href="#"><img src="images/member/drive.png"></a>
			</div>
		</div>
	</div> -->
	<div style="margin-top:-2%;">　</div>
	<c:import url="../public/top.jsp"></c:import>
	<div class="main">
		<div class="main_left">
			<img src="images/member/person_face.png">
		</div>
		<c:forEach items="${info}" var="sr">
		<div class="main_right">
			${sr.NickName}<br>${sr.Phone}<br>${sr.PhoneCode}<br> ${sr.Address}
		</div>
		</c:forEach>
	</div>
	<div class="nav">
		<a href="<c:url value="/showUserInf"/>"><div class="nav_1">
				<div class="nav_1_left">会员信息管理</div>
				<div class="nav_1_right">
					<img src="images/member/you.png">
				</div>
			</div></a> <a href="<c:url value="/show_address"/>"><div class="nav_1">
				<div class="nav_1_left">收货地址管理</div>
				<div class="nav_1_right">
					<img src="images/member/you.png">
				</div>
			</div></a> <a href="<c:url value="/orderlist"/>"><div class="nav_1">
				<div class="nav_1_left">订单管理</div>
				<div class="nav_1_right">
					<img src="images/member/you.png">
				</div>
			</div></a> <a href="<c:url value="/showlist"/>"><div class="nav_1">
				<div class="nav_1_left">骑士卡绑定</div>
				<div class="nav_1_right">
					<img src="images/member/you.png">
				</div>
			</div></a>
	</div>
</body>
</html>
