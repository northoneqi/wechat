<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@page import="com.qishi.util.ServerConfig"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">

 <meta http-equiv="Expires" CONTENT="0">        
 <meta http-equiv="Cache-Control" CONTENT="no-cache">        
 <meta http-equiv="Pragma" CONTENT="no-cache">     
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>订单信息结算</title>
<link href="<c:url value='../css/settle/close.css'/>" type="text/css"	rel="stylesheet" />
<link href="<c:url value='../css/settle/close_wirte.css'/>" type="text/css" rel="stylesheet" />
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.3.2.js"></script>
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/shoppingcart/cart.js"></script>
 <script type="text/javascript">        
    
javascript:window.history.forward(1); 
           
 </script>   
<style type="text/css">
a:link,a:visited{ text-decoration:none;  /*超链接无下划线*/}

/*弹出窗口的背景样式*/ 
.MessageBoxBG 
{ 
    display: none; 
    position: absolute; 
    width: 100%; 
    height: 400px; 
    background-color: white; 
    z-index:1001; 
    -moz-opacity: 0.80; 
    opacity:.80; 
    filter: alpha(opacity=80); 
} 

/*弹出窗口的样式*/ 
.MessageBoxBody 
{ 
    display: none; 
    position: absolute; 
    background-color:#FFF; 
    border:solid 1px #B6BCCC; 
    font:12px Verdana, Geneva, Arial, Helvetica, sans-serif; 
    text-align:center; 
    top:50%;                    
    left:50%; 
    height:135px; 
    width:300px; 
    margin:-60px 0 0 -150px;  
    padding: 0px; 
    z-index:1002; 
    overflow: auto; 
} 

/*弹出窗口的样式*/ 
.hongMessageBoxBody 
{ 
    display: none; 
    position: absolute; 
    background-color:#FFF; 
    border:solid 1px #B6BCCC; 
    font:12px Verdana, Geneva, Arial, Helvetica, sans-serif; 
    text-align:center; 
    top:50%;                    
    left:50%; 
    height:175px; 
    width:300px; 
    margin:-60px 0 0 -150px;  
    padding: 0px; 
    z-index:1002; 
    overflow: auto; 
}
</style>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
 $("#test").click(
 function (e){
     var ev = window.event;//获取event对象 
  location.replace(this.href);
  ev.returnValue=false;
 
 });
 
});

</script>
<!-- 支付 -->
 <script language="javascript" src="<c:url value="/js/wxpayjs/jquery.js"/>"></script>
        <script language="javascript" src="<c:url value="/js/wxpayjs/lazyloadv3.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/md5.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/sha1.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/paysupply.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/payfunction.js"/>"></script>  
          <!-- 支付 -->
