<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士-个人信息管理(填写)</title>
<link type="text/css" rel="stylesheet" href="css/personal/personal_message_write.css">
<link type="text/css" rel="stylesheet" href="css/address/top_footer.css">
<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
</head>

<body>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">
		window.onload = function() {

			var cityCode = document.getElementById("cityCode").value;
			document.getElementById(cityCode).selected = "selected";

			var areaCode = document.getElementById("areaCode").value;
			document.getElementById("area"+areaCode).selected = "selected";
			
		};
		
		function check() {
			var nickName = document.getElementById("nickName").value;
			 var val = $("#phone").val();
			 var m = /^((0?1[3578]\d{9})|((0(10|2[1-3]|[3-9]\d{2}))?[1-9]\d{6,7}))$/;
			 var tel = /^\d{11}$/;
			 var telVal = $("#phoneCode").val();
			 if(nickName==""){
				sAlert("姓名不能为空");
				return;
			}
			if(document.getElementById("phone").value==""){
				sAlert("手机号不能为空");
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
			if(document.getElementById("area").value==0){
				sAlert("请选择区");
				return;
			}
			if(document.getElementById("address").value==""){
				sAlert("详细地址不能为空");
				return;
			}
			form1.submit();
		};
	</script>
	<c:import url="../public/top.jsp"></c:import>
<form action="updUserInf" method="post" id="form1">
<div class="main">
			<input type="hidden" name="UserID" value="${user.userID}">
			<input type="hidden" name="url" value="${url}">
          <div class="main1">
                    姓名：<input type="text"  name="NickName" id="nickName" value="${user.nickName}" maxlength="15">
          </div>
          <div class="main1">
                    手机：<input type="text"  name="Phone" id="phone" value="${user.phone}" maxlength="11">
          </div>
          <div class="main1">
                    宅电：<input type="text"  name="PhoneCode" id="phoneCode" value="${user.phoneCode}" maxlength="11">
          </div>
          <div class="main2">
                    地址：<select class="on" name="City" id="city">
					<option value="2" id="2">北京</option>
				</select> <input type="hidden" value="${user.city}" id="cityCode" /> 
                            <select class="on" name="Area" id="area">
                            		<option value="0" id="area">区</option>
                                    <option value="3" id="area3">朝阳区</option>
									<option value="4" id="area4">东城区</option>
									<option value="5" id="area5">西城区</option>
									<option value="6" id="area6">丰台区</option>
									<option value="7" id="area7">石景山区</option>
									<option value="8" id="area8">海淀区</option>
									<option value="9" id="area9">门头沟区</option>
									<option value="10" id="area10">房山区</option>
									<option value="11" id="area11">通州区</option>
									<option value="12" id="area12">顺义区</option>
									<option value="13" id="area13">昌平区</option>
									<option value="14" id="area14">大兴区</option>
									<option value="15" id="area15">怀柔区</option>
									<option value="16" id="area16">平谷区</option>
                            </select><input type="hidden" value="${user.area}" id="areaCode" />
          </div>
          
          
</div>
<div class="bottom" style="text-indent:1em;">
                  详细地址：<input type="text"  name="Address" id="address" value="${user.address}" maxlength="21">
</div>
<a href="javascript:void(0)" onclick="check()"><div class="button">保存</div></a>
</form>
</body>
</html>
