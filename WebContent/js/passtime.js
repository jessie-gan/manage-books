var currentpage = 1;
var totalpage;
$(function(){
	ChaoQi_lend();
	$("#upPage").bind("click",function(){
		if($("#currentPage").val() == 1){
			alert("当前是第一页！");
			return;
		}
		var temp = parseInt($("#currentPage").val());
		currentpage = temp - 1;
		$("#currentPage").val(currentpage);
		 ChaoQi_lend();
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
		 ChaoQi_lend();
	});
});
 
function ChaoQi_lend(){
	var Crray = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../message/showOverTimeRecord",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){			
			if(result.overTimeRecords == undefined){
				alert(result.msg);
				return;
			}
			Crray = result.overTimeRecords;
			$("#totalPage").val(result.totalPage);
		},error:function(result){
			alert("获取数据失败！");
		}
	});
	var html="";
	var txt = (currentpage-1)*10;
	for(var i = 0; i < Crray.length;i++){
		var state;
		if(Crray[i].borrow_state == 0){
			state = "未还";
		}else{
			state ="已还";
		}
		html += "<tr>"
				+"<td>"+(txt+i+1)+"</td><td>"
				+ Crray[i].borrow_bookNum +"</td><td>"
				+ Crray[i].borrow_bookName +"</td><td>"
				+ Crray[i].borrow_time +"</td><td>"
				+ Crray[i].shouldReturn_time +"</td><td>"
				+ state +"</td>"
				+"</tr>"
	}
	$("#overtime").html(html);
}