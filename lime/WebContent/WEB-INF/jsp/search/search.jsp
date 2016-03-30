<%@ page language="java" contentType="text/html; charset=utf-8"  import="java.util.List"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>搜索</title>
<link type="text/css" rel="stylesheet" href="css/address/top_footer.css">
<style>
.box{margin:1%;margin-top: 15%;}
.sou{width:100%; background:#f5f5f5; height:50px;text-align: left;}
.sou .sou_left{width:15%; float:left; text-align:center;}
.sou .sou_center{width:60%;float:left;margin-top: 1px;}
.sou .sou_center input{border:0px; line-height:40px; color:#999; font-size:14px; background:#f5f5f5;height: 50px;width: 100%}
.sou .sou_right{background:#2e8f02;width:25%; float:left;text-align: center;height:50px; text-align:center; color:#fff; font-size:16px; line-height:50px;}
.sou .sou_right .a{color:#333333;}
.sou .sou_right1{width:5%;float: center;}
.nav{width:100%; height:55px; border:1px solid #e9e9e9; color:#333; font-size:13px; background:#f5f5f5; margin-top:2%;}
.nav .nav_left{width:49.5%; border-right:1px solid #e9e9e9; text-align:center; float:left; line-height:55px;}
.nav a{color:#333;}
.nav .nav_right{width:49.5%; text-align:center; float:left; line-height:55px;}
.nav .click{background:#fff; width:50%; margin-top:1px;}
.main{width:100%; color:#333; font-size:12px; height:40px; margin-top:70px;}
.main a{color:#333;}
.ul{font-size:16px; text-align:center;background:#f5f5f5;border:1px solid #e9e9e9;word-break:normal|break-all|keep-all;float:left;list-style:none;margin: 10px 10px; padding:2px;border-radius:7px;}
.sear{color:#333333;}
.main2{width:20%; text-align:center; background:#f5f5f5; border:1px solid #e9e9e9;  float:left; margin-right:2%; line-height:40px;}
.main3{width:30%; text-align:center; background:#f5f5f5; border:1px solid #e9e9e9;  float:left; line-height:40px;margin-right:2%;}
.main4{width:40%;text-align:center; background:#f5f5f5; border:1px solid #e9e9e9;  float:left; line-height:40px;margin-right:2%;color: #333333;}
.sou .sou_right .button{color: #333333;width:10%; background:#2e8f02; height:40px; text-align:center; color:#fff; font-size:16px; line-height:40px;}
</style>
</head>

<body>
<c:import url="../public/top.jsp"></c:import>
<div class="box">
          <div class="sou">
                    <div class="sou_left"><img src="images/goodlist/sousuo.png"></div>
                    <div class="sou_center"><input type="text" id="search" name="searchName" maxlength="20" value="输入关键字，搜索商品"></div>
                    <div class="sou_right"><a class="a" href="#" id="searchButton"> 搜索</a></div>
          </div>
          <div class="nav">
                    <a href="#"><div class="nav_left click">最近搜索</div></a>
                    <a href="#"><div class="nav_right "></div></a>
          </div>
          
<%--   <div class="tag-book">
		<div class="tag-list clearfix">
			<div class="tag-hot">
				<ul class="clearfix">
				<c:forEach items="${list}" var="info">
 					<c:forEach items="${info}" var="_info">
					<li>${_info }</li>
					</c:forEach>
					</c:forEach>
				</ul>
			</div>
		</div> --%> 
  </div>
 
         <div>
         	<ul>
         	<c:forEach items="${list}" var="info">
 				<c:forEach items="${info}" var="_info">
         	<li class="ul">
         	
                    <a href="#"><div class="sear">${_info }</div></a>
                   <%--  <a href="#"><div class="main2"><%=obj[1]%></div></a>
                    <a href="#"><div class="main3">酸奶</div></a> --%>
          	</li>
          	          	</c:forEach>
			</c:forEach>
          	</ul>
          	</div>


<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">
	$(function(){
		$("#search").click(function(){
			//alert("--");
			$(this).val("");
			
			$(this).keyup(function(event){
				var keycode = event.which;
				if(keycode == 13){
					var goodName = $(this).val();
					window.location="searchGood?searchName="+encodeURI(encodeURI(goodName));
				}
			});
		});
		
		$("#searchButton").click(function(){
			var goodName = $("#search").val();
			if(goodName=="输入关键字，搜索商品"){
					goodName="";
				}
			window.location="searchGood?searchName="+encodeURI(encodeURI(goodName));
		});
		
		$("#searchTo").click(function(){
			var goodName = $("#search").val();
			window.location="searchGood?searchName="+encodeURI(encodeURI(goodName));
		});
		
		$(".sear").click(function(){
			var goodName = $.trim($(this).text());
			//alert("|"+goodName+"|");
			window.location="searchGood?searchName="+encodeURI(encodeURI(goodName));
		});
	});
</script>
</body>
</html>
