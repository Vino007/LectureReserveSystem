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
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/lectureManage.jsp">讲座预约系统</a>
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
					<li ><a href="#" onclick="loadJsp('${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=1')">预约讲座</a></li>
					<li><a href="#" onclick="loadJsp('${pageContext.request.contextPath}/QueryAllLectureAction?pageBean.pageNo=1')">讲座查询</a></li>
					<li><a href="#" onclick="loadJsp('${pageContext.request.contextPath}/QueryReservedLectureAction?pageBean.pageNo=1')">考勤查询</a></li>
					<li><a href="#" onclick="loadJsp('${pageContext.request.contextPath}/user/userInfo.jsp')">用户信息</a></li>
					<li><a href="#" onclick="loadJsp('${pageContext.request.contextPath}/user/updateUser.jsp')">修改密码</a></li>
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
				<ul class="nav nav-sidebar" >
					<li><a id="a_reserveLecture" href="#">预约讲座</a></li>
					<li><a  id="a_queryAllLecture" href="#">查询历史讲座</a></li>
					<li><a  id="a_queryReservedLecture" href="#">考勤查询</a></li>
				</ul>
				
				<ul class="nav nav-sidebar">
				
				
					<li><a id="a_userInfo" href="#">用户信息</a></li>
					<li><a id="a_updateUser" href="#">修改密码</a></li>
					
				</ul>
				
			</div>
			<!--tab的内容  -->
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
					<div id="div0">
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
			$("#a_reserveLecture").click(function() {
				$("#div0").load("${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=1");
			});
		});	
		$(document).ready(function(){
			$("#a_queryAllLecture").click(function() {
				$("#div0").load("${pageContext.request.contextPath}/QueryAllLectureAction?pageBean.pageNo=1");
			});
		});	
		$(document).ready(function(){
			$("#a_queryReservedLecture").click(function() {
				$("#div0").load("${pageContext.request.contextPath}/QueryReservedLectureAction?pageBean.pageNo=1");
			});
		});	
		$(document).ready(function(){
			$("#a_userInfo").click(function() {
				$("#div0").load("${pageContext.request.contextPath}/user/userInfo.jsp ");
			});
		});	
		
		$(document).ready(function(){
			$("#a_updateUser").click(function() {
				$("#div0").load("${pageContext.request.contextPath}/user/updateUser.jsp ");
			});
		});	
		function loadJsp(url){
			$("#div0").load(url);
		}
		
		</script>
		<script >
	
	$(function () { $("[data-toggle='popover']").popover(); 
	
	});
	</script>

</body>
</html>