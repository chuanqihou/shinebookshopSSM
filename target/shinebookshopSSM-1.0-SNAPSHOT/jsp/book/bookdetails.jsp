<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书商城-${bookInfo.bookName}详细信息</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <link rel="stylesheet" href="css/book/bookDetails.css">
    <link href="css/book/head_footer.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="bs/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/book/landing.js"></script>
    <script type="text/javascript" src="js/book/addCart.js"></script>
</head>
<body>
<%--图书详细页开始--%>
<div class="container-fullid">
    <%--导入头部页面--%>
    <%@include file="header.jsp" %>
    <div class="wrapper">
        <div class="main container">
            <div class="sub-nav">
                <ol class="breadcrumb">
                    <li><a href="jsp/book/index.jsp">主页</a></li>
                    <li><a href="#">${bookInfo.catalogName}</a></li>
                    <li class="active">${bookInfo.bookName}</li>
                </ol>
            </div>
            <div class="row">
                <div class="col-md-5">
                    <img class="img-responsive" src="${bookInfo.imgSrc}"/>
                </div>
                <div class="col-md-7">
                    <table class="pro_info">
                        <tr>
                            <td colspan="2"><h2>${bookInfo.bookName}</h2></td>
                        </tr>
                        <tr>
                            <td>价格：</td>
                            <td><i>￥${bookInfo.price}</i></td>
                        </tr>
                        <tr>
                            <td>图书编号：</td>
                            <td>${bookInfo.bookId}</td>
                        </tr>
                        <tr>
                            <td>图书分类：</td>
                            <td>${bookInfo.catalogName}</td>
                        </tr>
                        <tr>
                            <td>作者：</td>
                            <td>${bookInfo.author}</td>
                        </tr>
                        <tr>
                            <td>出版社：</td>
                            <td>${bookInfo.press}</td>
                        </tr>
                        <tr>
                            <td>上架日期：</td>
                            <td>${bookInfo.addTime}</td>
                        </tr>
                        <tr>
                            <td>服务：</td>
                            <td>由 图书商城 发货,并提供售后服务,预计三天内送达，我们会全力加快为您服务，谢谢！</td>
                        </tr>
                    </table>
                    <p class="buy_pro">
                        <a onclick="addToCart(${bookInfo.bookId})" class="btn btn-info" href="#">立即购买</a>
                        <button type="button" class="btn btn-danger" onclick="addToCart(${bookInfo.bookId})"
                                data-toggle="modal" data-target=".bs-example-modal-sm">加入购物车
                        </button>
                    </p>
                    <p>温馨提示：支持7天无理由退货</p>
                </div>
            </div>
            <div class="row pro_desc">
                <h3>图书简介</h3>
                <div>${bookInfo.description}</div>
            </div>
        </div>
    </div>
    <%--    导入底部页面--%>
    <%@include file="footer.jsp" %>
</div>
<%--图书详细页面结束--%>

<!--加入购物车提示弹窗盒子 -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
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
<!--加入购物车提示弹窗盒子 -->
</body>
</html>