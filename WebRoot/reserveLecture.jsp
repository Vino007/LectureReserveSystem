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
		<table border="1"  >
			<tr>
			<th>标题</th>
			<th>主讲人</th>
			<th>时间</th>
			<th>地点</th>
			<th>已预约人数</th>
			<th>最大允许人数</th>	
			<th>操作</th>		
			</tr>
	<s:iterator value="pageBean.beanList" status="status">
			<tr>
			<td>${title}</td>
			<td>${lecturer}</td>
			<td>${time}</td>
			<td>${address}</td>
			<td>${currentPeople}</td>
			<td>${maxPeople}</td>
					
			<!-- theme=simple 解决了 submit标签自动换行问题 -->
			<td>
			<s:form action="LectureAction" method="post" theme="simple">
				<s:hidden name="reserveInfo.username"
					value="%{#session.user.username}"></s:hidden>
				<s:hidden name="reserveInfo.name" value="%{#session.user.name}"></s:hidden>
				<!-- 此id为lectureInfo的id -->
				<s:hidden name="reserveInfo.lectureId" value="%{id}"></s:hidden>			
				<s:submit value="预定" method="reserveLecture"></s:submit>
				<s:submit value="取消" method="cancelReserveLecture"></s:submit>			
			</s:form>
			</td>
			</tr>
			</s:iterator>
			</table>
	
</center>
<center>
<!-- %得用在struts标签中，$是el表达式 -->
		第${pageBean.pageNo}页/共${pageBean.totalPage}页 
		<s:a
			href="QueryAvailableLectureAction?pageBean.pageNo=1">首页</s:a>
		<s:if test="pageBean.pageNo > 1 ">
		
			<s:a
				href="QueryAvailableLectureAction?pageBean.pageNo=%{pageBean.pageNo-1}">上一页</s:a>
		</s:if>

		<s:if test="pageBean.pageNo < pageBean.totalPage ">
			<s:a
				href="QueryAvailableLectureAction?pageBean.pageNo=%{pageBean.pageNo+1}">下一页</s:a>
		</s:if>
		<s:a
			href="QueryAvailableLectureAction?pageBean.pageNo=%{pageBean.totalPage}">尾页</s:a>
		
	</center>
</body>
</html>