<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>预定讲座</title>
</head>
<body>
<center>
	<s:iterator value="lectureInfos" status="status" var="li">
		<table border="2" width="400">
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
				<td>当前人数</td>
				<td>${currentPeople}</td>
			</tr>
			<tr>
				<td>讲座介绍</td>
				<td><s:property value="content" /></td>
			</tr>
			</table>
			<s:form action="LectureAction" method="post" theme="simple">
				<s:hidden name="reserveInfo.username"
					value="%{#session.user.username}"></s:hidden>
				<s:hidden name="reserveInfo.name" value="%{#session.user.name}"></s:hidden>
				<!-- 此id为lectureInfo的id -->
				<s:hidden name="reserveInfo.lectureId" value="%{id}"></s:hidden>			
				<s:submit value="预定" method="reserveLecture"></s:submit>
				<s:submit value="取消" method="cancelReserveLecture"></s:submit>			
			</s:form>
			
		<p><p><p>
	</s:iterator>
</center>
</body>
</html>