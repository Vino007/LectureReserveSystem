<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预定讲座</title>
</head>
<body>
<s:iterator value="lectureInfos" status="status">
				
		<table border="2">		
		<tr>
			<td>id</td>
			<td>${id}</td>
			</tr>
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
		
			<%-- <s:param name="reserveInfo.name" value="#user.name"></s:param> --%>
			
			<%-- <s:param name="reserveInfo.username" value="%{user.username}"></s:param>
			 <s:param name="reserveInfo.lectureId" value="#lectureInfo.id"></s:param> 
			<s:submit value="预定"></s:submit> --%>
			<s:form action="ReserveLectureAction" method="post">
			<s:hidden name="reserveInfo.username" value="%{#session.user.username}"></s:hidden>
			<s:hidden name="reserveInfo.name" value="%{#session.user.name}"></s:hidden>
			<s:hidden name="reserveInfo.lectureId" value="%{id}"></s:hidden>
			
			<s:submit value="预定"></s:submit>
			</s:form>
							
			</table>
			
			<p><p><p>
		</s:iterator>
		
</body>
</html>