/**
 * 
 */
	function verify(){
		var jqueryObj=$("#username");
		var username=jqueryObj.val();
		//访问的url
		$.get("AjaxServlet?username="+username,null,callback);
	}
	
	function callback(data){
		var resultObj=$("#result");
		resultObj.html(data);
	}
	/*
	 * # id id选择器
	 */
	function query(){
		var obj=$("#pageNo");
		var pageNo=obj.val();
		$.get("QueryAvailableLectureAction?pageBean.pageNo="+pageNo,null,callback);
	}
	
	function queryReservedCallback(data,status){
		var beanList=data.beanList;
		var pageNo=data.pageNo;
		var totalPage=data.totalPage;

		$("#table").html("<tr class='success'><th>标题</th><th>主讲人</th><th>时间</th><th>地点</th><th>已预约人数</th><th>最大允许人数</th><th>详情</th></tr>");
		for(var i=0 ; i<beanList.length; i++){
		$("#table").append("<tr><td>"+beanList[i]["title"]+"</td><td>"+beanList[i]["lecturer"]+
				"</ td><td>"+beanList[i]["time"]+"</td><td>"+beanList[i]["address"]+"</td><td>"+beanList[i]["currentPeople"]+
				"</td><td>"+beanList[i]["maxPeople"]+"</td><td><a href='#' data-container='body' title='讲座详情' data-toggle='popover'"
		+"data-placement='right' data-delay='100' data-content="+beanList[i]["content"]+">详情</a></td></tr>");
		}
		
		
		$("#pageNo").text(pageNo);
		$("#totalPage").text(totalPage);
		
		//激活分页的弹出框
		$(document).ready(function() {
		$(function () { $("[data-toggle='popover']").popover(); 
		
		});
		});
		
	}
	function queryAvailableCallback(data,status){
		var beanList=data.beanList;
		var pageNo=data.pageNo;
		var totalPage=data.totalPage;
		
		//$("td").remove("#td1");//清空子元素
		$("#tbody").empty();
		//$("#table").html("<tr class='success'><th>标题</th><th>主讲人</th><th>时间</th><th>地点</th><th>已预约人数</th><th>最大允许人数</th></tr>");
		for(var i=0 ; i<beanList.length; i++){			
		$("#tbody").append("<tr><td>"+beanList[i]["title"]+"</td><td>"+beanList[i]["lecturer"]+
				"</td><td>"+beanList[i]["time"]+"</td><td>"+beanList[i]["address"]+"</td><td>"+
				beanList[i]["currentPeople"]+"</td><td>"+beanList[i]["maxPeople"]+"</td>");
		
		//添加预约表单
		//要合并到上面
		//写一个button click的脚本
		$("#tbody").append("<td><button name="+beanList[i]["id"]+"type='submit' value=''>预定</button></td></tr>");
	//	$("tbody").append("td");
	
		
		}
		//alert("nihao");
		$("#pageNo").text(pageNo);
		$("#totalPage").text(totalPage);
		
		
	}
	
	function reserve(){
		$(document).ready(function() {
			$("button").click(function() {
				$.get("ajax/AjaxLecture", {
					"username" : "1",
					"name": "8",
					"id":""
				}, function(data, status) {
				//设置button的显示值
				});
			});
		});
	}