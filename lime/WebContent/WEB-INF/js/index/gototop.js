/**
 * 
 */


function gotoTop(min_height){
    //Ԥ���巵�ض�����html���룬����css��ʽĬ��Ϊ����ʾ
    var gotoTop_html = '<div id="gotoTop"><img src="images/index/top.png"></div>';
    //�����ض�����html�������ҳ����idΪpage��Ԫ�ص�ĩβ 
    $("#page").append(gotoTop_html);
    $("#gotoTop").click(//���巵�ض���������Ϲ����Ķ���
        function(){$('html,body').animate({scrollTop:0},700);
    }).hover(//Ϊ���ض�������������ķ���Ч���������ɾ��css��ʵ��
        function(){$(this).addClass("hover");},
        function(){$(this).removeClass("hover");
    });
    //��ȡҳ�����С�߶ȣ��޴���ֵ��Ĭ��Ϊ600����
    min_height ? min_height = min_height : min_height = 600;
    //Ϊ���ڵ�scroll�¼��󶨴�����
    $(window).scroll(function(){
        //��ȡ���ڵĹ������Ĵ�ֱλ��
        var s = $(window).scrollTop();
        //�����ڵĹ������Ĵ�ֱλ�ô���ҳ�����С�߶�ʱ���÷��ض���Ԫ�ؽ��֣�������
        if( s > min_height){
            $("#gotoTop").fadeIn(100);
        }else{
            $("#gotoTop").fadeOut(200);
        };
    });
};
gotoTop();