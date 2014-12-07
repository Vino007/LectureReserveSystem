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

</head>
<body>
	
<div class="container-fluid">
				<s:if test="pageBean.totalPage<1">
							<p><strong>考勤未上传！</strong>	
							</s:if>
				<table id="table" class="table table-bordered table-hover  "
					contenteditable="false">
					<thead>
						<tr class="success">
						<th >学号</th>
						<th >姓名</th>	
						<th >年级</th>
						<th >专业</th>								
						</tr>
					</thead>
					<tbody id="tbody">
					
						<s:iterator id="beanList" value="pageBean.beanList" status="status">
							
							<tr>							
								<td>${username}</td>
								<td>${name}</td>
								<td>${grade}</td>
								<td>${major}</td>
							<tr>
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
						<!-- 
						<li><a href="#">&raquo;</a></li> -->
					</ul>
				</nav>
				<p class="text-center">第<span id="pageNo">${pageBean.pageNo}</span>页/共<span id="totalPage">${pageBean.totalPage}</span>页</p>
			</div>



	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/admin_myajax.js"></script>
<script type="text/javascript">

function queryAttenceListCallback(data,status){
	var beanList=data.beanList;
	var pageNo=data.pageNo;
	var totalPage=data.totalPage;
	$("#tbody").empty();
	for(var i=0 ; i<beanList.length; i++){			
	$("#tbody").append("<tr><td>"+beanList[i]["username"]+"</td><td>"+beanList[i]["name"]+
			"</td><td>"+beanList[i]["grade"]+"</td><td>"+beanList[i]["major"]+
			"</td></tr>");		
	}

	$("#pageNo").text(pageNo);
	$("#totalPage").text(totalPage);
	
	
}
</script>
	<script>
		//首页
		$(document).ready(function() {
			$("#firstPage").click(function() {
				$.get("${pageContext.request.contextPath}/ajax/AjaxQueryAttenceList", {
					"pageBean.pageNo" : "1"
				}, function(data, status) {
					queryAttenceListCallback(data, status);
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
				$.get("${pageContext.request.contextPath}/ajax/AjaxQueryAttenceList", {
					"pageBean.pageNo" : nextPage
				}, function(data, status) {
					queryAttenceListCallback(data, status);

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
				$.get("${pageContext.request.contextPath}/ajax/AjaxQueryAttenceList",{
					"pageBean.pageNo" : nextPage
					},function(data,status) {
						queryAttenceListCallback(data,status);
					});
				});
		 });

		//尾页
		$(document).ready(function() {
			$("#lastPage").click(function() {
				$.get("${pageContext.request.contextPath}/ajax/AjaxQueryAttenceList", {
					"pageBean.pageNo" : $("#totalPage").text()
				}, function(data, status) {
					queryAttenceListCallback(data, status);

				});
			});
		});
	</script>


</body>
</html>