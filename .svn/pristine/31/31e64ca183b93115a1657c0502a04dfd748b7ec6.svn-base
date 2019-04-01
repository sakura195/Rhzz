<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请输入模块信息名称</title>
</head>
<body>
	<center>
		<form name="form1" action="querymo.action" method="post"
			style="margin-top: 30px;">
			<input type="text" value="" class="mytxt"
				style="width: 200px; height: 40px;" name="queryName" /> <br />
			<button type="button" class="button" style="margin-top: 20px;"
				id="doquery" onclick="queryFunc()">查询</button>
			<button type="button" class="button" onclick="recover()">重置</button>
			<table class="table" id="showTable" style="margin-top: 20px;">
				<tbody style="text-align: center;" id="table">
					<tr>
						<td style="width: 10%">子模块</td>
						<td style="width: 10%">中级模块</td>
						<td style="width: 10%">顶级模块</td>
						<td style="width: 10%">dwobject</td>
						<td style="width: 40%">selectSql</td>
					</tr>
				</tbody>
			</table>
		</form>
	</center>
</body>
<style>
.table {
	border: 1px solid #eee;
	border-collapse: collapse;
	border-spacing: 0;
}
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
<script type="text/javascript">
	function dosubmit() {
		document.form1.submit();
	}
	function recover() {
		document.form1.queryName.value = "";
		$('#table')
				.html(
						"<tr><td style='width:10%'>子模块</td><td style='width:10%'>中级模块</td><td style='width:10%'>顶级模块</td><td style='width:10%'>dwobject</td><td style='width:40%'>selectSql</td></tr>");
	}

	function queryFunc() {
		var queryName = document.form1.queryName.value;
		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/mokuaiquery.do",
					dataType : "json",
					data : "queryname=" + queryName,
					statusCode : {
						404 : function() {
							alert('page not found');
						}
					},
					success : function(data) {
						$('#table')
								.html(
										"<tr><td style='width:10%'>子模块</td><td style='width:10%'>中级模块</td><td style='width:10%'>顶级模块</td><td style='width:10%'>dwobject</td><td style='width:40%'>selectSql</td></tr>");
						var item = data.length;
						//console.log(item);
						for (var i = 0; i < item; i++) {
							$('#table').append(
									"<tr><td>" + data[i].子模块 + "</td>" + "<td>"
											+ data[i].中级模块 + "</td>" + "<td>"
											+ data[i].顶级模块 + "</td>" + "<td>"
											+ data[i].dwobject + "</td>"
											+ "<td>" + data[i].selectSql
											+ "</td>" + "</tr>");
						}

					}
				});
	}
</script>
</html>