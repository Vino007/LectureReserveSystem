<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<button id="submit">ajax</button>
<script src="js/myajax.js"></script>
<script src="js/jquery-2.1.1.js"></script>
<script>
	/* $.get("test.cgi", { name: "John", time: "2pm" },
 	 function(data){
  	  alert("Data Loaded: " + data);
  	}); */
  	

		 $(document).ready(function(){
			$("#submit").click(function(){
				//struts2 json-plugin返回的是json对象！
				$.get("AjaxAction",function(data,status){
					var strJson=data;
				
					alert(strJson.pageBean.pageNo);
				});
			});
		}); 
		

		
	</script>
</body>

</html>