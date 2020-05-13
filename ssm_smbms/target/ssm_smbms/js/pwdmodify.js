var oldpassword=null;
var newpassword=null;
var rnewpassword=null;
var saveBtn=null;
$(function(){
	oldpassword=$("#oldpassword");
	newpassword=$("#newpassword");
	rnewpassword=$("#rnewpassword");
	saveBtn=$("#save");
	
	oldpassword.next().html("*");
	newpassword.next().html("*");
	rnewpassword.next().html("*");
	
	oldpassword.on("blur",function(){
		$.ajax({
			type:"GET",
			url:path+"/user.do",
			data:{method:"pwdmodify",oldpassword:oldpassword.val()},
			dataType:"json",
			success:function(data){
				//旧密码正确
				if(data.result=="true"){//旧密码正确
					validateTip(oldpassword.next(),{"color":"green"},"输入正确",true);
				}else if(data.result=="false"){//旧密码不正确
					validateTip(oldpassword.next(),{"color":"red"},"密码输入错误，请重新输入！",false);

				}else if(data.result=="error"){//当前用户session过期
					validateTip(oldpassword.next(),{"color":"red"},"当前用户session过期,请重新登录！",false);
				}
				
				
				//当前用户session过期，请重新登录
			},
			error:function(data){
				//请求出错
				validateTip(oldpassword.next(),{"color":"red"},"当前用户session过期,请重新登录！",false);
			}
		});
	}).on("focus",function(){
		validateTip(oldpassword.next(),{"color":"#666666"},"请输入原密码！",false);
	});
	

	newpassword.on("focus",function(){
		validateTip(newpassword.next(),{"color":"#666666"},"* 密码长度必须大于6小于20",false);
	}).on("blur",function(){
		if(newpassword.val()!=null && newpassword.val().length>6 && newpassword.val().length<20){
		validateTip(newpassword.next(),{"color":"green"},"输入正确",true);			
	}else{
		validateTip(newpassword.next(),{"color":"red"}," 密码输入不符合规范，请重新输入",false);							
	}
	});
	
	
	rnewpassword.on("focus",function(){
		validateTip(rnewpassword.next(),{"color":"#666666"},"* 请输入与上面一致的密码！",false);
	}).on("blur",function(){
		if(rnewpassword.val()!=null && rnewpassword.val().length>6 && rnewpassword.val().length<20 && rnewpassword.val()==newpassword.val()){
			validateTip(rnewpassword.next(),{"color":"green"},"输入正确",true);			
		}else{
			validateTip(rnewpassword.next(),{"color":"red"}," 密码输入不符合规范，请重新输入",false);							
		}
	});
	
	saveBtn.on("click",function(){
		oldpassword.blur();
		newpassword.blur();
		rnewpassword.blur();
		if(oldpassword.attr("validateStatus")=="true"
			&& newpassword.attr("validateStatus")=="true"
			&& rnewpassword.attr("validateStatus")=="true"){
			if(confirm("确定要修改密码吗？")){
				$("#userForm").submit();
			}
		}
	});
});