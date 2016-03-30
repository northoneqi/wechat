<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>登录</title>
<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>

<link href="css/index/login.css" type="text/css" rel="stylesheet" />
<link href="css/index/top_footer.css" type="text/css" rel="stylesheet" />
	<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function loginvail(){
	
	var Username = $("#Username").val();
	var UserPwd = $("#UserPwd").val();
	
	if (Username==null || Username=="") {
		sAlert("请输入用户名!");
		return false;
	}
	else if (UserPwd==null || UserPwd=="") {
		sAlert("请输入密码!");
		return false;
	}else {
		
		return true;
	}
	
}


function loginsub(){
	var Username = $("#Username").val();
	var UserPwd = $("#UserPwd").val();
	
	if (Username==null || Username=="") {
		sAlert("请输入用户名!");
		return false;
	}
	else if (UserPwd==null || UserPwd=="") {
		sAlert("请输入密码!");
		return false;
	}else {
		$("#loginsub").submit();
	}
	
}
</script>

</head>

<body>
<!-- <div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/index/logo.png"></a>
          </div>
          <div class="header_right">
                    <div class="header_right_1"><a href="#"><img src="images/index/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="#"><img src="images/index/person.png"></a></div>
                    <div class="header_right_3"><a href="#"><img src="images/index/drive.png"></a></div>
          </div>
</div> -->
<c:import url="public/top.jsp"></c:import>
<div class="logo"><img src="images/index/logo1.png"></div>
<div class="icon">
          <div class="icon_left">
                    <img src="images/index/zhengpin.png">
          </div>
          <div class="icon_center">
                    <img src="images/index/mianfei.png">
          </div>
          <div class="icon_right">
                    <img src="images/index/youzhi.png">
          </div>
</div>
<div  style="clear:both;"></div>
<form action="login?redirct_url=${redirct_url }" method="post" onsubmit="return loginvail();" id="loginsub">
<input name="lotteryUrl" type="hidden" value="${lotteryUrl}"></input>
<input name="cardNo" type="hidden" value="${cardNo}"></input>
<input name="openId" type="hidden" value="${openId}"></input>
<input name="ManyUrl" type="hidden" value="${ManyUrl}"></input>
<div class="main">
<div style="color: red;">${errmsg }</div>
          <div class="main_left">用户名：</div>
          <div class="main_center"><input type="text" name="Username" id="Username"></div>
          <div class="main_right"></div>
</div>
<div  style="clear:both;"></div>
<div class="main">
          <div class="main_left">密&nbsp;&nbsp;&nbsp;码：</div>
          <div class="main_center"><input type="password" name="UserPwd" id="UserPwd"></div>
          <div class="main_right"></div>
</div>
<div  style="clear:both;"></div>
<div class="button">
          <div class="button_left">&nbsp;</div>
          <a href="javascript:void(0);" onclick="loginsub();"><div class="button_center">登&nbsp;&nbsp;&nbsp;录 </div></a>
          <div class="button_right"></div>
</div>
</form>
<div  style="clear:both;"></div>
<div class="wen">
          <div class="wen_left">&nbsp;</div>
          <div class="wen_center">
                    <div class="wen_center_left"><a href="wxlogin?url=${lotteryUrl}&cardno=${cardNo}&openid=${openId}">
                    直接用微信新建账户</a></div>
                    <div class="wen_center_right"><a href="#"></a></div>
          </div>
          <div class="wen_right"></div>
</div>
</body>
</html>
