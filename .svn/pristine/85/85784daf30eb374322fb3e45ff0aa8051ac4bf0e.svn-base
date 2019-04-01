<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>内部编号变更</title>
</head>
<body>
	<form name="form1" action="nbbhbgcz.do" method="post"  style="margin-top:100px;text-align:center;">
	<input type="hidden" name="type" value=""/>
<!-- 	<div>
	<h2>单个编号变更(不允许内部编号重复，系统内已存编号不能相同)</h2>
	更改前内部编号：<input type="text" name="nbbh" ></input>
	订单细则号：<input type="text" name="xzh"></input>
	更改后内部编号：<input type="text" name="nbbh2"></input><br>
	</div>
	<button type="button" onclick="tijiao()">提交</button> -->
	<div id="append">
	<h2>批量编号变更</h2>
	<div class="a">
	更改前内部编号：<input type="text" name="bh"></input>订单细则号：<input type="text" name="xz">更改后内部编号：<input type="text" name="bh2"><br>
	</div>
	</div>
	<br>
	<button type="button" onclick="tianj()">添加</button>
	<button type="button" onclick="remov()">删除</button>
	<button type="button" onclick="tijiao2()">提交</button>
	
	</form>


</body>
<script type="text/javascript">
function remov(){
	var a=document.getElementsByClassName("a");
	var s=document.getElementsByName('bh').length -1;
	a[s].remove();
}
	function tijiao() {
		document.form1.type.value = "0";
		document.form1.submit();
	}
	function tijiao2() {
		document.form1.type.value = "1";
		var s=document.getElementsByName('bh').length;
		var flag=true;
	 	for(var b=0;b<s;b++){
			if(document.getElementsByName('bh')[b].value==''){
				alert('内部编号'+b+'不能为空');
			flag=false;
			break;
			}
			if(document.getElementsByName('xz')[b].value==''){
				alert('细则号'+b+'不能为空');
			flag=false;
			break;
			}
			if(document.getElementsByName('bh2')[b].value==''){
				alert('更改后内部编号'+b+'不能为空');
			flag=false;
			break;
			}
		} 
		if(flag==false)
		{
		return false;
		}
		
		
		
		
		document.form1.submit();
		
	}
	function tianj(){
		 $("#append").append("<div class='a'>更改前内部编号：<input type='text' name='bh'></input>订单细则号：<input type='text' name='xz'>更改后内部编号：<input type='text' name='bh2'><br></div>");
	}
</script>
</html>