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
				<li><s:a id="firstPage" href="#" >首页</s:a></li>

				<li><s:a id="prePage" href="#">上一页</s:a></li>


				<li><s:a id="nextPage" href="#">下一页</s:a></li>


				<li><s:a id="lastPage" href="#">尾页</s:a></li>

			</ul>
		</nav>
		<p id="p_pageNo" hidden="true" >${pageBean.pageNo}</p>
		<p id="p_totalPage" hidden="true" >${pageBean.totalPage}</p>
		<p class="text-center">第${pageBean.pageNo}页/共${pageBean.totalPage}页</p>
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
			$("#div0").load("${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=${pageBean.pageNo+1}");
		});
});	
	$(document).ready(function(){
		$("#prePage").click(function() {
			if(pageNo>1)
			$("#div0").load("${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=${pageBean.pageNo-1}");
		});
});	
	$(document).ready(function(){
		$("#firstPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=1");
		});
});	
	$(document).ready(function(){
		$("#lastPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/AdminQueryAllLectureAction?pageBean.pageNo=${pageBean.totalPage}");
		});
});	
	</script>
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