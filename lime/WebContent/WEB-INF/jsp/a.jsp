<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>成功返回数据</title>
 <script language="javascript" src="<c:url value="/js/wxpayjs/jquery.js"/>"></script>
        <script language="javascript" src="<c:url value="/js/wxpayjs/lazyloadv3.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/md5.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/sha1.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/paysupply.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/payfunction.js"/>"></script>
        <script Language="javascript">
           
      
            var app_id = "wxdf56a63c63a3f762";
            var app_key = "Md6BO5atVwP2jl5aDAm9PCYiM7c2OhcFxkmrt5XYbQ4xgcLZQaHKRI7ITnhNHwvjWUNszOuiJASzmGBKVlAP0JA60VVV22h1t948wCsifVpGE7ZFu0xbVIR0Im8MVrSZ";
            var openid ="o6Lh5t7dGSFiAvHFTDzRZvhl1fuQ";
            var transid="1218461501201407023211603685";
            var out_trade_no="3012911226616088600";
            var date = new Date();
            var deliver_timestamp=date.getTime();
            var deliver_status="1";
            var deliver_msg ="ok";
          
            //第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
            var keyvaluestring = "appid"+app_id+"&appkey="+app_key+"&deliver_msg="+deliver_msg+"&deliver_status="+deliver_status+"&deliver_timestamp="+deliver_timestamp+"&openid="+openid+"&out_trade_no="+out_trade_no+"&transid="+transid;
            alert(keyvaluestring);
            sign = CryptoJS.SHA1(keyvaluestring).toString();
            alert(sign) ;
            $("#test").val(sign);
        
            
            
            
            </script>
</head>
<body>
成功返回数据
<input type="text" id="test" >
<%-- <c:forEach items="${list}" var="list">
${list.proName }
</c:forEach> --%>
</body>
</html>