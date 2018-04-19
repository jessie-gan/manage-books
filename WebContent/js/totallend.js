var currentpage = 1;
var totalpage;
$(function(){
	New_lend();
});
 
function New_lend(){
	var Crray = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../message/showReRecord",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			
			if(result.recentRecord == undefined){
				alert("当前无借阅！");
				return;
			}
			Crray = result.recentRecord;
		},error:function(result){
			alert(result.msg);
		}
	});
	var html="";
	for(var i = 0; i < Crray.length;i++){
		html += "<tr>"
				+"<td>"+(i+1)+"</td><td>"
				+ Crray[i].borrow_bookNum +"</td><td>"
				+ Crray[i].borrow_bookName +"</td><td>"
				+ Crray[i].borrow_time +"</td><td>"
				+ Crray[i].shouldReturn_time +"</td>"
				+"</tr>"
	}
	$("#totalLend").html(html);
}