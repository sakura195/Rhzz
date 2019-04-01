<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>芸峰软件激活授权文件下载</title>
</head>
<body>
	<form name="form1" action="jhxz.do" method="post"  style="margin-top:100px;text-align:center;" enctype="multipart/form-data">
	<div id="append">
	<h2>请输入本地生成文件中的字符</h2>
	<div class="a">
	<input type="file" name="bh"></input><br>
	</div>
	</div>
	<br>
	<button type="button" onclick="submit()">提交</button>
	</form>


</body>
<script type="text/javascript">
function submit(){
	document.form1.submit();
}

</script>
</html>