$(function(){
	ChaoQi_lend();
});
 
function ChaoQi_lend(){
	var Crray = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../message/showWillOverTimeRecord",
		async:false,
		dataType:"json",
		data:{},
		success:function(result){			
			if(result.overTimeRecord == undefined){
				alert("当前无即将超期图书！");
				return;
			}
			Crray = result.overTimeRecord;
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
	$("#chaoqi").html(html);
}