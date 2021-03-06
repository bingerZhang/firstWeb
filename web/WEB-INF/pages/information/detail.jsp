﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="header_div"><iframe scrolling="no" src="/header.htm"  allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:85px;"></iframe></div>
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
		<div class="read_loc"><img width="12" height="11" src="/images/ic_home.png">&nbsp;&nbsp;<a href="../index.htm">首页</a>  /  资讯中心  / 企业动态</div>
	</div>
	<div class="news_view">
		<div class="news_view_title"><h3>${news.title}</h3><span>发布时间：${news.updatetime}</span></div>
		<div class="area_nr">
			${news.detail}
			<%--<p><img src="/images/3.jpg" border="0"></p>--%>
			<%--<p>受大环境影响，石材企业转型是必然之路，对于单纯的石材工程等企业欲往家装工程方面发展方向，利用加盟家装品牌而获取的无形资产价值及商机，实现真正意义的品牌资源共享是最好的选择。</p>--%>
			<%--<p>作为石材家装一线品牌，近日，溪石装饰全国诚邀加盟商正式启动，溪石家装集工装产品与家装产品相配套、材料销售与工程服务相促进、建筑与装饰装修相结合；优秀合作伙伴包括（中铁、中建、万科、龙湖、万达、中海、泛海、融创、绿城、星河湾、中梁、招商、华润、远洋、融侨等国内龙头地产），将助您战略转型。<br>目前溪石装饰已陆续在各大交通主干道投放户外广告。无论是在国道、省道、高速公路、码头、还是在酒店皆可看到别墅豪宅专家·助您战略转型，溪石装饰诚邀品牌加盟商的广告。<br>▼高速公路广告牌</p>--%>
			<%--<p><img src="/images/1.jpg" border="0"><br></p>--%>
			<%--<p><strong><span style="color: rgb(153, 51, 0);">溪石优势<br></span></strong>※“中国驰名商标”品牌，石材与装饰一体化服务商<br>※“建筑装饰工程设计与施工一级”、“建筑幕墙工程设计与施工一级”双一级资质<br>※中国房地产开发企业500强首选供应品牌<br>※与万科、龙湖、万达、绿城、星河湾等龙头地产达成长期合作关系。<br>我们期待您的加入，助您战略转型，共享豪宅资源。<br>加盟热线：188 --%>
	<%--7623 7777<br></p>--%>
		</div>
	</div>
	<div class="n_p">
        <c:choose>
            <c:when test="${nextnews eq null}">
                <span><a href="#">下一篇：没有了></a></span>
            </c:when>
            <c:otherwise>
                <span><a href="detail.htm?type=${type}&id=${nextnews.id}">下一篇：${nextnews.title}></a></span>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${prenews eq null}">
                <span><a href="#">上一篇：没有了></a></span>
            </c:when>
            <c:otherwise>
                <span><a href="detail.htm?type=${type}&id=${prenews.id}">上一篇：${prenews.title}></a></span>
            </c:otherwise>
        </c:choose>
		<span><a href="news.htm?type=${type}">&lt;&lt;返回资讯列表</a></span>
	</div>
	<div class="sp_60"></div>
</div>
<!-- 正文 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
</body>
</html>