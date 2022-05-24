<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<base href="${basePath}">
	<meta charset="UTF-8">
	<title>图书管理界面</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script> 
	<style type="text/css">
		body{
			margin:0;
			padding:0;
			background:#eee;
		}
		.content{
			padding:20px 40px 0 40px;
		} 
		.page-div{
			display: inline-block;
			width:110px;
		}
		.homePage,.prePage,.page-go,.nextPage,.lastPage{
			border-radius:15px;
			color:#337ab7;
		}
		.pager{
			padding:0 20px;
		}
		
		#page-input{
			display:inline-block;
			width:60px;
			border-radius: 10px;
		}
		.bookImg{
			width:50px;
		}
		.funbtn{
			margin:10px 0;
		}
		.funbtn a{
			margin-right:10px;
		}

		/* .table #desc{
			display:inline-block;
			width:300px;
			word-break:keep-all;
    		white-space:nowrap;
    		overflow:hidden;
    		text-overflow:ellipsis;
		} */
	</style>

</head>
<body>
	<c:if test="${!empty bookMessage}">
		<h3 class="text-center">${bookMessage}</h3>
	</c:if>
	<h2 class="text-center">图书列表</h2>
	<div class="container-fulid content">
		<div class="funbtn">
			<a id="batDel" class="btn btn-danger" href="javascript:void(0)" >批量删除</a>
			<a class="btn btn-info" href="jsp/admin/queryCatalog.do">增加图书</a>
			<div class="search col-md-4">
						<div class="input-group">
							<form action="jsp/admin/findBookByName.do" method="get">
								<input type="hidden" value="seach" name="action">
		     	 				<input style="float: left;width: 160px;" type="text" class="form-control" name="bookname" placeholder="输入要搜索的图书名称...">
		       					&nbsp;&nbsp;&nbsp;
		       					<input style="float: left;width: 45px;" class="btn btn-default" type="submit" value="Go!"/>
							</form>
   						</div>
			</div>
		</div>
		<table class="table table-striped table-hover">
			<tr class="success">
				<th>
					<input type="checkbox" id="batDelChoice">
					<label for="batDelChoice"> 全/反选</label>
				</th>
				<th>图书名称</th>
				<th>图书分类</th>
				<th>作者</th>
				<th>出版社</th>
				<th>价格</th>
				<th width="140px;">操作</th>
			</tr>
			<c:choose>
				<c:when test="${!empty bookList}">
					<c:forEach items="${bookList}" var="i" varStatus="n">
						<tr>
							<td class="noClick"><input type="checkbox" name="choice" value="${i.bookId}">
							<td>${i.bookName}</td>
							<td>${i.catalogName}</td>
							<td>${i.author}</td>
							<td>${i.press}</td>
							<td>${i.price}</td>
							<td class="noClick">
								<a class="btn btn-default btn-xs" href="jsp/admin/bookDetail.do?id=${i.bookId}">详情</a>
								<a class="btn btn-info btn-xs" href="jsp/admin/bookEditById.do?id=${i.bookId}">修改</a>
								<a class="btn btn-danger btn-xs" href="jsp/admin/deleteBookById.do?id=${i.bookId}" onclick="javascript:return confirm('确定要删除吗？');">删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="8"><h4 class="text-center">当前无更多图书信息</h4></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	
	<ul class="pager">
		<li><button class="homePage btn btn-default btn-sm">首页</button></li>
		<li><button class="prePage btn btn-sm btn-default">上一页</button></li>
		<li>总共 ${pageBean.pageCount} 页 | 当前 ${pageBean.curPage} 页</li>
		<li>
			跳转到
			<div class="input-group form-group page-div">
				<input id="page-input" class="form-control input-sm" type="text" name="page"/>
				<span>
					<button  class="page-go btn btn-sm btn-default">GO</button>
				</span>
			</div>
		</li>
		<li><button class="nextPage btn btn-sm btn-default">下一页</button></li>
		<li><button class="lastPage btn btn-sm btn-default">末页</button></li>
	</ul>
	</div>
<script type="text/javascript">
	//按钮禁用限制
	if("${pageBean.curPage}"==1){
		$(".homePage").attr("disabled","disabled");
		$(".prePage").attr("disabled","disabled");
	}
	if("${pageBean.curPage}"=="${pageBean.pageCount}"){
		$(".page-go").attr("disabled","disabled");
		$(".nextPage").attr("disabled","disabled");
		$(".lastPage").attr("disabled","disabled");
	}
	//按钮事件
	$(".homePage").click(function(){
		window.location="${bsePath}jsp/admin/queryAllBook.do?page=1";
	})
	$(".prePage").click(function(){
		window.location="${basePath}jsp/admin/queryAllBook.do?page=${pageBean.prePage}";
	})
	$(".nextPage").click(function(){
		window.location="${basePath}jsp/admin/queryAllBook.do?page=${pageBean.nextPage}";
	})
	$(".lastPage").click(function(){
		window.location="${basePath}jsp/admin/queryAllBook.do?page=${pageBean.pageCount}";
	})
	//控制页面跳转范围
	$(".page-go").click(function(){
		var page=$("#page-input").val();
		var pageCount=${pageBean.pageCount};
		if(isNaN(page)||page.length<=0){
			$("#page-input").focus();
			alert("请输入数字页码");
			return;
		}
		if(page > pageCount || page < 1 ){
			alert("输入的页码超出范围！");
			$("#page-input").focus(); 
		}else{
			window.location="${basePath}jsp/admin/queryAllBook.do?page="+page;
		}
	})
	
	//批量选中
	$("#batDelChoice").change(function(){
		if(!$("input[name='choice']").prop("checked")){
			$(this).prop("checked",true);
			$("input[name='choice']").prop("checked",true);
			
		}else{
			$(this).removeProp("checked");
			$("input[name='choice']").removeProp("checked");
		}	
	})

	//批量删除
	$("#batDel").click(function(){
		var choices=$("input:checked[name='choice']");
		var ids= new Array();
		for(i=0;i<choices.length;i++){
			ids.push(choices.eq(i).val())
		}
		if(ids==""){
			alert("请勾选要删除的内容！");
			return;
		}
		if(confirm("你确定要删除"+choices.length+"条用户吗？")){
			var idParam = "";
			for (var i = 0; i < ids.length; i++) {
				if (i!=ids.length-1) {
					idParam += "ids="+ids[i] + "&";
				}else {
					idParam += "ids="+ids[i];
				}
			}
			window.location = "${basePath}jsp/admin/bookDeletesById.do?"+idParam;
		}
	})

</script>
</body>
</html>