<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>抽奖活动</title>  
<style type="text/css">  
body {padding-top:100px;font:12px "\5B8B\4F53",sans-serif;text-align:center;}  
.result_box {margin:0 auto;width:700px;padding:100px 0;text-align:center;border:3px solid #40AA53;background:#efe;}  
.result_box #oknum {width:700px;color:#cc0000;font-size:50pt;font-family:Verdana;text-align:center;border:none;background:#efe;}  
.button_box {margin:50px 0 0 0;}  
.button_box .btn {cursor:pointer;padding:0 30px;margin:0 10px;color:#555;font-family:"\5B8B\4F53",sans-serif;font-size:40px;}  
</style>  
</head>     
<body>  
<script type="text/javascript">  
//抽奖数据，以英文逗号分隔  
var alldata = "一等奖,二等奖,三等奖,参与奖 ,";    
var alldataarr = alldata.split(",");    
var num = alldataarr.length-1;  
var timer;  
function change(){     
    document.getElementById("oknum").value = alldataarr[GetRnd(0,num)];     
}  
function start(){     
    clearInterval(timer);     
    timer = setInterval('change()',100); //随机数据变换速度，越小变换的越快     
  
}  	
function ok(){     
    clearInterval(timer);     
    //以下代码表示获得奖的，不能再获奖了。  重置刷新页面即可。  
    alldata = alldata.replace(document.getElementById("oknum").value,"").replace(",,",",");  
    // 去掉前置，最末尾的,  
    if (alldata.substr(0,1)==",")  
    {  
      alldata = alldata.substr(1,alldata.length);  
    }  
    if (alldata.substr(alldata.length-1,1)==",")  
    {  
      alldata = alldata.substring(0,alldata.length-1);  
    }  
    alldataarr = alldata.split(",");    
    num = alldataarr.length-1;    
}     
function GetRnd(min,max){     
    return parseInt(Math.random()*(max-min+1));     
} 
function SetTime(){
	 clearInterval(timer);     
	    timer = setInterval('change()',50); 
	  setTimeout(
		    	"window.location.href='http://www.baidu.com/'"
		    	
		    ,1000);
}
</script>  
<div class="result_box"><input type="text" id="oknum" name="oknum" value="您准备好了吗" /></div>  
<div class="button_box"><button class="btn"  onclick="SetTime()" accesskey="s">开始</div>  
	
</body>
</html>