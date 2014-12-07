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
		<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mycss.css">

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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/adminLectureManage.jsp">讲座预约系统</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
				
				<ul class="nav navbar-nav navbar-right">
					<li ><a onclick="loadJsp('${pageContext.request.contextPath}/lecture/admin_addLecture.jsp')" href="#">新增讲座</a></li>
					<li><a onclick="loadJsp('${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=1')" href="#">讲座管理</a></li>
					<li><a onclick="loadJsp('${pageContext.request.contextPath}/AdminManageAttenceAction?pageBean.pageNo=1')" href="#">考勤管理</a></li>
					<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_addAdmin.jsp')"  href="#">新增管理员</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_addUser.jsp')" href="#">新增用户</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/PageQueryUserAction')" href="#">用户管理</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_updateAdmin.jsp')" href="#">修改密码</a></li>						
					
					<%-- <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">用户 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_addAdmin.jsp')"  href="#">新增管理员</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_addUser.jsp')" href="#">新增用户</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/PageQueryUserAction')" href="#">用户管理</a></li>
							<li><a onclick="loadJsp('${pageContext.request.contextPath}/user/admin_updateAdmin.jsp')" href="#">修改密码</a></li>						
						
						</ul></li>		 --%>			
						
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
					

					<li><a id="a_addLecture"
						href="#">新增讲座</a></li>
					<li><a id="a_queryLecture" href="#">讲座管理</a></li>
					<!-- 查询讲座中有修改讲座，和删除讲座，预约清单 按钮，导出该讲座预约名单 -->
					<!-- 默认显示一个讲座表，点击显示考勤信息，用户（学号）考勤查询 -->

					<!-- 上传excel，单个修改考勤，查询考勤 -->
					<li><a id="a_manageAttence" href="#">考勤管理</a></li>
				</ul>

				<ul class="nav nav-sidebar">
					<li><a id="a_addUser"
						href=#>新增用户</a></li>
					<!-- 用户管理中有批量导入用户，用户增删改查 -->
					<li><a id="a_manageUser" href="#">用户管理</a></li>
					<!-- 基本信息+已听讲座次数， -->
	
				</ul>
				<ul class="nav nav-sidebar">
					<li><a id="a_addAdmin"
						href="#">新增管理员</a></li>
					<li><a id="a_updateAdmin"
						href="#">修改密码</a></li>
					<li><a href="#" data-toggle="modal" data-target="#myModal">关于</a></li>

				</ul>

			</div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				
				<div id="div0" >
				 <h4 class="text-center">欢迎登陆讲座预约系统</h4>
				  
				</div>
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">讲座预约系统beta 1.0</h4>
							</div>
							<div class="modal-body">发现bug请联系 E-mail:540134090@qq.com</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>
				
			</div>
		</div>
	</div>
	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="js/bootstrap.min.js"></script>
	<!-- 异步载入页面 -->
	<script type="text/javascript">
	$(document).ready(function(){
		$("#a_addLecture").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/lecture/admin_addLecture.jsp ");
		});
});	
	
	$(document).ready(function(){
		$("#a_manageAttence").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/AdminManageAttenceAction?pageBean.pageNo=1");
		});
});	
	
	$(document).ready(function(){
		$("#a_queryLecture").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=1");
		});
});	
	
	$(document).ready(function(){
		$("#a_addUser").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/user/admin_addUser.jsp");
		});
});	
	$(document).ready(function(){
		$("#a_manageUser").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/PageQueryUserAction");
		});
});	
	$(document).ready(function(){
		$("#a_addAdmin").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/user/admin_addAdmin.jsp");
		});
});	
	$(document).ready(function(){
		$("#a_updateAdmin").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/user/admin_updateAdmin.jsp");
		});
});	
	function loadJsp(url){
		$("#div0").load(url);
	}
	</script>

</body>
</html>