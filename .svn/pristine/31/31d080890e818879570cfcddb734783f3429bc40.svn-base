<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


<head>
    <title>上传</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge" >
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <style type="text/css">
    #dropzone {
        width: 300px;
        height: 300px;
        border: 2px dashed gray;
    }

    #dropzone.over {
        width: 300px;
        height: 300px;
        border: 2px dashed red;
    }
    </style>
</head>

<body>
<div style="display:none;">
<%@ include file="/WEB-INF/zh_CN/head.jsp"%>
</div>
<form name="form1" action="downloading.do" method="post">  
<h2 style="text-align:center;">请往方框中拖入需要转换的文件并选择时间,不选择默认为当天</h2>
   <div style="text-align:center;margin-top:80px;">时间  <input class="Wdate" type="text"
					onClick="WdatePicker()" name="time" value=""/></div>
  <input type="hidden" name="realtext" />
    <div id="dropzone" dropEffect="link" style="margin:auto"></div>
</form>
</body>
<script type="text/javascript">
function uploadFile(formData) {
	$.ajax({
		 type : "POST",  
	        url : "gszh.do",  
	        data : formData,
	        async: false,  
	        cache: false,  
	        contentType: false,  
	        processData: false,
	        success : function(msg) {
	        document.form1.realtext.value=msg;
	        document.form1.submit();
	        }  
	});
}
var dz = document.getElementById('dropzone');
dz.ondragover = function(ev) {
    //阻止浏览器默认打开文件的操作
    ev.preventDefault();
    this.className = 'over';
}

dz.ondragleave = function() {
    this.className = '';
}

dz.ondrop = function(ev) {
    this.className = '';
    //阻止浏览器默认打开文件的操作
    ev.preventDefault();
    //表单上传文件
    var formData = new FormData();
    formData.append('file', ev.dataTransfer.files[0]);
    formData.append('time', document.form1.time.value);
    uploadFile(formData);
}
</script>

</html>