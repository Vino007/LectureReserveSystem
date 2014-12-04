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
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dashBoard.css">
<!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
<style type="text/css">
.table-full {
	width: 100%;
	display: table;
	margin-left: auto;
	margin-right: auto;
	margin-top: 100dx;
}

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
	<div class="container-fluid">

		<div class="row">
			<div class="col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<%-- <li class="active"><a href="#">Overview <span
							class="sr-only">(current)</span></a></li> --%>

				<li><a
						href="${pageContext.request.contextPath}/lecture/admin_addLecture.jsp">新增讲座</a></li>
					<li><a href="AdminQueryAllLectureAction?pageBean.pageNo=1">讲座管理</a></li>
					<!-- 查询讲座中有修改讲座，和删除讲座，预约清单 按钮，导出该讲座预约名单 -->
					<!-- 默认显示一个讲座表，点击显示考勤信息，用户（学号）考勤查询 -->
					
					<!-- 上传excel，单个修改考勤，查询考勤 -->
					<li><a href="AdminManageAttenceAction?pageBean.pageNo=1">考勤信息管理</a></li>
				</ul>

				<ul class="nav nav-sidebar">
					<!-- 用户管理中有批量导入用户，用户增删改查 -->
					<li><a href="PageQueryUserAction?pageBean.pageNo=1">用户管理</a></li>
					<!-- 基本信息+已听讲座次数， -->
					<li><a href="#">用户信息查询</a></li>
					<li><a href="#">待定</a></li>
				</ul>
				<ul class="nav nav-sidebar">
					<li><a
						href="${pageContext.request.contextPath}/user/userInfo.jsp">新增管理员</a></li>
					<li><a href="user/userInfo.jsp">修改密码</a></li>
					<li><a href="#">关于</a></li>

				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div id="myAlert" class="alert alert-success" hidden="true">
					<a href="#" class="close" data-dismiss="alert">&times;</a> <strong
						id="alertMsg"></strong>
				</div>
				<table id="table" class="table table-hover table-full "
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
							<th colspan="4" style="text-align: center">操作</th>
						</tr>
					</thead>
					<tbody id="tbody">
						<s:iterator value="pageBean.beanList" status="status">
							<tr class="tr">
								<td valign="bottom">${title}</td>
								<td valign="bottom">${lecturer}</td>
								<td valign="bottom">${time}</td>
								<td valign="bottom">${address}</td>
								<td valign="bottom">${currentPeople}</td>
								<td valign="bottom">${maxPeople}</td>
								<td valign="bottom"><a href="#" data-container="body"
									title="讲座详情" data-toggle="popover" data-placement="right"
									data-delay="100" data-content="${content}">详情</a></td>

								
								<!-- theme=simple 解决了 submit标签自动换行问题 -->
								<td>
									<button
										onclick="javascript:window.location.href='QueryLectureByIdAction?lectureInfo.id=${id}'"
										type="button" class="btn btn-default center">修改</button>
								</td>

								<td><s:form id="ajaxForm" action="AjaxDeleteLectureAction"
										method="post" namespace="/ajax" role="form" theme="simple">
										<!-- 此id为lectureInfo的id -->
										<s:hidden name="lectureInfo.id" value="%{id}"></s:hidden>
										<button id="delete_btn" type="submit" class="btn btn-default">删除</button>
									</s:form></td>

								<!-- window.location.href="你所要跳转的页面"; -->
								<td>
									<button
										onclick="javascript:window.location.href='QueryReserveListAction?pageBean.pageNo=1&reserveInfo.lectureId=${id}'"
										type="button" class="btn btn-default center">查看预约清单</button>
								</td>
								
								<td>
									<button
										onclick="javascript:window.location.href='ExportReserveListAction?lectureId=${id}'"
										type="button" class="btn btn-default center">导出预约清单</button>
								</td>
								
						
							<tr>
						</s:iterator>
					</tbody>

				</table>
				<!-- 分页 -->
				<nav>
					<ul class="pagination center">
						<li><s:a href="AdminQueryAllLectureAction?pageBean.pageNo=1">首页</s:a></li>
						<s:if test="pageBean.pageNo > 1 ">
							<li><s:a
									href="AdminQueryAllLectureAction?pageBean.pageNo=%{pageBean.pageNo-1}">上一页</s:a></li>
						</s:if>
						<s:else>
							<li><s:a href="#">上一页</s:a></li>
						</s:else>
						<s:if test="pageBean.pageNo <pageBean.totalPage">
							<li><s:a
									href="AdminQueryAllLectureAction?pageBean.pageNo=%{pageBean.pageNo+1}">下一页</s:a></li>
						</s:if>
						<s:else>
							<li><s:a href="#">下一页</s:a></li>
						</s:else>
						<li><s:a
								href="AdminQueryAllLectureAction?pageBean.pageNo=%{pageBean.totalPage}">尾页</s:a>
						</li>

					</ul>
				</nav>
				<p class="text-center">第${pageBean.pageNo}页/共${pageBean.totalPage}页</p>
			</div>
		</div>
	</div>

	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	
	<!-- 只有删除用异步，其他都是直接跳转 -->
	<script>
		$(document).ready(function() {
			$("form").submit(function() {
				$(this).ajaxSubmit(function(data) {
					if (data.result == "delete_success") {
						$("#alertMsg").text("删除成功");
					} else
						$("#alertMsg").text("删除失败，请重新尝试");
				
					$("#myAlert").show();
				});
				return false;//阻止表单默认提交 

			});
		});
	</script>
	<!-- popover弹出框需要激活才能使用！！！原因是它不是单纯的css插件 -->
	<script type="text/javascript">
		$(function() {
			$("[data-toggle='popover']").popover();
		});
	</script>

</body>
</html>