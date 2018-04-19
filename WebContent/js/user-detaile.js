var codeid = window.location.href;
var cid = codeid.split("?")[1];
$(function(){
	$("#bookname").text("");
	$("#bookhistory").text("");
	$("#coming").text("");
	$("#bookres").text("");
	Book_tail();
	$("button").click(function(){
				if(id){
					$.ajax({
						type:"post",
						contentType:"application/x-www-form-urlencoded;charset=UTF-8",
						url:"../user/borrowBook",
						async:false,
						dataType:"json",
						data:{
							bookNum:cid
						},
						success:function(result){
							alert("借阅成功！");
							window.location.href = "melendbtn.html";
						},
						error:function(result){
							alert(result.msg);
						}
					});
				}
			}
		);
});

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
			$("#bookname").html(Crray.book_name);
			$("#bookhistory").html(Crray.book_author);
			$("#coming").html(Crray.book_press);
			$("#bookres").html(Crray.book_summary);
			id = Crray.book_num;
			if(Crray.book_state == 0){
				$('#edit').attr("disabled",false); 
			}
		},
		error:function(result){
			alert(result.msg);
		}
	});
}