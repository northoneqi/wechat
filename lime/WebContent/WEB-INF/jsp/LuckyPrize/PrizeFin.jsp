<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
<!-- <meta http-equiv="refresh" content="6;url=/lime/index"/> -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>抽奖结束</title>
<style>
.main {
	width: 98%;
	margin: 1%;
	border-radius: 6px;
	background: #fff;
	text-align: center;
	margin-top: 15%;
}

.main1 {
	text-align: center;
	height: 40px;
	width: 20%;
	background-color: #2e8f02;
	margin: 0 auto;
	padding-top: 1.5%;
}

.box {
	width: 100%;
	margin: 0 auto;
	background: #2e8f02;
	height: 60px;
	font-size: 16px;
	line-height: 60px;
	color: #fff;
}

.box2 {
	width: 50%;
	margin: 0 auto;
	height: 30px;
	font-size: 16px;
	line-height: 30px;
	color: #fff;
}
</style>
</head>
<body>

	<c:import url="../public/top.jsp"></c:import>
	<br />
	<div class="main">
		<div id="div">
			<img src="images/prize/gg.jpg" />
		</div>
		<img src="images/prize/prize6.jpg" /> <br>
		<c:if test="${remind !=null || remind !='' }">
			<div class="box2">
				<a href="bangding?cardno=${p.cardNo}&openId=${openid}" style="">${remind}</a>
			</div>
		</c:if>
		<div class="box">
			<c:if test="${msg ==null || msg =='' }">
		恭喜您抽中了${p.prizeContent}的骑士卡${openid},${p.cardNo}
		
				</c:if>
			<c:if test="${msg !=null || msg !='' }">
			${msg}
		</c:if>

		</div>
		<div id="url" style="font-size: 12px; color: #999;height: 30px;">
			${url} 
			<input type="button" style="font-size: 14px;" value="复制链接发给好友" id="button" />
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="js/LuckyPrize/ZeroClipboard.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#div").click(function() {
				//alert("---")
				window.location = "index";
			});

		});
		var url = $("#url").text();
		copyToClipboard(url, "button");
		function copyToClipboard(txt, id) {
			ZeroClipboard.setMoviePath("js/LuckyPrize/ZeroClipboard.swf");
			var clip = new ZeroClipboard.Client();
			clip.setHandCursor(true);
			//var text = document.getElementById("url").value;
			//alert(url);
			clip.setText(url);
			clip.glue(id);
			clip.addEventListener("complete", function() {
				alert("复制成功！");
			});
		}
	</script>
</body>
</html>
