<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>login</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/signin.css" rel="stylesheet">

</head>
<body>
	<div class="container">

		
		<form action="LoginAction" method="post" class="form-signin"
			role="form">
			<h2 class="form-signin-heading">登录</h2>
			<s:actionerror />	
			<input name="user.username" type="text" class="form-control"
				placeholder="Username" required autofocus> 
			<input
				name="user.password" type="password" class="form-control"
				placeholder="Password" required>
			<div class="form-group">
						
						<label class="checkbox-inline"> <input type="radio"
							name="type" value="user"
							checked> 用户
						</label> <label class="checkbox-inline"> <input
							type="radio" name="type" 
							value="admin"> 管理员
						</label>
					</div>

			
			
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>

	</div>
	<!-- /container -->



</body>
</html>