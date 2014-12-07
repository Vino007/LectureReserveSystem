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

	<div class="container-fluid">
			<div  class="col-sm-9  col-md-5  main">
			<div id="content">
				<div id="myAlert" class="alert alert-success" hidden="true">
					<a href="#" class="close"  data-dismiss="alert">&times;</a> 
					<strong id="alertMsg">添加成功！</strong>
				</div>
				<s:form id="addForm"
					action="AjaxAddLectureAction" namespace="/ajax"
					method="post" role="form">
					<div class="form-group">
						<label for="title">讲座主题</label> <input type="text"
							class="form-control" id="title" placeholder="请输入讲座主题"
							name="lectureInfo.title"  required>
					</div>
					<div class="form-group">
						<label for="lecturer">主讲人</label> <input type="text"
							class="form-control " id="lecturer" placeholder="请输入主讲人"
							name="lectureInfo.lecturer" required>
					</div>
					<div class="form-group">
						<!-- 输出的时间为2014-11-30T08:00 -->
						<label for="time">讲座时间</label> <input type="datetime-local"
							class="form-control" id="time" placeholder="请严格按照如下格式输入YYYY.MM.DD HH:ss(24小时制)"
							name="lectureInfo.time" required>
					</div>
					<div class="form-group">
						<label for="address">讲座地点</label> <input type="text"
							class="form-control" id="address" placeholder="请输入讲座地点"
							name="lectureInfo.address" required>
					</div>
					<div class="form-group">
						<label for="maxPeople">最大人数</label> <input type="number"
							class="form-control" id="maxPeople" placeholder="请输入讲座最大允许的人数(10-500)之间"
							name="lectureInfo.maxPeople" required  min="10" max="500">
					</div>
					<div class="form-group">
						<label for="content">讲座介绍</label> <textarea  
							class="form-control" id="content" placeholder="请输入讲座简介"
							name="lectureInfo.content" required></textarea>
					</div>
					<div class="form-group">			
					<button type="submit" class="btn btn-default" id="submit1">提交</button>
					</div>		
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
		$("#addForm").submit(function(){					
			$(this).ajaxSubmit(function(data){
				alert(data.result);
			//	
				if(data.result=="success"){
					$("#alertMsg").text("添加成功");
				}
				else 
					$("#alertMsg").text("添加失败，请重新尝试");
					//重置讲座表单
					document.getElementById("addForm").reset();
					$("#myAlert").show();
			});
			 return false;//阻止表单默认提交 
			
			});
		});
	</script>
</body>
</html>
