<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form action="UpdateUserAction" method="post">
		用户名:<s:property value="%{#session.user.username}"/><br>
		id:<s:property value="%{#session.user.id}"/><br>
		<s:hidden name="user.id" value="%{#session.user.id}" ></s:hidden>
		<s:hidden name="user.username" value="%{#session.user.username}" ></s:hidden>
		<s:textfield name="user.name" value="%{#session.user.name}" label="真实姓名"></s:textfield>
		<s:textfield name="user.password" value="%{#session.user.password}" label="密码"></s:textfield>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>