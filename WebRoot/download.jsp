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
<a href="ExportReserveListAction?id=1">下载</a><br>
<a href="ExportReserveListAction?id=1">导出预约名单</a><br>
	<s:form action="FileUploadAction" method="post" enctype="multipart/form-data" namespace="/upload">
		<s:file name="file" lable="请选择上传的文件"></s:file>
		<s:textarea name="lectureId" value="107"></s:textarea>
		<s:textarea name="description" cols="50" rows="10" label="文件描述"></s:textarea>
		<s:submit value="上传"></s:submit>
	</s:form>
</body>
</html>