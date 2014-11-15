<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>	
		<table border="1" width="400" >
			<tr>
			<th>用户名</th>
			<th>密码</th>
			<th>姓名</th>
			</tr>
			<s:iterator value="users" status="status">
			<tr>
			<td>${username}</td>
			<td>${password}</td>
			<td>${name}</td>
			<tr>	
			</s:iterator>		
			</table>
			<p><p><p>
		
		</center>
	
</body>
</html>