var userCode=null;
var userName=null;
var userPassword=null;
var ruserPassword=null;
var phone=null;
var birthday=null;
var addBtn=null;


$(function(){
	userCode=$("#userCode");
	userName=$("#userName");
	userPassword=$("#userPassword");
	ruserPassword=$("#ruserPassword");
	phone=$("#phone");
	birthday=$("#birthday");
	addBtn=$("#add");
	
	//初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
		userCode.next().html("*");
		userName.next().html("*");
		userPassword.next().html("*");
		ruserPassword.next().html("*");
		phone.next().html("*");
		birthday.next().html("*");

	//验证
	//失焦\获焦
	//jquery的方法传递
		userCode.bind("blur",function(){
			//ajax的后台验证---userCode是否已存在
			//此账户不可用
			//不可用
			$.ajax({
				type:"GET",//请求类型
				url:path+"/user.do",//请求url
				data:{method:"ucexist",userCode:userCode.val()},//请求参数
				dateType:"json",//ajax接口（请求url）返回的数据类型
				success:function(data){//返回的数据（json对象）
					if(data.userCode=="exist"){//账号已经存在，错误提示
						validateTip(userCode.next(),{"color":"red"},"该用户账号已存在",false);
						
					}else{//账号可用，正确提示
						validateTip(userCode.next(),{"color":"green"},"该账号可以使用",true);
						
					}
					
				},
				error:function(data){//当访问的时候，404,500等非200的错误状态码，进入error
					validateTip(userCode.next(),{"color":"red"},"您访问的页面不存在",false);
					
				}
			});
		
		}).bind("focus",function(){
			//显示友情提示
			validateTip(userCode.next(),{"color":"#666666"},"* 用户编码是您登陆系统的账号",false);
		
		}).focus();
		
		
		userName.bind("focus",function(){
			validateTip(userName.next(),{"color":"#666666"},"* 用户名必须是大于3小于10的字符",false);	
		}).bind("blur",function(){
			if(userName.val()!=null && userName.val().length>3 && userName.val().length<10){
				validateTip(userName.next(),{"color":"green"},"输入正确",true);			
			}else{
				validateTip(userName.next(),{"color":"red"}," 用户名输入不符合规范，请重新输入",false);							
			}
			
		});
		
		userPassword.bind("focus",function(){
			validateTip(userPassword.next(),{"color":"#666666"},"* 密码长度必须大于6小于20",false);
		}).bind("blur",function(){
			if(userPassword.val()!=null && userPassword.val().length>6 && userPassword.val().length<20){
				validateTip(userPassword.next(),{"color":"green"},"输入正确",true);			
			}else{
				validateTip(userPassword.next(),{"color":"red"}," 密码输入不符合规范，请重新输入",false);							

			}
			
		});
			
		ruserPassword.bind("focus",function(){
			
			validateTip(ruserPassword.next(),{"color":"#666666"},"* 请输入与上面一致的密码",false);

		}).bind("blur",function(){
			if(ruserPassword.val()!=null && ruserPassword.val().length>6 && ruserPassword.val().length<20 && ruserPassword.val()==userPassword.val()){
				validateTip(ruserPassword.next(),{"color":"green"},"输入正确",true);			
			}else{
				validateTip(ruserPassword.next(),{"color":"red"}," 两次密码输入不一致，请重新输入",false);							
			}
		});
		
		birthday.bind("focus",function(){
			validateTip(birthday.next(),{"color":"#666666"},"* 点击输入框，选择日期",false);
		}).bind("blur",function(){
			if(birthday.val()!=null && birthday.val()!=""){
				validateTip(birthday.next(),{"color":"green"},"输入正确",true);			
			}else{
				validateTip(birthday.next(),{"color":"red"}," 选择的日期不正确，请重新选择",false);							

			}
		});
		phone.bind("focus",function(){
			validateTip(phone.next(),{"color":"#666666"},"* 请输入手机号！",false);
		}).bind("blur",function(){
			var patrn=/^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
			if(phone.val().match(patrn)){
				validateTip(phone.next(),{"color":"green"},"输入正确",true);			
			}else{
				validateTip(phone.next(),{"color":"red"}," 输入的手机号格式不正确",false);							
			}
		});

		addBtn.bind("click",function(){
			if(userCode.attr("validateStatus")!="true"){
				userCode.blur();
			}else if(userName.attr("validateStatus")!="true")
				userName.blur();
			else if(userPassword.attr("validateStatus")!="true")
				userPassword.blur();
			else if(ruserPassword.attr("validateStatus")!="true")
				ruserPassword.blur();
			else if(birthday.attr("validateStatus")!="true")
				birthday.blur();
			else if(phone.attr("validateStatus")!="true")
				phone.blur();
			else{
				if(confirm("是否确认提交数据")){
					$("#userForm").submit();
				}
			}
				
		});
		
		
		
		
});


	

