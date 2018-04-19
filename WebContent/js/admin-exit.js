var usernum=1,niname=1;
$(function(){
	Get_userName();
	$("#exit").bind("click",function(){
		if(confirm("确定要注销此账户？")){
		$.ajax({
			type:"post",
			content:"application/x-www-form-urlencoded;charset=UTF-8",
			url:"../admin/logout",
			async:false,
			dataType:'json',
			data:{},
			success:function(result){
				alert(result.msg);
				window.location.href="login.html";
			}
		});
		}
		else
			return false;
	});
	
});

function Get_userName(){
	$.ajax({
		type:"post",
		contentType:"application/x-www-form-urlencoded",
		url:"../admin/getAdminInfo",
		async:false,
		dataType:"json",
		data:{},
		success:function(result){
			if(result.msg == undefined){
				alert("您尚未登录！请先登录！");
				window.location.href="login.html";
			}
		}
	});
}