<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>首页 </title>

	<link href="<c:url value='css/index/scroll.css'/>" type="text/css"
	rel="stylesheet" />
	<link href="<c:url value='css/index/top_footer.css'/>" type="text/css"
	rel="stylesheet" />
	<script src="<c:url value="/js/index/top.js"/>"
	type="text/javascript" charset="utf-8"></script>
		<script src="<c:url value="/js/jquery-1.3.2.js"/>"
	type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="header" >
          <div class="header_left">
                    <a href="index"><img src="images/index/logo.png"></a>
          </div> 
          <div class="header_right">
                   <div class="header_right_1"><a href="<c:url value="/toSearchPage"/>"><img src="images/index/sousuo.png"></a></div>
                    <div class="header_right_2"><a href="<c:url value="/memberCenter"/>"><img src="images/index/person.png"></a></div>
                    <div class="header_right_3">
                              <div class="header_right_3_left">
                                         <a href="<c:url value="/ordercartlist"/>"><img src="images/index/drive.png"></a>
                              </div>
                             
                              <div class="header_right_3_right"id ="cat" style="z-index: 100;">${cartnum}</div>
                              
                    </div>
          </div>
          
          
          
          
          
</div>







</body>
</html>