<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行结果</title>

</head>
<body>
<form name="form1" action="uploadPic.do" method="post"   enctype="multipart/form-data">
<div style="display:none">
<%@ include file="head.jsp"%>
</div>
<img id="pic3" src="/upload/1-12121R03018.jpg" width="600px" alt="">
    <input type='file' style="width: 200px;" name='picPaths3'id='picPaths3'           
    onchange="showPic(this, 3);" />  
    <input type="hidden" name="sbpic" value="1"/>
<button type="button" onclick="dosubmit('<c:out value='${no}'/>')">提交</button>
<input type="hidden" name="no" value="<c:out value='${no}'/>"/>
</form>
<script>
function showPic(obj, id) { 
    var newPreview = document.getElementById('pic'+id); 
    if (obj) { 
        //ie浏览器兼容 
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) { 
            obj.select(); 
            newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);"; 

            var path = document.selection.createRange().text;
            var flag = judgeImgSuffix(path);
            if(flag){
                newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document.selection.createRange().text;
            }else{
                alert("要求图片格式为png,jpg,jpeg,bmp");
            }

            return; 
        } 
        //firefox浏览器兼容 
        else { 
            if (obj.files) { 
                newPreview.src = window.URL.createObjectURL(obj.files.item(0)); 
                return; 
            } 
            newPreview.src = obj.value; 
                    return; 
                } 
                newPreview.src = obj.value; 
                return; 
            } 
        } 

        function judgeImgSuffix(path){
            var index = path.lastIndexOf('.');
            var suffix = "";
            if(index > 0){
                suffix = path.substring(index+1);
            }
            if("png"==suffix || "jpg"==suffix || "jpeg"==suffix || "bmp"==suffix || "PNG"==suffix || "JPG"==suffix || "JPEG"==suffix || "BMP"==suffix){
                return true;
            }else{
                return false;
            }

        }
function dosubmit(no){
	var s1=document.form1.picPaths3.value;
	if(s1==""||s1=="undefined"){
		alert("图片不能为空，请选择图片后上传");
		return false;
	}
	document.form1.submit();
	
}
</script>


</body>
</html>