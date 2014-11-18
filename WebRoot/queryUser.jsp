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
<s:debug></s:debug>
	<center>	
		<table border="1"   >
			<tr>
			<th>用户名</th>
			<th>密码</th>
			<th>姓名</th>
			</tr>
			<s:iterator value="pageBean.beanList" status="status">
			<tr>
			<td>${username}</td>
			<td>${password}</td>
			<td>${name}</td>
			<tr>	
			</s:iterator>		
			</table>
	
		</center>
		
	<center>
		<!-- %得用在struts标签中，$是el表达式 -->
		第${pageBean.pageNo}页/共${pageBean.totalPage}页 
		<s:a
			href="PageQueryUserAction?pageBean.pageNo=1">首页</s:a>
		<s:if test="pageBean.pageNo > 1 ">
		
			<s:a
				href="PageQueryUserAction?pageBean.pageNo=%{pageBean.pageNo-1}">上一页</s:a>
		</s:if>

		<s:if test="pageBean.pageNo < pageBean.totalPage ">
			<s:a
				href="PageQueryUserAction?pageBean.pageNo=%{pageBean.pageNo+1}">下一页</s:a>
		</s:if>
		<s:a
			href="PageQueryUserAction?pageBean.pageNo=%{pageBean.totalPage}">尾页</s:a>
		
	</center>
	
</body>
</html>