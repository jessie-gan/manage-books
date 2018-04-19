var codeid = window.location.href;
var cid = codeid.split("?")[1];
$(function(){
	$("#bookname").text("");
	$("#bookhistory").text("");
	$("#coming").text("");
	$("#bookres").text("");
	Book_tail();
	$("button").click(function(){
				var id = $(this).attr("id");
				window.location.href = 'admin-manage-bookdetaile.html?'+id+'';
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
			$("#edit").attr("id",Crray.book_num);
			if(Crray.book_state == 1){
				$("button").attr("disabled","disabled").css("background-color","#00688B");
			}
		},
		error:function(result){
			alert(result.msg);
		}
	});
}