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
                $("#out_trade_code").val(encryptret);
               
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
            	var name = document.form1.name;//配货日期
            	name = encodeURI(encodeURI(name));
//            	var shijian = document.form1.shijian.value;//配货时间段
            	var openidpay = document.form1.openidpay.value;
            	var paramurladd ="?name="+name+"&openidpay="+openidpay;
                var banktype = "WX";
                var body = document.form1.body.value;//商品名称信息，这里由测试网页填入。
                var fee_type = "1";//费用类型，这里1为默认的人民币
                var input_charset = "GBK";//字符集，这里将统一使用GBK
                var notify_url = "http://weixin.yijia360.com/lime/settle/zcSuccess"+paramurladd;//支付成功后将通知该地址
                var out_trade_no = ""+getout_trade_no();//订单号，商户需要保证该字段对于本商户的唯一性
                var partner = getPartnerId();//测试商户号
                var spbill_create_ip = "127.0.0.1";//用户浏览器的ip，这个需要在前端获取。这里使用127.0.0.1测试值
                var total_fee = document.form1.totalFee.value;//总金额。
                var partnerKey = getPartnerKey();//这个值和以上其他值不一样是：签名需要它，而最后组成的传输字符串不能含有它。这个key是需要商户好好保存的。
                var time_expire = document.form1.time_expire.value;//订单失效时间
              //首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
                var signString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&key="+partnerKey+"&time_expire="+time_expire;
                
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
                var completeString = "bank_type="+banktype+"&body="+body+"&fee_type="+fee_type+"&input_charset="+input_charset+"&notify_url="+notify_url+"&out_trade_no="+out_trade_no+"&partner="+partner+"&spbill_create_ip="+spbill_create_ip+"&total_fee="+total_fee+"&time_expire="+time_expire;
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