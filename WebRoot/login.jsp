<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<s:actionerror/>
	<!-- /test 加不加/都可以-->
	<s:form action="LoginAction" method="post">
		<s:textfield label="用户名" name="user.username"></s:textfield>
		<s:password label="密码" name="user.password"></s:password>
		<s:submit value="登录"></s:submit><br>
		<button type="submit">submit</button>
	
	</s:form>




</body>
</html>