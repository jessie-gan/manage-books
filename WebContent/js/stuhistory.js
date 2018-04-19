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
	var Current = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
		url:"../user/showHisRecord",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			Current = result.OnesHisRecord;
			$("#totalPage").val(result.totalPage);
			if(result.msg){
				//alert(result.msg);
			}
		},
		error:function(result){
			alert(result.msg);
		}
	});
	
	var html="";
	var txt = (currentpage-1)*10;
	for(var i = 0; i < Current.length;i++){
		html += "<tr>"
				+"<td>"+(txt+i+1)+"</td><td>"
				+ Current[i].borrow_bookNum +"</td><td><a style='color:#337ab7;' href='user-detaile.html?"+ Current[i].borrow_bookNum +"'>"
				+ Current[i].borrow_bookName +"</a></td><td>"
				+ Current[i].borrow_time +"</td><td>"
				+ Current[i].shouldReturn_time +"</td><td>"
				+ Current[i].Return_time +"</td>"
				+"</tr>"
	}
	$("#his_table").html(html);
}