var currentpage = 1;
var totalpage;
$(function(){
	Latest_List();
	Return_Book();
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
		url:"../manage/showRecords",
		async:false,
		dataType:"json",
		data:{
			page:currentpage
		},
		success:function(result){
			Current = result.borrowList;
			if(result.msg){
				//alert(result.msg);
			}
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
				+ Current[i].borrow_bookNum +"</td><td>"
				+ Current[i].borrow_bookName +"</td><td>"
				+ Current[i].borrow_stuNum +"</td><td>"
				+ Current[i].borrow_stuName +"</td><td>"
				+ Current[i].borrow_time +"</td><td>"
				+ Current[i].shouldReturn_time +"</td><td>"
				+"<button type='button' id="
				+ Current[i].borrow_bookNum +" name="+ Current[i].borrow_id +" class='btn-warning btn btn-sm retur' title="+Current[i].borrow_stuNum+" style='padding:2px;'></span>还书</button>"+"</td>"
				+"</tr>"
	}
	$("#Latestlist").html(html);
}


function Return_Book(){
	var Id,booknum,stunum;
	$(".retur").live('click',function(){
		booknum = $(this).attr("id");
		Id= $(this).attr("name");
		stunum= $(this).attr("title");
		$.ajax({
			type:"post",
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			url:"../manage/returnBook",
			async:false,
			dataType:"json",
			data:{
				id:Id,
				bookNum:booknum,
				stuNum:stunum
			},
			success:function(result){
				alert(result.msg);
				location.reload();
			},
			error:function(result){
				alert("获取失败！");
			}
		});
	});
	
}