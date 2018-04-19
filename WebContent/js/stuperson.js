var stuNum,niname,truename,sex,telphone,email,banji,major;
$(function(){
	$("#news").click(function(){
		window.location.href = 'stuperson.html';
	});
	$("#passw").click(function(){
		window.location.href = 'stupassw.html';
	});
	$("#lend").click(function(){
		window.location.href = 'stulend.html';
	});
	$("#history").click(function(){
		window.location.href = 'stuhistory.html';
	});
	$("#fork").click(function(){
		window.location.href = 'stufork.html';
	});
	getUserDetailsInfo();
	
	$("#save").click(function(){
		Save_personnews();
		window.location.href = "stuperson.html";
	});
});
function getUserDetailsInfo(){
	$("#stuNum").val("");
	$("#niname").val("");
	$("#truename").val("");
	$("#sex").val("");
	$("#telphone").val("");
	$("#email").val("");
	$("#banji").val("");
	$("#major").val("");
	
	$.ajax({
		type : "post",
		content : "application/x-www-form-urlencoded;charset=UTF-8",
		url:"../user/getUserDetailsInfo",
		dataType:'json',
		async:false,
		data:{ },
		success:function(result){
			stuNum = result.stuNum;
			niname = result.username;
			truename = result.realName;
			sex = result.sex;
			telphone = result.tel;
			email = result.email;
			banji = result.gradeClass;
			major = result.major;
		}
	});
	$("#stuNum").val(stuNum);
	$("#niname").val(niname);
	$("#truename").val(truename);
	$("#sex").val(sex);
	$("#telphone").val(telphone);
	$("#email").val(email);
	$("#banji").val(banji);
	$("#major").val(major);
	if($("#email").val() == "" || $("#telphone").val() == ""){
		$("#passw").attr("disabled","disabled");
		$("#lend").attr("disabled","disabled");
		$("#history").attr("disabled","disabled");
		$("#fork").attr("disabled","disabled");
		alert("请完善信息！");
	}
}
function Save_personnews(){
	niname = $("#niname").val();
	truename = $("#truename").val();
	sex = $("#sex").val();
	telphone = $("#telphone").val();
	email = $("#email").val();
	banji = $("#banji").val();
	major = $("#major").val();
	$.ajax({
		type : "post",
		content : "application/x-www-form-urlencoded;charset=UTF-8",
		url:"../personalCenter/modifyDetails",
		dataType:'json',
		async:false,
		data:{
			setUserName:niname,
			setRealName:truename,
			setSex:sex,
			setEmail:email,
			setMajor:major,
			setTel:telphone,
			setGrade:banji
		},
		success:function(result){
			alert(result.msg); 
		},
		error:function(result){
			alert("保存失败！");
		}
	});
}