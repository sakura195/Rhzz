<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工艺排箱</title>
</head>
<body>
	<form name="form1" action="dealwithPackage.do" method="post"
		style="margin-top: 100px;">
		<button name="dealwithPackage" type="button"
			onclick="dealPackage()">工艺排箱工作区</button>
			<button type="button" onclick="UNpackageable()">执行排箱</button>
			<br/>
			<input type="text" name="packageUnwait" value="<c:out value="${packageUnwait}" />" style="width:1000px;"/><button type="button" onclick="cx()">撤销</button>
			<input type="text" name="packageUnwait2" value="<c:out value="${packageUnwait2}" />" style="width:1000px;"/><button type="button" onclick="cx2()">撤销</button>
		<table class="table" style="margin-top: 20px;">
			<tbody>
				<tr>
					<td style="width:6%;">客户简称</td>
					<td>内部编号</td>
					<td>订单细则号</td>
					<td style="width:10%;">部件名称</td>
					<td style="width:10%;">铸件编号</td>
					<td>材质</td>
					<td style="width:6%;">模型厂家</td>
					<td>密度比</td>
					<td>项目号</td>
					<td>工序</td>
					<td>订货日期</td>
					<td>细则交货日期</td>
					<td>数据有效性</td>
					<td>排箱</td>
				</tr>
				<c:forEach items="${LoopResult}" var="item">
					<tr>
						<td><c:out value="${item.客户简称}" /></td>
						<td><c:out value="${item.内部编号}" /></td>
						<td><c:out value="${item.订单细则号}" /></td>
						<td><c:out value="${item.部件名称}" /></td>
						<td><c:out value="${item.铸件编号}" /></td>
						<td><c:out value="${item.材质}" /></td>
						<td><c:out value="${item.模型厂家}" /></td>
						<td><c:out value="${item.密度比}" /></td>
						<td><c:out value="${item.项目号}" /></td>
						<td><c:out value="${item.工序}" /></td>
						<td><c:out value="${item.订货日期}" /></td>
						<td><c:out value="${item.细则交货日期}" /></td>
						<td><button type="button"
								onclick="changeDetail('<c:out value="${item.订单细则号}" />')">设置为无效数据</button></td>
						<td><button type="button" onclick="addValue('<c:out value="${item.订单细则号}" />')">排箱</button>
						<button type="button" onclick="addValue2('<c:out value="${item.订单细则号}" />')">-1件排箱</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<!-- //翻页标签 -->
		<button type="button" onclick="befopage()">上一页</button>
		<button type="button" onclick="nextpage()">下一页</button>
		<input type="hidden" name="page" value="<c:out value='${pageNum}'/>" />
		<input type="hidden" name="coverPage" value="25" /> <input
			type="hidden" name="nextorback" /> <input type="hidden" name="Flag" />
	</form>


</body>
<script type="text/javascript">
function UNpackageable(){
	document.form1.action = "Packageable.do";
	document.form1.submit();
}
function addValue(id){
	var s=document.form1.packageUnwait.value;
	s=s+";"+id;
	document.form1.packageUnwait.value=s;
	
	}
function addValue2(id){
	var s=document.form1.packageUnwait2.value;
	s=s+";"+id;
	document.form1.packageUnwait2.value=s;
	
	}
	function befopage() {
		document.form1.nextorback.value = "0";
		document.form1.Flag.value = "1";
		document.form1.submit();

	}
	function nextpage() {
		document.form1.nextorback.value = "1";
		document.form1.Flag.value = "1";
		document.form1.submit();
	}
	function dealPackage() {
		document.form1.action = "Packingprocess.do";
		document.form1.submit();
	}
	function changeDetail(id){
		
		
		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/changeUnPackageDetail.do",
					dataType : "json",
					data : "id=" + id,
					statusCode : {
						404 : function() {
							alert('设置无效数据失败');
						}
					},
					success : function(data) {
						document.form1.action="dealwithPackage.do";
						document.form1.submit();
					}
				});
		
		
	}

</script>
</html>