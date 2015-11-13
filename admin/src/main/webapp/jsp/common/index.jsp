<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- saved from url=(0050)http://themes.tehnoremont-ds.com/atina/index.html# -->
<html lang="en" class="">
<head>
<title>微信对接</title>
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="/jsp/admin/css/bootstrap.css" rel="stylesheet">
<link href="/jsp/admin/css/bootstrap-responsive.css" rel="stylesheet">
<link href="/jsp/admin/css/general.css" rel="stylesheet">
<link href="/jsp/admin/css/colors/noise-red.css" rel="stylesheet" id="theme">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <link href="css/ie8.css" rel="stylesheet">
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
      <script src="js/respond/respond.min.js"></script>
    <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="http://themes.tehnoremont-ds.com/atina/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="http://themes.tehnoremont-ds.com/atina/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="http://themes.tehnoremont-ds.com/atina/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="http://themes.tehnoremont-ds.com/atina/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="/jsp/admin/image/logo.jpg">

<script type="text/javascript" src="/jsp/admin/js/beacon.js"></script>
<script src="/jsp/admin/js/hm.js"></script>
<script type="text/javascript" charset="UTF-8" src="/jsp/admin/js/logb02.js"></script>
<script>
	//* hide all elements & show preloader
	document.documentElement.className += 'loader';
	function redirect(url) {
		 $("iframe").attr("src", url);
	}
</script>
<style type="text/css">
.jqstooltip {
	position: absolute;
	left: 0px;
	top: 0px;
	visibility: hidden;
	background: rgb(0, 0, 0) transparent;
	background-color: rgba(0, 0, 0, 0.6);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,
		endColorstr=#99000000 );
	-ms-filter:
		"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000, endColorstr=#99000000)";
	color: white;
	font: 10px arial, san serif;
	text-align: left;
	white-space: nowrap;
	padding: 5px;
	border: 1px solid white;
	z-index: 10000;
}

