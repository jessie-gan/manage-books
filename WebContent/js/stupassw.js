var Ppass,Nnew,Rnew;
$(function(){
	$("#Ppass").val("");
	$("#Nnew").val("");
	$("#Rnew").val("");
	$("#Rok").click(function(){
		Reset_password();
	});
	
});
function Reset_password(){
	
	Ppass = $("#Ppass").val();
	Nnew = $("#Nnew").val();
	Rnew= $("#Rnew").val();
	if(Nnew !== Rnew){
		alert("两次密码不一致！");
		return;
	}
	if( (Nnew.length >= 6) && (Nnew.length <= 12) ){
		$.ajax({
			type : "post",
			content : "application/x-www-form-urlencoded;charset=UTF-8",
			url:"../personalCenter/modifyPsw",
			dataType:'json',
			async:false,
			data:{
				setPsw:Nnew,
				oldPsw:Ppass
			},
			success:function(result){
				alert(result.msg);
				window.location.href = 'stupassw.html';
			},
			error:function(result){
				alert(result.msg);
			}
		});
	}else{
		alert("密码格式不对！");
		return;
	}
	
}