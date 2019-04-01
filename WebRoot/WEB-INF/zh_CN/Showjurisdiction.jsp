<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<link href="/static/style.css"  rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量权限变更</title>
</head>
<body>
<div>

<center>
<h1>请输入需要变更的模块名称</h1>
<form name="form1" action="ChangJurisdic.action" method="post">
<input type="text" name="Mname" class="mytxt"></input>
<input type="hidden" name="changeID"/>
<button type="button" class="button" onclick="queryWobjectNum()">提交</button>
</form>
</center>
</div>
</body>
<script type="text/javascript">

function queryWobjectNum(){
	var Mname = document.form1.Mname.value;
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/ChangJurisdic.do",
				dataType : "json",
				data : "Mname=" + Mname,
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data) {
					var s1=data.flag;
					if(s1){
						alert("未查询出有效的结果");
					}else{
						var s2=data.子模块;
						var s3=data.子模块位置;
						
						if(confirm("查询到子模块为:"+s2+"  模块序号为:"+s3+"是否进行权限变更？")) {
							
							document.form1.changeID.value=s3;
							document.form1.action="queryAllUserStatus.do";
							document.form1.submit();
						}
					}
					
					}

				}
			);
	
}
</script>
<style>
.mytxt {
	color: #333;
	line-height: normal;
	font-family: "Microsoft YaHei", Tahoma, Verdana, SimSun;
	font-style: normal;
	font-variant: normal;
	font-size-adjust: none;
	font-stretch: normal;
	font-weight: normal;
	margin-top: 0px;
	margin-bottom: 0px;
	margin-left: 0px;
	padding-top: 4px;
	padding-right: 4px;
	padding-bottom: 4px;
	padding-left: 4px;
	font-size: 15px;
	outline-width: medium;
	outline-style: none;
	outline-color: invert;
	border-top-left-radius: 3px;
	border-top-right-radius: 3px;
	border-bottom-left-radius: 3px;
	border-bottom-right-radius: 3px;
	text-shadow: 0px 1px 2px #fff;
	background-attachment: scroll;
	background-repeat: repeat-x;
	background-position-x: left;
	background-position-y: top;
	background-size: auto;
	background-origin: padding-box;
	background-clip: border-box;
	background-color: rgb(255, 255, 255);
	margin-right: 8px;
	border-top-color: #ccc;
	border-right-color: #ccc;
	border-bottom-color: #ccc;
	border-left-color: #ccc;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-width: 1px;
	border-radius: 3px;
}

.mytxt:focus {
	border: 1px solid #fafafa;
	-webkit-box-shadow: 0px 0px 6px #007eff;
	-moz-box-shadow: 0px 0px 5px #007eff;
	box-shadow: 0px 0px 5px #007eff;
}

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
</style>
</html>