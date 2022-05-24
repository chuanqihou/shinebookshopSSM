<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--头部整个页面开始--%>
<div class="head">

	<%--网站顶部服务开始--%>
	<div class="top">
		<div class="container">
			<div class="pull-right">|
				<a href="#">服务中心</a>
				<a href="#">网站地图</a>
			</div>
			<div class="pull-right">
				<c:choose>
					<c:when test="${empty landing}">
						<div class="top-right">
							HI~
							<a href="jsp/book/reg.jsp?type=login">[登录]</a>
							<a href="jsp/book/reg.jsp?type=reg">[免费注册]</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="btn-group adminName top-right">
							<a href="javascript:void(0)">
								${landing.name} <span class="caret"></span>
							</a>
							<ul class="dropdown-menu dropdown-menu-right">
								<li><a href="viewOrder.do" >我的订单</a></li>
								<li><a href="userMessage.do">我的资料</a></li>
								<li><a style="border-top:1px #ccc solid" href="loginOut.do" onClick="return confirm('确定要退出登陆了么？')">退 出 登 录</a></li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<%--网站顶部页面结束--%>

	<%--网站名称,logo以及图书导航,购物车显示数量页面开始--%>
	<div class="mid container">
		<div class="row">
			<a class="logo col-md-5" href="jsp/book/index.jsp" title="shine网上书城">
				<img alt="" src="images/logo.png">
				<span>图书书城</span>
			</a>
			<div class="search col-md-4">
				<div class="input-group">
					<form action="searchBook.do" method="get">
						<input style="float: left;width: 160px;" type="text" class="form-control" name="bookName" placeholder="输入要搜索的图书...">
						&nbsp;&nbsp;&nbsp;
						<input style="float: left;width: 45px;" class="btn btn-default" type="submit" value="Go!"/>
					</form>
				</div>
			</div>
			<div class="shopcart col-md-2 col-md-offset-1">
				<a id="cart" href="jsp/book/cart.jsp">
					<span class="badge num">
						<c:choose>
							<c:when test="${!empty shopCart}">
								${shopCart.getTotQuan()}
							</c:when>
							<c:otherwise>
								0
							</c:otherwise>
						</c:choose>
					</span>
					<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span>
					<span>购物车</span>
					<span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
				</a>
			</div>
		</div>
		<%--网站名称,logo以及图书导航,购物车显示数量页面结束--%>

		<%--图书导航页面开始--%>
		<div class="navbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="jsp/book/index.jsp">首 页 <span class="sr-only">(current)</span></a></li>
				<li><a href="#">分 类</a></li>
				<li><a href="#">新 品</a></li>
				<li><a href="#">特 惠</a></li>
				<li><a href="#">热销榜</a></li>
			</ul>
		</div>
		<%--图书导航页面结束--%>
	</div>
	<%--网站名称,logo以及图书导航页面结束--%>
</div>
<%--头部整个页面结束--%>