.jqsfield {
	color: white;
	font: 10px arial, san serif;
	text-align: left;
}
</style>
<script></script>
<script id="hp_same_"></script>
<script id="hp_done_"></script>
</head>

  <body>

    <div class="loading"><img src="/jsp/admin/image/loader01.gif" alt=""></div>

    <div class="mainContainer">     
      <header>
        <div >
         <font  style="font-size:30px;line-height:38px;">微信</font>
          <span > 尊敬的:${sessionScope.userName}，您好！</span>
          [<a title="logout" href="http://manage.111.com.cn">退出</a>]
		 <ul class="headerButtons">     
            <li class="conta">
            <a href="#" class="changeContainer">
            <span class="container"></span></a>
            </li>
          </ul>
		   </div>
      </header>

      <div class="widgetBar"  style="display: block;">
        <div>
          <ul class="navigation">
			<li><a href="#">常用功能</a>
				<ul class="subMenu" style="display: block;">
                <li><a onclick="redirect('http://www.111.com.cn')"><span>+</span>自动回复设置</a></li>
                <li><a href="forms-wizard.html"><span>+</span>查询绑定用户</a></li>
              </ul>
			</li>
			
			<c:forEach items="${sessionScope.userFunc}" var="item">
                 <c:if test="${item.funcType eq 0 }">
                    <li>
                        <span>${item.funcName}</span>
                        <ul class="subMenu" style="display: none;">
                        <c:forEach items="${sessionScope.userFunc}" var="itemChild">
      					<c:if test="${itemChild.funcType eq 1 and itemChild.funcPid eq item.funcId}">
                            <li> <a href="${itemChild.funcUrl}<c:if test="${itemChild.remark eq 'marketing'}">&user_login_name=${sessionScope.username}</c:if>">
                                ${itemChild.funcName}
                                </a>
                            </li>
                            </c:if></c:forEach>
                        </ul>
                        
                    </li>
                    </c:if>
                    </c:forEach>
          </ul>

        
         
        </div>
      </div>

      <div class="content contentRight">

        <div class="topBar">
          <div class="topBarInner">
            <ul class="breadcrumbs">
              <li><a href=""><img src="/jsp/admin/image/home5.png" alt=""></a></li>
              <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="">订单管理</a>
              </li>
              <li><a href="">订单查询</a></li>
            </ul>

      
            <ul class="barButtons">
              <li class=""><a href=""><img src="/jsp/admin/image/cog(1).png" alt="">最新消息 <strong>(5)</strong></a>
              </li>
              <li><a href=""><img src="/jsp/admin/image/pad.png" alt="">绑定账户数<strong>(5)</strong></a></li>
              <li><a href=""><img src="/jsp/admin/image/box2.png" alt="">新增用户数 <strong>(5)</strong></a>
              </li>
              <li><a href=""><img src="/jsp/admin/image/file.png" alt="">用户数 <strong>(5)</strong></a>
              </li>
          
            </ul>
          </div>
        </div>

          <div class="contentInner" >
 			<iframe  style='width:100%;height:100%' src="" frameborder="0"></iframe>
          </div>

       </div>
    </div>

    <!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/jsp/admin/js/jquery-1.8.3.js"></script>
	<script src="/jsp/admin/js/ui/jquery-ui-1.9.2.custom.js"></script>

	<!-- flot plugin -->
	<script src="/jsp/admin/js/flot/excanvas.min.js"></script>
	<script src="/jsp/admin/js/flot/jquery.flot.js"></script>
	<script src="/jsp/admin/js/flot/jquery.flot.pie.min.js"></script>
	<script src="/jsp/admin/js/flot/jquery.flot.resize.js"></script>
	<script src="/jsp/admin/js/flot/jquery.flot.orderBars.js"></script>

	<!-- Jquery form wizard -->
	<script src="/jsp/admin/js/formWizard/jquery.form.js"></script>
	<script src="/jsp/admin/js/formWizard/jquery.validate.js"></script>
	<script src="/jsp/admin/js/formWizard/bbq.js"></script>
	<script src="/jsp/admin/js/formWizard/jquery.form.wizard.js"></script>

	<!-- antiscroll plugin -->
	<script src="/jsp/admin/js/scrollbar/jquery.mCustomScrollbar.js"></script>

	<!-- fullcalendar plugin -->
	<script src="/jsp/admin/js/fullcalendar/fullcalendar.js"></script>

	<!-- tipsyS plugin -->
	<script src="/jsp/admin/js/tipsy/jquery.tipsy.js"></script>

	<!-- fancybox plugin -->
	<script src="/jsp/admin/js/fancybox/jquery.fancybox.pack.js"></script>

	<!-- uniform plugin -->
	<script src="/jsp/admin/js/uniform/jquery.uniform.js"></script>

	<!-- Jquery dataTable -->
	<script src="/jsp/admin/js/dataTable/jquery.dataTables.js"></script>

	<!-- uniform plugin -->
	<script src="/jsp/admin/js/sparklines/jquery.sparkline.js"></script>

	<!-- chosen plugin -->
	<script src="/jsp/admin/js/chosen/chosen.jquery.js"></script>

	<!-- cookie plugin -->
	<script src="/jsp/admin/js/cookie/jquery.cookie.js"></script>

	<!-- jplayer plugin -->
	<script src="/jsp/admin/js/jplayer/jquery.jplayer.min.js"></script>

	<!-- mask plugin -->
	<script src="/jsp/admin/js/mask/jquery.maskedinput-1.3.js"></script>

	<!-- easypiechart plugin -->
	<script src="/jsp/admin/js/easypiechart/jquery.easy-pie-chart.js"></script>

	<!-- globalize plugin -->
	<script src="/jsp/admin/js/globalize/globalize.js"></script>
	<script src="/jsp/admin/js/globalize/cultures/globalize.culture.de.js"></script>

	<!-- jplayer plugin -->
	<script src="/jsp/admin/js/jplayer/jquery.jplayer.min.js"></script>
	<script src="/jsp/admin/js/jplayer/jplayer.playlist.min.js"></script>

	<!-- ibutton plugin -->
	<script src="/jsp/admin/js/ibutton/jquery.ibutton.js"></script>

	<!-- daterangepicker plugin -->
	<script src="/jsp/admin/js/dateRangepicker/date.js"></script>
	<script src="/jsp/admin/js/dateRangepicker/daterangepicker.jQuery.js"></script>

	<!-- antiscroll plugin -->
	<script src="/jsp/admin/js/antiscroll/jquery-mousewheel.js"></script>
	<script src="/jsp/admin/js/antiscroll/antiscroll.js"></script>

	<!-- cleditor plugin -->
	<script src="/jsp/admin/js/cleditor/jquery.cleditor.js"></script>

	<script src="/jsp/admin/js/bootstrap.min.js"></script>
	<script src="/jsp/admin/js/application.js"></script>

	<script src="/jsp/admin/js/general.js"></script>
	<script src="/jsp/admin/js/forms.js"></script>

	<script>
		$(document).ready(function() {
			setTimeout('$("html").removeClass("loader")', 1000);
		});
	</script>



	<div id="ui-datepicker-div"
		class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>
	<div class="cleditorPopup cleditorList" style="display: none;">
		<div style="font-family: Arial;">Arial</div>
		<div style="font-family: &amp; #39;">Arial Black</div>
		<div style="font-family: &amp; #39;">Comic Sans MS</div>
		<div style="font-family: &amp; #39;">Courier New</div>
		<div style="font-family: Narrow;">Narrow</div>
		<div style="font-family: Garamond;">Garamond</div>
		<div style="font-family: Georgia;">Georgia</div>
		<div style="font-family: Impact;">Impact</div>
		<div style="font-family: &amp; #39;">Sans Serif</div>
		<div style="font-family: serif;">Serif</div>
		<div style="font-family: Tahoma;">Tahoma</div>
		<div style="font-family: &amp; #39;">Trebuchet MS</div>
		<div style="font-family: Verdana;">Verdana</div>
	</div>
	<div class="cleditorPopup cleditorList" style="display: none;">
		<div>
			<font size="1">1</font>
		</div>
		<div>
			<font size="2">2</font>
		</div>
		<div>
			<font size="3">3</font>
		</div>
		<div>
			<font size="4">4</font>
		</div>
		<div>
			<font size="5">5</font>
		</div>
		<div>
			<font size="6">6</font>
		</div>
		<div>
			<font size="7">7</font>
		</div>
	</div>
	<div class="cleditorPopup cleditorList" style="display: none;">
		<div>
			<p>Paragraph</p>
		</div>
		<div>
			<h1>Header 1</h1>
		</div>
		<div>
			<h2>Header 2</h2>
		</div>
		<div>
			<h3>Header 3</h3>
		</div>
		<div>
			<h4>Header 4</h4>
		</div>
		<div>
			<h5>Header 5</h5>
		</div>
		<div>
			<h6>Header 6</h6>
		</div>
	</div>
	<div class="cleditorPopup cleditorColor" style="display: none;">
		<div style="background-color: rgb(255, 255, 255);"></div>
		<div style="background-color: rgb(255, 204, 204);"></div>
		<div style="background-color: rgb(255, 204, 153);"></div>
		<div style="background-color: rgb(255, 255, 153);"></div>
		<div style="background-color: rgb(255, 255, 204);"></div>
		<div style="background-color: rgb(153, 255, 153);"></div>
		<div style="background-color: rgb(153, 255, 255);"></div>
		<div style="background-color: rgb(204, 255, 255);"></div>
		<div style="background-color: rgb(204, 204, 255);"></div>
		<div style="background-color: rgb(255, 204, 255);"></div>
		<div style="background-color: rgb(204, 204, 204);"></div>
		<div style="background-color: rgb(255, 102, 102);"></div>
		<div style="background-color: rgb(255, 153, 102);"></div>
		<div style="background-color: rgb(255, 255, 102);"></div>
		<div style="background-color: rgb(255, 255, 51);"></div>
		<div style="background-color: rgb(102, 255, 153);"></div>
		<div style="background-color: rgb(51, 255, 255);"></div>
		<div style="background-color: rgb(102, 255, 255);"></div>
		<div style="background-color: rgb(153, 153, 255);"></div>
		<div style="background-color: rgb(255, 153, 255);"></div>
		<div style="background-color: rgb(187, 187, 187);"></div>
		<div style="background-color: rgb(255, 0, 0);"></div>
		<div style="background-color: rgb(255, 153, 0);"></div>
		<div style="background-color: rgb(255, 204, 102);"></div>
		<div style="background-color: rgb(255, 255, 0);"></div>
		<div style="background-color: rgb(51, 255, 51);"></div>
		<div style="background-color: rgb(102, 204, 204);"></div>
		<div style="background-color: rgb(51, 204, 255);"></div>
		<div style="background-color: rgb(102, 102, 204);"></div>
		<div style="background-color: rgb(204, 102, 204);"></div>
		<div style="background-color: rgb(153, 153, 153);"></div>
		<div style="background-color: rgb(204, 0, 0);"></div>
		<div style="background-color: rgb(255, 102, 0);"></div>
		<div style="background-color: rgb(255, 204, 51);"></div>
		<div style="background-color: rgb(255, 204, 0);"></div>
		<div style="background-color: rgb(51, 204, 0);"></div>
		<div style="background-color: rgb(0, 204, 204);"></div>
		<div style="background-color: rgb(51, 102, 255);"></div>
		<div style="background-color: rgb(102, 51, 255);"></div>
		<div style="background-color: rgb(204, 51, 204);"></div>
		<div style="background-color: rgb(102, 102, 102);"></div>
		<div style="background-color: rgb(153, 0, 0);"></div>
		<div style="background-color: rgb(204, 102, 0);"></div>
		<div style="background-color: rgb(204, 153, 51);"></div>
		<div style="background-color: rgb(153, 153, 0);"></div>
		<div style="background-color: rgb(0, 153, 0);"></div>
		<div style="background-color: rgb(51, 153, 153);"></div>
		<div style="background-color: rgb(51, 51, 255);"></div>
		<div style="background-color: rgb(102, 0, 204);"></div>
		<div style="background-color: rgb(153, 51, 153);"></div>
		<div style="background-color: rgb(51, 51, 51);"></div>
		<div style="background-color: rgb(102, 0, 0);"></div>
		<div style="background-color: rgb(153, 51, 0);"></div>
		<div style="background-color: rgb(153, 102, 51);"></div>
		<div style="background-color: rgb(102, 102, 0);"></div>
		<div style="background-color: rgb(0, 102, 0);"></div>
		<div style="background-color: rgb(51, 102, 102);"></div>
		<div style="background-color: rgb(0, 0, 153);"></div>
		<div style="background-color: rgb(51, 51, 153);"></div>
		<div style="background-color: rgb(102, 51, 102);"></div>
		<div style="background-color: rgb(0, 0, 0);"></div>
		<div style="background-color: rgb(51, 0, 0);"></div>
		<div style="background-color: rgb(102, 51, 0);"></div>
		<div style="background-color: rgb(102, 51, 51);"></div>
		<div style="background-color: rgb(51, 51, 0);"></div>
		<div style="background-color: rgb(0, 51, 0);"></div>
		<div style="background-color: rgb(0, 51, 51);"></div>
		<div style="background-color: rgb(0, 0, 102);"></div>
		<div style="background-color: rgb(51, 0, 153);"></div>
		<div style="background-color: rgb(51, 0, 51);"></div>
	</div>
	<div class="cleditorPopup cleditorPrompt" style="display: none;">
		Enter URL:<br>
		<input type="text" value="http://" size="35"><br>
		<input type="button" value="Submit">
	</div>
	<div class="cleditorPopup cleditorPrompt" style="display: none;">
		Paste your content here and click submit.<br>
		<textarea cols="40" rows="3"></textarea>
		<br>
		<input type="button" value="Submit">
	</div>
	<script type="text/javascript" async="" src="/jsp/admin/js/cnzz.js"></script>
	<script type="text/javascript">
		var vglnk = {
			api_url : '//api.viglink.com/api',
			key : '084c74521c465af0d8f08b63422103cc'
		};
	</script>
	<script type="text/javascript" async="" src="/jsp/admin/js/vglnk.js"></script>
</body>
</html>