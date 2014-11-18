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
		<table border="1"  >
			<tr>
			<th>标题</th>
			<th>主讲人</th>
			<th>时间</th>
			<th>地点</th>
			<th>已预约人数</th>
			<th>最大允许人数</th>
			
			</tr>
			<s:iterator value="pageBean.beanList" status="status">
			<tr>
			<td>${title}</td>
			<td>${lecturer}</td>
			<td>${time}</td>
			<td>${address}</td>
			<td>${currentPeople}</td>
			<td>${maxPeople}</td>
			
			
			<tr>	
			</s:iterator>		
			</table>
	
		</center>
		
	<center>
		<!-- %得用在struts标签中，$是el表达式 -->
		第${pageBean.pageNo}页/共${pageBean.totalPage}页 
		<s:a
			href="QueryReservedLectureAction?pageBean.pageNo=1">首页</s:a>
		<s:if test="pageBean.pageNo > 1 ">
		
			<s:a
				href="QueryReservedLectureAction?pageBean.pageNo=%{pageBean.pageNo-1}">上一页</s:a>
		</s:if>

		<s:if test="pageBean.pageNo < pageBean.totalPage ">
			<s:a
				href="QueryReservedLectureAction?pageBean.pageNo=%{pageBean.pageNo+1}">下一页</s:a>
		</s:if>
		<s:a
			href="QueryReservedLectureAction?pageBean.pageNo=%{pageBean.totalPage}">尾页</s:a>
		
	</center>
	
</body>
</html>