var Curent = new Array();
var res;
var num;
var currentpage = 1;
var totalpage;
$(function(){
	Lend_List();
	$("#upPage").bind("click",function(){
		if($("#currentPage").val() == 1){
			alert("当前是第一页！");
			return;
		}
		var temp = parseInt($("#currentPage").val());
		currentpage = temp - 1;
		$("#currentPage").val(currentpage);
		List_Book();
	});
	$("#nextPage").bind("click",function(){
		var temp = parseInt($("#currentPage").val());
		var totalPage = temp + 1;
		if($("#totalPage").val() < totalPage){
			alert("当前是最后一页！");
			return;
		}
		currentpage = temp + 1;
		$("#currentPage").val(currentpage);
		List_Book();
	});
});
function Lend_List(){
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../user/readBeanRecord",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			Curent = result.beanRecords;
			$("#totalPage").val(result.totalPage);
		},
		error:function(result){
			alert("获取失败！");
		}
	});
	var html="";
	var R;
	var txt = (currentpage-1)*10;
	for( var i = 0;i < Curent.length;i++){
		if(Curent[i].option == 0){
			html += "<tr><td>"
				+(txt+i+1)+"</td><td>"
				+Curent[i].optionTime+"</td><td>"
				+"<button class='btn btn-success btn-sm' style='padding:2px 3px;'><span class='glyphicon glyphicon-time'></span>&nbsp;借书</button></td><td>"
				+"+5</td><td>"
				+Curent[i].readBean+"</td>"
				+"</tr>"
		}else{
			html += "<tr><td>"
				+(txt+i+1)+"</td><td>"
				+Curent[i].optionTime+"</td><td>"
				+"<button class='btn btn-danger btn-sm' style='padding:2px 3px;'><span class='glyphicon glyphicon-time'></span>&nbsp;超期</button></td><td>"
				+"-5</td><td>"
				+Curent[i].readBean+"</td>"
				+"</tr>"
		}
		$("#R").html(Curent[0].readBean);
		
	}
	
	$("#Beantable").html(html);
}