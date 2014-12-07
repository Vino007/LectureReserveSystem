<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>讲座预约系统</title>
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/dashBoard.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mycss.css">

</head>
<body>
	
	<div class="container-fluid ">
			<div class="col-sm-9  col-md-5  main">
						<h4>用户信息</h4>
						<hr>
						<div><span>用户名:</span><span>${user.username}</span></div>
						<div><span>姓名:</span><span>${user.name}</span></div>
						<div><span>性别:</span><span>${user.gender}</span></div>
						<div><span>年级:</span><span>${user.grade}</span></div>
						<div><span>专业:</span><span>${user.major}</span></div>
											
			</div>

		</div>

	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<!-- popover弹出框需要激活才能使用！！！原因是它不是单纯的css插件 -->
	
</body>
</html>

