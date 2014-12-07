function queryReserveListCallback(data,status){
		var beanList=data.beanList;
		var pageNo=data.pageNo;
		var totalPage=data.totalPage;
		
		//$("td").remove("#td1");//清空子元素
		$("#tbody").empty();
		//$("#table").html("<tr class='success'><th>标题</th><th>主讲人</th><th>时间</th><th>地点</th><th>已预约人数</th><th>最大允许人数</th></tr>");
		for(var i=0 ; i<beanList.length; i++){			
		$("#tbody").append("<tr><td>"+beanList[i]["username"]+"</td><td>"+beanList[i]["name"]+
				"</td></tr>");		
		}

		$("#pageNo").text(pageNo);
		$("#totalPage").text(totalPage);
		
		
	}

