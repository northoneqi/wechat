<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.qishi.util.ServerConfig" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">

 <meta http-equiv="Expires" CONTENT="0">        
 <meta http-equiv="Cache-Control" CONTENT="no-cache">        
 <meta http-equiv="Pragma" CONTENT="no-cache">     
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>结算</title>
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
         
           
          <!-- 支付 -->
<script type="text/javascript">
function setChecked (name) {

	//var isChecked = document.getElementById(name).checked;
    //isChecked = isChecked ? false : true;
    //document.getElementById(name).checked = isChecked;
   document.getElementById("paytype").value='weixin';
   
}

function selectChange(){
	//判断配送时间段
	var timeSen = $("#selSpan option:selected").val();
	$("#input_shijian").val(timeSen);
}

function check() {
	
	
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
   
    
    msgBox.style.marginTop = -75+clientHeight+"px";
    msgBox.style.display="block"; 
    document.getElementById("divMessageBoxBG").style.display="none"; 
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

    form1.submit();
} 

function Hide2() 
{ 
    document.getElementById("divMessageBoxBody").style.display="none"; 
    
    document.getElementById("divMessageBoxBG").style.display="none"; 
    
    return false;
} 





function doSubmit() {
	if(check()){
		var check2 = document.getElementById("money3").checked;
		 var checkmoney = ${Balancedou<sumPay};
		 if(check2&&checkmoney){
			 var daomoney = ${fn:substring(sumPay-Balancedou,0,12)};
			 var sumPay = ${sumPay};
			 var Balancedou = ${Balancedou};
			 ConfirmBox("骑士卡总金额为:"+Balancedou+"元，订单总金额为:"+sumPay+"元，需到付:"+daomoney+"元，是否继续支付");
		 }else{
			 form1.submit();
		 }
	}
	
}







</script>


<script src="<c:url value="/js/shoppingcart/alert.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
/**
 * 
 */
			function sAlert(str){
			var msgw,msgh,bordercolor;
			msgw=400;//提示窗口的宽度
			msgh=50;//提示窗口的高度
			titleheight=25 //提示窗口标题高度
			bordercolor="#efefef";//提示窗口的边框颜色
			titlecolor="#99CCFF";//提示窗口的标题颜色
			
			
			var clientHeight=0;
			if(document.documentElement&&document.documentElement.scrollTop)
		    {
				clientHeight=document.documentElement.scrollTop;
		    }
		    else if(document.body)
		    {
		    	clientHeight=document.body.scrollTop;
		    }
		   
//			var sWidth,sHeight;
//			sWidth=document.body.offsetWidth;
//			sHeight=screen.height;
//
//			var bgObj=document.createElement("div");
//			bgObj.setAttribute('id','bgDiv');
//			bgObj.style.position="absolute";
//			bgObj.style.top="0";
//			bgObj.style.background="#777";
//			bgObj.style.filter="progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
//			bgObj.style.opacity="0.6";
//			bgObj.style.left="0";
//		    msgObj.style.marginTop = -75+clientHeight+"px";
//            msgObj.style.width = "100%";
//            msgObj.style.height =msgh + "px";
//			bgObj.style.zIndex = "10000";
//			document.body.appendChild(bgObj);
			
			var msgObj=document.createElement("div")
			msgObj.setAttribute("id","msgDiv");
			msgObj.setAttribute("align","center");
			msgObj.style.background="#efefef";
			msgObj.style.border="1px solid " + bordercolor;
	    	msgObj.style.position = "absolute";
            msgObj.style.left = "25%";
            msgObj.style.top = "50%";
            msgObj.style.font="12px/1.6em Verdana, Geneva, Arial, Helvetica, sans-serif";
            //msgObj.style.marginLeft = "-225px" ;
            msgObj.style.marginTop = -75+clientHeight+"px";
            msgObj.style.width = "50%";
            msgObj.style.height =msgh + "px";
            msgObj.style.textAlign = "center";
            msgObj.style.lineHeight ="25px";
            msgObj.style.zIndex = "10001";
   
//		   var title=document.createElement("h4");
//		   title.setAttribute("id","msgTitle");
//		   title.setAttribute("align","right");
//		   title.style.margin="0";
//		   title.style.padding="3px";
//		   title.style.background=bordercolor;
//		   title.style.filter="progid:DXImageTransform.Microsoft.Alpha(startX=20, startY=20, finishX=100, finishY=100,style=1,opacity=75,finishOpacity=100);";
//		   title.style.opacity="0.75";
//		   title.style.border="1px solid " + bordercolor;
//		   title.style.height="18px";
//		   title.style.font="12px Verdana, Geneva, Arial, Helvetica, sans-serif";
//		   title.style.color="white";
//		   title.style.cursor="pointer";
//		   title.innerHTML="关闭";
//		   title.onclick=function(){
//		        document.body.removeChild(bgObj);
//                document.getElementById("msgDiv").removeChild(title);
//                document.body.removeChild(msgObj);
//                }
		   document.body.appendChild(msgObj);
//		   document.getElementById("msgDiv").appendChild(title);
		   var txt=document.createElement("p");
		   txt.style.margin="1em 0"
		   txt.setAttribute("id","msgTxt");
		   txt.innerHTML=str;
           document.getElementById("msgDiv").appendChild(txt);
           setTimeout(function(){
        	   document.body.removeChild(msgObj);
           }, 700);
          }
