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
<center>
	
	<s:a href="addUser.jsp">添加用户</s:a><br>
	<s:a href="updateUser.jsp">更新用户信息</s:a><br>
	<s:a href="deleteUser.jsp">删除用户</s:a><br>
	<s:a href="QueryAllUserAction">查询全部用户</s:a><br>
	<hr>
	<hr>
	<s:if test="#request.Result=='success'">
		
			<script type="text/javascript">
			alert("操作结果：成功！");
			</script>
		<s:else>
			<script type="text/javascript">
			alert("操作结果：失败！");
			</script>
		</s:else>
	</s:if>
	
	<s:actionmessage/>
	</center>
</body>
</html>