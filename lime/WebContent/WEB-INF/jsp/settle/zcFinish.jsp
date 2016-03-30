<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.qishi.util.ServerConfig" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>众筹支付</title>  
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<%-- <link href="<c:url value='../css/index/top_footer.css'/>" type="text/css"	rel="stylesheet" /> --%>
<link href="<c:url value='../css/settle/close.css'/>" type="text/css"	rel="stylesheet" />
<link href="<c:url value='../css/settle/close_wirte.css'/>" type="text/css" rel="stylesheet" />
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.3.2.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/shoppingcart/cart.js"></script>
 <script type="text/javascript">        
     
           
    </script>   
<style type="text/css">
	html,body,div,span,iframe,h1,h2,h3,h4,h5,h6,p,b,u,i,center,dl,dt,dd,ol,ul,li,fieldset,form,label,legend{margin:0;padding:0}
	ol,ul{list-style:none}:focus{outline:0}
	body{

	}
	.line1{
		width:80%;
		height:50px;
		text-align: center;
		margin:20px auto 0 auto;
	}
	.line1 input{
		background-color: #2c7037;
		color: white;
		border-left:0px;
		border-top:0px;
		border-right:0px;
		border-bottom:0px;
		width:200px;
	}
	.line2{
		width:80%;
		text-align: center;
		margin:0 auto;
	}
	.line2 .mb{
		background-color:#fff5bd;
		width:100%;
		margin:0 auto;
		border-radius:15px;
		padding: 10px 10px 10px 10px;
	}
	.line2 .mb .nr{
		color: red;
		font-weight: bold;
	}
  </style>

<script type="text/javascript" src="../js/jquery-1.3.2.js"></script>
<script type="text/javascript">

$(function(){
	$("#bgImage").attr("height", $(window).height());
	$("#bgImage").attr("width", $(window).width());
	
	$(window).resize(function(){
		$("#bgImage").attr("height", $(window).height());
		$("#bgImage").attr("width", $(window).width());
		$("#bgImage").removeAttr("style");
	});
});
</script>      
</head>
	
<body>
 <div style="height:100%;">
 <img class="img" alt="" src="../images/zc/bj1.png" id="bgImage" >
	<div class="line2" style="position:absolute; top:5%;left:9%;">
		<div class="mb">
		你的好友"${zName }"已经完成了众筹你也来参加吧
		<br/>
</div>
</div>
<div>
		<div style="position:absolute;bottom: 35%;left:30%;">
		<a href="http://<%=ServerConfig.getBaseUrl()%>/ManyPlan/goodsku?proSkuId=${tuijian.SKU}">
		<img src="${tuijian.GOOD_PNG}" width="80%;"></a> 
		</div>
		</div>
	<div>
		<div style="position:absolute;bottom: 10%;left:30%;">
		<a id="" href="http://<%=ServerConfig.getBaseUrl()%>/ManyPlan/manyPlan">
			<img src="../images/zc/wycj.png">
		</a><br/>
		</div>
	</div>
	</div>
<div style="position:absolute;bottom: 1px;right:0px;text-align: right;">
 <img src="../images/zc/logo.png" width="80%;"/>
 
</div>
</body>
</html>

