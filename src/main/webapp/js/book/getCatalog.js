//获取分类信息ajax请求
$.ajax({
	url:"GetCatalog.do",
	dataType:"json",
	async:true,
	type:"get",
	success:function(data){
		//分类信息
		if(data!=null){
			$.each(data,function(i,n){
				$("#catalog-list").append("<li><a href='BookList.do?catalogId="+n.catalogId+"'>"+n.catalogName+"("+n.catalogSize+")</a></li>");
			})
		}
	}
})


