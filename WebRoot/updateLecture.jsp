<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改讲座信息</title>
</head>
<body>
<s:form action="UpdateLectureAction" method="post" >
	<table>
	<tr>
			<td>id</td>
			<td><s:textfield name="id"></s:textfield></td>
		</tr>
		<tr>
			<td>标题</td>
			<td><s:textfield name="lectureInfo.title"></s:textfield></td>
		</tr>
		<tr>
			<td>主讲人</td>
			<td><s:textfield name="lectureInfo.lecturer"></s:textfield></td>
		</tr>
		<tr>
			<td>时间</td>
			<td><s:textfield name="lectureInfo.time"></s:textfield></td>
		</tr>
		<tr>
			<td>地点</td>
			<td><s:textfield name="lectureInfo.address"></s:textfield></td>
		</tr>
		<tr>
			<td>最大容纳人数</td>
			<td><s:textfield name="lectureInfo.maxPeople"></s:textfield></td>
		</tr>
		<tr>
			<td>内容</td>
			<td><s:textarea name="lectureInfo.content" cols="50" rows="20" ></s:textarea></td>
			</tr>
		<tr>
			<td><s:submit value="提交"></s:submit></td>
			</tr>			
	</table>
	</s:form>
</body>
</html>