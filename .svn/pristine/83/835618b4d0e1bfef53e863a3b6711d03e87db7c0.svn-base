<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>	
<head>
<title>Login</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8;width=device-width;minimum-scale=1.0, maximum-scale=2.0">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<style>
a{
color:white}
</style>
</head>
<body>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <h1>鸿鹄材料登陆界面</h1>
<div class="login-form">
 <form name="form1" method="post" action="login.do">
	<div class="close"> </div>
		<div class="head-info">
			<div style="font-size:24px;width:250px;display:inline;" ><a href="<%=basePath%>login.jsp"/>用户密码登陆</div>
			<div style="font-size:24px;width:250px;display:inline;" ><a href="<%=basePath%>login2.jsp"/>人脸识别登陆</div>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="images/logo2.png" style="width:120px;"/>
	</div>
	<div  id="ERRORLOG" style="margin-top:30px;font-size:25px;color:white;" ><c:out value="${ERRORLOG}"/></div>
					<input type="text" class="text" name="Username" value="用户名" onfocus="hideTip()" >
						<div class="key">
					<input type="password" name="Password" value="" onfocus="hideTip()">
						</div>
			</form>
	<div class="signin">
		<input type="submit" value="Login" onclick="beforesubmit()">
	</div>
</div>
 <div class="copy-rights">
					<p></p>
			</div>
<script>

function hideTip(){
	var tip = document.getElementById("ERRORLOG");
	tip.style.visibility = "hidden";
}


function beforesubmit(){
	var username=document.form1.Username.value;
	var password=document.form1.Password.value;
	if(username="")
		{
		alert("用户名不能为空");
		return false;
		}
	else if(password="")
		{
		alert("密码不能为空");
		return false;
		}
	else{
		 document.form1.submit();	
	}

	
	
}

</script>
</body>
</html>