var username,niname,passw;
$(function(){
	ZhuCe();
});
function ZhuCe(){
	$("#username").val("");
	$("#niname").val("");
	$("#passw").val("");
	var str="";
	$("#register").click(function(){
		username = $("#username").val();
		niname = $("#niname").val();
		passw = $("#passw").val();
		str = passw;
		var n = Number(str);
		if(username == null || username == ""){
			alert("学号不能为空！");
			return;
		}
		if(niname == null || niname == ""){
			alert("昵称不能为空！");
			return;
		}
		if(passw == null || passw == ""){
			alert("密码不能为空！");
			return;
		}
		if (!isNaN(n) && (passw.length >= 6) && (passw.length <= 12))
		{
			if(username == "" || niname == "" || passw == ""){
				alert("请确认信息！");
			}
			$.ajax({
				type : "post",
				content : "application/x-www-form-urlencoded;charset=UTF-8",
				url : "../user/register",
				dataType : 'json',
				data:{
					stuNum:username,
					userName:niname,
					password:passw
				},
				async : false,
				success : function(result){ 
					alert("注册成功！");
					window.location.href = "stuperson.html";
				},
				error:function(result){
					alert("注册失败！");
					return;
				}
			});
		}else{
			alert("密码格式不合要求！");
		}
		
	});
}