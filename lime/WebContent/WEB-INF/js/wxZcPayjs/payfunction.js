/**
 * 
 */

 				function auto_remove(img){
                div=img.parentNode.parentNode;div.parentNode.removeChild(div);
                img.onerror="";
                return true;
            }
            
            function changefont(fontsize){
                if(fontsize < 1 || fontsize > 4)return;
                $('#content').removeClass().addClass('fontSize' + fontsize);
            }
            
            
           
            
         // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
            	 //公众号支付
                                      jQuery('a#getBrandWCPayRequest').click(
                                    		
                                    		  function(e){  if(check()){
                                                                             WeixinJSBridge.invoke('getBrandWCPayRequest',{
                                                                            	 "appId" : getAppId(), //公众号名称，由商户传入
                                                                                 "timeStamp" : getTimeStamp(), //时间戳
                                                                                 "nonceStr" : getNonceStr(), //随机串
                                                                                 "package" : getPackage(),//扩展包
                                                                                 "signType" : getSignType(), //微信签名方式:1.sha1
                                                                                 "paySign" : getSign() //微信签名
                                                                                                   },function(res){
                                                                                                  if(res.err_msg == "get_brand_wcpay_request:ok" ) {
                                                                                                	  window.location.href="/lime/index";
                                                                                                  }
                                                                                              	  // window.location.href="http://weixin.yijia360.com/lime/index";
                                                                                                   // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                                                                                                   //因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面。
                                                                                                   }); 
                                    		  }
                                                                             
                                                                             });
                                      
                                      
            																		
                                      
                                      WeixinJSBridge.log('yo~ ready.');
                                      
                                      }, true);
            
            if(jQuery){
                jQuery(function(){
                       
                       var width = jQuery('body').width() * 0.87;
                       jQuery('img').error(function(){
                                           var self = jQuery(this);
                                           var org = self.attr('data-original1');
                                           self.attr("src", org);
                                           self.error(function(){
                                                      auto_remove(this);
                                                      });
                                           });
                       jQuery('img').each(function(){
                                          var self = jQuery(this);
                                          var w = self.css('width');
                                          var h = self.css('height');
                                          w = w.replace('px', '');
                                          h = h.replace('px', '');
                                          if(w <= width){
                                          return;
                                          }
                                          var new_w = width;
                                          var new_h = Math.round(h * width / w);
                                          self.css({'width' : new_w + 'px', 'height' : new_h + 'px'});
                                          self.parents('div.pic').css({'width' : new_w + 'px', 'height' : new_h + 'px'});
                                          });
                       });
            }