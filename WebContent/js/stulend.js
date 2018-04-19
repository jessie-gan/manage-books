
var res;
$(function(){
	Lend_List();
});
function Lend_List(){
	var Current;
	var Curent = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
		url:"../user/showCurrentRecords",
		async:false,
		dataType:"json",
		data:{
			stuNum:usernum
		},
		success:function(result){
			Current = result;
			if(result.currentRecords == undefined){
				alert(Current.msg);
				return;
			}
			Curent = result.currentRecords;
		},
		error:function(result){
			if(Current.msg){
				alert(Current.msg);
				return;
			}
		}
	});
	var html="";
	for(var i = 0; i < Curent.length;i++){
		html += "<tr>"
				+"<td>"+(i+1)+"</td><td>"
				+ Curent[i].borrow_bookNum +"</td><td><a style='color:#337ab7;' href='user-detaile.html?"+ Curent[i].borrow_bookNum +"'>"
				+ Curent[i].borrow_bookName +"</a></td><td>"
				+ Curent[i].borrow_time +"</td><td>"
				+ Curent[i].shouldReturn_time +"</td>"
				+"</tr>"
	}
	$("#table").html(html);
}