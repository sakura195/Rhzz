<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ include file="head.jsp"%>
<title>人脸注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style>
body {
	height: 100%;
	background: #213838;
	overflow: hidden;
}
 
canvas {
	z-index: -1;
	position: absolute;
}
</style>
<!-- <script src="js/jquery.js"></script>
<script src="js/verificationNumbers.js"></script>
<script src="js/Particleground.js"></script> -->

 
<style type="text/css">
.button {
	width: 134px;
	display: inline-block;
	padding: 6px 16px;
	font-size: 14px;
	font-weight: 400;
	border: 0;
	text-align: center;
	white-space: nowrap;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-color: #fff;
	color: black;
	border-radius: 5px;
	border: 1px solid #1774da;
	height: 40px;
}

.button, a:focus, a:hover {
	cursor: pointer;
}

button:hover {
	cursor: default;
	background-color: #eee;
	color: #808080 !important;
}
* {
	margin: 0;
	padding: 0;
}
 
body {
	height: 100vh;
	background-position: center;
	overflow: hidden;
}
 
h1 {
	color: #fff;
	text-align: center;
	font-weight: 100;
	margin-top: 40px;
}
 
#media {
	width: 300px;
	height: 295px;
	margin: 10px auto 0;
	border-radius: 30px;
	opacity: 0.7px;
}
 
#video {
	
}
 
#canvas {
	display: none;
}
 
#btn {
	width: 160px;
	height: 50px;
	background: #03a9f4;
	margin: 70px auto 0;
	text-align: center;
	line-height: 50px;
	color: #fff;
	border-radius: 40px;
}
</style>
</head>
 
<body>
 
	<form action="faceaddaction.do" method="post" name="form1">
		<dl class="admin_login">
			<dt>
				 <strong>请把你的脸放摄像头面前</strong>
			</dt>
			<div>
			<div style="height:300px;float:left;width:145px;background-color: #213838;position: absolute;left:680px"></div>
			<div id="media">
				<video id="video" width="530" height="300" autoplay style="margin-left:-129px"></video>
				<canvas id="canvas" width="400" height="300"></canvas>
			</div>
			<div style="height:300px;float:left;width:115px;background-color: #213838;position: absolute;left:1100px;top:70px;"></div>
			<div style="text-align:center;margin-top:30px">
			<button type="button" class="button" onclick="query()">立即注册</button>
			<button type="button" class="button" onclick="query2()">数据检测</button>
			</div>
			</div>
			
			
			
 
		</dl>
		<!-- <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script> -->
		<script type="text/javascript">
  		//var 是定义变量
  		var video = document.getElementById("video"); //获取video标签
  		var context = canvas.getContext("2d");
  		var con  ={
  			audio:false,
  			video:{
  			width:1980,
  			height:1024,
  			}
  		};	
  			
  			//导航 获取用户媒体对象
  			navigator.mediaDevices.getUserMedia(con)
  			.then(function(stream){
  				video.src = window.URL.createObjectURL(stream);
  				video.onloadmetadate = function(e){
  					video.play();
  				}
  			});
  			function query(){
  				
  				//把流媒体数据画到convas画布上去
  				context.drawImage(video,0,0,400,300);
  				var base = getBase64();
  				$.ajax({
  					type:"post",
  					url:"${pageContext.request.contextPath}/faceaddaction.do", 
  					data:{"base":base},
  					statusCode : {
  						404 : function() {
  							alert('page not found');
  						}
  					},
  					success:function(data){
 							var json=JSON.parse(data);
							if(json.error_code == 0){
								 alert("注册成功")
								} else {
									alert("面容识别失败,请继续验证 错误码:"+json.error_code+"错误原因:"+json.error_msg);
								}
								;
							}
						});
 
			}
			function getBase64() {
				var imgSrc = document.getElementById("canvas").toDataURL("image/png");
				return imgSrc.split("base64,")[1];
 
			};
		</script>
	</form>
</body>
</html>