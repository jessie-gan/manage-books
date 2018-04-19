$(function(){
	/*点击退出*/
	checkBrower();
	$("#exit").click(function(){
		window.location.href = "login.html";
	});
	$("a").focus(function(){
		$("a").css("color","red");
	});
	$(document).keydown(function(event){
		if(event.keyCode==13){
			$("#login").click();
		}
	});
	$("#login").bind("click",function(){			
		var username = $("#username").val();
		var uname = username.toString();
		var n = Number(username);
		if(username==null||username=="")
		{
			alert("用户不能为空！");
			return;
		}
		var userpass = $("#password").val();
		if(userpass==null||userpass=="")
		{
			alert("密码不能为空！");
			return;
		}
		if (!isNaN(n)){
			$.ajax({
				type:"post",
				contentType:"application/x-www-form-urlencoded;charset=UTF-8",
				url:"../user/login",
				async:false,
				dataType:"json",
				data:{
					stuNum:username,
					 psw:userpass
				},
				success:function(res){
					if(res.info == "登录成功"){
						alert(res.info);
						window.location.href = 'studenthunt.html';
					}else{
						alert(res.info);
						return;
					}	
				},
				error:function(res){
					alert("登录失败！");
					return false;
				}
			});
		}else{
			alert("学号格式不正确！");
		}
	});
});

var browTag=0;
//判断是否需要更换浏览器
function checkBrower(){ 
	var System = {};
	var UserAgent = navigator.userAgent.toLowerCase();
	// alert(UserAgent);
	var s;//array
	(s = UserAgent.match(/msie ([\d.]+)/))  ? System.ie = s[1]:
		(s = UserAgent.match(/firefox\/([\d.]+)/)) ? System.firefox = s[1]:
			(s = UserAgent.match(/chrome\/([\d.]+)/)) ? System.chrome = s[1]:
				(s = UserAgent.match(/opera.([\d.]+)/)) ? System.opera = s[1]:
					(s = UserAgent.match(/version\/([\d.]+).*safari/))? System.safari = s[1]:0;

					if (System.ie||System.firefox||System.chrome){ browTag=1;}

					else{
						alert("你的浏览器不适合浏览本系统，请升级或使用建议浏览器后浏览！");
						window.location.href="error.html";
					} 
}