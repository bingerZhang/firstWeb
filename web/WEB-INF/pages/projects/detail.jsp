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
<title>工程案例</title>
<link href="/styles/public.css"	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
<script type="text/javascript" src="/scripts/jquery.ad-gallery.js"></script>
<script type="text/javascript" src="/scripts/zzsc.js"></script>
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
		<a class="loc" href="cases.htm">经典工程</a>
		<a href="lists.htm">工程名录</a>
	</div>
</div>
<div class="area_1200">
	<div class="area_loc">
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  工程案例  / 经典工程</div>
	</div>
	<div class="case_view">
		<h3>项目名称：${famousproject.name} <a href="cases.htm">返回</a></h3>
		<div class="ad-gallery" id="gallery">
			<div class="ad-nav">
				<div class="ad-image-wrapper"></div>
				<div class="ad-controls"></div>
				<div class="ad-thumbs">
					<ul class="ad-thumb-list">
						<c:forEach var="image" items="${famousproject.imagePath}" varStatus="status">
							<li><a href="${image}"><img width="157" height="96" alt="${famousproject.name}" src="${image}"></a></li>
						</c:forEach>
						<%--<li><a href="/images/a1.jpg"><img width="157" height="96" alt="俄罗斯莫斯科凯宾斯基酒店" src="/images/a1.jpg"></a></li>--%>
					</ul>
				</div>
			</div>
		</div>
		<div class="case_intro">工程名：${famousproject.name} ${famousproject.description}</div>
	</div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
</body>
</html>