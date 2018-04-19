var usernum=1,niname=1;
$(function(){
	$("#name").text("");
	//userName();
	$("#lendbtn").click(function(){
		window.location.href = "melendbtn.html";
	});
	Get_userName();
	$("#name").html(niname);
	$("#person").click(function(){
		window.location.href = 'stuperson.html';
	});
	$("#hunt").click(function(){
		Get_Reult();
	});
});
function Get_userName(){
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../user/getUserInfo",
		async:false,
		dataType:"json",
		data:{},
		success:function(result){
			if(result.username == undefined){
				alert("您尚未登录！请先登录！");
				window.location.href = "login.html";
			}
			usernum = result.stuNum;
			niname = result.username;
		}
	});
}

function Get_Reult(){
	var Result;
	Result = $("#result").val();
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded;charset=UTF-8",
		url:"../user/showSearch",
		async:false,
		dataType:"json",
		data:{
			bookName:Result
		},
		success:function(result){
			window.location.href = "huntresult.html?"+Result;
		},error:function(result){
			alert("搜索失败！");
		}
	});
}