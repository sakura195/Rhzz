<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误数据展示</title>
</head>
<body>
	<form name="form1" action="" method="post"
		style="margin-top: 100px; text-align: center;">
		<table style="margin:auto;">
			<tr>
				<td>细则号</td>
				<td>产品件号</td>
				<td>内部编号</td>
			</tr>

			<c:forEach items="${LoopResult}" var="item" varStatus="status">
				<tr>
					<td><input type="text" name="细则号"
						value="<c:out value="${item.细则号}" />"></input></td>
					<td><input type="text" name="产品件号"
						value="<c:out value="${item.产品件号}" />"></input></td>
					<td><input type="text" name="内部编号"
						value="<c:out value="${item.内部编号}" />"></input></td>
				</tr>

			</c:forEach>

		</table>
	</form>


</body>
<script type="text/javascript">
	
</script>
</html>