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
	
		<s:iterator value="lectureInfos" status="status">
		
		<table border="2">
		
		<tr>
			<td>标题</td>
			<td>${title}</td>
			</tr>
			<tr>
			<td>地点</td>
			<td>${address}</td>
			</tr>
			<tr>
			<td>时间</td>
			<td>${time}</td>
			</tr>
			<tr>
			<td>主讲人</td>
			<td>${lecturer}</td>
			</tr>
			<tr>
			<td>最大人数</td>
			<td>${maxPeople}</td>
			</tr>
			<tr>
			<td>讲座介绍</td>
			<td><s:property value="content"/></td>
			</tr>					
			</table>
			<p><p><p>
		</s:iterator>
		
	
</body>
</html>