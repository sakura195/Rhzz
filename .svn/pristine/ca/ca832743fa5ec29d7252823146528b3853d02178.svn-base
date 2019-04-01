<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择模块</title>
</head>
<body>
<input type="hidden" name="no" value="<c:out value="${no}" />">
<button onclick="changemainpackage('<c:out value="${no}" />')">修改主表</button>
<button onclick="changedetailpackage('<c:out value="${no}" />')">修改细则</button>
<%-- <button onclick="changejiaokou('<c:out value="${no}" />')">修改浇口</button> --%>
</body>

<script>
function changemainpackage(a){
	 var url;                                 //转向网页的地址;
	  var name;                           //网页名称，可为空;
	  var iWidth=1000;                          //弹出窗口的宽度;
	  var iHeight=500;                        //弹出窗口的高度;
	  var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
	  var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
	  window.open("changemainpackage.do?no="+a+"","选择修改内容",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}
function changedetailpackage(a){
	var url;                                 //转向网页的地址;
	  var name;                           //网页名称，可为空;
	  var iWidth=1000;                          //弹出窗口的宽度;
	  var iHeight=500;                        //弹出窗口的高度;
	  var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
	  var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
	  window.open("changedetailpackage.do?no="+a+"","选择修改内容",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}
function changejiaokou(a){
	
}
</script>

</html>