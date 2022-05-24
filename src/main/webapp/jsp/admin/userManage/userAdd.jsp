<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户增加</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="bs/validform/style.css">
	<script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script> 
	<%--<script type="text/javascript" src="js/admin/userManage/userAdd.js"></script>--%>
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
	</style>

	<script>
		//注册表单验证
		$(function(){
			var form=$("#myForm").Validform({
				tiptype:2,//validform初始化
			});

			form.addRule([
				{
					ele:"#userName",
					datatype:"*2-15",
					ajaxurl:"jsp/admin/findUserByName.do",
					nullmsg:"*请输入用户名！",
					errormsg:"*用户名为2-15个字符，请重新输入！"
				},
				{
					ele:"#passWord",
					datatype:"*4-8",
					nullmsg:"*请输入密码！",
					errormsg:"*密码为4-8位字符，请重新输入"
				},
				{
					ele:"#c_passWord",
					datatype:"*",
					recheck:"passWord",
					mullmsg:"*请输入确认密码！",
					errormsg:"*两次输入的密码不一致，请重新输入！"
				},
				{
					ele:"#name",
					datatype:"*2-15",
					nullmsg:"请输入姓名！",
					errormsg:"姓名为2-15个字符,请重新输入！"
				},
				{
					ele:"#sex",
					datatype:"*",
					nullmsg:"请选择性别！",
					errormsg:"请选择性别！"
				},
				{
					ele:"#age",
					datatype:"n1-2",
					nullmsg:"请输入年龄",
					errormsg:"年龄为1-2位数字，请重新输入！"
				},
				{
					ele:"#tell",
					datatype:"/^13[0-9]{9}$|17[0-9]{9}$|14[0-9]{9}&|15[0-9]{9}$|18[0-9]{9}$/",
					nullmsg:"请输入电话号码",
					errormsg:"电话号码输入不正确，请重新输入！"
				},
				{
					ele:"#address",
					datatype:"*1-100",
					nullmsg:"请输入地址！",
					errormsg:"请输入地址长度过长（100）！"
				}

			]);

		});
	</script>
</head>
<body>
	<c:if test="${!empty userMessage}">
		<h3 class="text-center">${userMessage}</h3>
	</c:if>
	<div class="container">
		
		<h2 class="text-center">用户增加</h2>
		<form id="myForm" action="jsp/admin/userAdd.do" method="post" class="form-horizontal"">
			<div class="form-group">
				<label for="userName" class="col-md-2 col-md-offset-2 control-label">用户名：</label>
				<div class="col-md-4">
					<input name="userName" id="userName" type="text" class="form-control" >
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>	
			</div>
			<div class="form-group">
				<label for="passWord" class="col-md-2 col-md-offset-2 control-label">密码：</label>
				<div class="col-md-4">
					<input type="password" name="passWord" id="passWord" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
			<div class="form-group">
				<label for="c_passWord" class="col-md-2 col-md-offset-2 control-label">确认密码：</label>
				<div class="col-md-4">
					<input type="password" name="c_passWord" id="c_passWord" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="name" class="col-md-2 col-md-offset-2 control-label">姓名：</label>
				<div class="col-md-4">
					<input type="text" id="name" name="name" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2 col-md-offset-2 control-label">性别：</label>
				<div class="col-md-4 ">
					<label class="radio-inline">
						<input type="radio" name="sex" id="sex" class="pr1" value="男">男
					</label>
					<label class="radio-inline">
						<input type="radio" name="sex"  class="pr1"  value="女">女
					</label>
				</div>
				
			</div>
			<div class="form-group">
				<label for="age" class="col-md-2 col-md-offset-2 control-label">年龄：</label>
				<div class="col-md-4">
					<input type="text" id="age" name="age" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
			<div class="form-group">
				<label for="tell" class="col-md-2 col-md-offset-2 control-label">电话：</label>
				<div class="col-md-4">
					<input type="text" id="tell" name="tell" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-md-2 col-md-offset-2 control-label">地址：</label>
				<div class="col-md-4">
					<input type="text" id="address" name="address" class="form-control">
				</div>
				<div class="col-md-4">
					<span class="Validform_checktip">*必填</span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-2  control-label col-md-offset-2">
					<input class="btn btn-success btn-block" type="submit" value="添加">
				</label>
				<label class="col-md-2 control-label">
					<input class="btn btn-warning btn-block" type="reset" value="重置">
				</label>
			</div>
		</form>
	</div>
</body>
</html>