//辅助函数
            function Trim(str,is_global)
            {
                var result;
                result = str.replace(/(^\s+)|(\s+$)/g,"");
                if(is_global.toLowerCase()=="g") result = result.replace(/\s/g,"");
                return result;
            }
            function clearBr(key)
            {
                key = Trim(key,"g");
                key = key.replace(/<\/?.+?>/g,"");
                key = key.replace(/[\r\n]/g, "");
                return key;
            }
            
            //获取随机数
            function getANumber()
            {
            	var encryptret="";
                var date = new Date();
                var times1970 = date.getTime();
                var times = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
                var encrypt = times * times1970;
                if(arguments.length == 1){
                	encryptret=arguments[0] + encrypt;
                    return encryptret;
                }else{
                	encryptret=encrypt;
                    return encryptret;
                }
              
               
            }
            //获去提交的订单号
            function getout_trade_no(){
            var out_trade_no =	$("#WeixinOrderCode").val();
           
            return out_trade_no;
            }
            
            //以下是package组包过程：
            
            var oldPackageString;//记住package，方便最后进行整体签名时取用
            
            function getPartnerId()
            {
                return document.form1.partnerId.value;
            }
            
            function getPartnerKey()
            {
                return "2c86b292a95f610d914e51b608075c07";
            }
            
            
            
            function getPackage()
            {
            	var riqi = document.form1.riqi.value;//配货日期
            	var shijian = document.form1.shijian.value;//配货时间段
            	var paramurladd ="?riqi="+riqi+"&shijian="+shijian;
                var banktype = "WX";
                var body = "京华骑士";//商品名称信息，这里由测试网页填入。
                var fee_type = "1";//费用类型，这里1为默认的人民币
                var input_charset = "GBK";//字符集，这里将统一使用GBK
                var notify_url = "http://weixin.yijia360.com/lime/settle/success";//支付成功后将通知该地址
                var out_trade_no = ""+getANumber();//订单号，商户需要保证该字段对于本商户的唯一性
                var partner = getPartnerId();//测试商户号
                var spbill_create_ip = "127.0.0.1";//用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
                var total_fee = 1;//总金额。
               
                var partnerKey = getPartnerKey();//这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。
                
              //首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
                var signString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&key="+partnerKey;
                
                var md5SignValue =  ("" + CryptoJS.MD5(signString)).toUpperCase();
              //然后第二步，对每个参数进行url转码，如果您的程序是用js，那么需要使用encodeURIComponent函数进行编码。
                
                
                banktype = encodeURIComponent(banktype);
                body=encodeURIComponent(body);
                fee_type=encodeURIComponent(fee_type);
                input_charset = encodeURIComponent(input_charset);
                notify_url = encodeURIComponent(notify_url);
                out_trade_no = encodeURIComponent(out_trade_no);
                partner = encodeURIComponent(partner);
                spbill_create_ip = encodeURIComponent(spbill_create_ip);
                total_fee = encodeURIComponent(total_fee);
                
                //然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
                var completeString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee;
                completeString = completeString + "&sign="+md5SignValue;
                
                
                oldPackageString = completeString;//记住package，方便最后进行整体签名时取用
                
                return completeString;
            }
            
            
