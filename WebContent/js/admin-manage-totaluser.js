var currentpage = 1;
var totalpage;
var num;
$(function(){
	Latest_List();
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
		url:"../manage/showUsers",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			Current = result.allUsers;
			$("#totalPage").val(result.totalPage);
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
				+ Current[i].student_num +"</td><td>"
				+ Current[i].real_name +"</td><td>"
				+ Current[i].sex +"</td><td>"
				+ Current[i].gradeClass +"</td><td>"
				+ Current[i].major+"</td><td>"
				+ Current[i].tel+"</td><td>"
				+ Current[i].email +"</td><td>"
				+"<button type='button' style='padding:2px 3px;' class='btn btn-danger btn-sm del' id='"+Current[i].student_num+"'>删除</button></td>"
				+"</tr>"
	}
	$("#user").html(html);
}

function Delete_Book(){
		$.ajax({
			type:"post",
			contentType:"application/x-www-form-urlencoded",
			url:"../admin/deleteUser",
			async:false,
			dataType:"json",
			data:{
				stuNum:num
			},
			success:function(result){
				Current = result;
				if(result.msg){
					alert(result.msg);
				}
				location.reload();
			},
			error:function(result){
				alert("删除失败！");
			}
	});
}