<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排箱细则</title>
</head>
<body>
<form name="form1" action="detailPackagepool.do" method="post">
	<input type="hidden" name="funct" value="update"/>
	<input type="hidden" name="number" value=""/>
	<input type="hidden" name="listsize" value="<c:out value="${listsize}" />"/>
	<input type="hidden" name="exece" value="0"/>
	
	<table id="detail">
	<tr>
	<td>工艺细则编号</td><td>排箱号</td><td>材质</td><td>单重</td><td>内浇口数量</td><td>订单细则号</td><td>密度比</td><td>内部编号</td><td>备注</td><td>客户简称</td>
	
	
	</tr>
		<c:forEach items="${LoopResult}" var="item" varStatus="status">
		<input type="hidden" name="工艺单编号" value="<c:out value="${item.工艺单编号}" />"/>
			<tr>
				<td><input type="text" style="width:100px"
					name="工艺细则编号"
					value="<c:out value="${item.工艺单细则号}" />" />
				</td>
				<td><input type="text" style="width:70px"
					name="排箱号"
					value="<c:out value="${item.排箱号}" />" />
				</td>
				<td><input type="text" style="width:50px"
					name="材质"
					value="<c:out value="${item.材质}" />"></input>
				</td>
				<td><input type="text" style="width:60px"
					name="单重"
					value="<c:out value="${item.单重}" />" onchange="doexe()"></input>
				</td>
				<td><input type="text" style="width:80px"
					name="内浇口数量"
					value="<c:out value="${item.内浇口数量}" />"></input>
				</td>
				<td><input type="text" style="width:100px"
					name="订单细则号"
					value="<c:out value="${item.订单细则号}" />"></input>
				</td>
				<td><input type="text" style="width:50px"
					name="密度比"
					value="<c:out value="${item.密度比}" />" onchange="doexe()"></input>
				</td>
				<td><input type="text" style="width:100px"
					name="内部编号"
					value="<c:out value="${item.内部编号}" />"></input>
				</td>
				<td><input type="text" style="width:80px"
					name="备注"
					value="<c:out value="${item.备注}" />"></input>
				</td>
				<td><input type="text" style="width:60px"
					name="客户简称"
					value="<c:out value="${item.客户简称}" />"></input>
				</td>
				<td><button onclick="updatedetail('<c:out value='${status.index}'/>')">更新</button></td>
				<td><button onclick="deletedeatil('<c:out value='${status.index}'/>')">删除</button></td>
			</tr>
		</c:forEach>
	</table>
	<button type="button" onclick="adddetail()">新增</button>
</form>
	
</body>
<script>
	function updatedetail(a){
		document.form1.funct.value="update";
		document.form1.number.value=a;
		document.form1.action="detailPackagepool.do";
		document.form1.submit();
	}
	
	function deletedeatil(a){
		document.form1.funct.value="delete"; 
		document.form1.number.value=a;
		document.form1.action="detailPackagepool.do";
		document.form1.submit();
	}
	
	function adddetail(){
		var a=document.getElementsByName('工艺单编号')[0].value;
		var b=document.getElementsByName('排箱号')[0].value;
		var c=document.form1.listsize.value;
		var d=Number(c)+1;
		$("#detail tbody").append("<tr><input type='hidden' name='工艺单编号'  value='"+a+"'></input><td><input type='text' style='width:100px'name='工艺细则编号' value='"+a+'-'+d+"' /></td><td><input type='text' style='width:70px' name='排箱号' value='"+b+"' /></td>   <td><input type='text' style='width:50px'name='材质'value=''></input></td><td><input type='text' style='width:60px'name='单重'value='' onchange='doexe()'></input></td><td><input type='text' style='width:80px'name='内浇口数量'value=''></input></td><td><input type='text' style='width:100px'name='订单细则号'value=''></input></td><td><input type='text' style='width:50px'name='密度比'value='' onchange='doexe()'></input></td><td><input type='text' style='width:100px'name='内部编号'value=''></input></td><td><input type='text' style='width:80px'name='备注'value=''></input></td><td><input type='text' style='width:60px'name='客户简称'value=''></input></td><td><button type='button' onclick='insertdeatil("+c+")'>插入</button></td></tr>");
	document.form1.listsize.value=d;
	}
	function insertdeatil(a){
		debugger;
		document.form1.funct.value="insert"; 
		document.form1.number.value=a;
		document.form1.action="detailPackagepool.do";
		var flag=false;
		for(var x=0;x<a;x++){
			if(document.getElementsByName('工艺细则编号')[x].value==document.getElementsByName('工艺细则编号')[a].value){
				alert("工艺细则编号不能相同，请调整后重新录入");
				flag=true;
				break;
			}
		}if(!flag){			
		document.form1.submit();
		}
	}
	function doexe(){
		document.form1.exece.value="1";
	}
	</script>
</html>