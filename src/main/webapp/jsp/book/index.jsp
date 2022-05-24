<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();  
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书商城</title>
	<link rel="stylesheet" href="bs/css/bootstrap.css">
	<link href="css/book/head_footer.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="css/book/index.css" />
	<script type="text/javascript" src="bs/js/jquery.min.js"></script>
	<script type="text/javascript" src="bs/js/bootstrap.js"></script>
	<script type="text/javascript" src="js/book/landing.js"></script>
	<%--引入ajax请求js文件--%>
	<script type="text/javascript" src="js/book/getCatalog.js"></script>
	<script type="text/javascript" src="js/book/addCart.js"></script>
	<script type="text/javascript" src="js/book/index.js"></script>
</head>
<body>
	<%--整个首页--%>
	<div class="container-fullid">

		<%--引入网站头部页面--%>
		<%@include file="header.jsp" %>

		<%--网站横幅以及左边图书分类页面开始--%>
		<div class="wrapper">
			<!-- 网站横幅div开始 -->
			<div class="banner">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						<li data-target="#carousel-example-generic" data-slide-to="4"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img src="images/book/banner1.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/book/banner2.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/book/banner3.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/book/banner4.jpg" alt="...">
						</div>
						<div class="item">
							<img src="images/book/banner5.jpg" alt="...">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					</a>
					<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					</a>
				</div>
			</div>
			<!-- 网站横幅div结束 -->

			<%--图书分类页面开始--%>
			<div class="main container">
				<div class="row">
					<div class="col-md-2 main-left">
						<h3>图书分类</h3>
						<ul id="catalog-list">
							<li><a href="BookList.do">全部图书</a></li>
						</ul>
					</div>
					<div class="col-md-10 main-right">
						<div class="pro col-md-12">
							<h3>推荐图书</h3>
							<div id="recBooks" class="pro-list">
								<ul></ul>
							</div>
						</div>
						<div class="pro col-md-12">
							<h3>新书上架</h3>
							<div id="newBooks" class="pro-list">
								<ul ></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%--图书分类页面结束--%>
		</div>
		<%--网站横幅以及左边图书分类页面结束--%>

		<%--引入网站底部页面--%>
		<%@include file="footer.jsp" %>
	</div>
	<!--弹窗盒子(用户将图书加入购物车弹窗)开始 -->
	<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	  	<div class="modal-dialog modal-sm">
	    	<div class="modal-content">
	    		<div class="modal-body" style="color:green;font-size:24px;">
				  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>&nbsp商品已成功加入购物车！
				</div>
	      		<div class="modal-footer">
	      			<a href="javascript:void(0)" type="button" class="btn btn-default" data-dismiss="modal">返回继续购物</a>
			        <a href="jsp/book/cart.jsp" type="button" class="btn btn-success">立即去结算</a>
			    </div>
	    	</div>
	  	</div>
	</div>
	<%--弹窗盒子(用户将图书加入购物车弹窗)结束--%>
</body>
</html>