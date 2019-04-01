/**~~~~~~������ start ~~~~~~~**/
//�½������㲢��֮��ӵ�top�У�
function creatPopups(popupContent) {
    var popupsBackdrop = $('<div id="popupsBackdrop" class="popupsBackdrop"></div>');
    var popups = $('<div id="popups" class="popups"></div>').append(popupContent);
    
    function close () {
    	popupsBackdrop.detach();
        popups.detach();
    }
    function open () {
    	popupsBackdrop.appendTo(window.top.document.body);
        popups.appendTo(window.top.document.body);
    };
    this.open = open;
    this.close = close;
    this.modal=popups;
}
//���������㣬�����ֱ�Ϊ��trans�����⣬��ȣ��߶ȣ��Ƿ��йرհ�ť���Ƿ���ʾ��������
function openPopup(src,title,width,height,btnFlag){
   var pupModal=new creatPopups($("#pubPopupModal").html());
   var pupModalContent = pupModal.modal.children();
   var popIframe=pupModal.modal.find("iframe")[0];
   var popTitle=pupModal.modal.find("span")[0];
   var popupDivTop=pupModal.modal.find("#popupDivTop");
   var popupDivTopDiv=pupModal.modal.find("#popupDivTopDiv");
   var popWidth=pupModal.modal.find("#popWidth");
   var popHeight=pupModal.modal.find("#popHeight");
   $(popWidth).val(width);
   $(popHeight).val(height);
   if(btnFlag==false){
	   $(popupDivTopDiv).css("display","none");
   }
   if(title=="" || title==null){
		$(popupDivTop).css({"background-color":"white","border-bottom":"0"});
	}
   popTitle.innerHTML=title;
   $(popIframe).attr("src",src);
   pupModalContent.appendTo(pupModal.modal);
   pupModal.open();
   $(popupDivTopDiv).click(function(){
	   pupModal.close();
   });
   $(popupDivTopDiv).mouseover(function(){
	   $(this).css("cursor","pointer");
   });
}
//��������һ�����ı�·�������⣬��ȣ��߶ȣ��Ƿ���ʾ�رհ�ť����
function nextStepPopup(title,width,height,btnFlag){
	if($(window.parent.document).find("#popupDiv")){
		var popDiv=$(window.parent.document).find("#popupDiv");
		var spanDiv=$(popDiv).find("span")[0];
		$(popDiv).css("display","none");
		if(title=="" || title==null){
			$($(popDiv).find("#popupDivTop")).css({"background-color":"white","border-bottom":"0"});
		}
		spanDiv.innerHTML=title;
		$($(popDiv).find("#popWidth")).val(width);
		$($(popDiv).find("#popHeight")).val(height);
		$($(popDiv).find("#popupDivTopDiv")).css("display",(btnFlag==false)?"none":"block");
	}
}
//�رյ����㣻
function closePopup(){
	if($(window.parent.document).find("#popupsBackdrop")){
		$(window.parent.document).find("#popupsBackdrop").detach();
    }
    if($(window.parent.document).find("#popups")){
	   $(window.parent.document).find("#popups").detach();
    }
}
//���������
function showIframe(){
	var cWidth=document.documentElement.clientWidth;
	var cHeight=document.documentElement.clientHeight;
	var divWidth=$("#popWidth").val();
	var divHeight=$("#popHeight").val();
	var width=Math.min(cWidth,divWidth);
	var height=Math.min(cHeight,divHeight);
	var marginLeft,marginTop;
	if(cWidth>divWidth){
		marginLeft=(cWidth-divWidth) /2;
	}else{
		marginLeft=0;
	}
	if(cHeight>divHeight){
		marginTop=(cHeight-divHeight) /2;
	}else{
		marginTop=0;
	}
	$("#popupIframe").css("height",height-46);
	$("#popupDiv").css({"height":"auto","width":width,"marginTop":marginTop,"marginLeft":marginLeft,"display":"block"});
}
//�������С�ı����iframeλ�ã�
window.onresize=function(){
	if(parent.frames["popupIframe"]){
		showIframe();
	}
};
/**~~~~~~������ end ~~~~~~~**/