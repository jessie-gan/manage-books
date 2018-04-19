var num;
var currentpage = 1;
var totalpage;
$(function(){
	List_Book();
	
	/*$("button").click(function(){
		num = $(this).attr("id");
		Delete_Book();
	});*/
	$(".del").live('click',function(){
		num = $(this).attr("id");
		Delete_Book();
	});
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
function List_Book(){
	var listbook = new Array();
	$.ajax({
		type:"post",
		content:"application/x-www-form-urlencoded;charset=utf-8",
		url:"../manage/showAllBooks",
		async:false,	
		data:{
			page:currentpage
		},
		dataType:"json",
		success:function(result){
			listbook = result.bookList;
			$("#totalPage").val(result.totalPage);
		}	
	});
	var html="";
	var txt = (currentpage-1)*10;
	for(var i = 0; i < listbook.length;i++){
		if(listbook[i].book_state == 1){
			html += "<tr>"
				+"<td>"+(txt+i+1)+"</td><td>"
				+ listbook[i].book_num +"</td><td>"
				+ "<a href='admin-bookdetail.html?"+listbook[i].book_num+"'>"+listbook[i].book_name +"</a></td><td>"
				+ listbook[i].book_author +"</td><td>"
				+ listbook[i].book_press +"</td><td>"
				+"<a class='btn btn-success btn-sm' style='padding:2px;font-size:12px;margin-right:10px;' role='button'><span>已借阅</span></a>" +"</td><td>"
				+"<a class='btn btn-warning btn-sm' style='padding:2px;font-size:13px;margin-right:10px;' role='button' disabled><span>编辑</span></a>"
				+ "<button type='button' class='btn-danger btn btn-sm' style='padding:2px;' id='"+ listbook[i].book_num +"' disabled></span>删除</button>"
				+"</td>"
				+"</tr>"
			}
		else{
			html += "<tr>"
				+"<td>"+(txt+i+1)+"</td><td>"
				+ listbook[i].book_num +"</td><td>"
				+ "<a href='admin-bookdetail.html?"+listbook[i].book_num+"'>"+listbook[i].book_name +"</a></td><td>"
				+ listbook[i].book_author +"</td><td>"
				+ listbook[i].book_press +"</td><td>"
				+"<a class='btn btn-warning btn-sm' style='padding:2px;font-size:12px;margin-right:10px;' role='button'><span>未借阅</span></a>" +"</td><td>"
				+"<a class='btn btn-warning btn-sm' style='padding:2px;font-size:13px;margin-right:10px;' href='admin-manage-bookdetaile.html?"+ listbook[i].book_num +"' role='button'><span>编辑</span></a>"
				+ "<button type='button' class='btn-danger btn btn-sm del' style='padding:2px;' id='"+ listbook[i].book_num +"'></span>删除</button>"
				+"</td>"
				+"</tr>"
		}
}
	$("#ListBook").html(html);
}


function Delete_Book(){
	
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		url:"../admin/deleteBook",
		async:false,
		dataType:"json",
		data:{
			bookNum:num
		},
		success:function(result){
			alert(result.msg);
			List_Book();
		},
		error:function(result){
			alert("获取失败！");
		}
	});
	
}
