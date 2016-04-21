<%--
Created by IntelliJ IDEA.
User: zlb
Date: 16-3-7
Time: 下午5:15
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>工程案例</title>
<link href="/styles/public.css"	rel="stylesheet" type="text/css">
<script type="text/javascript" src="/scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="/scripts/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript" src="/scripts/jquery.litenav.js"></script>
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
		<div class="read_loc_left"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  工程案例  / 经典工程</div>
		<div class="search_css search_css_loc">
			<form name="case_search" class="case_search" action="case_search.php?dhs=d04" method="post">
				<input name="keywords" class="searchInput" style="" type="text" autocomplete="off" placeholder="请输入工程名称">
				<input name="button" id="button" type="image" src="/images/ic_search.jpg">
			</form>
		</div>
	</div>
	<div class="case_css">
		<c:forEach var="famousp" items="${famousprojects}" varStatus="status">
            <tr>
                <%--<td title="${famousp.name}"><c:out value="${famousp.name}"/></td>--%>
                <%--<td>--%>
                    <li>
                    <a style='background: url("${famousp.imagePath}") no-repeat 50%;' href="1-1.htm">
                        <span>"${famousp.name}"</span>
                        <div class="case_mask"></div>
                        <div class="case_ic_zoom"></div>
                    </a>
                    </li>

                <%--</td>--%>
            </tr>

        </c:forEach>
		<li>
			<a style='background: url("/images/1.jpg") no-repeat 50%;' href="1-1.htm">
				<span>NOVATEK天然气公司总部办公大楼</span>
				<div class="case_mask"></div>
				<div class="case_ic_zoom"></div>
			</a>
		</li>
		<li class="sp">
			<a style='background: url("/images/3.jpg") no-repeat 50%;' href="1-1.htm">
				<span>NOVATEK天然气公司总部办公大楼</span>
				<div class="case_mask"></div>
				<div class="case_ic_zoom"></div>
			</a>
		</li>
		<li class="sp">
			<a style='background: url("/images/1.jpg") no-repeat 50%;' href="1-1.htm">
				<span>NOVATEK天然气公司总部办公大楼</span>
				<div class="case_mask"></div>
				<div class="case_ic_zoom"></div>
			</a>
		</li>
		<li class="sp">
			<a style='background: url("/images/2.jpg") no-repeat 50%;' href="1-1.htm">
				<span>NOVATEK天然气公司总部办公大楼</span>
				<div class="case_mask"></div>
				<div class="case_ic_zoom"></div>
			</a>
		</li>


	</div>
	<!-- 分页 -->
	<div><iframe scrolling="no" src="/scott.htm" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:70px;"></iframe></div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script type="text/javascript">
$(document).ready(function(){
	
	$(".case_css li a .case_mask").hide();
	$(".case_css li a .case_ic_zoom").hide();
	
	$(".case_css li a").hover(function(){
		$(this).find(".case_mask").stop().fadeTo(500,0.7)
		$(this).find(".case_ic_zoom").stop().fadeTo(500,1.0)
		$(this).find("span").stop().animate({bottom:'0'}, {duration: "fast"})
		
	},function(){
		$(this).find(".case_mask").stop().fadeTo(500,0)
		$(this).find(".case_ic_zoom").stop().fadeTo(500,0)
		$(this).find("span").stop().animate({bottom:'-35'}, {duration: "fast"})
	});

});
</script>
</body>
</html>