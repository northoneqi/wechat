<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>我的骑士-订单管理-订单明细(${orderInfo.ParaName})</title>
<link type="text/css" rel="stylesheet"
	href="css/order/personal_order_detailed_money.css">
<link type="text/css" rel="stylesheet" href="css/order/top_footer.css">
<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">

	//取消订单
	var flag=true;
	function cancleOrder(OrderCode){
		if(flag==false){
			return;
		}
		flag=false;
		window.location.href = "/lime/orderCancle?OrderCode="+OrderCode+"&PayType="+${orderInfo.PayType}+"&AcceptType="+${orderInfo.AcceptType};
	}
	//ajax取消订单并刷新
	 function noPay(OrderCode) {
		 var SKU=$('#SKU').val();
		 $.ajax( {  
			  type:'POST', 
			  data:{sku:SKU},   
			  url:'/lime/checkxiajia',  
		      success:function(data) {
		    	if(data==1){
		    		$.ajax({
		    			data : {
		    				"OrderCode" : OrderCode,
		    			},
		    			url : '/lime/orderAccept',
		    			success : function(msg) {
		    				//sAlert(msg+"订单状态"); 
		    				if(msg=="1"&&msg!="12"){
		    				var split=document.getElementById("paysplit").value;
		    				window.location.href = "/lime/settle/settlement?dingdanhao="+OrderCode+"&split="+split;
		    			}else{
		    				window.location.reload(); 
		    			}
		    			},
		    			error : function(msg) {
		    				sAlert(msg); 
		    			}
		    		});
		    		return false;
		    	}else{
		    		sAlert("商品已下架");
		    		window.location.href = "/lime/orderlist";
		    	}
		       },    
		       error : function(data) {   
		    	   sAlert(data);
		       }    
		       
		  }); 
		
	} 

	//未付款跳转支付
	/* function noPay(OrderCode,AcceptType) {
		//alert(AcceptType+"状态");
		if(AcceptType==1){
			var split=document.getElementById("paysplit").value;
			window.location.href = "/lime/settle/settlement?dingdanhao="+OrderCode+"&split="+split;
		}else{
			window.location.reload(); 
				
		}
	} */
</script>

</head>


<body>
	<c:import url="../public/top.jsp"></c:import>
	<div style="margin-top: 15%;"></div>
	<div class="main">
		<div class="main_1">
			<div class="main_1_left" id="state">
				订单状态：<span>${orderInfo.ParaName}</span>
			</div>
			<br />
			<div class="main_1_right">
				<c:if test="${orderInfo.AcceptType=='1'}">
					<a href="#" onclick="noPay('${orderInfo.WeixinOrderCode}','${orderInfo.AcceptType}');"><img
						src="images/order/buttom6.png">&nbsp;</a>
					<br />
				</c:if>
				<c:if test="${orderInfo.AcceptType<='6'}">
					<c:if test="${orderInfo.PayType!='12'||orderInfo.AcceptType<='1'}">
					<a
						href="javascript:;" onclick="return cancleOrder('${orderInfo.WeixinOrderCode}');">
						<img src="images/order/buttom5.png"> &nbsp;
					</a>
					</c:if>
				</c:if>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div style="clear: both;"></div>
		<div class="main_1">
			订单号：${orderInfo.WeixinOrderCode}<br> 成交时间：
			${fn:substring(orderInfo.OrderTime,0,10)}
			<br> 订单金额：<span>￥${orderInfo.total}</span>
		</div>
		<c:if test="${orderInfo.Address!=null}">
		<div class="main_1">
			地址：${orderInfo.Address}<br> 姓名：${address.Consignee}<br>
			手机：${address.Mobile}<br> 电话：${address.Telephone}
		</div>
		<div class="main_1">
		<c:if test="${number!=0}">
			<c:if test="${orderInfo.total-daofu!=0}">已付金额：<span>￥<fmt:formatNumber value="${orderInfo.total-daofu}" pattern="#.##" type="number"/></span></c:if>
			<c:if test="${daofu!=0.00}"><br>现金补差：<span>￥${daofu}</span></c:if><br>
			</c:if>
			
			配送日期：${fn:substring(address.SendDate,0,10)}<br> 配送时间：
			<c:if test="${address.SendTimeSpan==1}">9:00-11:30</c:if>
			<c:if test="${address.SendTimeSpan==2}">14:00-18:00</c:if>
			<c:if test="${address.SendTimeSpan==3}">18:00-20:00</c:if>
			<c:if test="${address.SendTimeSpan==4}">任意时间</c:if>
			
			
		</div>
		</c:if>
		<c:forEach items="${detail}" var="sr">
			<div class="main_2" style="border-bottom: 0px;">
				<div class="main_2_left">
				<a  href="<c:url value="/goodsku?proskuid=${sr.ProSKUId }"/>">	<img src="http://images.yijia360.com${sr.ListPagePic}"></a>
				</div>
				<div class="main_2_right">
					${sr.ProName}<br> 小计：<span>￥${sr.Subtotal}</span><br> <em>数量：${sr.ProNum}</em>
					<input type="hidden" value="${sr.OrderClass }" id="paysplit">
					<input type="hidden" value="${sr.ProSKUId }" id="SKU">
				</div>
				<div style="clear: both;"></div>
			</div>
		</c:forEach>
	</div>

</body>
</html>
