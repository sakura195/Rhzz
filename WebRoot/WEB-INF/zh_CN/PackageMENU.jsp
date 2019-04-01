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
	<form name="form1" action="Packingprocess.do" method="post"
		style="margin-top: 100px;">
		<input type="hidden" name="page" value="<c:out value='${pageNum}'/>" />
		<input type="hidden" name="nextorback" /> <input type="hidden"
			name="Flag" />
		<input type="hidden" name="coverPage" value="15" />
		<input type="hidden" name="工艺单编号" value=""/>
		<button type="button" onclick="dealUnPackage()">工艺排箱代办区</button>
		<table class="table" style="margin-top: 20px;">
			<tbody>
				<tr>
					<td>工艺单编号</td>
					<td>排箱号</td>
					<td>排箱号1</td>
					<td>技术员</td>
					<td>制定时间</td>
					<td>评审员</td>
					<td>评审时间</td>
					<td>图片</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${LoopResult}" var="item">
					<tr>
						<td><c:out value="${item.工艺单编号}" /></td>
						<td><c:out value="${item.排箱号}" /></td>
						<td><c:out value="${item.排箱号1}" /></td>
						<td><c:out value="${item.技术员}" /></td>
						<td><c:out value="${item.制定时间}" /></td>
						<td><c:out value="${item.评审员}" /></td>
						<td><c:out value="${item.评审时间}" /></td>
						<td><c:out value="${item.图片}" /></td>
						<td><button type="button" onclick="changeDetail('<c:out value="${item.工艺单编号}" />')">修改</button>
							<button type="button" onclick="deleteDetail('<c:out value="${item.工艺单编号}" />')">删除</button>
							<button type="button" onclick="uploadPic('<c:out value="${item.工艺单编号}" />')">上传图片</button>
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<button type="button" onclick="befopage()">上一页</button>
		<button type="button" onclick="nextpage()">下一页</button>
	</form>


</body>
<script type="text/javascript">
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
	function dealUnPackage(){
		document.form1.action="dealwithPackage.do";
		document.form1.submit();
	}
	function deleteDetail(a){	
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/deleteDetail.do",
			dataType : "json",
			data : "工艺单编号=" + a,
			statusCode : {
				404 : function() {
					alert('删除数据失败');
				}
			},
			success : function(data) {
				alert(data);
				document.form1.action="Packingprocess.do";
				document.form1.submit();
				
			}
		});
		
		
	}
	function  changeDetail(a){ 
		 var url;                                 //转向网页的地址;
		  var name;                           //网页名称，可为空;
		  var iWidth=1000;                          //弹出窗口的宽度;
		  var iHeight=500;                        //弹出窗口的高度;
		  var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
		  var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
		  window.open("choicedelete.do?no="+a+"","选择修改内容",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		
	}
	function uploadPic(a){
		 var url;                                 //转向网页的地址;
		  var name;                           //网页名称，可为空;
		  var iWidth=1000;                          //弹出窗口的宽度;
		  var iHeight=500;                        //弹出窗口的高度;
		  var iTop = (window.screen.availHeight-30-iHeight)/2;       //获得窗口的垂直位置;
		  var iLeft = (window.screen.availWidth-10-iWidth)/2;           //获得窗口的水平位置;
		  window.open("upload.do?no="+a+"","上传排箱图片",'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
		
		
		
	}
</script>
</html>