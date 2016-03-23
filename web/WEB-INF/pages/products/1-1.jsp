<%--
Created by IntelliJ IDEA.
User: zlb
Date: 16-3-7
Time: 下午5:15
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>产品中心</title>
<link href="/styles/public.css"	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/scripts/jqueryUI/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
<script type="text/javascript" src="/scripts/keep.js"></script>
<meta name="keywords" content="集团"> 
<meta name="description" content="企业"> 
</head>
<body>
<!-- header begin -->
<div class="header_div"><iframe scrolling="no" src="/header.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:85px;"></iframe></div>
<!-- header end -->
<!-- 正文 开始 -->
<div class="ad" style="background-image:url('/images/2.jpg')">
	<div class="ban_top"></div>
	<div class="area_sec_dhs">
		<a class="loc" href="1.htm">市政工程系列</a>
		<a href="1.htm">园林工程系列</a>
		<a href="1.htm">水利工程系列</a>
		<a href="1.htm">建筑工程系列</a>
		<a href="1.htm">碑牌系列</a>
		<a href="1.htm">工艺系列</a>
	</div>
</div>
<div class="area_1200">
	<div class="area_loc">
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  产品中心  / 市政工程系列</div>
	</div>
	<div class="case_view">
		<h3>品名：PH-08 <a href="1.htm">返回</a></h3>
		<div class="b_cont">
			<div id="scroll_jdt">
				<div class="ct_p_05"><img width="960" height="590" src="/images/a1.jpg"><span class="ct_txt">PH-08</span></div>
			</div>
			<div class="scrDotList_wrap"><span class="scrDotList" id="slide_dot"><span></span></span></div>
			<a class="scrArrAbsLeft" id="scroll_left" href="javascript:void(0)"></a>
			<a class="scrArrAbsRight" id="scroll_right" href="javascript:void(0)"></a>
		</div>
		<div class="case_intro"></div>
	</div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script type="text/javascript">
(function(){
	var focusScroll_01 = new ScrollPic();
	focusScroll_01.scrollContId   = "scroll_jdt"; //内容容器ID
	focusScroll_01.dotListId = "slide_dot";
	focusScroll_01.dotClassName = "";
	focusScroll_01.dotOnClassName = "on";
	focusScroll_01.listType       = "";//列表类型(number:数字，其它为空)
	focusScroll_01.listEvent      = "onmouseover"; //切换事件
	focusScroll_01.frameWidth     = 960;//显示框宽度
	focusScroll_01.pageWidth      = 960; //翻页宽度
	focusScroll_01.upright        = false; //垂直滚动
	focusScroll_01.speed          = 15; //移动速度(单位毫秒，越小越快)
	focusScroll_01.space          = 60; //每次移动像素(单位px，越大越快)
	focusScroll_01.autoPlay       = true; //自动播放
	focusScroll_01.autoPlayTime   = 5; //自动播放间隔时间(秒)
	focusScroll_01.circularly     = true;
	focusScroll_01.initialize(); //初始化
	document.getElementById('scroll_left').onmousedown = function(){
		focusScroll_01.pre();
		return false;
	}
	document.getElementById('scroll_right').onmousedown = function(){
		focusScroll_01.next();
		return false;
	}
})()
</script>
</body>
</html>