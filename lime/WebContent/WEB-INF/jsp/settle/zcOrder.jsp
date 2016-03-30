<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.qishi.util.ServerConfig" %> 
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>众筹支付</title> 
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
  <link rel="stylesheet" href="../css/prize/award.css">
<style type="text/css">
	html,body{margin:0;padding:0; }
	ol,ul{list-style:none}:focus{outline:0}
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
		text-align:left;
		background-color:#fff5bd;
		width:80%;
		margin:0 auto;
		border-radius:15px;
		padding: 20px 20px 20px 20px;
	}
	.line2 .mb .nr{
		
		color: #000;
		font-weight: bold;
	}
  </style>
<script  type="text/javascript" src="<%=request.getContextPath() %>/js/shoppingcart/cart.js"></script>
<script type="text/javascript" src="../js/jquery-1.3.2.js"></script>
<script type="text/javascript">

var format = function(time, format){
 	time += 180000;
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    });
};

function imgClick(){
	var flag = false;
	
	if($("#name").val()=="" || "您的姓名"==$("#name").val()){
		sAlert("请输入姓名");
		return false;
	}
	
	$.ajax( {  
		 
		  type:'GET', 
		  data:{zccode:$("#zccode").val(),openId:$("#openidpay").val(),state:$("#state").val()},
		  url:'updIsPayMent',
		  dataType:"json",
		  async:false,
	      success:function(data) {
	    	 if(data==1){
	    		 var time_e = format(new Date().getTime(), 'yyyyMMddHHmmss');
	    		 $("#time_expire").val(time_e);
	    		 flag=true;
	    	 }else if(data == 0){
	    		 sAlert("他人正在支付中或该众筹已完成");
	    		 flag=false;
	    	 }else if(data == 3){
	    		 sAlert("本人不能参与众筹支付");
	    		 flag=false;
	    	 }
	       },    
	       error : function(data) {   
	    	   sAlert("请稍后再试");
	    	  flag=false;
	       }
	  }); 
	return flag;
}; 
$(function(){
	
	
	
	$("#name").click(function(){
		$("#name").val("");
	});
	$("#bgImage").attr("height", $(window).height());
	$("#bgImage").attr("width", $(window).width());
	var i = 0;
	$(window).resize(function(){
		$("#bgImage").attr("height", $(window).height());
		$("#bgImage").attr("width", $(window).width());
		$("#bgImage").removeAttr("style");
		if(i%2==0){
			$("#imgDIV").attr("style", "z-index: 1000");
			$("#payDIV").attr("style", "display:none");
			$("#logoDIV").attr("style", "display:none");
			$("#gzDIV").attr("style", "display:none");
		}else{
			$("#imgDIV").attr("style", "display:block;position:absolute; bottom:35%;left:30%; z-index: 1000");
			$("#logoDIV").attr("style", "display:block;position:absolute;bottom: 1px;right:0px;text-align: right;z-index: 1000");
			$("#payDIV").attr("style", "display:block;position:absolute;bottom: 10%;left:10%; z-index: 1001");
			$("#gzDIV").attr("style", "position:absolute;bottom: 2%;right:40%;text-align: right; z-index: 1001");
		}
		i++;
	});
	$("#name").focus(function(){
		$("#imgDIV").attr("style", "display:none");
		$("#logoDIV").attr("style", "display:none");
	});
	$("#name").blur(function(){
		$("#imgDIV").attr("style", "display:block;position:absolute; bottom:35%;left:30%; z-index: 1000");
		$("#logoDIV").attr("style", "display:block;position:absolute;bottom: 1px;right:0px;text-align: right;z-index: 1000");
	}); 
});
</script>

       <script language="javascript" src="<c:url value="/js/wxpayjs/lazyloadv3.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/md5.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/sha1.js"/>"></script>
