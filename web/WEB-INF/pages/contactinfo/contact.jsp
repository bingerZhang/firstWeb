<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>联系信息</title>
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
		<a class="loc" href="contact.htm">联系信息</a>
	</div>
</div>
<div class="area_1200">
	<div class="area_loc">
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  联系信息</div>
	</div>
	<div class="area_nr">
        <c:forEach var="cont" items="${contact}" varStatus="status">
            <h3>${cont.companyname}</h3>
            <p>${cont.address}</p>
            <p>${cont.phone}</p>
            <p>${cont.name}</p>
            <p>${cont.fax}</p>
            <p></p>
        </c:forEach>
	</div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
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