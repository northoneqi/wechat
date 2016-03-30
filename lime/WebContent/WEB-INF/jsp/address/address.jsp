<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士-收货地址管理-收货人地址</title>
<link type="text/css" rel="stylesheet" href="css/address/address.css">
<link type="text/css" rel="stylesheet" href="css/address/top_footer.css	">
</head>
<body>
<c:import url="../public/top.jsp"></c:import>
<div class="main">
			<input type="hidden" value="${reurl}" name="reurl" id="reurl">
			<input type="hidden" value="${orderCodes}" name="orderCodes" id="orderCodes">
<c:forEach items="${list}" var="item">
          <div class="main_a" >
          	<input type="hidden" value="${item.userId}" id="userId">
                    <div class="main_a_left" id="${item.OAddressID}">
                             <span>&nbsp;&nbsp;<c:out value="${item.OConsignee}"/>&nbsp;<c:out value="${item.OMbile}"/> </span><br>
                             <span>&nbsp;&nbsp;<c:out value="${item.OTelephone}"/></span><br>
                             <span>&nbsp;&nbsp;<c:out value="${item.OAddress}"/></span><br>
                    </div>
          
                    <div class="main_a_right">
                              <a href="to_Upd_Page?id=${item.OAddressID}&url=${reurl}&orderCodes=${orderCodes}"><img src="images/address/bianji.png">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
                               <c:if test="${item.defAddress eq 1}" >
                                        <img src="images/address/duihao.png">
                              </c:if>
                              <c:if test="${item.defAddress ne 1&&empty reurl}">
                              <a href="del_Address?id=${item.OAddressID}" class="del"><img src="images/address/shanchu.png">删除</a>
                    		</c:if>
                    </div>
                    <div  style="clear:both;"></div>
          </div>
          </c:forEach>

</div>
<a href="to_Add_Page?url=${reurl}&orderCodes=${orderCodes}"><div class="bottom">
          <div class="bottom_left"><img src="images/address/jiahao1.png"></div>
          <div class="bottom_right">新增地址</div>
</div></a>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		$(".main_a_left").click(function(){
			var userId=$("#userId").val();
			var addressId = $(this).attr("id");
			var reurl=$("#reurl").val();
			var orderCodes = $("#orderCodes").val();
			window.location="defaultAddress?userId="+userId+"&addressId="+addressId+"&url="+reurl+"&orderCodes="+orderCodes;
			//alert("已选为默认地址");
		});
		
	});
</script>
</body>
</html>
