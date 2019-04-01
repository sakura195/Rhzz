<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查询权限一览</title>
</head>
<body>
<form name="form1" action="ChangJurisdic.action" method="post">
<input name="number"  type="hidden" value="<c:out value='${number}'/>">
<table id="table1">

<tr><td>部门</td><td>用户名</td><td>用户权限</td><td>是否变更</td></tr>
<c:forEach items="${LoopResult}" var="item">
<tr><td><div><c:out value='${item.用户组}'/></div></td>
<td><div><c:out value='${item.登陆名}'/></div></td>
<td>
<select name="M<c:out value='${item.用户ID}'/>">
<option  <c:if test="${item.当前查询权限位 eq '1'}">selected</c:if>   value="1">管理</option>
<option  <c:if test="${item.当前查询权限位 eq '3'}">selected</c:if>   value="3">浏览</option>
<option  <c:if test="${item.当前查询权限位 eq '2'}">selected</c:if>   value="2">录入</option>
<option  <c:if test="${(item.当前查询权限位 eq '0' )||(item.当前查询权限位 == null)||item.当前查询权限位 eq ' ' }">selected</c:if>   value="0">无权限</option>
</select>
</td>
<td>
<button type="button"  onclick="changUserStatus(M<c:out value='${item.用户ID}'/>)">变更</button>
</td>
</tr>
</c:forEach>
</table>
<div id="table2">
<div>用户组权限批量修改</div>
<select name="Group">
<option value="财务部">财务部</option>
<option value="仓库">仓库</option>
<option value="管理部">管理部</option>
<option value="管理用户">管理用户</option>
<option value="技术部">技术部</option>
<option value="商务部">商务部</option>
<option value="生产部">生产部</option>
<option value="虚拟用户">虚拟用户</option>
<option value="质量保证部">质量保证部</option>
<option value="总经办">总经办</option>
<option value="所有用户">所有用户</option>
</select>
<select name="character">
<option value="0">无权限</option>
<option value="1">管理</option>
<option value="2">录入</option>
<option value="3">浏览</option>
</select>
<button type="button"  onclick="changgroupStatus()">变更</button>

</div>
<div>
<select name="character2">
<option value="0">无权限</option>
<option value="1">管理</option>
<option value="2">录入</option>
<option value="3">浏览</option>
</select>
<button type="button" onclick="changeAllUser()">变更所有用户(不包含权限大于当前的用户)</button>
</div>


</form>
</body>
<script>
function changUserStatus(a){
	var s1=a.name;
	s1=s1.substring(1,5);
	var s2=a.value;
	var s3=document.form1.number.value;
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/UpdateUserByID.do",
		dataType : "json",
		data :{用户编号:s1,变更权限位:s2,number:s3},
		statusCode : {
			404 : function() {
				alert('page not found');
			}
		},
		success : function(data) {
			var s2="已执行"+data+"条数据";
			alert(s2);
			}

		}
	);
	
	
	}
function changeAllUser(){changgroupStatus
	var character=document.form1.character2.value;
	var s3=document.form1.number.value;
	if(confirm(" 即将变更所有用户，是否确认？")) {
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/UpdateAllUser.do",
		dataType : "json",
		data :{Character:character,number:s3},
		statusCode : {
			404 : function() {
				alert('page not found');
			}
		},
		success : function(data) {
			var s2="已执行"+data+"条数据";
			alert(s2);
			}

		}
	);
	
}}
function changgroupStatus(){
	
	var character=document.form1.character.value;
	var group=document.form1.Group.value;
	var s3=document.form1.number.value;
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath}/UpdateUserByGroup.do",
		dataType : "json",
		data :{Character:character,Group:group,number:s3},
		statusCode : {
			404 : function() {
				alert('page not found');
			}
		},
		success : function(data) {
			var s2="已执行"+data+"条数据";
			alert(s2);
			}

		}
	);
}
</script>
</html>