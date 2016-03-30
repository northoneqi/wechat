<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!doctype html>
<html>

  
<head>
<meta charset="utf-8">
<meta http-equiv="Expires" CONTENT="-1000">        
<meta http-equiv="Cache-Control" CONTENT="no-cache">        
<meta http-equiv="Pragma" CONTENT="no-cache">  
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>购物车</title>
<link type="text/css" rel="stylesheet" href="css/ordercart/shopping.css">
<link type="text/css" rel="stylesheet" href="css/ordercart/top_footer.css">
<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
	<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
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
	
	<script type="text/javascript">
	
function numadd(SKU,Userid,openId,price){
	 var Num = document.getElementById("num"+SKU).value;
	 Num = Num-(-1);
	  $.ajax( {  
		  type:'POST', 
		  data:{UserId:Userid,sku:SKU,openid:openId,num:Num},   
		  url:'/lime/ordercartadd',  
		  dataType:"json",
	      success:function(data) {
	    	 
	    	  document.getElementById("num"+SKU).value=Num;
		    	 document.getElementById("xiaoji"+SKU).innerHTML=parseFloat(Num)*parseFloat(price*1000000)/1000000;
		    	 document.getElementById("zongjine").innerHTML = data.zhongjia;
		    	 document.getElementById("jiesheng").innerHTML =data.zhongjiam;
		    	
	       },    
	       error : function(date) {   
	    	   sAlert(msg);
	       }    
	  });  
	  return false;
	  };

	  
	  function numcut(SKU,Userid,openId,price){
			 var Num = document.getElementById("num"+SKU).value-1;
				if (parseFloat(Num)<=0) {
					
				}else{
			  $.ajax( {  
				  type:'POST', 
				  data:{UserId:Userid,sku:SKU,openid:openId,num:Num},   
				  url:'/lime/ordercartadd', 
				  dataType:"json",
			      success:function(data) {
			    	 document.getElementById("num"+SKU).value=Num;
			    	 document.getElementById("xiaoji"+SKU).innerHTML=parseFloat(Num)*parseFloat(price*1000000)/1000000;
			    	 document.getElementById("zongjine").innerHTML = data.zhongjia;
			    	 document.getElementById("jiesheng").innerHTML =data.zhongjiam;
			    	 
			    	
			       },    
			       error : function(date) {   
			    	   sAlert(msg);
			       }    
			  });  
			  return false;
				}
				};

			  
			  function cutmoney(SKU){
				  var test = document.getElementById("checkbox"+SKU).checked ;
				
				  if(test){
					  document.getElementById("zongjia").innerHTML = document.getElementById("zongjia").innerHTML-(-document.getElementById("xiaoji"+SKU).innerHTML);
					  document.getElementById("zongjine").innerHTML = document.getElementById("zongjia").innerHTML;
					 
				  }else{
					  document.getElementById("zongjia").innerHTML = document.getElementById("zongjia").innerHTML-document.getElementById("xiaoji"+SKU).innerHTML;
					  document.getElementById("zongjine").innerHTML = document.getElementById("zongjia").innerHTML;
					  
				  }
					  };

					  
					  
					  
			  function check(){
				 var test = document.getElementById("checkmianbox").checked;
				 var arrSon = document.getElementsByName("son");
				 
				 var cbAll = document.getElementsByName("father");
				
				 var tempState=cbAll.checked;
				 for(var i=0;i<arrSon.length;i++) {
				 if(arrSon[i].checked!=tempState){
					 
				            arrSon[i].click();
				 }
			     }
				 sAlert(test);
				 if(test){
					 
					 document.getElementById("zongjine").innerHTML = document.getElementById("zongjia").innerHTML;
				 }else{
					 
					 document.getElementById("zongjine").innerHTML = 0.00;
				 }
							  };

	
	
	
</script>
<script> 
window.onload=function(){ 
	
	  $.ajax( {  
		  type:'GET', 
		  data:{UserId:'1'},   
		  url:'/lime/ordercartcheck?'+new Date(), 
	      success:function(data) {
	    	if(data=="1"){
	    		window.location.reload(true);
	    	}
	    	 
	    	
	       },    
	       error : function(date) {   
	    	   sAlert(msg);
	       }    
	  });  
	  return false;


} 
</script>
<script>
function downGoods(){
	var downCart=$("#downgood").val();
	//alert(downCart);
	if(downCart!=""){
		//alert(123123123123);
		sAlert("您的购物车中包含下架商品“"+downCart+"”,请删除后再结算！");
	} else {
		window.location.href ="/lime/toOrder";
		
	}
}
</script>
</head>


<body>
<%  response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0);
%>

<input type="hidden" id="downgood" value="${downGoodName}">
<c:forEach items="${goodCart}" var="goodCart">
<div class="main">

          <div class="main_left" style= "height:100px;width:100px;margin-top:10px">
                    <div class="main_left_top"></div>
                    <div class="main_left_bottom" style= "margin-top:-10px"><a  href="<c:url value="/goodsku?proskuid=${goodCart.sku }"/>"><img src="http://images.yijia360.com${goodCart.ListPagePic}"></a></div>
          </div>
          <div class="main_center"  >
                    <div class="main_center_top">${goodCart.ProName}</div>
                    <div class="main_center_center"></div>
                    <div  style="clear:both;"></div>
                    <div class="main_center_bottom">
                              <div class="main_center_bottom_left"  style= "height:25px;width:25px;margin-top:12px;"><span><a onclick="numcut('${goodCart.sku}','${goodCart.UserId}','${goodCart.openId}','${goodCart.sellPrice}');"><img src="images/ordercart/jianhao.png" ></a></span></div>
                              <div class="main_center_bottom_center"><input style= "height:19px;width:20px;margin-top:12px;"  type="text" id = "num${goodCart.sku}" value='${goodCart.BuyNum}'readonly="readonly"></div>
                              <div class="main_center_bottom_right"style= "height:25px;width:25px;margin-top:12px;"><span ><a  onclick="numadd('${goodCart.sku}','${goodCart.UserId}','${goodCart.openId}','${goodCart.sellPrice}');"><img src="images/ordercart/jiahao.png"></a></span></div>
                    </div>
          </div>
          
          <div class="main_right">
                    <div class="main_right_top">&nbsp骑士价：${goodCart.sellPrice} <br><span style="color:#999; font-size:12px; text-decoration:line-through; text-align:right;">￥${goodCart.marketprice}</span></div>
                    <DIV class="main_right_center"><a href="/lime/ordercartdelete?UserId=${goodCart.UserId}&openId=${goodCart.openId}&sku=${goodCart.sku}"><img src="images/ordercart/cha.png"></a></DIV>
                    <div class="main_right_bottom" >小计<div class="heji"  id="xiaoji${goodCart.sku}"> <fmt:formatNumber  type="number" value="${goodCart.sellPrice*goodCart.BuyNum} " maxFractionDigits="2" /></div></div>
                   
          
          </div>
          <div  style="clear:both;"></div>

      
</div>
  </c:forEach>  
<div  style="clear:both;"></div>


<div class="main1">
          <div class="main1_left"></div>
          <div class="main1_right"></div>
          <div  style="clear:both;"></div>
</div>
<br/>
<div  style="clear:both;"></div>
<div class="bottom">
          <div class="bottom_left" >总金额：<span class="hong" id ="zongjine">${zongjia}</span><br><span style="color:#999; font-size:14px;">为您节省：<span id="jiesheng">${jiesheng}</span></span></div>
        
          <a onclick="downGoods()" ><div class=" bottom_right">结算</div></a>
</div>
</body>
</html>