//下面是app进行签名的操作：
            
            var oldTimeStamp ;//记住timestamp，避免签名时的timestamp与传入的timestamp时不一致
            var oldNonceStr ; //记住nonceStr,避免签名时的nonceStr与传入的nonceStr不一致
            
            function getAppId()
            {
                return document.form1.appId.value;
            }
            
            function getAppKey()
            {
                return "Md6BO5atVwP2jl5aDAm9PCYiM7c2OhcFxkmrt5XYbQ4xgcLZQaHKRI7ITnhNHwvjWUNszOuiJASzmGBKVlAP0JA60VVV22h1t948wCsifVpGE7ZFu0xbVIR0Im8MVrSZ";
            }
            
            
            
            function getTimeStamp()
            {
                var timestamp=new Date().getTime();
                var timestampstring = timestamp.toString();//һ��Ҫת���ַ�
                oldTimeStamp = timestampstring;
                return timestampstring;
            }
            
            function getNonceStr()
            {
                var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
                var maxPos = $chars.length;
                var noceStr = "";
                for (i = 0; i < 32; i++) {
                    noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
                }
                oldNonceStr = noceStr;
                return noceStr;
            }
            
            function getSignType()
            {
                return "SHA1";
            }
            
            function getSign()
            {
                var app_id = getAppId().toString();
                var app_key = getAppKey().toString();
                var nonce_str = oldNonceStr;
                var package_string = oldPackageString;
                var time_stamp = oldTimeStamp;
                //第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
                var keyvaluestring = "appid="+app_id+"&appkey="+app_key+"&noncestr="+nonce_str+"&package="+package_string+"&timestamp="+time_stamp;
                sign = CryptoJS.SHA1(keyvaluestring).toString();
                return sign;
            }
</script>

<script type="text/javascript">
/**
 * 
 */

 				function auto_remove(img){
                div=img.parentNode.parentNode;div.parentNode.removeChild(div);
                img.onerror="";
                return true;
            }
            
            function changefont(fontsize){
                if(fontsize < 1 || fontsize > 4)return;
                $('#content').removeClass().addClass('fontSize' + fontsize);
            }
            
            
            
         // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            	
            	 //公众号支付
                                      jQuery('a#getBrandWCPayRequest').click(
                                    		
                                    		  function(e){  
                                                                             WeixinJSBridge.invoke('getBrandWCPayRequest',{
                                                                            	 "appId" : getAppId(), //公众号名称，由商户传入
                                                                                 "timeStamp" : getTimeStamp(), //时间戳
                                                                                 "nonceStr" : getNonceStr(), //随机串
                                                                                 "package" : getPackage(),//扩展包
                                                                                 "signType" : getSignType(), //微信签名方式:1.sha1
                                                                                 "paySign" : getSign() //微信签名
                                                                                                   },function(res){
                                                                                                   if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                                                                                               	   window.location.href="/lime/index";
                                                                                               	  
                                                                                                  }
                                                                                              	  // window.location.href="http://weixin.yijia360.com/lime/index";
                                                                                                   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                                                                                                   //因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
                                                                                                   }); 
                                    		 
                                                                             
                                                                             });
                                      
                                      
            																		
                                      
                                      WeixinJSBridge.log('yo~ ready.');
                                      
                                      }, true);
            
            if(jQuery){
                jQuery(function(){
                       
                       var width = jQuery('body').width() * 0.87;
                       jQuery('img').error(function(){
                                           var self = jQuery(this);
                                           var org = self.attr('data-original1');
                                           self.attr("src", org);
                                           self.error(function(){
                                                      auto_remove(this);
                                                      });
                                           });
                       jQuery('img').each(function(){
                                          var self = jQuery(this);
                                          var w = self.css('width');
                                          var h = self.css('height');
                                          w = w.replace('px', '');
                                          h = h.replace('px', '');
                                          if(w <= width){
                                          return;
                                          }
                                          var new_w = width;
                                          var new_h = Math.round(h * width / w);
                                          self.css({'width' : new_w + 'px', 'height' : new_h + 'px'});
                                          self.parents('div.pic').css({'width' : new_w + 'px', 'height' : new_h + 'px'});
                                          });
                       });
            }
</script>
	
</head>
	
<body>
  <form action="<c:url value="/paysubmit"/>" method="post" id="form1" onsubmit="return check();"  name="form1"> 
<div class="main">

  <div class="main1">
                    <div class="main1_top">收货信息</div>
                    <a href="#"><div class="main1_bottom"><span >&nbsp;&nbsp;&nbsp;&nbsp;张三&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;13000000000</span><br>
