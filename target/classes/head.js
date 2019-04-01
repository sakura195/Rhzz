function submit(){
	document.form1.submit();
	
}
function show1(){
	cygj.style.display="block";
}
function hide1(){
	cygj.style.display="none";
}
function show2(){
	jsgy.style.display="block";
}
function hide2(){
	jsgy.style.display="none";
}
function Mokuai(){
	document.form1.action="mokuai.do";
	document.form1.submit();
}
function Changejurisdiction(){
	document.form1.action="jurisdiction.do";
	document.form1.submit();
}
function Packingprocess(){
	document.form1.action="Packingprocess.do";
	document.form1.submit();
}
function logout(){
	document.form1.action="Loginout.do";
	document.form1.submit();
}

