<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工艺排箱</title>
<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}
</style>
</head>
<body>
	<form name="form1" action="Packingprocess.do" method="post"
		style="margin-top: 100px;">
		<input type="hidden" name="update" value="1"/>
		<input type="hidden" name="listsize"
			value="<c:out value="${listsize}" />" />
		<button name="pxA" type="button" onclick="pxAction()">修改排箱</button>
		<table cellspacing=0>
			<tr>
				<td>技术员<input type="text" name="技术员"
					value="<c:out value="${技术员}" />" /></td>
				<td>制定时间<input  type="text"
					name="制定时间"
					value="<c:out value="${制定时间}" />"></td>
				<td>工艺单编号<input type="text" name="工艺单编号"
					value="<c:out value="${工艺单编号}" />" /></td>
				<td colspan="2">评审员<input type="text" name="评审员" value="<c:out value="${评审员}" />" /></td>
				<td colspan="2">评审时间<input class="Wdate" type="text"
					onClick="WdatePicker()" name="评审时间" value="<c:out value="${评审时间}" />"></td>
			</tr>
			<tr>
				<td>排箱号<input type="text" name="排箱号"
					value="<c:out value="${排箱号}" />" /></td>
				<td>类型<select name="拉延模">
						<option value="普通" <c:if test="${拉延模 == '普通'}">selected</c:if>><c:out value="普通" /></option>
						<option value="拉延凸模" <c:if test="${拉延模 == '拉延凸模'}">selected</c:if>><c:out value="拉延凸模" /></option>
						<option value="拉延凹模" <c:if test="${拉延模 == '拉延凹模'}">selected</c:if>><c:out value="拉延凹模" /></option>
						<option value="小件" <c:if test="${拉延模 == '小件'}">selected</c:if>><c:out value="小件" /></option>
						<option value="压边圈" <c:if test="${拉延模 == '压边圈'}">selected</c:if>><c:out value="压边圈" /></option>
				</select></td>
				<td colspan="3" rowspan="7">图片</td>
				<td>排箱号<input type="text" name="排箱号1" value="<c:out value="${排箱号1}" />"/>
				</td>
				<td>类型<select name="拉延模1">
						<option value=""></option>
						<option value="普通" <c:if test="${拉延模1 == '普通'}">selected</c:if>><c:out value="普通" /></option>
						<option value="拉延凸模" <c:if test="${拉延模1 == '拉延凸模'}">selected</c:if>><c:out value="拉延凸模" /></option>
						<option value="拉延凹模" <c:if test="${拉延模1 == '拉延凹模'}">selected</c:if>><c:out value="拉延凹模" /></option>
						<option value="小件" <c:if test="${拉延模1 == '小件'}">selected</c:if>><c:out value="小件" /></option>
						<option value="压边圈" <c:if test="${拉延模1 == '压边圈'}">selected</c:if>><c:out value="压边圈" /></option>
				</select></td>
			</tr>
			<tr>
				<td>材质<input name="材质" type="text" value="<c:out value="${材质}" />"/>
				</td>

				<td>浇注总重<input type="text" name="浇注总重" value="<c:out value="${浇注总重}" />" readonly="readonly" /></td>
				<td>材质<input type="text" name="材质1" value="<c:out value="${材质1}" />"></td>

				<td>浇注总重<input type="text" name="浇注总重1" value="<c:out value="${浇注总重1}" />"
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>砂箱<select name="砂箱">
						<option value="0#砂箱" <c:if test="${砂箱 == '0#砂箱'}">selected</c:if>><c:out value="0#砂箱" /></option>
						<option value="1#砂箱" <c:if test="${砂箱 == '1#砂箱'}">selected</c:if>><c:out value="1#砂箱" /></option>
						<option value="2#砂箱" <c:if test="${砂箱 == '2#砂箱'}">selected</c:if>><c:out value="2#砂箱" /></option>
						<option value="3#砂箱" <c:if test="${砂箱 == '3#砂箱'}">selected</c:if>><c:out value="3#砂箱" /></option>
						<option value="4#砂箱" <c:if test="${砂箱 == '4#砂箱'}">selected</c:if>><c:out value="4#砂箱" /></option>
				</select></td>
				<td>出气口<select name="出气口">
						<option value="Φ30" <c:if test="${出气口 == 'Φ30'}">selected</c:if>><c:out value="Φ30" /></option>
						<option value="负压" <c:if test="${出气口 == '负压'}">selected</c:if>><c:out value="负压" /></option>
				</select></td>
				<td>砂箱<select name="砂箱1">
						<option value=""></option>
						<option value="0#砂箱" <c:if test="${砂箱1 == '0#砂箱'}">selected</c:if>><c:out value="0#砂箱" /></option>
						<option value="1#砂箱" <c:if test="${砂箱1 == '1#砂箱'}">selected</c:if>><c:out value="1#砂箱" /></option>
						<option value="2#砂箱" <c:if test="${砂箱1 == '2#砂箱'}">selected</c:if>><c:out value="2#砂箱" /></option>
						<option value="3#砂箱" <c:if test="${砂箱1 == '3#砂箱'}">selected</c:if>><c:out value="3#砂箱" /></option>
						<option value="4#砂箱" <c:if test="${砂箱1 == '4#砂箱'}">selected</c:if>><c:out value="4#砂箱" /></option>
				</select></td>
				<td>出气口<select name="出气口1">
						<option value=""></option>
						<option value="Φ30" <c:if test="${出气口1 == 'Φ30'}">selected</c:if>><c:out value="Φ30" /></option>
						<option value="负压" <c:if test="${出气口1 == '负压'}">selected</c:if>><c:out value="负压" /></option>
				</select></td>
			</tr>
			<tr>
				<td>浇注时长<input type="text" name="浇注时间" value="<c:out value="${浇注时间}" />"
					readonly="readonly" /></td>
				<td>浇注温度<select name="浇注温度">
						<option value="1365" <c:if test="${浇注温度 == 1365}">selected</c:if>><c:out value="1365±5℃" /></option>
						<option value="1375" <c:if test="${浇注温度 == 1375}">selected</c:if>><c:out value="1375±5℃" /></option>
						<option value="1385" <c:if test="${浇注温度 == 1385}">selected</c:if>><c:out value="1385±5℃" /></option>
						<option value="1390" <c:if test="${浇注温度 == 1390}">selected</c:if>><c:out value="1390±5℃" /></option>
						<option value="1395" <c:if test="${浇注温度 == 1395}">selected</c:if>><c:out value="1395±5℃" /></option>
						<option value="1400" <c:if test="${浇注温度 == 1400}">selected</c:if>><c:out value="1400±5℃" /></option>
				</select></td>
				<td>浇注时长<input type="text" name="浇注时间1" value="<c:out value="${浇注时间1}" />"
					readonly="readonly" /></td>
				<td>浇注温度<select name="浇注温度1">
						<option value=""></option>
						<option value="1365" <c:if test="${浇注温度1 == 1365}">selected</c:if>><c:out value="1365±5℃" /></option>
						<option value="1375" <c:if test="${浇注温度1 == 1375}">selected</c:if>><c:out value="1375±5℃" /></option>
						<option value="1385" <c:if test="${浇注温度1 == 1385}">selected</c:if>><c:out value="1385±5℃" /></option>
						<option value="1390" <c:if test="${浇注温度1 == 1390}">selected</c:if>><c:out value="1390±5℃" /></option>
						<option value="1395" <c:if test="${浇注温度1 == 1395}">selected</c:if>><c:out value="1395±5℃" /></option>
						<option value="1400" <c:if test="${浇注温度1 == 1400}">selected</c:if>><c:out value="1400±5℃" /></option>
				</select></td>
			</tr>
			<tr>
				<td>浇注系统方式<select name="浇注系统方式">
						<option value="底注式" <c:if test="${浇注系统方式 == '底注式'}">selected</c:if>><c:out value="底注式" /></option>
						<option value="阶梯式" <c:if test="${浇注系统方式 == '阶梯式'}">selected</c:if>><c:out value="阶梯式" /></option>
						<option value="侧注式" <c:if test="${浇注系统方式 == '侧注式'}">selected</c:if>><c:out value="侧注式" /></option>
						<option value="顶注式" <c:if test="${浇注系统方式 == '顶注式'}">selected</c:if>><c:out value="顶注式" /></option>
						<option value="侧注及顶注式" <c:if test="${浇注系统方式 == '侧注及顶注式'}">selected</c:if>><c:out value="侧注及顶注式" /></option>
				</select></td>
				<td>锅台<input type="text" name="锅台" value="<c:out value="${锅台}" />"/></td>
				<td>浇注系统方式<select name="浇注系统方式1">
						<option value=""></option>
						<option value="底注式" <c:if test="${浇注系统方式1 == '底注式'}">selected</c:if>><c:out value="底注式" /></option>
						<option value="阶梯式" <c:if test="${浇注系统方式1 == '阶梯式'}">selected</c:if>><c:out value="阶梯式" /></option>
						<option value="侧注式" <c:if test="${浇注系统方式1 == '侧注式'}">selected</c:if>><c:out value="侧注式" /></option>
						<option value="顶注式" <c:if test="${浇注系统方式1 == '顶注式'}">selected</c:if>><c:out value="顶注式" /></option>
						<option value="侧注及顶注式" <c:if test="${浇注系统方式1 == '侧注及顶注式'}">selected</c:if>><c:out value="侧注及顶注式" /></option>
				</select></td>
				<td>锅台<input type="text" name="锅台1" value="<c:out value="${锅台1}" />"/></td>
			</tr>
			<tr>
				<td colspan="2">浇注系统比例<input type="text" name="浇注系统比例" value="<c:out value="${浇注系统比例}" />" /></td>
				<td colspan="2">浇注系统比例<input type="text" name="浇注系统比例1"
					value="<c:out value="${浇注系统比例1}" />" /></td>
			</tr>
			<tr>
				<td colspan="2">浇注系统说明<input type="text" name="浇注系统说明" value="<c:out value="${浇注系统说明}" />" /></td>
				<td colspan="2">浇注系统说明<input type="text" name="浇注系统说明1"
					value="<c:out value="${浇注系统说明1}" />" /></td>
			</tr>
			<tr>
				<td colspan="7">备注<input type="text" name="备注" value="<c:out value="${备注}" />" /></td>
			</tr>
		</table>



	</form>


</body>
<script type="text/javascript">
	function dealPackage() {
		document.form1.action = "changemainpackage.do";
		document.form1.submit();
	}
function pxAction(){
	document.form1.action = "changemainpackage.do";
	document.form1.submit();
}
</script>

</html>