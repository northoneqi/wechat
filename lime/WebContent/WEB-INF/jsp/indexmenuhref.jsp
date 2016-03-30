<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<title>商品列表页</title>
<link type="text/css" rel="stylesheet" href="css/goodlist/list.css">
<link type="text/css" rel="stylesheet" href="css/index/top_footer.css">
<script src="<c:url value="/js/shoppingcart/cart.js"/>"
	type="text/javascript" charset="utf-8"></script>

<script type="text/javascript">
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
	
}

function goPay(SellPrice,sku,split){
	var buynum = 1;
	//alert(buynum);
	window.location.href = "/lime/goPay?SellPrice="+SellPrice+"&ProNum="+buynum+"&sku="+sku+"&split="+split;
}	
	

	
	
	
</script>
</head>

<body>
<!-- <div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/goodlist/logo.png"></a>
          </div>
          <div class="header_right">
                    <div class="header_right_1"><a href="#"><img src="images/goodlist/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="#"><img src="images/goodlist/person.png"></a></div>
                    <div class="header_right_3"><a href="#"><img src="images/goodlist/drive.png"></a></div>
          </div>
</div> -->
<c:import url="public/top.jsp"></c:import>
<div class="main">
          <div class="main_left"id="m">
            
                    <table width="100%"  align="center"  cellpadding="0" cellspacing="0"  >
                    <c:forEach   begin="1" end ="${size}" varStatus="t" step = "2">
                           <tr><td height="5"></td></tr>
                           <tr>
                           <td width="1%"></td>
                             <c:forEach items="${good}" var="good" begin="${t.index-1 }" end ="${t.index }">
                             <td width="48.5%" style="border:1px solid #ccc; " align="" >
                                 <table width="100%" cellspacing="0" cellpadding="0"class="main_left_left" align="">
                                          <tr class="main_left_left_top">
                                              <td colspan="2"> <a href="<c:url value="/goodsku?proskuid=${good.ProSKUId }"/>"><img src="http://images.yijia360.com${good.ListPagePic}"></a></td>
                                          </tr>
                                          <tr >
                                            <td width="70%" class="main_left_left_bottom">${fn:substring(good.ProName ,0,8)}<br/>${fn:substring(good.WXPRONAME != null?good.WXPRONAME:good.ProName ,8,16)}
												<c:if test="${fn:length(good.ProName)>16 }">...</c:if> <br>骑士价：<span>￥${good.SellPrice}</span></td>
                                          </tr>
                                          <tr>
                                          <td id='gouwuche' <c:if test="${good.OrderSplit==5}">style="display:none"</c:if> width="70%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="showBySupmid('${good.ProSKUId }','1');"><img width="80px" height="25px" src="images/goodlist/buttom22.png"></a></td>
                                            <td id='lijigoumai' <c:if test="${good.OrderSplit!=5}">style="display:none"</c:if> width="70%" valign="bottom" align="center" class="main_left_left_bottom"> <a onclick="goPay('${good.SellPrice }','${good.ProSKUId }','${good.OrderSplit}');"><img width="80px" height="25px" src="images/goodlist/buttom1.png"></a></td>
                                          </tr>
                                   </table>
                              </td>
                               <td width="1%"></td>
                                </c:forEach>
                                
                              <td width="1%"></td>
                           </tr>
                            </c:forEach>  
                    </table>
                   
          </div>
        
          <div class="main_right" id="m2">
                    <a href="<c:url value="/indexhref?ColumnId=77"/>">
                    <c:choose>
                    <c:when test="${param.ColumnId==77 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/nav7.png"><br>骑士特价</div></a>
                    <a href="<c:url value="/indexhref?ColumnId=79"/>">
                    <c:choose>
                    <c:when test="${param.ColumnId==79 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/nav8.png"><br>新品上架</div></a>
                    <a href="<c:url value="/indexhref?ColumnId=78"/>">
                    <c:choose>
                    <c:when test="${param.ColumnId==78 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/nav9.png"><br>销量排行</div></a>
                    <a href="<c:url value="/indexhref?ColumnId=80"/>">
                     <c:choose>
                    <c:when test="${param.ColumnId==80 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/nav10.png"><br>限时抢购</div></a>
                    
                    <!-- ------------- -->
                    <a href="<c:url value="/indexhref?ColumnId=81"/>">
                     <c:choose>
                    <c:when test="${param.ColumnId==81 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/yingyang.png"><br>健康套餐</div></a>
                      <a href="<c:url value="/indexhref?ColumnId=82"/>">
                     <c:choose>
                    <c:when test="${param.ColumnId==82 }"><div class="on"></c:when>
                    <c:otherwise><div class="main_right_1"></c:otherwise>
                    </c:choose>
                    <img src="images/goodlist/suoyou.png"><br>所有分类</div></a>
          </div>
        
                    
          </div>

<div  style="clear:both;"></div>

<div class="footer">
            <a href="tel:96008"><img src="images/goodlist/phone.png"></a>
</div>

</body>
</html>
