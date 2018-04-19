var username,password;
$(function(){
	$("#username").val("");
	$("#password").val("");
	$(document).keydown(function(event){
		if(event.keyCode==13){
			$("#login").click();
		}
	});
	$("#login").click(function(){
			username = $("#username").val();
			password = $("#password").val();
			if(username==null||username=="")
			{
				alert("用户不能为空！");
				return;
			}
			var password = $("#password").val();
			if(password==null||password=="")
			{
				alert("密码不能为空！");
				return;
			}
			$.ajax({
				type:'post',
				contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				url:"../admin/login",
				dataType:'json',
				async:false,
				data:{
					username:username,
					psw:password
				},
				success:function(result){
					if(result.msg == "登录成功"){
						alert(result.msg);
						window.location.href = "admin-manage-addbook.html";
					}else{
						alert(result.msg);
						return;
					}
				},
				error:function(result){
					alert(result);
				}
			});
	});
});