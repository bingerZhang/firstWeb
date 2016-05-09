<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>产品中心</title>
<link href="/styles/public.css"	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/scripts/jqueryUI/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
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
		<%--class="loc" --%>
		<a <c:if test="${type eq 1}">class="loc"</c:if>href="city.htm">市政工程系列</a>
		<a <c:if test="${type eq 2}">class="loc"</c:if>href="garden.htm">园林工程系列</a>
		<a <c:if test="${type eq 3}">class="loc"</c:if>href="water.htm">水利工程系列</a>
		<a <c:if test="${type eq 4}">class="loc"</c:if>href="architecture.htm">建筑工程系列</a>
		<a <c:if test="${type eq 5}">class="loc"</c:if>href="monument.htm">碑牌系列</a>
		<a <c:if test="${type eq 6}">class="loc"</c:if>href="technology.htm">工艺系列</a>
	</div>
</div>
<div class="area_1200">
	<div class="p_class">
		<ul>
		  <h3>石材分类：</h3>
		  <li><a href="javascript:void(0)">全部</a></li>
		  <li class="p_class_loc"><a href="javascript:void(0)">大理石</a></li>
		  <li><a href="javascript:void(0)">花岗岩</a></li>
		  <li><a href="javascript:void(0)">奢石</a></li>
		  <li><a href="javascript:void(0)">石灰石</a></li>
		  <li><a href="javascript:void(0)">砂岩</a></li>
		  <li><a href="javascript:void(0)">其它</a></li></ul>
		<ul>
		  <h3>石材工艺：</h3>
		  <li><a href="javascript:void(0)">全部</a></li>
		  <li><a href="javascript:void(0)">工艺面</a></li>
		  <li><a href="javascript:void(0)">雕刻</a></li>
		  <li><a href="javascript:void(0)">线条</a></li>
		  <li><a href="javascript:void(0)">水刀拼花</a></li></ul>
		<div class="read_loc_p"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  产品中心  / 市政工程系列</div>
		<div class="search_css search_css_loc_p">
			<form name="case_search" class="case_search" action="case_search.php?dhs=d04" method="post">
				<input name="keywords" class="searchInput" style="" type="text" autocomplete="off" placeholder="请输入产品名称">
				<input name="button" id="button" type="image" src="/images/ic_search.jpg">
			</form>
		</div>
	</div>
	<div class="products_css">

		<c:forEach var="product" items="${productlists}"  varStatus="status">
			<li><a href="detail.htm"><i style='background: url("${product.imagepath[0]}") no-repeat 50%;'></i><span><c:out value="${product.name}">${product.name}</c:out></span>
				<div class="p_mask"></div>
				<div class="p_ic_zoom"></div></a></li>
		</c:forEach>

		<%--<li class="sp"><a href="1-1.htm"><i style='background: url("/images/2.jpg") no-repeat 50%;'></i><span>圣雅米黄</span>--%>
			<%--<div class="p_mask"></div>--%>
			<%--<div class="p_ic_zoom"></div></a></li>--%>
	</div>
	<!-- 分页 -->
	<div><iframe scrolling="no" src="/scott.htm"  allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:70px;"></iframe></div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm"  allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script type="text/javascript">
$(document).ready(function(){
	
	$(".products_css li a .p_mask").hide();
	$(".products_css li a .p_ic_zoom").hide();
	
	$(".products_css li a").hover(function(){
		$(this).find(".p_mask").stop().fadeTo(500,0.7)
		$(this).find(".p_ic_zoom").stop().fadeTo(500,1.0)
	},function(){
		$(this).find(".p_mask").stop().fadeTo(500,0)
		$(this).find(".p_ic_zoom").stop().fadeTo(500,0)
	});

});
</script>
</body>
</html>