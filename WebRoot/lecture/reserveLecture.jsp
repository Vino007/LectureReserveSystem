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
	
	<div class="container-fluid ">
	<h6>讲座开始前两小时截止预约</h6>
	<div id="myAlert" class="alert alert-success" hidden="true">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong
				id="alertMsg">void</strong>
		</div>
		<table id="table"
				class="table  table-hover "
				contenteditable="false">
				<thead>
					<tr class="success ">
						<th >标题</th>
						<th >主讲人</th>
						<th >时间</th>
						<th >地点</th>
						<th >已预约人数</th>
						<th >最大允许人数</th>
						<th>详情</th>
						<th colspan="2" style="text-align: center">操作</th>
					</tr>
				</thead>
				<tbody id="tbody">
					<s:iterator value="pageBean.beanList" status="status">
						<tr>

							<td>${title}</td>
							<td>${lecturer}</td>
							<td>${time}</td>
							<td>${address}</td>
							<td>${currentPeople}</td>
							<td>${maxPeople}</td>
							<td><a href="#" data-container="body" title="讲座详情" data-toggle="popover" 
							data-placement="right" data-delay="100"
							data-content="${content}">详情</a></td>
							<!-- 表单异步提交，返回result -->
							<!-- theme=simple 解决了 submit标签自动换行问题 -->
							<td><s:form id="%{id}" action="AjaxReserveLecture"
									method="get" namespace="/ajax" role="form" theme="simple">
									<s:hidden name="reserveInfo.username"
										value="%{#session.user.username}"></s:hidden>
									<s:hidden name="reserveInfo.name" value="%{#session.user.name}"></s:hidden>
									<s:hidden name="reserveInfo.major" value="%{#session.user.major}"></s:hidden>
									<s:hidden name="reserveInfo.grade" value="%{#session.user.grade}"></s:hidden>
									<!-- 此id为lectureInfo的id -->
									<s:hidden name="reserveInfo.lectureId" value="%{id}"></s:hidden>
									<button type="submit" id="reserve" class="btn btn-default btn-sm">预定</button>

								</s:form></td>
							<td><s:form id="%{id}" action="AjaxCancelReserveLecture"
									method="get" namespace="/ajax" role="form" theme="simple">
									<s:hidden name="reserveInfo.username"
										value="%{#session.user.username}"></s:hidden>
									<s:hidden name="reserveInfo.name" value="%{#session.user.name}"></s:hidden>
									<!-- 此id为lectureInfo的id -->
									<s:hidden name="reserveInfo.lectureId" value="%{id}"></s:hidden>

									<button type="submit" id="cancel" class="btn btn-default btn-sm">取消</button>
								</s:form></td>
						</tr>
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
	<script src="${pageContext.request.contextPath}/js/myajax.js"></script>
	
	<script type="text/javascript">
	var pageNo=$("#p_pageNo").text();
	var totalPage=$("#p_totalPage").text();
	pageNo=parseInt(pageNo);
	totalPage=parseInt(totalPage);
	$(document).ready(function(){
		$("#nextPage").click(function() {
			if(pageNo<totalPage)
			$("#div0").load("${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=${pageBean.pageNo+1}");
		});
});	
	$(document).ready(function(){
		$("#prePage").click(function() {
			if(pageNo>1)
			$("#div0").load("${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=${pageBean.pageNo-1}");
		});
});	
	$(document).ready(function(){
		$("#firstPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=1");
		});
});	
	$(document).ready(function(){
		$("#lastPage").click(function() {
			$("#div0").load("${pageContext.request.contextPath}/QueryAvailableLectureAction?pageBean.pageNo=${pageBean.totalPage}");
		});
});	
	</script>
	
	
	
	<script>
		$(document).ready(function(){
		$("form").submit(function(){
			//id的作用都是为了锁定预约按钮
			var id=$(this).attr("id");
			$(this).ajaxSubmit(function(data){
				alert(data.result);
				if(data.result=="reserve_success"){
					$("#"+id).children("#reserve").text("已预约");
					$("#alertMsg").text("预约成功");}
					else if(data.result=="cancel_success"){
					$("#"+id).children("#reserve").text("预约");	
					$("#alertMsg").text("取消成功");}
					else if(data.result=="repeat")
						$("#alertMsg").text("您已经预约过了");
					else if(data.result=="cancel_fail")
						$("#alertMsg").text("取消失败，请重新尝试");
					else if(data.result=="reserve_fail")
						$("#alertMsg").text("预约失败，请重新尝试");
					else 
						$("#alertMsg").text("失败，请重新尝试");
				
				$("#myAlert").show();
			});
			 return false;//阻止表单默认提交 
			
			});
		});
		
	
		
	
	</script>
	<!-- popover弹出框需要激活才能使用！！！原因是它不是单纯的css插件 -->
	
	<script >
	
	$(function () { $("[data-toggle='popover']").popover(); 
	
	});
	</script>
</body>
</html>