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
	