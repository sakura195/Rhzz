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
		<input type="hidden" name="listsize"
			value="<c:out value="${listsize}" />" />
		<button type="button" onclick="dealUnPackage()">工艺排箱代办区</button>
		<button name="dealwithPackage" type="button" onclick="dealPackage()">工艺排箱工作区</button>
		<button name="pxA" type="button" onclick="pxAction()">排箱</button>
		<table cellspacing=0>
			<tr>
				<td>技术员<input type="text" name="技术员"
					value="<c:out value="${uname}" />" /></td>
				<td>制定时间<input class="Wdate" type="text"
					onClick="WdatePicker()" name="制定时间"
					value="<c:out value="${nowtime}" />"></td>
				<td>工艺单编号<input type="text" name="工艺单编号"
					value="<c:out value="${PackagelistNo}" />" /></td>
				<td colspan="2">评审员<input type="text" name="评审员" value="" /></td>
				<td colspan="2">评审时间<input class="Wdate" type="text"
					onClick="WdatePicker()" name="评审时间" value=""></td>
			</tr>
			<tr>
				<td>排箱号<input type="text" name="排箱号"
					value="<c:out value="${PackageNo}" />" /></td>
				<td>类型<select name="拉延模">
						<option value="普通"><c:out value="普通" /></option>
						<option value="拉延凸模"><c:out value="拉延凸模" /></option>
						<option value="拉延凹模"><c:out value="拉延凹模" /></option>
						<option value="小件"><c:out value="小件" /></option>
						<option value="压边圈"><c:out value="压边圈" /></option>
				</select></td>
				<td colspan="3" rowspan="7">图片</td>
				<td>排箱号<select name="排箱号1">
						<option value=""></option>
						<option value="<c:out value="${PackageNo}" />-1"><c:out
								value="${PackageNo}" />-1
						</option>
				</select>
				</td>
				<td>类型<select name="拉延模1">
						<option value="普通"><c:out value="普通" /></option>
						<option value="拉延凸模"><c:out value="拉延凸模" /></option>
						<option value="拉延凹模"><c:out value="拉延凹模" /></option>
						<option value="小件"><c:out value="小件" /></option>
						<option value="压边圈"><c:out value="压边圈" /></option>
				</select></td>
			</tr>
			<tr>
				<td>材质<select name="材质">
						<c:forEach items="${qualitylist}" var="item">
							<option value="<c:out value="${item}" />"><c:out
									value="${item}" /></option>
						</c:forEach>
				</select>
				</td>

				<td>浇注总重<input type="hidden" name="浇注总重" value=""
					readonly="readonly" /></td>
				<td>材质<select name="材质1">
						<c:forEach items="${qualitylist}" var="item">
							<option value="<c:out value="${item}" />"><c:out
									value="${item}" /></option>
						</c:forEach>
				</select></td>

				<td>浇注总重<input type="hidden" name="浇注总重1" value=""
					readonly="readonly" /></td>
			</tr>
			<tr>
				<td>砂箱<select name="砂箱">
						<option value="0#砂箱"><c:out value="0#砂箱" /></option>
						<option value="1#砂箱"><c:out value="1#砂箱" /></option>
						<option value="2#砂箱"><c:out value="2#砂箱" /></option>
						<option value="3#砂箱"><c:out value="3#砂箱" /></option>
						<option value="4#砂箱"><c:out value="4#砂箱" /></option>
				</select></td>
				<td>出气口<select name="出气口">
						<option value="Φ30"><c:out value="Φ30" /></option>
						<option value="负压"><c:out value="负压" /></option>
				</select></td>
				<td>砂箱<select name="砂箱1">
						<option value="0#砂箱"><c:out value="0#砂箱" /></option>
						<option value="1#砂箱"><c:out value="1#砂箱" /></option>
						<option value="2#砂箱"><c:out value="2#砂箱" /></option>
						<option value="3#砂箱"><c:out value="3#砂箱" /></option>
						<option value="4#砂箱"><c:out value="4#砂箱" /></option>
				</select></td>
				<td>出气口<select name="出气口1">
						<option value="Φ30"><c:out value="Φ30" /></option>
						<option value="负压"><c:out value="负压" /></option>
				</select></td>
			</tr>
			<tr>
				<td>浇注时长<input type="hidden" name="浇注时间" value=""
					readonly="readonly" /></td>
				<td>浇注温度<select name="浇注温度">
						<option value="1365"><c:out value="1365±5℃" /></option>
						<option value="1375"><c:out value="1375±5℃" /></option>
						<option value="1385"><c:out value="1385±5℃" /></option>
						<option value="1390"><c:out value="1390±5℃" /></option>
						<option value="1395"><c:out value="1395±5℃" /></option>
						<option value="1400"><c:out value="1400±5℃" /></option>
				</select></td>
				<td>浇注时长<input type="hidden" name="浇注时间1" value=""
					readonly="readonly" /></td>
				<td>浇注温度<select name="浇注温度1">
						<option value="1365"><c:out value="1365±5℃" /></option>
						<option value="1375"><c:out value="1375±5℃" /></option>
						<option value="1385"><c:out value="1385±5℃" /></option>
						<option value="1390"><c:out value="1390±5℃" /></option>
						<option value="1395"><c:out value="1395±5℃" /></option>
						<option value="1400"><c:out value="1400±5℃" /></option>
				</select></td>
			</tr>
			<tr>
				<td>浇注系统方式<select name="浇注系统方式">
						<option value="底注式"><c:out value="底注式" /></option>
						<option value="阶梯式"><c:out value="阶梯式" /></option>
						<option value="侧注式"><c:out value="侧注式" /></option>
						<option value="顶注式"><c:out value="顶注式" /></option>
						<option value="侧注及顶注式"><c:out value="侧注及顶注式" /></option>
				</select></td>
				<td>锅台<select name="锅台">
						<option value="否"><c:out value="否" /></option>
						<option value="250"><c:out value="250" /></option>
						<option value="500"><c:out value="500" /></option>
				</select></td>
				<td>浇注系统方式<select name="浇注系统方式1">
						<option value="底注式"><c:out value="底注式" /></option>
						<option value="阶梯式"><c:out value="阶梯式" /></option>
						<option value="侧注式"><c:out value="侧注式" /></option>
						<option value="顶注式"><c:out value="顶注式" /></option>
						<option value="侧注及顶注式"><c:out value="侧注及顶注式" /></option>
				</select></td>
				<td>锅台<select name="锅台1">
						<option value="否"><c:out value="否" /></option>
						<option value="250"><c:out value="250" /></option>
						<option value="500"><c:out value="500" /></option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2">浇注系统比例<input type="text" name="浇注系统比例" value="" /></td>
				<td colspan="2">浇注系统比例<input type="text" name="浇注系统比例1"
					value="" /></td>
			</tr>
			<tr>
				<td colspan="2">浇注系统说明<input type="text" name="浇注系统说明" value="" /></td>
				<td colspan="2">浇注系统说明<input type="text" name="浇注系统说明1"
					value="" /></td>
			</tr>
			<tr>
				<td colspan="7">备注<input type="text" name="备注" value="" /></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>
					<table>
						<tr>
							<td>类型</td>
							<td>规格</td>
							<td>数量</td>
							<td>排箱号</td>
							<td>工艺单编号</td>
						</tr>
						<tr>
							<td>直浇道</td>
							<td><select name="gg1"  onchange="calculate()">
									<c:forEach items="${guige1}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="s1" value="" onchange="calculate()"/></td>
							<td><c:out value="${PackageNo}" /></td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>横浇道</td>
							<td><select name="gg2"  onchange="calculate()">
									<c:forEach items="${guige2}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="s2" value="" onchange="calculate()" /></td>
							<td><c:out value="${PackageNo}" /></td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>内浇口</td>
							<td><select name="gg3"  onchange="calculate()">
									<c:forEach items="${guige3}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="s3" value="" onchange="calculate()" /></td>
							<td><c:out value="${PackageNo}" /></td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>内浇口</td>
							<td><select name="gg4"  onchange="calculate()">
									<c:forEach items="${guige3}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="s4" value="0" onchange="calculate()"/></td>
							<td><c:out value="${PackageNo}" /></td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
					</table>
				</td>
				<td style="padding-left: 305px;">
					<table>
						<tr>
							<td>类型</td>
							<td>规格</td>
							<td>数量</td>
							<td>排箱号</td>
							<td>工艺单编号</td>
						</tr>
						<tr>
							<td>直浇道</td>
							<td><select name="gg11" onchange="calculate1()">
									<c:forEach items="${guige1}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="sl11" value="" onchange="calculate1()"/></td>
							<td><c:out value="${PackageNo}" />-1</td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>横浇道</td>
							<td><select name="gg12" onchange="calculate1()">
									<c:forEach items="${guige2}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="sl12" value="" onchange="calculate1()"/></td>
							<td><c:out value="${PackageNo}" />-1</td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>内浇口</td>
							<td><select name="gg13" onchange="calculate1()">
									<c:forEach items="${guige3}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="sl13" value="" onchange="calculate1()"/></td>
							<td><c:out value="${PackageNo}" />-1</td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
						<tr>
							<td>内浇口</td>
							<td><select name="gg14" onchange="calculate1()">
									<c:forEach items="${guige3}" var="item">
										<option value="<c:out value="${item}" />"><c:out
												value="${item}" /></option>
									</c:forEach>
							</select></td>
							<td><input type="text" name="sl14" value="0"onchange="calculate1()" /></td>
							<td><c:out value="${PackageNo}" />-1</td>
							<td><c:out value="${PackagelistNo}" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table>
			<c:forEach items="${LoopResult}" var="item" varStatus="status">
				<tr>
					<td>排箱号<select name="排箱号细则">
							<option value="<c:out value="${PackageNo}" />" <c:if test="${item.排箱号细则 == PackageNo}">selected</c:if>><c:out
									value="${PackageNo}" /></option>
							<option value="<c:out value="${PackageNo1}" />" <c:if test="${item.排箱号细则 == PackageNo1}">selected</c:if>><c:out
									value="${PackageNo1}" />
							</option>
					</select>
					</td>
					<td>铸件编号<input type="text" name="铸件编号"
						value="<c:out value="${item.铸件编号}" />"></input>
					</td>
					<td>材质<input type="text" name="材质3"
						value="<c:out value="${item.材质}" />"></input>
					</td>
					<td>单重<input type="text" name="单重" value=""></input>
					</td>
					<td>内浇口数量<input type="text" name="内浇口数量" value=""></input>
					</td>
					<td>订单细则号<input type="text" name="订单细则号"
						value="<c:out value="${item.订单细则号}" />"></input>
					</td>
					<td>密度比<input type="text" name="密度比"
						value="<c:out value="${item.密度比}" />"></input>
					</td>
					<td>内部编号<input type="text" name="内部编号"
						value="<c:out value="${item.内部编号}" />"></input>
					</td>
					<td>备注<input type="text" name="备注" value=""></input>
					</td>
					<td>客户简称<input type="text" name="客户简称"
						value="<c:out value="${item.客户简称}" />"></input>
					</td>
				</tr>
			</c:forEach>
		</table>



	</form>


