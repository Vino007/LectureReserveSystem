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
			<div class="col-sm-9  col-md-5  main">
				<div id="myAlert" class="alert alert-success" hidden="true">
					<a href="#" class="close"  data-dismiss="alert">&times;</a> 
					<strong id="alertMsg">修改成功！</strong>
				</div>
				<s:form id="addForm"
					action="AjaxUpadteUser" namespace="/ajax"
					method="post" role="form">
					<div class="form-group">
						<label for="username">用户名:</label> <br>
						${sessionScope.user.username}
					</div>
					<input type="hidden" name="user.id" value="${sessionScope.user.id}">
					<div class="form-group">
						<label for="name">密码</label> <input type="text"
							class="form-control " id="name" placeholder="请输入密码"
							name="user.password" required>
					</div>				
					<button type="submit" class="btn btn-default" id="submit1">提交</button>
				</s:form>
	

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
					$("#alertMsg").text("修改成功");
				}
				
				else
					$("#alertMsg").text("修改失败，请重新输入");
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
