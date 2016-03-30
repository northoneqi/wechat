<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta content="text/html;charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>骑士卡绑定-添加新卡</title>
<link type="text/css" rel="stylesheet" href="css/card/card.css">
<link type="text/css" rel="stylesheet" href="css/card/top_footer.css">
<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
 function notNull() {
		var notN=document.getElementById("carName");
// 		var reg =/^[u4E00-u9FA5]+$/;  
		
		if(notN.value==""){
			sAlert(" 卡号不能为空 ");
			return false ;
		}
		if(document.getElementById("cardPwd").value==""){
			sAlert("密码不能为空 ");
			return false ;
		}
// 		if(!reg.test(notN.value)){  
// 			sAlert("不能输入汉字 ");
// 			return false;    				
// 		}
		
		
		form_card.submit();
		
	};
</script>

</head>

<body>

<c:import url="../public/top.jsp"></c:import>

<form id='form_card' action="/lime/save" method="post">
	<div class="main" style="margin-top:15%;">
          <div class="main1">
                    卡号：<input type="text" name="CardNo" id="carName" maxlength="20" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" >
          </div>
          
          <div class="main1" style="border-bottom:0px;">
                    密码：<input type="password" name="CardPwd" id="cardPwd" maxlength="20" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" >
          </div>
	
	</div>
	<div align="left"><span style="color:red" >${zhuangtai}</span></div>
	<div align="left"><span style="color:red" >${moneyNull}</span></div>
	<div align="left"><span style="color:red" >${bangding}</span></div>
    <div  style="clear:both;"></div>
    <input type="hidden" value="${url}" name="url">
	<input type="hidden" value="${orderCodes}" name="orderCodes">
    <div class="button" onclick="javascript:notNull();"> 确定 </div>
</form>
</body>
</html>
