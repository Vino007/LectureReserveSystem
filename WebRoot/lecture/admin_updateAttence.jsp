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
				
				<div id="myAlert" class="alert alert-success" hidden="true">
					<a href="#" class="close" data-dismiss="alert">&times;</a> <strong
						id="alertMsg">添加成功！</strong>
				</div>
				<form
					action="${pageContext.request.contextPath}/ajax/AjaxDeleteAttences"
					method="post">
					<table id="table" class="table table-hover  "
						contenteditable="false">
						<thead>
							<tr class="success">
								<th>选择</th>
								<th>学号</th>
								<th>姓名</th>
								<th>年级</th>
								<th>专业</th>

							</tr>
						</thead>
						<tbody id="tbody">

							<s:iterator value="pageBean.beanList" status="status">
								<tr class="tr">
									<td><input type="checkbox" name="ids[${status.index}]"
										value="${id}"></td>
									<td>${username}</td>
									<td>${name}</td>
									<td>${grade}</td>
									<td>${major}</td>

									<!-- theme=simple 解决了 submit标签自动换行问题 -->

									<!-- 修改考勤，跳转到该讲座的考勤清单界面，界面有删除，增加，按钮 -->
								<tr>
							</s:iterator>
						</tbody>
					</table>
					<span class="center">
						<button type="submit" class="btn btn-default btn-lg ">删除</button>
						<button type="button" class="btn btn-default btn-lg "
							data-toggle="modal" data-target="#addModal">增加</button>
					</span>
				</form>


				<!-- 分页 -->
					<nav>
			<ul class="pagination center">
				<li><s:a id="firstPage" href="#" >首页</s:a></li>

				<li><s:a id="prePage" href="#">上一页</s:a></li>


				<li><s:a id="nextPage" href="#">下一页</s:a></li>


				<li><s:a id="lastPage" href="#">尾页</s:a></li>

			</ul>
		</nav>
		<p id="p_pageNo" hidden="true" >${pageBean.pageNo}</p>
		<p id="p_totalPage" hidden="true" >${pageBean.totalPage}</p>
		<p class="text-center">第${pageBean.pageNo}页/共${pageBean.totalPage}页</p>
		
					<!-- 模态框（Modal） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加考勤</h4>
				</div>
				<form
					action="${pageContext.request.contextPath}/ajax/AjaxAddAttence"
					method="get">
					<div class="modal-body">

						<input type="text" name="reserveInfo.username" placeholder="请输入学号">
						<input type="hidden" name="reserveInfo.lectureId"
							value="${reserveInfo.lectureId}">

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-default  ">提交</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	</div>


	<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
	<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
	
	<script type="text/javascript">
	var pageNo=$("#p_pageNo").text();
	var totalPage=$("#p_totalPage").text();
	pageNo=parseInt(pageNo);
	totalPage=parseInt(totalPage);
	$(document).ready(function(){
		$("#nextPage").click(function() {
			if(pageNo<totalPage)
			$("#div0").load("${pageContext.request.contextPath}/UpdateAttenceListAction?pageBean.pageNo=${pageBean.pageNo+1}");
		});
});	
	$(document).ready(function(){
		$("#prePage").click(function() {
			if(pageNo>1)
			$("#div0").load("${pageContext.request.contextPath}/UpdateAttenceListAction?pageBean.pageNo=${pageBean.pageNo-1}");
		});
});	
	$(document).ready(function(){
		$("#firstPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/UpdateAttenceListAction?pageBean.pageNo=1");
		});
});	
	$(document).ready(function(){
		$("#lastPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/UpdateAttenceListAction?pageBean.pageNo=${pageBean.totalPage}");
		});
});	

	
	</script>
	
	
	
	<script>
		$(document).ready(function(){		
		$("form").submit(function(){					
			$(this).ajaxSubmit(function(data){
				alert(data.result);
			
				if(data.result=="delete_success"){
					$("#alertMsg").text("删除成功");
				}
				else if(data.result=="user_no_exist")
					$("#alertMsg").text("用户不存在，请输入正确的学号");
				else if(data.result=="add_success")
					$("#alertMsg").text("添加成功");
				else if(data.result=="add_fail")
					$("#alertMsg").text("添加失败，请重新尝试");
				else  if(data.result=="delete_fail")
					$("#alertMsg").text("删除失败，请重新尝试");
				else
					$("#alertMsg").text("失败，请重新尝试");
					
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
