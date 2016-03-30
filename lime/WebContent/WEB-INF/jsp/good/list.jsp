<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 


<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>商品列表页</title>
<link type="text/css" rel="stylesheet" href="css/goodlist/list.css">
<link type="text/css" rel="stylesheet" href="css/index/top_footer.css">
	<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
	
	<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
	
function goPay(SellPrice,SKU,split){
	var buynum = 1;
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

window.onload=function(){ 
	var clientHeight=0;
	if(document.documentElement&&document.documentElement.scrollTop)
    {
		clientHeight=document.documentElement.scrollTop;
    }
    else if(document.body)
    {
    	clientHeight=document.body.scrollTop;
    }
	 show = document.getElementById("m");
	 show2 = document.getElementById("m2");
	 
	 show.style.height = window.innerHeight-90+"px";
	 show2.style.height =  window.innerHeight-90+"px";
	
	 //show2.scrollTop = (window.innerHeight-90)-${where}*93;
	 if (${where}*93 >(window.innerHeight-90)){
		 show2.scrollTop = ${where}*93 - (window.innerHeight-90) + 115;
	 }
	 
	 
	
}
</script>
</head>

<body>
<!-- <div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/goodlist/logo.png"></a>
          </div>
          <div class="header_right">
                    <div class="header_right_1"><a href="/lime/toSearchPage"><img src="images/goodlist/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="#"><img src="images/goodlist/person.png"></a></div>
                    <div class="header_right_3"><a href="/lime/ordercartlist"><img src="images/goodlist/drive.png"></a></div>
          </div>
</div> -->
<c:import url="../public/top.jsp"></c:import>
<div  class="main">
          <div id="m"class="main_left">
                    <table width="100%"  align="center"  cellpadding="0" cellspacing="0">
                         <c:forEach   begin="1" end ="${size}" varStatus="t" step = "2">
                           <tr><td height="5"></td></tr>
                           <tr>
                           <td width="1%"></td>
                           <c:forEach items="${good}" var="good" begin="${t.index-1 }" end ="${t.index }">
                             <td width="48.5%" style="border:1px solid #ccc; " align="" >
                                 <table width="100%" cellspacing="0" cellpadding="0"class="main_left_left" align="">
                                 
                                          <tr class="main_left_left_top">
                                              <td colspan="2"> <a  href="<c:url value="/goodsku?proskuid=${good.ProSKUId }"/>"><img width="150px" height="150px" src="http://images.yijia360.com${good.ListPagePic}"></a></td>
                                          </tr>
                                          <tr >
                                              <td width="70%" class="main_left_left_bottom">${fn:substring(good.WXPRONAME != null?good.WXPRONAME:good.ProName ,0,8)}<br>${fn:substring(good.WXPRONAME != null?good.WXPRONAME:good.ProName ,8,16)}
												<c:if test="${fn:length(good.WXPRONAME != null?good.WXPRONAME:good.ProName)>16 }">...</c:if> <br>骑士价：<span>￥${good.SellPrice}</span></td>
                                          </tr>												
                                          <tr>
                                          <td id ="jiaru" <c:if test="${good.OrderSplit==5}">style="display:none"</c:if>width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${good.ProSKUId }','1');"><img width="80px" height="25px" src="images/goodlist/buttom22.png"></a></td>
                                          <td id ="zhijie"<c:if test="${good.OrderSplit!=5}">style="display:none"</c:if> width="30%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="goPay('${good.SellPrice }','${good.ProSKUId }','${good.OrderSplit}');"><img width="80px" height="25px" src="images/goodlist/buttom1.png"></a></td>
                                          </tr>
                                   </table>
                              </td>
                            
                               <td width="1%"></td>
                                 </c:forEach> 
                                <c:choose>
                                <c:when test="${addsize==1}" ><td width="48.5%" style=" " align="" ></td></c:when>
                                <c:otherwise></c:otherwise>
                                </c:choose>  
                           </tr>
                            </c:forEach>
                    </table> 
                    
          </div>
          
          <div class="main_right" id="m2">
          
          <c:forEach items="${list}" var="list" >
           <a href="<c:url value='/good?classid=${list.classId}'/>">
         <c:choose>
         <c:when test="${list.classId==classid }">
          <div class="on">
         </c:when>
         <c:otherwise>
          <div class="main_right1">
         </c:otherwise>
         </c:choose>
         <br/><br/>${list.WXCLASSNAME}<br/><br/></div><HR align=center  color=#AAAAAA SIZE=2></a>
         
         </c:forEach>
                    
          </div>
</div>

<div  style="clear:both;"></div>

<div class="footer" >
           <a href="tel:96008"><img src="images/goodlist/phone.png"></a>
</div>

</body>
</html>
