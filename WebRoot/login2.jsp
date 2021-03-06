<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.js"></script>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script src="${path}/js/html5.js"></script>
<script src="${path}/js/excanvas.js"></script>
<script src="${path}/js/excanvas.compiled.js"></script>
</head>
<style>
a {
	color: white
}

#media {
	margin: 10px auto 0;
	border-radius: 30px;
	opacity: 0.7px;
}

#video {
	
}

#canvas {
	display: none;
}
</style>
<body>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>
	<h1>鸿鹄材料人脸识别登陆</h1>
	<div class="login-form">
		<form name="form1" method="post" action="login.do">
			<input type="hidden" name="user_id" value="" />
			<div class="close"></div>
			<div class="head-info">
				<div style="font-size: 24px; width: 250px; display: inline;">
					<a href="<%=basePath%>login.jsp" />用户密码登陆
				</div>
				<div style="font-size: 24px; width: 250px; display: inline;">
					<a href="<%=basePath%>login2.jsp" />人脸识别登陆
				</div>
			</div>
			<div class="clear"></div>
			<div class="avtar">
				<img src="images/logo2.png" style="width: 120px;" />
			</div>
			<div id="media" style="width: 300px; height: 295px;">
				<video id="video" autoplay
					style="margin-left: -115px; margin-top: -5px; width: 530px; height: 300px;"></video>
				<canvas id="canvas" width="400" height="300"></canvas>
			</div>




		</form>
		<div class="signin">
			<input type="submit" value="Login" onclick="distinguish()"
				style="display: none">
		</div>

	</div>
	<div style="text-align: center; margin-top: 20px">
		<span id="error_msg"
			style="height: 60px; font-size: 24px; margin-top: 20px; display: none"></span>
	</div>
	<div class="copy-rights">
		<p></p>
	</div>
	<script type="text/javascript">

		var video = document.querySelector('video');
		var canvas = document.querySelector('canvas');
		varctx = canvas.getContext('2d');
		var localMediaStream = null;
		//var 是定义变量

		var video = document.getElementById("video"); //获取video标签
		var context = document.getElementById("canvas").getContext("2d");
		var con = {
			audio : false,
			video : {
				width : 800,
				height : 1024,
			}
		};
		
		//导航 获取用户媒体对象
		navigator.mediaDevices.getUserMedia(con).then(function(stream) {
			video.src = window.URL.createObjectURL(stream);
			video.onloadmetadate = function(e) {
				video.play();
			}
		});
	 
		function getBase64() {
			var imgSrc = document.getElementById("canvas").toDataURL(
					"image/png");
			return imgSrc.split("base64,")[1];

		};
		//定时执行人脸识别任务
		function distinguish() {
			//把流媒体数据画到convas画布上去
			context.drawImage(video, 0, 0, 400, 300);
			var base = getBase64();
			$
					.ajax({
						type : "post",
						url : "${pageContext.request.contextPath}/facedistinguish.do",
						data : {
							"base" : base
						},
						statusCode : {
							404 : function() {
								alert('page not found');
							}
						},
						success : function(data) {
							var json = JSON.parse(data);
							if (json.error_code == 0) {
								alert("识别成功，点击确认后登陆");
								var json = JSON.parse(data);
								document.form1.user_id.value = json.user_id;
								document.form1.action = "login2.do";
								document.form1.submit();

							} else {

								document.getElementById("error_msg").innerHTML = "面容识别失败,请继续验证 错误码:"
										+ json.error_code
										+ "错误原因:"
										+ json.error_msg;
								$("#error_msg").fadeIn(300).delay(3000)
										.fadeOut(400);
							}
							;
						}
					});
		}

		setInterval("distinguish()", 8000);
	</script>
</body>
</html>