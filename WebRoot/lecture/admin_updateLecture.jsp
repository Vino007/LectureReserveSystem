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

			<div class="col-sm-9 col-sm-offset-3 col-md-5 col-md-offset-2 main">
				<div id="myAlert" class="alert alert-success" hidden="true">
					<a href="#" class="close"  data-dismiss="alert">&times;</a> 
					<strong id="alertMsg">添加成功！</strong>
				</div>
				<s:form id="addForm" action="AjaxUpdateLectureAction" method="post"
					role="form" namespace="/ajax">
					<input type="hidden" value="${lectureInfo.id}" name="lectureInfo.id">
					<div class="form-group">
						<label for="title">讲座主题</label> <input type="text"
							class="form-control" id="title" placeholder="请输入讲座主题"
							name="lectureInfo.title" value="${lectureInfo.title}" required>
					</div>
					<div class="form-group">
						<label for="lecturer">主讲人</label> <input type="text"
							class="form-control " id="lecturer" placeholder="请输入主讲人"
							name="lectureInfo.lecturer" value="${lectureInfo.lecturer}"
							required>
					</div>
					<div class="form-group">
						<!-- 输出的时间为2014-11-30T08:00 -->
						<label for="time">讲座时间</label> <input type="datetime-local"
							class="form-control" id="time"
							placeholder="请严格按照如下格式输入YYYY.MM.DD HH:ss(24小时制)"
							name="lectureInfo.time" value="${lectureInfo.time}" required>
					</div>
					<div class="form-group">
						<label for="address">讲座地点</label> <input type="text"
							class="form-control" id="address" placeholder="请输入讲座地点"
							name="lectureInfo.address" value="${lectureInfo.address}"
							required>
					</div>
					<div class="form-group">
						<label for="maxPeople">最大人数</label> <input type="number"
							class="form-control" id="maxPeople"
							placeholder="请输入讲座最大允许的人数(10-500)之间" name="lectureInfo.maxPeople"
							value="${lectureInfo.maxPeople}" required min="10" max="500">
					</div>
					<div class="form-group">
						<label for="content">讲座介绍</label>
						<textarea class="form-control" id="content" placeholder="请输入讲座简介"
							name="lectureInfo.content" required>${lectureInfo.content}</textarea>
					</div>
					<button type="submit" class="btn btn-default" id="submit1">确认修改</button>
				</s:form>


			</div>
		</div>
	</div>


	       	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>	
	<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	<script>
		$(document).ready(function(){		
		$("form").submit(function(){					
			$(this).ajaxSubmit(function(data){
				
			
				if(data.result=="update_success"){
					$("#alertMsg").text("修改成功")
				}
				else if(data.result=="update_fail")
					$("#alertMsg").text("修改失败，请重新尝试")
					
					$("#myAlert").show();
			});
			 return false;//阻止表单默认提交 
			
			});
		});
	</script>
</body>
</html>
