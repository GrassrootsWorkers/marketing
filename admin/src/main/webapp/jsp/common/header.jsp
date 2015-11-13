<%@ page contentType="text/html; charset=GBK" import="com.yao.common.config.DiamondConfig" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title></title>
<link href="http://s.maiyaole.com/css/OPTBG.css" rel="stylesheet" type="text/css" />
</head>

<body style="border:none;">
<div class="Top">
<div class="Nav">
<div class="Logo"></div><div class="LeftNav"><img src="http://p1.maiyaole.com/images/left_1.gif"/></div>
</div>                                                 
<div class="Exit">

<span class="LText ExitLeft"><font color="red"><%=DiamondConfig.getProperty("is_test", "") %> </font>尊敬的<s:property value="#session.admin.name" />，您好！</span>
<span class="DText ExitRit"><a href="javascript:" onclick="http://manage.111.com.cn">退出登录</a></span></div>
</div>
<script type="text/javascript">
function checkout(){
	window.parent.document.location.href="/admin/role/logout.action";
}
</script>
</body>
</html>