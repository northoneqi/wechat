<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
  <title>拼人品，看谁来帮你</title>
<style type="text/css">
body{margin:0px; padding:0px; margin-top:7%;}
img,object { max-width: 100%; max-height:100%; text-decoration:none;}
a{text-decoration:none;}
.main{width:96%; margin:4%; border-radius:6px;text-align:center;margin-top: 10%;}
.main div{font-size:40px; color:#fff; font-family:Microsoft YaHei;font-weight:bold; text-align:center;letter-spacing:-1px;}
.img{width: 50%;}
</style>
<script type="text/javascript">
	function sub(){
		
		forsub.submit();
	}

</script>
 </head>
 <body style="background-color:#268948; width: 100%;">
  <div class="main">
  <div><img src="images/prize/02-title6.png" width="80%" ><br/>
  <img src="images/prize/02-hongbao.png" style="width: 80%"></div>
<div style="font-size: 20px;text-align: left;"> 您未绑定的骑士奖品卡：<span style="color:red;">${yijiakasize}</span> 张</div>
<br/><c:if test="${yijiakasize!=0}"> <form name="forsub"action="bangding" method="post">
	<input type="hidden" name="cardno" value="${yijiaka.cardno}">
	<input type="hidden" name="openId" value="${openid}">
	</form>
	<div style="text-align:center;"><a href="javascript:sub()" style="text-decoration:none;" ><img border="0" src="images/prize/anniu.png" width="80%"/></a></div>
  </c:if></div>
  <%--  <div style="text-align:center; font-size: 20px;"><a href="${url}" style="text-decoration:none;"><img src="images/prize/fenxiang.png" width="80%"/></a></div>
   --%>
   <div style="text-align:right;"><img class="img" src="images/prize/02-logo.png"/></div>
 </body>
</html>
