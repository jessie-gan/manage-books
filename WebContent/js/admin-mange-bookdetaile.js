var codeid = window.location.href;
var cid = codeid.split("?")[1];
$(function(){
	$("#booknum").val("");
	$("#bookname").val("");
	$("#bookauthor").val("");
	$("#bookpress").val("");
	$("#booksum").val("");
	Book_tail();
	//Edit_Book();
	$("#Save_btn").bind("click",function(){
		Edit_Book();
	});
});

function Edit_Book(){
	var Current = new Array();
	var booknum = $("#booknum").val();
	var bookname = $("#bookname").val();
	var bookauthor = $("#bookauthor").val();
	var bookpress = $("#bookpress").val();
	var booksum = $("#booksum").val();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
		url:"../admin/editBook",
		async:false,
		dataType:"json",
		data:{
			bookNum:booknum,
			bookName:bookname,
			author:bookauthor,
			bookPress:bookpress,
			summary:booksum
		},
		success:function(result){
			alert("保存成功！");
			$("#booknum").val("");
			$("#bookname").val("");
			$("#bookauthor").val("");
			$("#bookpress").val("");
			$("#booksum").val("");
			window.location.href = "admin-manage-listbook.html";
		},
		error:function(result){
			alert(result.msg);
		}
		
	});
}

function Book_tail(){
	var Crray = new Array;
	$.ajax({
		type:"post",
		content:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../showBookDetails/showDetails",
		async:false,
		dataType:"json",	
		data:{
			bookNum:cid,
			},
		success:function(result){
			Crray = result.bookDetail;
			//alert(result.msg);
			$("#booknum").val(Crray.book_num);
			$("#bookname").val(Crray.book_name);
			$("#bookauthor").val(Crray.book_author);
			$("#bookpress").val(Crray.book_press);
			$("#booksum").val(Crray.book_summary);
		},
		error:function(result){
			alert(result.msg);
		}
	});
}