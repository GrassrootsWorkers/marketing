<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/jsp/common/js.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
        <title>发票系统</title>
        <script src="/jquery/lib/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
        <script src="/jquery/lib/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function() {
                layout = $("#page_layout").ligerLayout({
                    topHeight : 53,
                    leftWidth : 170,
                    bottomHeight : 25,
                    height : '100%',
                    space : 0
                });
                $("#tree").ligerTree({
                    checkbox : false,
                    slide : false,
                    nodeWidth : 170,
                    attribute : ['nodename', 'url'],
                    onSelect : function(node) {
                        if(!node.data.url)
                            return;
                        $("#framecenter iframe").attr("src", node.data.url)
                    }
                });
            });
        </script>
    </head>
    <body>
        <div id="page_layout">
            <div position="top">
                <span class="logo">微信</span>
                <div class="nav">
                    <span> 尊敬的:${sessionScope.userName}，您好！</span>
                    [<a title="logout" href="http://manage.111.com.cn">退出</a>]
                </div>
            </div>
            <div position="left">
                <ul id="tree">
                <c:forEach items="${sessionScope.userFunc}" var="item">
                 <c:if test="${item.funcType eq 0 }">
                    <li isexpand="false">
                        <span>${item.funcName}</span>
                        <ul>
                        <c:forEach items="${sessionScope.userFunc}" var="itemChild">
      					<c:if test="${itemChild.funcType eq 1 and itemChild.funcPid eq item.funcId}">
                            <li url="${itemChild.funcUrl}<c:if test="${itemChild.remark eq 'coupon'}">&user_login_name=${sessionScope.username}</c:if>">
                                <span>${itemChild.funcName}</span>
                            </li>
                            </c:if></c:forEach>
                        </ul>
                        
                    </li>
                    </c:if>
                    </c:forEach>
                </ul>
            </div>
            <div position="center" id="framecenter">
                <iframe src="" frameborder="0"></iframe>
            </div>
            <div position="bottom">
                <span>copyright &copy; 2013 111.com</span>
            </div>
        </div>
    </body>
    <script type="text/javascript">
function checkout(){
	window.parent.document.location.href="/bill/logout";
}
</script>
</html>