<script type="text/javascript">

			function payone(){
				 sAlert("您已支付");
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
            	var zccode = document.form1.zccode.value;
            	var name = document.getElementById("name").value; 
            	var openidpay = document.form1.openidpay.value;
            	var paramurladd ="?name="+encodeURI(encodeURI(name))+"&openidpay="+openidpay+"&zccode="+zccode;
                var banktype = "WX";
                var body = "京华骑士";//商品名称信息，这里由测试网页填入。
                var fee_type = "1";//费用类型，这里1为默认的人民币
                var input_charset = "GBK";//字符集，这里将统一使用GBK
                var notify_url = "http://weixin.yijia360.com/lime/settle/zcSuccess"+paramurladd;  //支付成功后将通知该地址
                var out_trade_no = ""+getANumber();//订单号，商户需要保证该字段对于本商户的唯一性
                var partner = getPartnerId();//测试商户号
                var spbill_create_ip = "127.0.0.1";//用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
                var total_fee = document.form1.totalFee.value;//总金额。
               	var time_expire = document.form1.time_expire.value;//订单失效时间
                var partnerKey = getPartnerKey();//这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。
                
                
              //首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
                var signString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&time_expire="+time_expire+"&total_fee="+total_fee+"&key="+partnerKey;
                
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
                
                time_expire = encodeURIComponent(time_expire);
                //然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
                var completeString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&time_expire="+time_expire+"&total_fee="+total_fee;
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
                                    		
                                    		  function(e){  if(imgClick()){
                                    			  
                                                                             WeixinJSBridge.invoke('getBrandWCPayRequest',{
                                                                            	 "appId" : getAppId(), //公众号名称，由商户传入
                                                                                 "timeStamp" : getTimeStamp(), //时间戳
                                                                                 "nonceStr" : getNonceStr(), //随机串
                                                                                 "package" : getPackage(),//扩展包
                                                                                 "signType" : getSignType(), //微信签名方式:1.sha1
                                                                                 "paySign" : getSign() //微信签名
                                                                                                   },function(res){
                                                                                                   if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                                                                                               	   window.location.href="/lime/toPage";
                                                                                               	  
                                                                                                  }
                                                                                                   if(res.err_msg == "get_brand_wcpay_request:fail" ||
                                                                                                		   res.err_msg == "get_brand_wcpay_request:cancel"  ) {
                                                                                                   	   window.location.href="/lime/settle/fail";
                                                                                                      }
                                                                                              	  // window.location.href="http://weixin.yijia360.com/lime/index";
                                                                                                   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                                                                                                   //因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
                                                                                                   }); 
                                    		  }
                                    		  
                                                                             });
                                      
                                    		  
            																		
                                      
                                      WeixinJSBridge.log('yo~ ready.');
                                      
                                      }, true);
            
          /*   if(jQuery){
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
            } */
            
           
</script>
</head>
	
<body style="width: 100%;height: 100%;">
 <div style="height:100%;">
 <img class="img" alt="" src="../images/zc/bj1.png" id="bgImage" >
	<div class="line2" style="position:absolute; top:5%;left:9%;">
	<form action="" id="form1" name="form1" method="post">
		<div class="line1" style="z-index: 1002">
		<div>
		<input type="text" name="name"  id="name" style="height: 25px; background-color: #57331b" value="您的姓名">
		</div>
	</div><br/>
		<div class="mb" style="z-index: 10001">
		<c:if test="${state eq 1}">
		<span class="nr">
				您的好友“${zName }”给您发来的众筹链接，需要您帮 “${zName }”进行众筹<br/>
				<span>您需要支付<span style="color:#cc0000;">${money}</span>元</span>
			</span></c:if>
			</div>
			<div>
		<br/>
		<input value="${state}" name="state" id="state" type="hidden">
		<INPUT value=<%=ServerConfig.getAPP_ID() %> name="appId" id="1" type="hidden">
        <INPUT value=<%=ServerConfig.getPartnerId() %> name="partnerId" id="2" type="hidden">
		<input value="${moneyFee}" id="3" name="totalFee" type="hidden" >
		 <INPUT value="京华骑士" name="body" id="4" type="hidden">
		 <input value="" name="time_expire" id="time_expire" type="hidden">
		 <input value="${zName}" name="zName" id="zName" type="hidden">
		<input type="hidden" value="${zccode}" name="zccode" id="zccode"/>
        <input type="hidden" value="${openId}" id="openidpay" name="openidpay"/>
		</div>
		<br/>
		</form>
		</div>
				<div id="imgDIV" style="position:absolute; bottom:35%;left:35%; z-index: 1000">
			<c:forEach items="${tuijian }" var="tuijian" begin="0" end="1" >
<td class="main_2_a">
<a href="http://<%=ServerConfig.getBaseUrl()%>/ManyPlan/goodsku?proSkuId=${tuijian.SKU}">
<img src="${tuijian.GOOD_PNG}"  style="width: 60%"></a> 
<%--  <br>${fn:substring(tuijian.GOOD_NAME,0,8)}<br/>${fn:substring(tuijian.GOOD_NAME,8,16)}
												<c:if test="${fn:length(tuijian.GOOD_NAME)>16 }">...</c:if>
												<span>骑士价${orderPrice }</span>元<br> --%>
</td>
</c:forEach>

</div>
		</div>
		<div id="payDIV" style="position:absolute;bottom: 10%;left:10%; z-index: 1001">
		<c:if test="${payone==null}"><a id="getBrandWCPayRequest" href="javascript:void(0);">
			<img src="../images/zc/wxzf.png"/></a>
		</c:if>
		<c:if test="${payone!=null}">
		您已支付过
		</c:if>
		<a href="http://<%=ServerConfig.getBaseUrl()%>/ManyPlan/ManyPlan"><img src="../images/zc/wycj.png"/></a>
		</div>
 <a href="http://<%=ServerConfig.getBaseUrl()%>/ManyPlan/index?ActivityId=1" style="font-size: 20px;">
<div id="gzDIV" style="position:absolute;bottom: 2%;right:40%;text-align: right; z-index: 1001" >
活动规则
</div></a>
		
<div id="logoDIV" style="position:absolute;bottom: 1px;right:0px;text-align: right;z-index: 1000">
 <img src="../images/zc/logo.png" width="80%;"/> 
</div>
</body>
</html>

