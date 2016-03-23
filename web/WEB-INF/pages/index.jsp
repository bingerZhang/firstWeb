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
    <title>首页</title>
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
<div class="header_top"></div>
<div class="header_dh">
    <div class="area_1200 header_position">
        <div class="logo"><a href="index.htm"><img width="328" height="59" alt="北京德富众邦石材有限公司" src="images/logo.png"></a></div>
        <div class="navi_list">
            <ul class="nav" id="nav">
                <li class="nli on">
                    <h3><a href="/index.htm" target="_top">网站首页</a></h3>
                </li>
                <li class="nli">
                    <h3><a href="/company/introduction.htm" target="_top">企业概况</a></h3>
                    <ul class="sub">
                        <li><a href="/company/introduction.htm" target="_top">企业简介</a></li>
                        <li><a href="/company/culture.htm" target="_top">企业文化</a></li>
                    </ul>
                </li>
                <li class="nli">
                    <h3><a href="/information/companyNews.htm" target="_top">资讯中心</a></h3>
                    <ul class="sub">
                        <li><a href="/information/companyNews.htm" target="_top">企业动态</a></li>
                        <li><a href="/information/industryNews.htm" target="_top">行业新闻</a></li>
                        <li><a href="/information/companyVideos.htm" target="_top">企业视频</a></li>
                    </ul>
                </li>
                <li class="nli">
                    <h3><a href="/projects/cases.htm" target="_top">工程案例</a></h3>
                    <ul class="sub">
                        <li><a href="/projects/cases.htm" target="_top">经典工程</a></li>
                        <li><a href="/projects/lists.htm" target="_top">工程名录</a></li>
                    </ul>
                </li>
                <li class="nli">
                    <h3><a href="/products/1.htm" target="_top">产品中心</a></h3>
                    <ul class="sub">
                        <li><a href="/products/1.htm" target="_top">市政工程系列</a></li>
                        <li><a href="/products/1.htm" target="_top">园林工程系列</a></li>
                        <li><a href="/products/1.htm" target="_top">水利工程系列</a></li>
                        <li><a href="/products/1.htm" target="_top">建筑工程系列</a></li>
                        <li><a href="/products/1.htm" target="_top">碑牌系列</a></li>
                        <li><a href="/products/1.htm" target="_top">工艺系列</a></li>
                    </ul>
                </li>
                <li class="nli">
                    <h3><a href="/equipment/1.htm" target="_top">品牌实力</a></h3>
                    <ul class="sub">
                        <li><a href="/equipment/1.htm" target="_top">生产设备</a></li>
                        <li><a href="/equipment/1.htm" target="_top">矿产资料</a></li>
                        <li><a href="/equipment/1.htm" target="_top">工厂实景</a></li>
                    </ul>
                </li>
                <li class="nli">
                    <h3><a href="/contactinfo/1.htm" target="_top">联系信息</a></h3>
                </li>
            </ul>
        </div>
    </div>
</div>
<!-- header end -->
<!-- ad begin -->
<div id="hotpic">
    <div id="NewsPic">
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/1.jpg" class="picture" alt="宣传图片"/></a>
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/2.jpg" class="picture" alt="宣传图片"/></a>
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/3.jpg" class="picture" alt="宣传图片"/></a>
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/1.jpg" class="picture" alt="宣传图片"/></a>
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/2.jpg" class="picture" alt="宣传图片"/></a>
        <a  href="/index.htm" target="_blank" class="pic_a"><img src="/images/3.jpg" class="picture" alt="宣传图片"/></a>
        <div class="Nav">
            <span class="Normal">6</span>
            <span class="Normal">5</span>
            <span class="Normal">4</span>
            <span class="Normal">3</span>
            <span class="Normal">2</span>
            <span class="Cur">1</span>
        </div>
    </div>
</div>
<!-- ad end -->
<!-- 简介等 begin -->
<div class="quick_dh">
    <div class="kuan_left"><img width="7" height="240" src=images/kuan_left.png"></div>
    <div class="kuan_center">
        <li class="sp"><a href="index.htm"><img width="260" height="140" src="images/quick_case.jpg"><span>公司简介</span></a></li>
        <li class="sp"><a href="index.htm"><img width="260" height="140" src="images/quick_case.jpg"><span>企业动态</span></a></li>
        <li class="sp"><a href="index.htm"><img width="260" height="140" src="images/quick_case.jpg"><span>工程案例</span></a></li>
        <li class="sp"><a href="index.htm"><img width="260" height="140" src="images/quick_case.jpg"><span>产品中心</span></a></li>
    </div>
    <div class="kuan_right"><img width="7" height="240" src="images/kuan_right.png"></div>
</div>
<!-- 简介等 end -->
<!--foot begin-->
<div class="foot"><iframe scrolling="no" src="/foot.htm" id="" allowtransparency="true" framespacing="0" border="0" frameborder="0" style="width:100%;height:60px;"></iframe></div>
<!--foot end-->
<script id="jsID" type="text/javascript">
    jQuery("#nav").slide({
        type:"menu",// 效果类型，针对菜单/导航而引入的参数（默认slide）
        titCell:".nli", //鼠标触发对象
        targetCell:".sub", //titCell里面包含的要显示/消失的对象
        effect:"slideDown", //targetCell下拉效果
        delayTime:300 , //效果时间
        triggerTime:0, //鼠标延迟触发时间（默认150）
        returnDefault:true //鼠标移走后返回默认状
    });
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $('#hotpic').liteNav(1000);
    });
</script>
</body>
</html>