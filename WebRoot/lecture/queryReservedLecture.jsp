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
<!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mycss.css">
</head>
<body>
	
	<!-- 加上这句整齐！ -->
	<div class="container-fluid ">
				<table id="table" class="table table-hover  "
					contenteditable="false">
					
					<thead>
					<tr><th>已签到的讲座：</th></tr>
						<tr class="success">
							<th>标题</th>
							<th>主讲人</th>
							<th>时间</th>
							<th>地点</th>
							<th>已预约人数</th>
							<th>最大允许人数</th>
							<th>详情</th>							
						</tr>
					</thead>
					<tbody>
						<s:iterator value="pageBean.beanList" status="status" >
							<tr>
								<td>${title}</td>
								<td>${lecturer}</td>
								<td>${time}</td>
								<td>${address}</td>
								<td>${currentPeople}</td>
								<td>${maxPeople}</td>
								<td><a href="#" data-container="body" title="讲座详情" data-toggle="popover" 
							data-placement="right" data-delay="100"
							data-content="${content}">详情</a></td>
							
							
							</tr>
						</s:iterator>
					</tbody>

				</table>				
				<!-- 分页 -->
				<nav>
					<ul class="pagination center">
						
						<li id="firstPage"><s:a href="#">首页</s:a></li>
					
							<li id="prePage"><s:a
									href="#">上一页</s:a></li>					
							<li id="nextPage"><s:a
									href="#">下一页</s:a></li>						
						<li id="lastPage"><s:a
								href="#">尾页</s:a>
						</li>
				
					</ul>
				</nav>
				<p class="text-center">第<span id="pageNo">${pageBean.pageNo}</span>页/共<span id="totalPage">${pageBean.totalPage}</span>页</p>
				
			</div>

	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/myajax.js"></script>
	<script>
		//首页
		$(document).ready(function() {
			$("#firstPage").click(function() {
				$.get("ajax/AjaxQueryReservedLecture", {
					"pageBean.pageNo" : "1"
				}, function(data, status) {
					queryReservedCallback(data, status);
				});
			});
		});

		//上一页 Number()将字符串转型成整型
		$(document).ready(function() {
			$("#prePage").click(function() {
				if (Number($("#pageNo").text()) > 1)
					var nextPage = Number($("#pageNo").text()) - 1;
				else
					var nextPage = 1;
				$.get("ajax/AjaxQueryReservedLecture", {
					"pageBean.pageNo" : nextPage
				}, function(data, status) {
					queryReservedCallback(data, status);

				});
			});
		});

		//下一页 Number()将字符串转型成整型
	
		$(document).ready(function() {
			$("#nextPage").click(function() {
				if (Number($("#pageNo").text()) < Number($("#totalPage").text()))
					var nextPage = Number($("#pageNo").text()) + 1;//加页
				else
					var nextPage = Number($("#pageNo").text());//保持不变
				$.get("ajax/AjaxQueryReservedLecture",{
					"pageBean.pageNo" : nextPage
					},function(data,status) {
					queryReservedCallback(data,status);
					});
				});
		 });

		//尾页
		$(document).ready(function() {
			$("#lastPage").click(function() {
				$.get("ajax/AjaxQueryReservedLecture", {
					"pageBean.pageNo" : $("#totalPage").text()
				}, function(data, status) {
					queryReservedCallback(data, status);

				});
			});
		});
	</script>
	<!-- popover弹出框需要激活才能使用！！！原因是它不是单纯的css插件 -->
	<script >
	$(document).ready(function() {
	$(function () { $("[data-toggle='popover']").popover(); 
	
	});
	});
	</script>
</body>
</html>

