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
	<s:a href="QueryLectureAction">讲座查询</s:a><br>
	<s:a href="addLecture.jsp">讲座新增</s:a><br>
	<s:a href="deleteLecture.jsp">讲座删除</s:a><br>
	<s:a href="updateLecture.jsp">讲座修改</s:a><br>
	<s:a href="QueryAvailableLectureAction">讲座预定</s:a>
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
	<%-- <s:if test="hasActionMessages()">
	<script type="text/javascript">
		alert(<s:actionmessage/>);
	</script>
	</s:if> --%>
	<s:actionmessage/>
	</center>
</body>
</html>