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
			msgObj.style.background="#BEBFC3";
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
           }, 1300);
          }


function showBySupmid(SKU,Num){
	
	  $.ajax( {  
		 
		  type:'POST', 
		  data:{sku:SKU,num:Num},   
		  url:'/lime/ordercartadd',  
		  dataType:"json",
	      success:function(data) {
	    	  $("#cat").html(data.size);
	    	  sAlert("成功添加到购物");
	       },    
	       error : function(data) {   
	    	   sAlert(data);
	       }    
	  });  
	  return false;
	  };
