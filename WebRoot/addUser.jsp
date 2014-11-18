<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
	<s:form action="AddUserAction" method="post" >
	<s:textfield name="user.username" label="用户名" placeholder="请输入用户名..." ></s:textfield>
	<s:password	name="user.password" label="密码" placeholder="请输入密码..."></s:password>
	<s:textfield name="user.name" label="真实姓名" placeholder="请输入姓名..."></s:textfield>
	<s:submit value="提交"></s:submit>
	</s:form>

</body>
</html>