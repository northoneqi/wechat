<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>公众号支付测试</title>
        <script language="javascript" src="<c:url value="/js/wxpayjs/jquery.js"/>"></script>
        <script language="javascript" src="<c:url value="/js/wxpayjs/lazyloadv3.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/md5.js"/>"></script>
        <script src="<c:url value="/js/wxpayjs/sha1.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/paysupply.js"/>"></script>
          <script src="<c:url value="/js/wxpayjs/payfunction.js"/>"></script>
        <script Language="javascript">
           
            
            
            
            
            </script>
        <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
        <meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;" />
        
        <style>
            
          
            </style>
        <script language="javascript">
           
            </script>
    </head>
    <body>
        
        <form name="form1" target="_blank">
            <table border="1">
                <TR><th>公众号ID</th> <th><INPUT value="wxdf56a63c63a3f762" name="appId" id="1"></th>
                    <tr><th>商户ID</th><th><INPUT value="1218461501" name="partnerId" id="2"></th>
                        <TR><th>总金额</th><th><INPUT value=1 name="totalFee" id="3"></th>
                            <TR><th>商品名</th><th><INPUT value="京华骑士支付测试请求" name="body" id="4"></th>
            </table>
        </form>
        <div class="WCPay">
            <a id="getBrandWCPayRequest" href="javascript:void(0);"><h1 class="title">提交</h1></a>
        </div>
        
        
    </body>
</html>
