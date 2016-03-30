<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士-收货地址管理-收货人地址编辑</title>
<link type="text/css" rel="stylesheet" href="css/address/address.css">
<link type="text/css" rel="stylesheet" href="css/address/top_footer.css">
<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		window.onload = function() {

			var cityCode = document.getElementById("cityCode").value;
			document.getElementById("city"+	cityCode).selected = "selected";

			var areaCode = document.getElementById("areaCode").value;
			document.getElementById(areaCode).selected = "selected";
			
		};
		function check() {
			var consignee = document.getElementById("consignee").value;
			 var val = $("#mbile").val();
			 var m = /^((0?1[3578]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
			 var tel = /^\d{11}$/;
			 var telVal = $("#telphone").val();
			if(consignee==""){
				sAlert("收货人不能为空");
				return;
			}
			if(document.getElementById("mbile").value==""){
				sAlert("请输入手机号");
				return;
			}
			if(!m.test(val)){
				sAlert("手机号不正确");
				return;
			}
			if(telVal !=""  && !tel.test(telVal)){
				sAlert("宅电不正确");
				return;
			}
			if(document.getElementById("address").value==""){
				sAlert("请输入详细地址");
				return;
			}
			form1.submit();
		};
	</script>
	<c:import url="../public/top.jsp"></c:import>
	<form action="upd_Address" method="post" id="form1">
		<div class="main">
			<input type="hidden" name="OAddressID" value="${order.OAddressID}">
			<input type="hidden" name="userId" value="${order.userId}">
			<div class="main1">
				收货人：<input type="text" name="OConsignee" id="consignee" value="${order.OConsignee}" maxlength="15">
			</div>
			<div class="main1">
				手　机：<input type="text" name="OMbile" id="mbile" value="${order.OMbile}" maxlength="11">
			</div>
			<div class="main1" >
				宅　电：<input type="text" name="OTelephone" id="telphone" value="${order.OTelephone}" maxlength="11">
			</div>
			<div class="main2">
				所在地区：<select class="on" name="OCity" id="city">
					<option value="2" id="city2">北京</option>
				</select> <input type="hidden" value="${order.OCity}" id="cityCode" /> 
				<select
					class="on" name="OArea" id="area">
					<option value="3" id="3">朝阳区</option>
					<option value="4" id="4">东城区</option>
					<option value="5" id="5">西城区</option>
					<option value="6" id="6">丰台区</option>
					<option value="7" id="7">石景山区</option>
					<option value="8" id="8">海淀区</option>
					<option value="9" id="9">门头沟区</option>
					<option value="10" id="10">房山区</option>
					<option value="11" id="11">通州区</option>
					<option value="12" id="12">顺义区</option>
					<option value="13" id="13">昌平区</option>
					<option value="14" id="14">大兴区</option>
					<!-- <option value="15" id="15">怀柔区</option>
					<option value="16" id="16">平谷区</option> -->
				</select> <input type="hidden" value="${order.OArea}" id="areaCode" />
			</div>
		</div>
		<input type="hidden" name="DefAddress" id="defAddress" value="${order.defAddress}">
		<div class="bottom" style="text-indent: 1em;">
			详细地址：<input type="text" name="OAddress" id="address" value="${order.OAddress}" maxlength="30">
		</div>
		<a href="javascript:void(0)" onclick="check();"><div class="button">保存</div></a>
		<input type="hidden" value="${reurl}" name="reurl">
		<input type="hidden" value="${orderCodes}" name="orderCodes">
	</form>
</body>
</html>
