var codeid = window.location.href;
var cid = codeid.split("?")[1];
var currentpage = 1;
var totalpage;
$(function(){
	cid = decodeURI(cid);
	$("#important").html(cid);
	Result_Book();
	
	$("#upPage").bind("click",function(){
		if($("#currentPage").val() == 1){
			alert("当前是第一页！");
			return;
		}
		var temp = parseInt($("#currentPage").val());
		currentpage = temp - 1;
		$("#currentPage").val(currentpage);
		Result_Book();
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
		Result_Book();
	});
	$(".wlent").live("click",function(){
		var booknum = $(this).attr("id");
		if(booknum){
			$.ajax({
				type:"post",
				contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				url:"../user/borrowBook",
				async:false,
				dataType:"json",
				data:{
					bookNum:booknum
				},
				success:function(result){
					alert("借阅成功！");
					location.reload();
				},
				error:function(result){
					alert("返回失败！");
				}
			});
		}
	});
});

function Result_Book(){
	var Crray = new Array();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../user/showSearch",
		async:false,
		dataType:"json",
		data:{
			bookName:cid,
			page:currentpage
		},
		success:function(result){
			Crray = result.searchResult;
			$("#totalPage").val(result.pageNum);
		},
		error:function(result){
			alert(result.msg);
		}
		
	});
	var html="";
	var txt = (currentpage-1)*10;
	for(var i = 0;i < Crray.length;i++){
		if(Crray[i].book_state == 0){
			html += "<tr><td>"
				+(txt+i+1)+"</td><td>"
				+Crray[i].book_num+"</td><td><a style='color:#337ab7;' href='user-detaile.html?"+ Crray[i].book_num +"'>"
				+Crray[i].book_name+"</a></td><td>"
				+Crray[i].book_author+"</td><td>"
				+Crray[i].book_press+"</td><td>"
				+"<button type='button' class='btn btn-success btn-xs lentbtn'><span class='glyphicon glyphicon-ok-circle'></span>&nbsp;未借阅</button></td><td>"
				+"<button type='button' id='"+Crray[i].book_num+"' class='btn btn-info btn-xs lentbtn wlent'><span class='glyphicon glyphicon-ok-circle'></span>&nbsp;借阅</button></td>"
				+"<tr>"
		}else{
			html += "<tr><td>"
				+(txt+i+1)+"</td><td>"
				+Crray[i].book_num+"</td><td><a style='color:#337ab7;' href='user-detaile.html?"+Crray[i].book_num+"'>"
				+Crray[i].book_name+"</a></td><td>"
				+Crray[i].book_author+"</td><td>"
				+Crray[i].book_press+"</td><td>"
				+"<button type='button' class='btn btn-danger btn-xs lentbtn'><span class='glyphicon glyphicon-ok-circle'></span>&nbsp;已借阅</button></td><td>"
				+"<button type='button' id='"+Crray[i].book_num+"' class='btn btn-info btn-xs lentbtn' disabled='disabled'><span class='glyphicon glyphicon-ok-circle'></span>&nbsp;借阅</button></td>"
				+"<tr>"
		}
	}
	$("#table").html(html);
	$("#mehunt").val("");
}