<script type="text/javascript">
function setChecked(name) {
	//var isChecked = document.getElementById(name).checked;
    //isChecked = isChecked ? false : true;
    //document.getElementById(name).checked = isChecked;
   // if(name == "money1"){
	   
	  
	   
    document.getElementById(name).checked = true;
    $("#hongbaoId").html("");
	$("#hongMoneyId").html("");
  //  }
    if(name == "money1"){
    	document.getElementById("paytype").value='weixin';
    }
    if(name == "money2"){
    	document.getElementById("paytype").value='huodao';
    }
    if(name == "money3"){
    	document.getElementById("paytype").value='caika';
    	 $.ajax({
 			url : '/lime/yijiaka',
 			success : function(msg) {
 				 if(msg){
 					   yijiakaBox("您没有骑士卡，是否需要绑定?");
 			   }
 			},
 			error : function(msg) {
 				sAlert(msg); 
 			}
 		});
    }
    if(name == "money4"){
	    if(${hongSize}>0){
	    	document.getElementById("paytype").value='hongbaoka';
		    //判断提示框自定义(红包卡)
		    var clientHeight=0;
			if(document.documentElement&&document.documentElement.scrollTop)
		    {
				clientHeight=document.documentElement.scrollTop;
		    }
		    else if(document.body)
		    {
		    	clientHeight=document.body.scrollTop;
		    }
		    var msgBox = document.getElementById("divhongMessageBoxBody"); 
		    var background = document.getElementById("divMessageBoxBG"); 
		    
		    background.style.height=document.body.scrollHeight+"px";
		    msgBox.style.marginTop = -75+clientHeight+"px";
		    msgBox.style.display="block"; 
		    document.getElementById("divMessageBoxBG").style.display="block"; 

		    var str="";
		    str+="<div style='float:left; color:#333; font-size:14px;  width:55%; line-height:40px; ' >请您选择要支付的卡：</div>";
		    str+="<div style='float:left; color:#333; font-size:14px;  width:55%; line-height:40px;margin-left:60px; ' >卡号&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp金额</div>";
		    <c:forEach items="${hongList }" var="item">
			    str+="<div style='width:100%; height:100px;'>";
			    str+="<div style='width:100%; height:100px;'>";
			    str+="<div style='float:left; color:#333; font-size:14px;  width:55%; line-height:40px; ' onclick=\"sethongChecked('${item.CardNo}','${item.Balance}');\">";
			    str+="<div style='float:left; color:#333; font-size:14px;  width:55%; line-height:40px;  ' ><input  type='radio' name='money' value='' ></div>";
			    str+="<div style='float:right; color:#333; font-size:14px;  width:55%; margin-top:-1px;margin-right:-80px; ' >"+'${item.Balance}'+"</div>";
			    str+="<div class='float:right; color:#333; font-size:14px;  width:55%; margin-top:-58px;margin-right:-130px;'>"+'${item.CardNo}'+"</div>";
			    str+="</div>";
			</c:forEach>
			 msgBox.innerHTML=str;
	    }else{
	    	sAlert("您没有红包卡，请选择其他支付方式！");
	    	setChecked('money1');
	    	//document.getElementById("money3").checked = true;
	    	//document.getElementById("paytype").value='caika';
	    }
    }
    if (name == "money1") {
		$("#otherbottom").css("display", "none");
		$("#weixinpaybottom").css("display", "block");
		
	}else {
		$("#otherbottom").css("display", "block");
		$("#weixinpaybottom").css("display", "none");
		
	}
}

//选择的红包卡
function sethongChecked(hongCard,hongMoney) {
	
	$("#hongbaoId").html("卡号：<font color='red'>"+hongCard+"</font>");
	/* $("#hongMoneyId").html("金额：<font color='red'>"+hongMoney+"</font>"); */
	$("#hongId").val(hongCard);
	$("#hongbaomoneyId").val(hongMoney);
	document.getElementById("divhongMessageBoxBody").style.display="none"; 
    document.getElementById("divMessageBoxBG").style.display="none"; 
    
    return false;
}

function selectChange(){
	//判断配送时间段
	var timeSen = $("#selSpan option:selected").val();
	$("#input_shijian").val(timeSen);
}

function check() {
	//var haveAddress = <c:if test="${!empty orderaddrs}">1</c:if><c:if test="${ empty orderaddrs }">0</c:if>;
	var   haveAddress =$("#exitaddress").val();
	//判断收货地址
	if (haveAddress == 0) {
		sAlert("请添加收货地址");
		return false;
	}
	//判断配送日期
	var sendDate = $("#d313").val();
	if (sendDate == "") {
		sAlert("请选择配送日期");
		return false;
	}
	//判断配送时间段
	var timeSen = $("#selSpan option:selected").val();
	if (timeSen == 0) {
		sAlert("请选择配送时间段");
		return false;
	}
	
	if (!dateCheck()) return false;
	
	return true;
}
function dateChange() {
	dateCheck();
}
Date.prototype.format = function(format){ 
	var o = { 
		"M+" : this.getMonth()+1, //month 
		"d+" : this.getDate(), //day 
		"h+" : this.getHours(), //hour 
		"m+" : this.getMinutes(), //minute 
		"s+" : this.getSeconds(), //second 
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
		"S" : this.getMilliseconds() //millisecond 
	} ;

	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
		if(new RegExp("("+ k +")").test(format)) { 
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
		} 
	} 
	return format; 
};
function dateCheck() {
	
	//取用户选择日期
	var arrs = $("#d313").val().split("-");
	var userDate = new Date(arrs[0], String(parseInt(arrs[1]) - 1), arrs[2], "23","59","59");
	//配送最早日期
	var beginDate = new Date();
	//配送最晚日期
	var endDate = new Date();
	//当前日期
	var curDate = new Date();
	
	//18:00之前订单将于明日配送
	//下单时间
	var curTime = curDate.format("hh:mm:ss");
	//alert(curTime);
	//alert(curDate+"下单时间");
	if (curTime <='18:00:00') {
		beginDate.setDate(curDate.getDate() + 1);
		if (beginDate.getTime() > userDate.getTime()) {
			sAlert("您下单的时间为【"+curTime+"】，货品最早将于明日配送");
			var tomorrow = new Date(); 
			tomorrow.setDate(curDate.getDate() + 1);
			$("#d313").val(tomorrow.getFullYear() + "-" + (tomorrow.getMonth() + 1)  + "-" + tomorrow.getDate());
			return false;
		}
	} else {
		//设置配送时间为后天
		beginDate.setDate(curDate.getDate() + 2);
		if (beginDate.getTime() > userDate.getTime()) {
			sAlert("您下单的时间为【18:00】之后，货品最早将于后天配送");
			//return false;
			var after = new Date();
			after.setDate(curDate.getDate() + 2);
			$("#d313").val(after.getFullYear() + "-" + (after.getMonth() + 1) + "-" + after.getDate());
		}
	}
	//结束日期最大60天之后
	endDate.setDate(curDate.getDate() + 60);
	if (endDate.getTime() < userDate.getTime()) {
		sAlert("预订时间最长为60天");
		return false;
	}
	return true;
}
//判断提示框自定义

