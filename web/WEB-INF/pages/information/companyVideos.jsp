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
<title>资讯中心</title>
<link href="/styles/public.css" rel="stylesheet" type="text/css">
<link href="/styles/jquery.fancybox-1.3.4.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/scripts/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
<script type="text/javascript" src="/scripts/jquery.fancybox-1.3.4.pack.js"></script>
<meta name="keywords" content="集团"> 
<meta name="description" content="企业"> 
<script type="text/javascript">
		$(document).ready(function() {
			$(".various3").fancybox({
				'width'				: 720,
				'height'			: 432,
				'autoScale'			: false,
				'transitionIn'		: 'none',
				'transitionOut'		: 'none',
				'type'				: 'iframe',
				'overlayColor'		: '#000',
				'overlayOpacity'	: 0.8
			});

		});
	</script>
</head>
<body>
<!-- header begin -->
<div class="header_div"><iframe scrolling="no" src="/header.htm" id="header" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:85px;"></iframe></div>
<!-- header end -->
<!-- 正文 开始 -->
<div class="ad" style="background-image:url('/images/2.jpg')">
	<div class="ban_top"></div>
	<div class="area_sec_dhs">
		<a <c:if test="${type eq 1}">class="loc"</c:if>href="companyNews.htm">企业动态</a>
		<a <c:if test="${type eq 2}">class="loc"</c:if>href="industryNews.htm">行业新闻</a>
		<a <c:if test="${type eq 3}">class="loc"</c:if>href="companyVideos.htm">企业视频</a>
	</div>
</div>
<div class="area_1200">
	<div class="area_loc">
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  资讯中心  / 企业视频</div>
	</div>
	<div class="video_css">
		<c:forEach var="video" items="${videolist}" varStatus="status">
			<li>
				<a title="${video.title}" class="various3" href="${video.videourl}">
					<img width="293" height="175" src="${video.imagepath}">
					<ul>
						<h3>${video.title}</h3><span>更新时间：${video.updatetime}</span>
					</ul>
					<div class="nk_mask"></div>
					<div class="nk_ic_zoom"></div>
				</a>
			</li>
		</c:forEach>
		<%--<li>--%>
			<%--<a title="企业宣传片" class="various3" href="http://xishigroup.com/cn/v_view.php?id=1">--%>
				<%--<img width="293" height="175" src="/images/3.jpg">--%>
				<%--<ul>--%>
				  <%--<h3>企业宣传片</h3><span>更新时间：2015/6/9</span>--%>
				<%--</ul>--%>
				<%--<div class="nk_mask"></div>--%>
				<%--<div class="nk_ic_zoom"></div>--%>
			<%--</a>--%>
		<%--</li>--%>

	</div>
	<!-- 分页 -->
	<div><iframe scrolling="no" src="../scott.htm" id="scott" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:70px;"></iframe></div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="foot" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script type="text/javascript">
	$(document).ready(function(){
		$(".video_css li a .nk_mask").hide();
		$(".video_css li a .nk_ic_zoom").hide();
		
		$(".video_css li a").hover(function(){
			$(this).find(".nk_mask").stop().fadeTo(500,0.7)
			$(this).find(".nk_ic_zoom").stop().fadeTo(500,1.0)
		},function(){
			$(this).find(".nk_mask").stop().fadeTo(500,0)
			$(this).find(".nk_ic_zoom").stop().fadeTo(500,0)
		});
	});
</script>
</body>
</html>