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
  <script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
  <title>拼人品，看谁来帮你</title>
<style type="text/css">
body{margin:0px; padding:0px; margin-top:7%;}
.main{text-align:center;width:100%; height:50px; border-bottom:1px solid #ccc; border-top:1px solid #ccc; color:#999; font-size:22px; line-height:50px; margin-top:15px; text-indent:0.5em;}
.main_2{width:100%; color:#999; font-size:12px; text-align:center; line-height:24px;}
.main_2 span{font-size:12px;}
.main_2 a{color:#999;height:400px;width: 400px}
.main_2 .main_2_a{width:50%; border-right:1px solid #ccc; padding-bottom:7%; padding-top:7%;}
.main_2 .main_2_b{width:50%; padding-bottom:7%; padding-top:7%;}
img,object { max-width: 100%; max-height:100%; text-decoration:none;}
a{text-decoration:none;}
.main1{width:100%; border-radius:6px;text-align:center;margin-top: 10%;}
.main1 div{font-size:40px; color:#fff; font-family:Microsoft YaHei;font-weight:bold; text-align:center;letter-spacing:-3px;}
.img{width: 50%;}
</style>
<script type="text/javascript">
		function sub(){
		forsub.submit();
	}
	function goPay(SellPrice,sku,split){
		var buynum = 1;
		//alert(buynum);
		window.location.href = "/lime/goPay?SellPrice="+SellPrice+"&ProNum="+buynum+"&sku="+sku+"&split="+split;
	}
</script>
 </head>
 <body style="background-color:#268948; width: 100%;">
  <div class="main1">
  <div style="text-align:center">
  								<c:if test="${p eq 1}"><img src="images/prize/02-prize1.png" style="width: 80%"></c:if>
  								<c:if test="${p eq 2}"><img src="images/prize/02-prize2.png" style="width: 80%"></c:if>
  								<c:if test="${p eq 3}"><img src="images/prize/02-prize3.png" style="width: 80%"></c:if>
  								<c:if test="${p eq 4}"><img src="images/prize/02-prize4.png" style="width: 80%"></c:if>
  								<c:if test="${p==null || p==''}">
  								<div style="font-size: 30px;">您已领过红包</div><img class="img" src="images/prize/open.png" ></c:if></div>
	
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
<table style="width:325px;margin:auto;" cellpadding="0" cellspacing="0">
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
</br>
</div>

  <form name="forsub"action="bangding" method="post">
	<input type="hidden" name="cardno" value="${plist.cardNo}">
	<input type="hidden" name="openId" value="${openid}">
	</form>
	<c:if test="${remind!=null}"><div><a href="javascript:sub()"><img src="images/prize/anniu.png" width="80%"/></a></div></c:if>
	<c:if test="${remind2!=null}"><div><a href="index"><img src="images/prize/02-goUsehongbao.png" width="80%"/></a></div></c:if>

  <div><img id="box" src="images/prize/02-fenxiang.png" style="width: 80%"></div>
  <br/>
  <input type="hidden" value="${url}"/>
  </div>
  <!-- ------- -->

  <!-- ------- -->
  <div class="main1">
  <div style="text-align:center;margin: 2%;">
<!--   <div style="font-size:20px;font-weight:600;font-family:Microsoft YaHei;letter-spacing:-1px; text-align:left;">抽奖页面内容提示：</div>
 -->    <div style="width:100%; font-size:14px;text-align:left;font-family:SongTi;letter-spacing:1px;font-weight:normal;word-wrap: break-word; 
　　word-break: normal;line-height: 25px;">
<ol style="list-style:none;">
<li><span style="font-size:20px;font-weight:600;font-family:Microsoft YaHei;letter-spacing:-1px; text-align:left;">分享有礼</span></li>
<li><span style="color:red;">1、爱分享</span><br/>
好东西当人要跟好朋友分享，点击“爱分享”按钮，分享活动至朋友圈,
大家一起抢完盒子，抢红包，抢完红包，拼饭啦~<br/>
举个例子：亲们，我收到了京华骑士的真相盒子，还获得了红包奖励，
快来加关注，抢好盒子和红包来我家拼饭吧~O(∩_∩)O~</li>
<li><span style="color:red;">2、晒真挚</span><br/>
<span>点击<a href="http://wsq.qq.com/reflow/260989977"><img style="margin-bottom: -7px;" width="50%" src="images/prize/02-jiankang.png"/></a></span><br/>
晒出你与孩子/父母的温馨照片（一定要有真相盒子的身影在里面哦~），
写上一段不求感天动地，但求感动大家感动小亿的话语，
京华骑士最受欢迎的桃木疙瘩鸡蛋盒就是你的啦~
每天10个名额，你的照片够真挚，你的故事够感人吗？show起来~
（据说得赞越多，获奖几率越大，快点拉动好友点赞吧~）</li>
<li><span>3、抵用券使用规则</span><br/>
本次活动抵用券券仅限PC端、微信端购买京华骑士任意产品时，
以现金券的形式抵用，不可变现。<br/>
优惠券有效期：2014年8月12日——2014年9月12日</li>
<li><span>4、本次活动最终解释权归京华骑士所有。</span></li>
</ol></div>
  </div>
   <div style="text-align:right;"><img class="img" src="images/prize/02-logo.png"/></div>
  <div style="height: 60px;"></div>
   <div  style="width:100%; text-align:center; height:60px;line-height:60px; padding-left:-1%;position:fixed;bottom:-3%;padding-bottom: 0%">
  	<img src="images/prize/02-fdt.png" usemap="#Map" id="img"/>
		<map id="Map" name="Map">
			<area id="area1" shape="rect" coords=""  href="index" />
			<area id="area2" shape="rect" coords="440,11,381,128" href="tuijian" />
			<area id="area3" shape="rect" coords="440,11,381,128" href="http://mp.weixin.qq.com/s?__biz=MzA3OTU1MzUyMw==&mid=200466812&idx=1&sn=87b72e1cf6189694899514453ccbfc15#rd" />
			<area id="area4" shape="rect" coords="440,11,381,128" href="http://mp.weixin.qq.com/s?__biz=MzA3OTU1MzUyMw==&mid=200469532&idx=1&sn=151fc15b424c9d570bcf8f9b708ae684#rd" />
		</map>
  	</div>
  </div>
   <div id="share_cover" class="cover" style="display: none;">
	<img src="images/prize/arrow_guid.png" id="arrow">
    <p id="share_note" style="line-height: 50px; font-size: 30px;">点击右上角，通过【发送给好友】或【分享到朋友圈】，邀请好友一起参与</p>
    <div id="share_close_btn" style="line-height: 70px; font-size: 42px; height:70px;">关&nbsp;闭</div>
</div>
  <script type="text/javascript" src="js/jquery-1.3.2.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#box").click(function() {
				$("#share_cover").css("display","block");
			});
			$("#share_close_btn").click(function() {
				$("#share_cover").css("display","none");
			});
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
