var currentpage = 1;
var totalpage;
$(function(){
	Latest_List();
	$("#upPage").bind("click",function(){
		if($("#currentPage").val() == 1){
			alert("当前是第一页！");
			return;
		}
		var temp = parseInt($("#currentPage").val());
		currentpage = temp - 1;
		$("#currentPage").val(currentpage);
		Latest_List();
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
		Latest_List();
	});
});
function Latest_List(){
	var Current = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
		url:"../manage/showReRecords",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			if(result.RecentRecord){
				Current = result.RecentRecord;
				$("#totalPage").val(result.totalPage);
			}
			if(result.msg){
				//alert(result.msg);
			}
		},
		error:function(result){
			alert("获取失败！");
		}
	});
	
	var html="";
	var txt = (currentpage-1)*10;
	for(var i = 0; i < Current.length;i++){
		html += "<tr>"
				+"<td>"+(txt+i+1)+"</td><td>"
				+ Current[i].borrow_bookNum +"</td><td>"
				+ Current[i].borrow_bookName +"</td><td>"
				+ Current[i].borrow_stuNum +"</td><td>"
				+ Current[i].borrow_stuName +"</td><td>"
				+ Current[i].borrow_time +"</td><td>"
				+ Current[i].shouldReturn_time +"</td>"
				+"</tr>"
	}
	$("#Latestlist").html(html);
}