<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>商品详细页</title>
<link type="text/css" rel="stylesheet" href="css/goodlist/detailed.css">
<link type="text/css" rel="stylesheet" href="css/index/top_footer.css">
	<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function jiabuynum(type){
	var buynum = $("#buynum").val();
	if(type=='jia'){
		
			$("#buynum").val(parseFloat(buynum)+1);
	
		
	}else if(type=='jian'){
		if (parseFloat(buynum)<=1) {
			
		}else{
		$("#buynum").val(parseFloat(buynum)-1);
		}
	}
	
}

function goPay(SellPrice){
	var SKU=document.getElementById("paymoney").value;
	var split=document.getElementById("split").value;
	  $.ajax( {  
		  type:'POST', 
		  data:{sku:SKU},   
		  url:'/lime/checkxiajia',  
	      success:function(data) {
	    	if(data==1){
	    		var buynum = $("#buynum").val();
	    		window.location.href = "/lime/goPay?SellPrice="+SellPrice+"&ProNum="+buynum+"&sku="+SKU+"&split="+split;
	    	}else{
	    		sAlert("商品已下架");
	    		window.location.href = "/lime/index";
	    	}
	       },    
	       error : function(data) {   
	    	   sAlert(data);
	       }    
	  });  
	

}
</script>
</head>

<body>
<!-- <div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/index/logo.png"></a>
          </div>
          <div class="header_right">
                    <div class="header_right_1"><a href="#"><img src="images/index/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="#"><img src="images/index/person.png"></a></div>
                    <div class="header_right_3"><a href="#"><img src="images/index/drive.png"></a></div>
          </div>
</div> -->
<c:import url="../public/top.jsp"></c:import>
<c:forEach items="${gooddet }" var="gooddet">
<div class="picture"><img src="http://images.yijia360.com${gooddet.ListPagePic}"></div>
<input type="hidden" value="${gooddet.OrderSplit}" id="split" >
<div class="main">
          <div class="main1">
                    <span><b>${gooddet.ProName }</b></span><br>
                    <span class="on1">骑士价：${gooddet.SellPrice }</span>&nbsp;&nbsp;<span class="on2">${gooddet.MarketPrice }</span><br>
                    <span class="on">免运费</span>
          </div>
          <div class="main2">
                    
                    <span class="shu">数量</span><br>
                    <div class="main2_left">
                              <span><a href="#" onclick="jiabuynum('jian');"><img src="images/goodlist/jianhao.png"></a></span>
                    </div>
                    <div class="main2_center">
                              <input name="BuyNum" id="buynum" type="text"  value="1" readonly="readonly">
                    </div>
                    <div class="main2_right">
                              <span><a href="#"  onclick="jiabuynum('jia');"><img src="images/goodlist/jiahao.png"></a></span>
                    </div>
                    <%-- <div class="main_right_1">库存<span id="volume">${gooddet.volume }</span></div> --%>
                    <div  style="clear:both;"></div>
          </div>
          <div class="main3">
                   <!--  <h1>商品信息</h1>
                    <p><span>商品名称：</span>北大荒绿野黑木耳150g<br>
                    <span>原料产地：</span>大兴安岭<br>
                    <span>商品规格：</span>150g<br>
                    <span>保质期：</span>12个月</p>
                    <div class="img"><img src="images/main16.png"></div>
                    <p><span>商品特点：</span>产品产自大兴安岭，无任何污染，经人工挑选、除杂、高温灭菌。具有色泽黑亮、泡发率高、叶片舒展等特性。本产品不含任何添加剂和防腐剂。</p>
                    <p><span>产品介绍：</span>黑木耳是著名的山珍，可食、可药、可补，中国老百姓餐桌上久食不
厌，有“素中之荤”之美誉，世界上被称之为“中餐中的黑色瑰宝”。而黑木耳
培植方法，在世界农艺、园艺、菌艺史上，都堪称一绝。北大荒绿野木耳150g本
本品产自大兴安岭，色泽黑亮口感细嫩、营养丰富、味道鲜美。本品是经过人工
精细筛选，无任何杂质，是纯正的健康食品。食用时免摘，适于炒菜、煲汤凉拌
及各种菜肴的配菜等，是宴席上的特色佳肴。</p>
                    <div class="img"><img src="images/main17.png"></div>
                    <p><span>产品功效：</span>1、木耳中铁的含量极为丰富，故常吃木耳能养血驻颜，令人肌肤红润，
                荣光焕发，并可防治缺铁性贫血。<br>
　　          2、木耳含有维生素K，能减少血液凝块，预防血栓症的发生，有防治
                动脉粥样硬化和冠心病的作用；<br>
　　          3、木耳中的胶质可把残留在人体消化系统内的灰尘、杂质吸附集中起
                来排出体外，从而起到清胃涤肠的作用。<br>
　　          4、它还有帮助消化纤维类物质功能，对无意中吃下的难以消化的头发、
                谷壳、木渣、沙子、金属屑等义务有溶解与烊化作用，因此，它是矿山、
                化工和纺织工人不可缺少的保健食品。<br>
　　          5、它对胆结石、肾结石等内源性异物也有比较显著的化解功能；<br>
　　          6、木耳含有抗肿瘤活性物质，能增强机体免疫力，经常食用可防癌抗
                癌。</p>
                <p><span>食用方法：</span>将产品泡发洗净，煮汤、热炒或者凉拌均可。</p>
                <p><span>储存方法：</span>避免阳光直射，置阴凉干燥处。</p> -->
                
                ${detail}
                
          </div>
          <div <c:if test="${gooddet.OrderSplit==7}">style="display:none"</c:if> class="buttom">
                    <div class="buttom_left">
                              <a onclick="goPay('${gooddet.SellPrice }');"><img src="images/goodlist/buttom1.png"></a>
                    </div>
                    <div class="buttom_right">
                              <a onclick="showBySupmid('${gooddet.ProSKUId }',$('#buynum').val());"><img src="images/goodlist/buttom2.png"></a>
                    </div>
          
<input type="hidden" value="${gooddet.ProSKUId }" id="paymoney">
</div>
<div <c:if test="${gooddet.OrderSplit!=5}">style="display:none"</c:if> class="buttom" style="margin-left: -12px">
<a onclick="goPay('${gooddet.SellPrice }');"><img src="images/goodlist/buttom1.png"></a>
</div>
</div>
</c:forEach>
</body>
</html>
