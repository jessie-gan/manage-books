var usernum=1,niname=1;
$(function(){
	$("#name").text("");
	// userName();
	$("#lendbtn").click(function(){
		window.location.href = "personnews.html";
	});
	Get_userName();
	$("#name").html(niname);
});
function Get_userName(){
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
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


