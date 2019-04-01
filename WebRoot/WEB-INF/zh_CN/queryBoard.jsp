<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${path}/css/head.css?t=1.06" rel="stylesheet" type="text/css"/>
<title>看板查询</title>
<style>
table {
	border-collapse: collapse;
}

table, td, th {
	border: 1px solid black;
}
.button {
	width: 134px;
	display: inline-block;
	padding: 6px 16px;
	font-size: 14px;
	font-weight: 400;
	border: 0;
	text-align: center;
	white-space: nowrap;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	background-color: #fff;
	color: black;
	border-radius: 5px;
	border: 1px solid #1774da;
	height: 40px;
}

.button, a:focus, a:hover {
	cursor: pointer;
}

button:hover {
	cursor: default;
	background-color: #eee;
	color: #808080 !important;
}
</style>
</head>
<body>
	<form name="form1" action="Packingprocess.do" method="post"style="margin-top: 100px;text-align:center;">
		<span style="font-size:30px;">看板类型</span>
		<select id="看板类型" style="font-size:30px;height:40px" name="看板类型">
                <option>模检</option>
                <option>实型</option>
        		<option>机加</option>
        		<option>解箱</option>
        		<option>入库</option>
        		<option>熔炼</option>
        		<option>清砂</option>
        </select>
        <span style="font-size:30px;">日期</span>
        <input class="Wdate" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="时间" value="<c:out value="${Date}" />" style="font-size:30px;height:40px;width:200px;"/>
		<button name="pxA" type="button" class="button" onclick="QueryAction()">查询</button>
		<table id="detail" cellspacing=0 style="margin:auto;margin-top:20px;width:1100px">
			
				
		</table>



	</form>
	<div class="bz-detail">
        <h5>备注:</h5><h5>1、本功能仅能查询到2019年2月20日之后的数据</h5>
        <h5>2、若查询只有标题无内容则表示当天看板无数据</h5>
    </div>