document.write(" <div id='divMessageBoxBody' class='MessageBoxBody'> </div>"); 
document.write(" <div id='divMessageBoxBG' class='MessageBoxBG'> </div>");

//红包卡的自定提示框
document.write(" <div id='divhongMessageBoxBody' class='hongMessageBoxBody'> </div>"); 
document.write(" <div id='divhongMessageBoxBG' class='hongMessageBoxBG'> </div>");





function ConfirmBox(message) 
{ 
	
	var clientHeight=0;
	if(document.documentElement&&document.documentElement.scrollTop)
    {
		clientHeight=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
    	clientHeight=document.body.scrollTop;
    }
	
	
	
    var msgBox = document.getElementById("divMessageBoxBody"); 
    var background = document.getElementById("divMessageBoxBG"); 
    
    background.style.height=document.body.scrollHeight+"px";
    msgBox.style.marginTop = -75+clientHeight+"px";
    msgBox.style.display="block"; 
    document.getElementById("divMessageBoxBG").style.display="block"; 

    var str=""; 
    str+=" <table> <tr> <td class='ConfirmBoxIcon'> </td> <td>"; 
    str+=" <table style=\"width:285px\"> <tr style=\"height:70px\"> <td>"+message+" </td> </tr>"; 
    str+=" <tr style='height:20px;'> <td style='text-align:center;'>"; 
    str+=" <input type='button' id='ButtonYes' value='是' onclick='Hide1();'/>　　　　"; 
    str+=" <input type='button' id='ButtonNo' value='否' onclick='Hide2();'/>"; 
    str+=" </td> </tr> </table> </td> </tr> </table>";        
    msgBox.innerHTML=str; 
} 
function Hide1() 
{ 
    document.getElementById("divMessageBoxBody").style.display="none"; 
    
    document.getElementById("divMessageBoxBG").style.display="none"; 
    	 ceng();
		 form1.submit();
	
   
} 

function Hide2() 
{ 
    document.getElementById("divMessageBoxBody").style.display="none"; 
    
    document.getElementById("divMessageBoxBG").style.display="none"; 
    
    return false;
} 

function yijiakaBox(message) 
{ 
	
	var clientHeight=0;
	if(document.documentElement&&document.documentElement.scrollTop)
    {
		clientHeight=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
    	clientHeight=document.body.scrollTop;
    }
	
	
	
    var msgBox = document.getElementById("divMessageBoxBody"); 
    var background = document.getElementById("divMessageBoxBG"); 
    
    background.style.height=document.body.scrollHeight+"px";
    msgBox.style.marginTop = -75+clientHeight+"px";
    msgBox.style.display="block"; 
    document.getElementById("divMessageBoxBG").style.display="block"; 

    var str=""; 
    str+=" <table> <tr> <td class='ConfirmBoxIcon'> </td> <td>"; 
    str+=" <table style=\"width:285px\"> <tr style=\"height:70px\"> <td>"+message+" </td> </tr>"; 
    str+=" <tr style='height:20px;'> <td style='text-align:center;'>"; 
    str+=" <input type='button' id='ButtonYes' value='是' onclick='bind();'/>　　　　"; 
    str+=" <input type='button' id='ButtonNo' value='否' onclick='Hide2();'/>"; 
    str+=" </td> </tr> </table> </td> </tr> </table>";        
    msgBox.innerHTML=str; 
}
 
