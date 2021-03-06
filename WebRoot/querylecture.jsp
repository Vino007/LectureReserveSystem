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
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--你自己的样式文件 -->
<link href="css/your-style.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/dashBoard.css">
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
	margin-top: 100dx;
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
					<%-- <li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li> --%>
					<li><s:a href="QueryAvailableLectureAction">预约讲座</s:a></li>
					<li class="active"><s:a href="QueryAllLectureAction">查询历史讲座</s:a></li>
					<li><s:a href="QueryReservedLectureAction">已约讲座查询</s:a></li>
				</ul>
				<%-- <li><s:a href="QueryAvailableLectureAction">预约讲座</s:a></li>
					<li class="active"><s:a href="QueryAllLectureAction">查询历史讲座</s:a></li>
					<li><s:a href="QueryReservedLectureAction">已约讲座查询</s:a></li>
				</ul> --%>
				<ul class="nav nav-sidebar">
					<li><a href="">用户信息</a></li>
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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<table class="table table-bordered table-hover center "
					contenteditable="true">
					<thead>
						<tr class="success">
							<th>标题</th>
							<th>主讲人</th>
							<th>时间</th>
							<th>地点</th>
							<th>已预约人数</th>
							<th>最大允许人数</th>
						</tr>
					</thead>
					<tbody>
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
					</tbody>

				</table>
				<!-- 分页 -->
				<nav>
					<ul class="pagination center">
						<!-- <li><a href="#">&laquo;</a></li> -->
						<li><s:a href="QueryALLLectureAction?pageBean.pageNo=1">首页</s:a></li>
						<s:if test="pageBean.pageNo > 1 ">
							<li><s:a
									href="QueryAllLectureAction?pageBean.pageNo=%{pageBean.pageNo-1}">上一页</s:a></li>
						</s:if>
						<s:else>
							<li><s:a href="#">上一页</s:a></li>
						</s:else>
						<s:if test="pageBean.pageNo <pageBean.totalPage">
							<li><s:a
									href="QueryAllLectureAction?pageBean.pageNo=%{pageBean.pageNo+1}">下一页</s:a></li>
						</s:if>
						<s:else>
							<li><s:a href="#">下一页</s:a></li>
						</s:else>
						<li><s:a
								href="QueryAllLectureAction?pageBean.pageNo=%{pageBean.totalPage}">尾页</s:a>
						</li>
						<!-- 
						<li><a href="#">&raquo;</a></li> -->
					</ul>
				</nav>
				<p class="text-center">第${pageBean.pageNo}页/共${pageBean.totalPage}页</p>

			</div>

		</div>
	</div>

	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>