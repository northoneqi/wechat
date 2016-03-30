<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
  <link rel="stylesheet" href="css/prize/award.css">
  <title>拼人品，看谁来帮你</title>
<style type="text/css">
body{margin:0px; padding:0px; margin-top:7%;color:#fff;}
img,object { max-width: 100%; max-height:100%; text-decoration:none;}
a{text-decoration:none;color:#fff;}
.main{width:100%; margin:0%; border-radius:6px;text-align:center;margin-top: 10%;}
.main div{font-size:40px; color:#fff; font-family:Microsoft YaHei;font-weight:bold; text-align:center;letter-spacing:-3px;}
.img{width: 50%;}
</style>

 </head>
 <body style="background-color:#268948; width: 100%;">
  <div class="main">
  <div><img src="images/prize/02-title5.jpg" ><br/>
  <a href="http://wsq.qq.com/reflow/260989977"><img src="images/prize/02-dtjdjkzq.png" ></a>
  </div>
  
  
  <div align="center" style="text-align: center;font-size: 14px;">
<table style="width:220px;margin:auto;color: #fff;" cellpadding="0" cellspacing="0">
<tr align="center">
<c:forEach items="${tuijian}" var="tuijian" begin="0" end="1" >
<td align="center"><a href="<c:url value="/goodsku?proskuid=${tuijian.ProSKUId }"/>">
<img width="100px" height="100px" src="http://images.yijia360.com${tuijian.ListPagePic }">
<br><span style="color:#fff;">${fn:substring(tuijian.ProName,0,8) }</span>
</a></td>

</c:forEach>
</tr>
</table>
<div  style="clear:both;"></div>
<table style="width:100%;margin:auto;" cellpadding="0" cellspacing="0">
<tr align="center">
<c:forEach items="${tuijian }" var="tuijian" begin="2" >
<td width="33%"><a href="<c:url value="/goodsku?proskuid=${tuijian.ProSKUId }"/>">
<img width="100px" height="100px" src="http://images.yijia360.com${tuijian.ListPagePic }">
<br><span style="color:#fff;">${fn:substring(tuijian.ProName,0,8) }</span>
</a>
</td>
</c:forEach>
</tr>
</table>
</div>
  <div style="text-align:right;position:fixed;bottom: 45px;"><img class="img" src="images/prize/02-logo.png"/></div>
      <div  style="width:100%; text-align:center; height:60px;line-height:60px; padding-left:-1%;position:fixed;bottom:-3%;padding-bottom: 0%">
  	<img src="images/prize/02-fdt.png" usemap="#Map" id="img"/>
		<map id="Map" name="Map">
			<area id="area1" shape="rect" coords=""  href="index" />
			<area id="area2" shape="rect" coords="" href="tuijian" />
			<area id="area3" shape="rect" coords="" href="http://mp.weixin.qq.com/s?__biz=MzA3OTU1MzUyMw==&mid=200466812&idx=1&sn=87b72e1cf6189694899514453ccbfc15#rd" />
			<area id="area4" shape="rect" coords="" href="http://mp.weixin.qq.com/s?__biz=MzA3OTU1MzUyMw==&mid=200469532&idx=1&sn=151fc15b424c9d570bcf8f9b708ae684#rd" />
		</map>
  	</div>
  </div>
 <script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		$(function() {
			var width = $('#img').width();
			var height = $('#img').height();
			var w1 = width/4;
			$("#area1").attr("coords","1,1,"+w1+","+height);
			$("#area2").attr("coords",""+w1+",1,"+w1*2+","+height);
			$("#area3").attr("coords",""+w1*2+",1,"+w1*3+","+height);
			$("#area4").attr("coords",""+w1*3+",1,"+w1*4+","+height);
		});
		</script>
 </body>
</html>