</body>
<script type="text/javascript">
function QueryAction() {
	var kind = document.form1.看板类型.value;
	var time=document.form1.时间.value;
	$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/queryBoard.do",
				dataType : "json",
				data :"看板类型="+kind+"&&"+"时间="+time,
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data) {
						 var tbody = window.document.getElementById("detail");
						 tbody.innerHTML="";
						 if(kind == "模检"){							 
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>排箱号</td><td style='width:10%'>砂箱</td><td style='width:10%'>整箱件数</td><td style='width:10%'>浇注材质</td><td style='width:10%'>整箱吨位</td><td style='width:10%'>等待作业</td></tr>";
						 }else if(kind =="机加"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>内部编号</td><td style='width:10%'>铸件名称</td><td style='width:10%'>工作机床</td><td style='width:10%'>工作内容</td><td style='width:10%'>计划时间</td><td style='width:10%'>机加交货期</td></tr>";	 
						 }else if(kind =="解箱"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>排箱编号</td><td style='width:10%'>浇注材质</td><td style='width:10%'>砂箱编号</td><td style='width:10%'>本箱总吨位</td><td style='width:10%'>本箱件数</td><td style='width:10%'>解箱时间</td></tr>";	 
						 }else if(kind =="实型"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>内部编号</td><td style='width:10%'>吨位</td><td style='width:10%'>编程计划</td><td style='width:10%'>机加计划</td><td style='width:10%'>手工计划</td><td style='width:10%'>打磨计划</td><td style='width:10%'>交检计划</td></tr>";	 
						 }else if(kind =="熔炼"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>排箱号</td><td style='width:10%'>备注</td><td style='width:10%'>浇注材质</td><td style='width:10%'>砂箱编号</td><td style='width:10%'>本箱吨位</td><td style='width:10%'>本箱件数</td><td style='width:10%'>计划日期</td></tr>";	 
						 }else if(kind =="清砂"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>排箱号</td><td style='width:10%'>内部编号</td><td style='width:10%'>吨位</td><td style='width:10%'>铸件材质</td><td style='width:10%'>解箱时间</td><td style='width:10%'>计划时间</td><td style='width:10%'>客户交期</td></tr>";	 
						 }else if(kind =="入库"){
						 tbody.innerHTML ="<tr><td style='width:10%'>序号</td><td style='width:10%'>内部编号</td><td style='width:10%'>清砂完成时间</td><td style='width:10%'>吨位</td><td style='width:10%'>铸件材质</td><td style='width:10%'>部件名称</td><td style='width:10%'>计划时间</td><td style='width:10%'>实际时间</td></tr>";	 
						 }
						 var item = data.length;
						 if((kind == "模检")||(kind == "机加")||(kind == "解箱")){	
					for (var i = 0; i < item; i++) {
						$("#detail tbody").append(
								"<tr><td>" + data[i].序号 + "</td>" + 
								"<td>" + data[i].A + "</td>" + 
								"<td>" + data[i].B + "</td>" + 
								"<td>" + data[i].C + "</td>" + 
								"<td>" + data[i].D + "</td>" + 
								"<td>" + data[i].E + "</td>" + 
								"<td>" + data[i].F + "</td>" + 
								"</tr>");
					}
					}else{
						for (var i = 0; i < item; i++) {
							$("#detail tbody").append(
									"<tr><td>" + data[i].序号 + "</td>" + 
									"<td>" + data[i].A + "</td>" + 
									"<td>" + data[i].B + "</td>" + 
									"<td>" + data[i].C + "</td>" + 
									"<td>" + data[i].D + "</td>" + 
									"<td>" + data[i].E + "</td>" + 
									"<td>" + data[i].F + "</td>" +
									"<td>" + data[i].G + "</td>" +
									"</tr>");
						}
					}
					if(kind == "模检"){
					for(var i=0;i<1;i++){
						$("#detail tbody").append("<tr><td>" +"当日模检吨位" +"</td>" + "<td>" + data[i].G+"</td>"+
													  "<td>" +"当日淋涂吨位" +"</td>" + "<td>" + data[i].H+"</td>"+
													  "<td>" +"当日补强吨位" +"</td>" + "<td>" + data[i].I+"</td>"+
													  "<td>"+"</td>"
						);
					}
					}else if(kind == "实型"){
						for(var i=0;i<1;i++){
							$("#detail tbody").append("<tr><td>" +"本月累计完成吨位" +"</td>" + "<td>" + data[i].H+"</td>"+
														  "<td>" +"实型在制件数" +"</td>" + "<td>" + data[i].I+"</td>"+
														  "<td>" +"" +"</td>" + "<td>" + data[i].J+"</td>"+"<td/>"+
														  "<td>"+"</td>"
							);
						}	
					}else if(kind == "机加"){
						for(var i=0;i<1;i++){
							$("#detail tbody").append("<tr><td>" +"今日发货数" +"</td>" + "<td>" + data[i].G+"</td>"+
														  "<td>" +"今日计划数" +"</td>" + "<td>" + data[i].H+"</td>"+
														  "<td>" +"" +"</td>" + "<td>" + data[i].I+"</td>"+
														  "<td>"+"</td>"
							);
						}	
					}else if(kind == "解箱"){
						for(var i=0;i<1;i++){
							$("#detail tbody").append("<tr><td>" +"今日待解箱吨位" +"</td>" + "<td>" + data[i].G+"</td>"+
														  "<td>" +"明日待解箱吨位" +"</td>" + "<td>" + data[i].H+"</td>"+
														  "<td>" +"后日待解箱吨位" +"</td>" + "<td>" + data[i].I+"</td>"+
														  "<td>"+"</td>"
							);
						}	
					}else if(kind == "熔炼"){
						for(var i=0;i<1;i++){
							$("#detail tbody").append("<tr><td>" +"今日造型计划吨位/箱数" +"</td>" + "<td>" + data[i].H+"</td>"+
														  "<td>" +"今日熔炼浇注吨位" +"</td>" + "<td>" + data[i].I+"</td>"+
														  "<td>" +"灰铁/球铁/珞铁吨位" +"</td>" + "<td>" + data[i].J+"</td>"+"<td/>"+
														  "<td>"+"</td>"
							);
						}
				}else if(kind == "清砂"){
					for(var i=0;i<1;i++){
						$("#detail tbody").append("<tr><td>" +"今日计划总吨位" +"</td>" + "<td>" + data[i].H+"</td>"+
													  "<td>" +"昨日清砂吨位" +"</td>" + "<td>" + data[i].I+"</td>"+
													  "<td>" +"今日计划清砂件数" +"</td>" + "<td>" + data[i].J+"</td>"+
													  "<td>"+"<td/>"+"</td>"
						);
					}
			}else if(kind == "入库"){
				for(var i=0;i<1;i++){
					$("#detail tbody").append("<tr><td>" +"昨日入库吨位" +"</td>" + "<td>" + data[i].H+"</td>"+
												  "<td>" +"待入库件数" +"</td>" + "<td>" + data[i].I+"</td>"+
												  "<td>" +"待入库总吨位" +"</td>" + "<td>" + data[i].J+"</td>"+"<td/>"+
												  "<td>"+"</td>"
					);
				}
		}
				}});
}
</script>

</html>