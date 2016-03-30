<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士-订单管理-订单列表</title>
<link type="text/css" rel="stylesheet" href="css/order/personal_order_detailed_money.css">
<link type="text/css" rel="stylesheet" href="css/order/top_footer.css">
<script type="text/javascript">
function orderAccept() {
	//alert(i);
	window.location.href = "/lime/orderlist";
}
	function orderlist(i) {
		window.location.href = "/lime/orderdetail?code=" + i;
		//window.location.href = "/lime/orderdetail?state=" + j;
	}
</script>
</head>

<body>
<c:import url="../public/top.jsp"></c:import>
<div style="margin-top:10%;">　</div>
<c:forEach items="${list}" var="sr">
<div class="main" onclick="orderlist('${sr.WeixinOrderCode}')" >
          <div class="main_3">
                    <div class="main_3_left">
                    
                              下单时间：${fn:substring(sr.OrderTime,0,10)}<br>
                             订单编号：${sr.WeixinOrderCode}<br>
                              订单金额：<span>￥${sr.total}</span>
                    </div>
                    <div class="main_3_right" id="state">订单状态<br/><span>${sr.ParaName}</span></div><div  style="clear:both;"></div>
          </div>
        <c:if test="${sr.Address!=null}">
        <div class="main_3" style=" margin-top:-4%;">送货地址：${sr.Address}</div>
        <div class="main_3" style=" margin-top:-4%;">支付方式：<span style="color:#00F;"><c:if test='${sr.PayType==2}'>货到付款</c:if >
        <c:if test='${sr.PayType==8}'>骑士卡支付</c:if >
        <c:if test='${sr.PayType==12}'>微信支付</c:if ></span>
        </div>
        </c:if>
        <div  style="clear:both;"></div>
          
</div>
</c:forEach>
</body>
</html>