<span>&nbsp;&nbsp;&nbsp;&nbsp;黑龙江_哈尔滨_道里区气象小区</span><br>
<span>&nbsp;&nbsp;&nbsp;&nbsp;工作日节假日均可送</span></div></a>
          </div>   
         
      
          <div class="main2">
                    <div class="main2_top">支付方式</div>
                    <div class="main2_bottom">
                   
					<div class="main2_bottom_left" onclick="setChecked('money1');">
                               <div class="main2_bottom_left_1"><input id="money1" type="radio" name="money" value=""  checked="checked"></div>
                               <div class="main2_bottom_left_2"><img src="../images/index/weixin.png"></div>
                               <div class="main2_bottom_left_3">&nbsp;&nbsp;&nbsp;<font color='red'>微信支付</font></div>
                               
                    </div><div  style="clear:both;"></div>
                    <div class="main2_bottom_center"onclick="setChecked('money2');">
                               <div class="main2_bottom_center_1"><input id="money2" type="radio"  name="money" value="" disabled="disabled"></div>
                               <div class="main2_bottom_center_2"><img src="../images/index/huodao.png"></div>
                               <div class="main2_bottom_center_3">&nbsp;&nbsp;&nbsp;&nbsp;货到付款</div>
                    </div><div  style="clear:both;"></div>
                    <div <c:if test="${split==5}">style="display:none"</c:if>class="main2_bottom_right"onclick="setChecked('money3');">
                               <div class="main2_bottom_right_1"><input id="money3" type="radio" name="money" value="" disabled="disabled"></div>
                               <div class="main2_bottom_right_2"><img src="../images/index/caika.png"></div>
                               <div class="main2_bottom_right_3">&nbsp;&nbsp;&nbsp;&nbsp;骑士卡支付</div>
                                
                    </div><div  style="clear:both;"></div>
                    <div <c:if test="${split!=5}">style="display:none"</c:if>class="main2_bottom_right">
                    			<div class="main2_bottom_right_1"><input id="money3" type="radio" name="money" value="" disabled="disabled"></div>
                               <div class="main2_bottom_right_2"><img src="../images/index/caika.png"></div>
                               <div class="main2_bottom_right_3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(不支持此支付方式)</div>
                                
                    </div><div  style="clear:both;"></div>
                    </div>
          </div>
          
           <div class="main3">
                    <div class="main3_top">配送时间</div>
                    <div class="main3_center">
						<div class="main3_center_left">配送日期</div>
						<div class="main3_center_right"><input id="d313" class="Wdate" type="text" name="riqi" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="dateChange()" value="2014-8-8"/></div>
					</div>
		            <div class="main3_center" style="border:0px;">
						<div class="main3_center_left">配送时间段</div>
						<div class="main3_center_right">
							<select class="select_add" onchange="selectChange()" id="selSpan">
								<option value="0">--请选择收货时间段--</option>
								<option value="1">9:00-11:30</option>
								<option value="2">14:00-18:00</option>
								<option value="3">18:00-20:00</option>
								<option value="4" selected="selected">任意时间</option>
							</select>
							<input id="input_shijian" type="hidden" name="shijian" value=""> 
						</div>
					</div>
                    
          </div>
          
          
          <div class="main3">
                    <div class="main3_top">费用详情</div>
                    <div class="main3_center">
                              <div class="main3_center_left">商品金额</div>
                              <div class="main3_center_right">￥0.01</div>
                             
                              
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
        <INPUT value="1218461501" name="partnerId" id="2" type="hidden">
        <INPUT value=1 name="totalFee" id="3" type="hidden">
        <INPUT value="京华骑士支付测试请求" name="body" id="4" type="hidden">
</div>
<div class="bottom" id="otherbottom" style="display: none;">
          <div class="bottom_left">总金额：<span class="hong">￥${sumPay }</span></div>
          <a href="javascript:void(0)" onclick="doSubmit()"><div class="bottom_right">提交</div></a>
</div>


<div class="bottom" id="weixinpaybottom" style="display: block;">
          <div class="bottom_left">总金额：<span class="hong">￥0.01</span></div>
          <a id="getBrandWCPayRequest" href="javascript:void(0);"><div class="bottom_right">微信支付</div></a>
                  
</div>

 
</form> 
</body>
</html>