function bind(){
	window.location.href="/lime/addCard?url=settle/settlement&orderCodes="+'${WeixinOrderCode}';
}

function ceng(){
	var clientHeight=0;
	if(document.documentElement&&document.documentElement.scrollTop)
    {
		clientHeight=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
    	clientHeight=document.body.scrollTop;
    }
    var msgBox = document.getElementById("divhongMessageBoxBody"); 
    var background = document.getElementById("divMessageBoxBG"); 
    
    background.style.height=document.body.scrollHeight+"px";
    msgBox.style.marginTop = -75+clientHeight+"px";
    msgBox.style.display="block"; 
    document.getElementById("divMessageBoxBG").style.display="block"; 

    var str="";
    str+="<div style='float:left; color:#333; font-size:14px;  width:55%; line-height:40px;text-aglin:center; ' >正在支付，请稍等。。。</div>";
	    str+="</div>";
	 msgBox.innerHTML=str;
}




function doSubmit() {
	if(check()){
		var check2 = document.getElementById("money3").checked;
		 var checkmoney = ${Balancedou<sumPay};
		 
		 var check3 = document.getElementById("money4").checked;
		 var hongmoney1=$("#hongbaomoneyId").val();
		 //alert(hongmoney1);
		 var che = hongmoney1<${sumPay};
		 
		 //alert(che);
		 //alert(check3);
		 if(check2&&checkmoney){
			 var daomoney = Math.round(${fn:substring(sumPay-Balancedou,0,12)}*100)/100;
			 var sumPay = ${sumPay};
			 var Balancedou = ${Balancedou};
			 ConfirmBox("骑士卡总金额为:"+Balancedou+"元，订单总金额为:"+sumPay+"元，需到付:"+daomoney+"元，是否继续支付");
		 }else if(check3&&che){
			 //var daomoney = Math.round(${fn:substring(sumPay-hongmoney1,0,12)}*100)/100;
			 var sumPay = ${sumPay};
			  var daomoney = parseFloat(sumPay-hongmoney1).toFixed(2); 
			
			
			 ConfirmBox("骑士卡总金额为:"+hongmoney1+"元，订单总金额为:"+sumPay+"元，需到付:"+daomoney+"元，是否继续支付");
		 }
		 else{
			 
			 	ceng();
			 	form1.submit();
		 }
	}
	
}







</script>


<script src="<c:url value="/js/shoppingcart/alert.js"/>"
	type="text/javascript" charset="utf-8"></script>

	
</head>
	
<body>
  <form action="<c:url value="/paysubmit"/>" method="post" id="form1" onsubmit="return check();"  name="form1">
  <input type="hidden"  id="hongId" name="hongId">
  <input type="hidden"  id="hongbaomoneyId" name="hongbaomoneyId" >
<div class="main">
<c:if test="${!empty orderaddrs}">
	<div class="main1">
		<div class="main1_top">收货信息</div>
		<a href="<c:url value="/show_adress?orderCodes=${WeixinOrderCode}"/>" style="text-decoration:none ; color:white ;">
		<div class="main1_bottom">
				<span>&nbsp;&nbsp;&nbsp;&nbsp;${orderaddrs.OConsignee }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${orderaddrs.OMbile }</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;${orderaddrs.Province }_${orderaddrs.City }_${orderaddrs.Area }_
				<c:if test="${fn:length(orderaddrs.Address)<=3 }">${orderaddrs.Address}</c:if>
				<c:if test="${fn:length(orderaddrs.Address)>3 }"><c:if test="${fn:length(orderaddrs.Address)<=18 }">${fn:substring(orderaddrs.Address,0,3)}<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(orderaddrs.Address,3,18)}</span></c:if></c:if>
				<c:if test="${fn:length(orderaddrs.Address)>18 }"><c:if test="${fn:length(orderaddrs.Address)<=30 }">${fn:substring(orderaddrs.Address,0,3)}<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(orderaddrs.Address,3,18)}</span><br/><span>&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(orderaddrs.Address,18,30)}</span></c:if></c:if>
				<c:if test="${fn:length(orderaddrs.Address)>30 }">${fn:substring(orderaddrs.Address,0,3)}<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(orderaddrs.Address,3,18)}</span><br/><span>&nbsp;&nbsp;&nbsp;&nbsp;${fn:substring(orderaddrs.Address,18,60)}</span></c:if>
				</span><br>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;工作日节假日均可送</span>
		</div>
		</a>
	</div>
