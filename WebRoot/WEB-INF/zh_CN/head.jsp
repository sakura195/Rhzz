<%@page import="javax.print.attribute.standard.DateTimeAtCompleted"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
 <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script src="${path}/js/head.js"></script>
<script src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<html style="height:100px;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="${path}/css/head.css?t=1.06" rel="stylesheet" type="text/css"/>
</head>
<body>
<div style="width:80%;background-color:#004790;position: relative;">
<div style="height:40px;background-color:#a81404;width:1920px">
<ul id="menu">
<li class="listyle"><div class="item" onmouseover="show1()">常用工具</div>
<ul id="cygj" onmouseleave="hide1()" style="display:none">
<li><div class="item_title"><a onclick="faceadd()">人脸注册</a></div></li>
<li><div class="item_title"><a onclick="Mokuai()">模块信息查询</a></div></li>

<li><div class="item_title"><a onclick="Changejurisdiction()">权限变更</a></div></li>

<li><div class="item_title"><a onclick="changNbbh()">内部编号变更</a></div></li>
<li><div class="item_title"><a onclick="JH()">软件激活</a></div></li>
</ul>
</li>
<li class="listyle"><div class="item" onmouseover="show2()">技术工艺</div>
<ul id="jsgy" onmouseleave="hide2()" style="display:none">
<!-- <li><div class="item_title"><a onclick="Packingprocess()">工艺排箱</a></div></li> -->
<li><div class="item_title"><a onclick="kbcx()">看板查询</a></div></li>
</ul>
</li>
<%-- <li class="listyle"><div class="item" >生产总控</div></li>
<li class="listyle"><div class="item" >质量管理</div></li>
<li class="listyle"><div class="item" >设备管理</div></li>
<li class="listyle"><div class="item" >人力资源</div></li>
<li class="listyle"><div class="item" >用户名:<c:out value='${uname}'/></div></li> --%>
<li class="listyle"><div class="item" ><a onclick="logout()">退出登录</a></div></li>
</ul>
</div> 






</div>

<script type="text/javascript">
function changNbbh(){
	document.form1.action="nbbhbg.do";
	document.form1.submit();
}
function JH(){
	document.form1.action="jh.do";
	document.form1.submit();
}
function kbcx(){
	document.form1.action="kbcx.do";
	document.form1.submit();
}
function faceadd(){
	document.form1.action="faceadd.do";
	document.form1.submit();
}
</script>
</body>
</html>