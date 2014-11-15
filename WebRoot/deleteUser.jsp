<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>删除用户</title>
</head>
<body>
	<s:form action="DeleteUserAction" method="post" >
		<s:textfield name="id" label="请输入要删除的用户的id"></s:textfield>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>