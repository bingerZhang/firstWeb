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
<title>品牌实力</title>
<link href="/styles/public.css"	rel="stylesheet" type="text/css">
<link href="/styles/jquery.fancybox-1.3.4.css"	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/scripts/jqueryUI/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
<script type="text/javascript" src="/scripts/jquery.fancybox-1.3.4.pack.js"></script>
<meta name="keywords" content="集团"> 
<meta name="description" content="企业"> 
</head>
<body>
<!-- header begin -->
<div class="header_div"><iframe scrolling="no" src="/header.htm" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:85px;"></iframe></div>
<!-- header end -->
<!-- 正文 开始 -->
<div class="ad" style="background-image:url('/images/2.jpg')">
	<div class="ban_top"></div>
	<div class="area_sec_dhs">
		<a class="loc" href="1.htm">生产设备</a>
		<a href="1.htm">矿产资料</a>
		<a href="1.htm">工厂实景</a>
	</div>
</div>
<div class="area_1200">
	<div class="area_loc">
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  品牌实力  / 生产设备</div>
	</div>
	<div class="equ_css">
		<li><a title="意大利薄板生产线" class="example7" href="/images/1.jpg"><i style='background: url("/images/1.jpg") no-repeat 50%;'></i><span>意大利薄板生产线</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/a2.jpg"><i style='background: url("/images/a2.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/3.jpg"><i style='background: url("/images/3.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/2.jpg"><i style='background: url("/images/2.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li><a title="意大利自动抛光机" class="example7" href="/images/a1.jpg"><i style='background: url("/images/a1.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/2.jpg"><i style='background: url("/images/2.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/3.jpg"><i style='background: url("/images/3.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
		<li class="sp"><a title="意大利自动抛光机" class="example7" href="/images/2.jpg"><i style='background: url("/images/2.jpg") no-repeat 50%;'></i><span>意大利自动抛光机</span>
			<div class="e_mask"></div>
			<div class="e_ic_zoom"></div></a></li>
	</div>
	<!-- 分页 -->
	<div><iframe scrolling="no" src="/scott.htm" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:70px;"></iframe></div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="../foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script type="text/javascript">
$(document).ready(function(){
	$("a.example7").fancybox({
		'overlayColor'		: '#000',
		'overlayOpacity'	: 0.8,
		'titlePosition'	: 'inside'
	});
	$(".equ_css li a .e_mask").hide();
	$(".equ_css li a .e_ic_zoom").hide();
	
	$(".equ_css li a").hover(function(){
		$(this).find(".e_mask").stop().fadeTo(500,0.7)
		$(this).find(".e_ic_zoom").stop().fadeTo(500,1.0)
	},function(){
		$(this).find(".e_mask").stop().fadeTo(500,0)
		$(this).find(".e_ic_zoom").stop().fadeTo(500,0)
	});

});
</script>
</body>
</html>