var CB,Num,Bname,Aname,Details,Bimg;
$(function(){
	$("#CB").val("");
	$("#Num").val("");
	$("#Bname").val("");
	$("#Aname").val("");
	$("#Details").val("");
	$("#Bimg").val("");
	Max_Num();
	$("#addBook").bind("click",function(){
		Get_AddBook();
	});
});
function Get_AddBook(){
	var News;
	CB = $("#CB").val();
	Num = $("#Num").val();
	Bname = $("#Bname").val();
	Aname = $("#Aname").val();
	Details = $("#Details").val();
	Bimg = $("#Bimg").val();
	if(Num == "" || Num == null){
		alert("编号不能为空！");
		return;
	}
	if(CB == "" || CB == null){
		alert("出版社不能为空！");
		return;
	}
	if(Bname == "" || Bname == null){
		alert("图书名不能为空！");
		return;
	}
	if(Aname == "" || Aname == null){
		alert("作者不能为空！");
		return;
	}
	if(Details == "" || Details == null){
		alert("详细说明不能为空！");
		return;
	}
	$.ajax({
		type:"post",
		content:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../admin/addBook",
		async:false,
		dataType:"json",	
		data:{
			bookNum:Num,
			bookName:Bname,
			author:Aname,
			bookPress:CB,
			summary:Details
			},
		success:function(result){
			//News = result;
			alert("添加成功");
			$("#CB").val("");
			$("#Num").val("");
			$("#Bname").val("");
			$("#Aname").val("");
			$("#Details").val("");
			$("#Bimg").val("");
			location.reload();
		},
		error:function(result){
			alert(result.msg);
		}
	});
}

function Max_Num(){
	$.ajax({
		type:"post",
		content:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../manage/showMaxNum",
		async:false,
		dataType:"json",	
		success:function(result){
		var	News = result.maxBookNum;
		$("#maxNum").val(News);
		},
		error:function(result){
			alert(result.msg);
		}
	});
}