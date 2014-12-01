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
<!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style type="text/css">
.center {
	width: auto;
	display: table;
	margin-left: auto;
	margin-right: auto;
}

body {
	padding-top: 70px;
}

.text-center {
	text-align: center;
}
</style>
</head>
<body>
	<!-- 导航条 -->
	<nav class="navbar navbar-default navbar-inverse  navbar-fixed-top "
		role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">讲座预约系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Link</a></li>
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
							<li class="divider"></li>
							<li><a href="#">One more separated link</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">Link</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Dropdown <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="#">Action</a></li>
							<li><a href="#">Another action</a></li>
							<li><a href="#">Something else here</a></li>
							<li class="divider"></li>
							<li><a href="#">Separated link</a></li>
						</ul></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<!-- 侧边栏 -->

	<!-- 加上这句整齐！ -->
	<div class="container-fluid ">

		<div class="row">
			<div class="col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<%-- 	<li><a href="#">Overview <span class="sr-only">(current)</span></a></li> --%>
					<li><s:a href="QueryAvailableLectureAction">预约讲座</s:a></li>
					<li ><s:a href="QueryAllLectureAction?pageBean.pageNo=1">查询历史讲座</s:a></li>
					<li class="active"><s:a href="QueryReservedLectureAction?pageBean.pageNo=1">已听讲座查询</s:a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href="${pageContext.request.contextPath}/user/userInfo.jsp">用户信息</a></li>
					<li><a href="">关于</a></li>
					<li><a href=""></a></li>
					<li><a href=""></a></li>
					<li><a href=""></a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a href=""></a></li>
					<li><a href=""></a></li>
					<li><a href=""></a></li>
				</ul>
			</div>
	
	
			
	<!-- 表格部分 -->		
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<table id="table" class="table table-bordered table-hover center "
					contenteditable="false">
					<thead>
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
						<!-- <li><a href="#">&laquo;</a></li> -->
						<%-- <li><s:a href="QueryReservedLectureAction?pageBean.pageNo=1">首页</s:a></li> --%>
						
						<li id="firstPage"><s:a href="#">首页</s:a></li>
					
							<li id="prePage"><s:a
									href="#">上一页</s:a></li>					
							<li id="nextPage"><s:a
									href="#">下一页</s:a></li>						
						<li id="lastPage"><s:a
								href="#">尾页</s:a>
						</li>
						<!-- 
						<li><a href="#">&raquo;</a></li> -->
					</ul>
				</nav>
				<p class="text-center">第<span id="pageNo">${pageBean.pageNo}</span>页/共<span id="totalPage">${pageBean.totalPage}</span>页</p>
				
			</div>

		</div>
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