</body>
<script type="text/javascript">
	function dealPackage() {
		document.form1.action = "Packingprocess.do";
		document.form1.submit();
	}
	function dealUnPackage() {
		document.form1.action = "dealwithPackage.do";
		document.form1.submit();
	}

	function pxAction() {
		var s1=document.form1.listsize.value;
		for(var i=0;i<s1;i++){
			if(document.form1.单重[i].value==0||document.form1.单重[i].value==''){
				alert("单重不可为空，请重新填写"+document.form1.订单细则号[i].value+"条数据后提交");
				return false;
			}
			if(document.form1.内浇口数量[i].value==0||document.form1.内浇口数量[i].value==''){
				alert("内浇口数量不可为空，请重新填写"+document.form1.订单细则号[i].value+"条数据后提交");
				return false;
			}
		}
		
		document.form1.action = "px.do";
		document.form1.submit();
	}
	function calculate(){
		var a1;
		var b1=document.form1.gg1.value;
			if(b1=='Φ50'){
			a1=1962.5;
		}else if (b1=='Φ70'){
			a1=3846.5;
		}else if (b1=='Φ100'){
			a1=7850;
		}
		else if (b1=='Φ110'){
			a1=9498.5;
		}
		else if (b1=='Φ120'){
			a1=11304;
		}
		else if (b1=='Φ130'){
			a1=13266.5;
		}
		else if (b1=='Φ140'){
			a1=15386;
		}
		else if (b1=='Φ150'){
			a1=17662.5;
		}
		else if (b1=='Φ160'){
			a1=20096;
		}
			if(document.form1.s1.value!=""&&document.form1.s1.value!="0"){
				a1=document.form1.s1.value*a1;
			}
			
			var a2;  
			var b2=document.form1.gg2.value;
			if(b2=="Φ70"){
				a2=3846.5;
			}else if(b2=="80*40"){
				a2=3200;
			}else if(b2=="80*60"){
				a2=4800;
			}else if(b2=="80*80"){
				a2=6400;
			}else if(b2=="80*100"){
				a2=8000;
			}else if(b2=="80*120"){
				a2=9600;
			}
			if(document.form1.s2.value!=""&&document.form1.s2.value!="0"){
				a2=document.form1.s2.value*a2;
			}
			
			var a3;
			var b3=document.form1.gg3.value;
			if(b3=="Φ50")
				{
				a3=1962.5;}
			else if(b3=="80*25")
				{a3=2000;}
			else if(b3=="80*30")
			{a3=2400;}
			if(document.form1.s3.value!=""&&document.form1.s3.value!="0"){
				a3=document.form1.s3.value*a3;
			}
			var a4;
			var b4=document.form1.gg4.value;
			if(b4=="Φ50")
				{
				a4=1962.5;}
			else if(b4=="80*25")
				{a4=2000;}
			else if(b4=="80*30")
			{a4=2400;}
			if(document.form1.s4.value!=""&&document.form1.s4.value!="0"){
				a4=document.form1.s4.value*a4;
			}
			if(document.form1.s4.value!=""&&document.form1.s4.value!="0")
			{
				if(document.form1.s1.value!=""&&document.form1.s1.value!="0"&&document.form1.s2.value!=""&&document.form1.s2.value!="0"&&document.form1.s3.value!=""&&document.form1.s3.value!="0")
			{
					a3=a4+a3;
					a5=a2/a1;
					a6=a3/a1;
					document.form1.浇注系统比例.value="1:"+a5.toFixed(2)+":"+a6.toFixed(2);
					document.form1.浇注系统说明.value="直浇道:"+b1+" 数量:"+document.form1.s1.value+"横浇道:"+b2+"数量:"+document.form1.s2.value+"内浇口"+b3+"数量:"+document.form1.s3.value+"内浇口"+b4+"数量:"+document.form1.s4.value;
			}
			
			}else{
				if(document.form1.s1.value!=""&&document.form1.s1.value!="0"&&document.form1.s2.value!=""&&document.form1.s2.value!="0"&&document.form1.s3.value!=""&&document.form1.s3.value!="0")
				{
						a5=a2/a1;
						a6=a3/a1;
						document.form1.浇注系统比例.value="1:"+a5.toFixed(2)+":"+a6.toFixed(2);
						document.form1.浇注系统说明.value="直浇道:"+b1+" 数量:"+document.form1.s1.value+"横浇道:"+b2+"数量:"+document.form1.s2.value+"内浇口"+b3+"数量:"+document.form1.s3.value;

				}
			}
			
		}


		function calculate1(){
		var a1;
		var b1=document.form1.gg11.value;
			if(b1=='Φ50'){
			a1=1962.5;
		}else if (b1=='Φ70'){
			a1=3846.5;
		}else if (b1=='Φ100'){
			a1=7850;
		}
		else if (b1=='Φ110'){
			a1=9498.5;
		}
		else if (b1=='Φ120'){
			a1=11304;
		}
		else if (b1=='Φ130'){
			a1=13266.5;
		}
		else if (b1=='Φ140'){
			a1=15386;
		}
		else if (b1=='Φ150'){
			a1=17662.5;
		}
		else if (b1=='Φ160'){
			a1=20096;
		}
			if(document.form1.sl11.value!=""&&document.form1.sl11.value!="0"){
				a1=document.form1.sl11.value*a1;
			}
			
			var a2;  
			var b2=document.form1.gg12.value;
			if(b2=="Φ70"){
				a2=3846.5;
			}else if(b2=="80*40"){
				a2=3200;
			}else if(b2=="80*60"){
				a2=4800;
			}else if(b2=="80*80"){
				a2=6400;
			}else if(b2=="80*100"){
				a2=8000;
			}else if(b2=="80*120"){
				a2=9600;
			}
			if(document.form1.sl12.value!=""&&document.form1.sl12.value!="0"){
				a2=document.form1.sl12.value*a2;
			}
			
			var a3;
			var b3=document.form1.gg13.value;
			if(b3=="Φ50")
				{
				a3=1962.5;}
			else if(b3=="80*25")
				{a3=2000;}
			else if(b3=="80*30")
			{a3=2400;}
			if(document.form1.sl13.value!=""&&document.form1.sl13.value!="0"){
				a3=document.form1.sl13.value*a3;
			}
			var a4;
			var b4=document.form1.gg14.value;
			if(b4=="Φ50")
				{
				a4=1962.5;}
			else if(b4=="80*25")
				{a4=2000;}
			else if(b4=="80*30")
			{a4=2400;}
			if(document.form1.sl14.value!=""&&document.form1.sl14.value!="0"){
				a4=document.form1.sl14.value*a4;
			}
			if(document.form1.sl14.value!=""&&document.form1.sl14.value!="0")
			{
				if(document.form1.sl11.value!=""&&document.form1.sl11.value!="0"&&document.form1.sl12.value!=""&&document.form1.sl12.value!="0"&&document.form1.sl13.value!=""&&document.form1.sl13.value!="0")
			{
					a3=a4+a3;
					a5=a2/a1;
					a6=a3/a1;
					document.form1.浇注系统比例1.value="1:"+a5.toFixed(2)+":"+a6.toFixed(2);
					document.form1.浇注系统说明1.value="直浇道: "+b1+"   数量:"+document.form1.sl11.value+"横浇道: "+b2+"   数量:"+document.form1.sl12.value+"内浇口: "+b3+"   数量:"+document.form1.sl13.value+"内浇口: "+b4+"   数量:"+document.form1.sl14.value;
			}
			
			}else{
				if(document.form1.sl11.value!=""&&document.form1.sl11.value!="0"&&document.form1.sl12.value!=""&&document.form1.sl12.value!="0"&&document.form1.sl13.value!=""&&document.form1.sl13.value!="0")
				{
						a5=a2/a1;
						a6=a3/a1;
						document.form1.浇注系统比例1.value="1:"+a5.toFixed(2)+":"+a6.toFixed(2);
						document.form1.浇注系统说明1.value="直浇道: "+b1+"   数量:"+document.form1.sl11.value+"横浇道: "+b2+"   数量:"+document.form1.sl12.value+"内浇口: "+b3+"   数量:"+document.form1.sl13.value;

				}
			}
			
		}
</script>

</html>