</c:if>
<c:if test="${ empty orderaddrs }">
          <div class="main1">
                    <div class="main1_top">收货信息</div>
                    <a href="<c:url value='/add_adress?orderCodes=${WeixinOrderCode}'/>"><div class="main1_bottom">
                    <div class="main1_bottom_left"><img src="../images/index/jiahao1.png">
                    </div><div class="main1_bottom_right">新增地址</div></div></a>
                    <input type="hidden" id="exitaddress" value="0">
          </div>
  </c:if>        
         
      
          <div class="main2">
                    <div class="main2_top">支付方式</div>
                    <div class="main2_bottom">
                   
					<div class="main2_bottom_left" onclick="setChecked('money1');">
                               <div class="main2_bottom_left_1"><input id="money1" type="radio" name="money" value="" checked="checked" ></div>
                               <div class="main2_bottom_left_2"><img src="../images/index/weixin.png"></div>
                               <div class="main2_bottom_left_3">&nbsp;&nbsp;&nbsp;微信支付</div>
                               
                    </div><div  style="clear:both;"></div>
                    <div class="main2_bottom_center"onclick="setChecked('money2');">
                               <div class="main2_bottom_center_1"><input id="money2" type="radio"  name="money" value="" ></div>
                               <div class="main2_bottom_center_2"><img src="../images/index/huodao.png"></div>
                               <div class="main2_bottom_center_3">&nbsp;&nbsp;&nbsp;&nbsp;货到付款</div>
                    </div><div  style="clear:both;"></div>
                    <div <c:if test="${split==5}">style="display:none"</c:if>class="main2_bottom_right"onclick="setChecked('money3');">
                               <div class="main2_bottom_right_1"><input id="money3" type="radio" name="money" value=""></div>
                               <div class="main2_bottom_right_2"><img src="../images/index/caika.png"></div>
                               <div class="main2_bottom_right_3">&nbsp;&nbsp;&nbsp;&nbsp;骑士卡支付</div>
                                
                    </div><div   style="clear:both;"></div>
                    <div <c:if test="${split==5}">style="display:none"</c:if> <c:if test="${hongbao!=null}">style="display:none" </c:if>class="main2_bottom_down"onclick="setChecked('money4');">
                               <div class="main2_bottom_down_1"><input id="money4" type="radio" name="money" value=""></div>
                               <div class="main2_bottom_down_2"><img src="../images/index/hongbao.png"></div>
                               <div class="main2_bottom_down_3">&nbsp;&nbsp;&nbsp;&nbsp;红包卡支付</div>
                               <div class="main2_bottom_down_4" id="hongbaoId" name="hongbaoId" style="color: #112"></div>
                               
                                
                    </div>
                    <div <c:if test="${split!=5}">style="display:none"</c:if>class="main2_bottom_right">
                    			<div class="main2_bottom_right_1"><input id="money3" type="radio" name="money" value="" disabled></div>
                               <div class="main2_bottom_right_2"><img src="../images/index/caika.png"></div>
                               <div class="main2_bottom_right_3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(不支持此支付方式)</div>
                                
                    </div><div  style="clear:both;"></div>
                    
                    <div <c:if test="${split!=5}">style="display:none"</c:if>class="main2_bottom_down">
                    			<div class="main2_bottom_down_1"><input id="money4" type="radio" name="money" value="" disabled></div>
                               <div class="main2_bottom_down_2"><img src="../images/index/hongbao.png"></div>
                               <div class="main2_bottom_down_3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(不支持此支付方式)</div>
                                
                    </div><div  style="clear:both;"></div>
                    </div>
          </div>
          
           <div class="main3">
                    <div class="main3_top">配送时间</div>
                    <div class="main3_center">
						<div class="main3_center_left">配送日期</div>
						<div class="main3_center_right"><input id="d313" class="Wdate" type="text" name="riqi" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="dateChange()"/></div>
					</div>
		            <div class="main3_center" style="border:0px;">
						<div class="main3_center_left">配送时间段</div>
						<div class="main3_center_right">
							<select class="select_add" onchange="selectChange()" id="selSpan">
								<option value="0" disabled="">--请选择收货时间段--</option>
								<option value="1">9:00-11:30</option>
								<option value="2">14:00-18:00</option>
								<option value="3">18:00-20:00</option>
								<option value="4" selected = "selected">任意时间</option>
							</select>
							<input id="input_shijian" type="hidden" name="shijian" value="4"> 
						</div>
					</div>
                    
          </div>
          
          
          <div class="main3">
                    <div class="main3_top">费用详情</div>
                    <div class="main3_center">
                              <div class="main3_center_left">商品金额</div>
                              <div class="main3_center_right"><c:if test="${hongbao==null}">￥${sumPay }</c:if><c:if test="${hongbao!=null}">￥${sumPay }(红包折后价) </c:if></div>
                              <input type="hidden" value="${sumPay }" name="paymoney">
                              <input type="hidden" value="${WeixinOrderCode }" name="WeixinOrderCode" id="WeixinOrderCode">
                              <input type="hidden" id="paytype" value="huodao" name="CarPayType">
                              <input type="hidden" value="${orderaddrs.OConsignee }" name="OConsignee">
                              <input type="hidden" value="${orderaddrs.OMbile }" name="OMbile">
                              <input type="hidden" value="${orderaddrs.Province }" name="Province">
                              <input type="hidden" value="${orderaddrs.City }" name="City">
                              <input type="hidden" value="${orderaddrs.Area }" name="Area">
                              <input type="hidden" value="${orderaddrs.Address }" name="Address">
                              <input type="hidden" value="${orderaddrs.OTelephone }" name="OTelephone">
                              
                               <input type="hidden" value="" id="out_trade_code" name="out_trade_code"/>
                               
                                <input type="hidden" value="${sessionScope.userinfo.WX_OPENID }" id="openidpay" name="openidpay"/>
                              
                              
                    </div>
                    <div class="main3_center" style="border:0px;">
                              <div class="main3_center_left">运费</div>
                              <div class="main3_center_right">￥0</div>
                    </div>
          </div>
         <!--  <a href="#"><div class="main4">
                    <div class="main4_left">配送计划</div>
                    <div class="main4_right"><img src="images/index/you.png"></div>
          </div></a> -->
           <a href="<c:url value="/orderdetail?code=${WeixinOrderCode }"/>"><div class="main4">
                    <div class="main4_left">查看清单</div>
                    <div class="main4_right"><img src="../images/index/you.png"></div>
          </div></a>
         
         <!--  <div class="main4" style="border:0px;">
                    <input type="text" value="请输入发票抬头">
          </div>
          <div class="main4" style="border:0px; margin-top:4%;">
                    <input type="text" value="备注">
          </div> -->
           <INPUT value=<%=ServerConfig.getAPP_ID() %> name="appId" id="1" type="hidden">
        <INPUT value=<%=ServerConfig.getPartnerId() %> name="partnerId" id="2" type="hidden">
        <INPUT value="${sumPays }" name="totalFee" id="3" type="hidden">
        <INPUT value="京华骑士" name="body" id="4" type="hidden">
</div>
<div class="bottom" id="otherbottom" style="display: none;">
          <div class="bottom_left">总金额：<span class="hong">￥${sumPay }</span></div>
          <a href="javascript:void(0)" onclick="doSubmit()"><div class="bottom_right">提交</div></a>
</div>


 <div class="bottom" id="weixinpaybottom">
          <div class="bottom_left">总金额：<span class="hong">￥${sumPay }</span></div>
          <a id="getBrandWCPayRequest" href="javascript:void(0);"><div class="bottom_right">提交</div></a>
                  
</div> 



</form> 
</body>
</html>

