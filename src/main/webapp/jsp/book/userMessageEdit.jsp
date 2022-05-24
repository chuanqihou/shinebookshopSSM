<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书商城-修改我的资料</title>
    <link rel="stylesheet" href="bs/css/bootstrap.css">
    <link href="css/book/head_footer.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="bs/validform/style.css">
    <link rel="stylesheet" type="text/css" href="css/book/cart.css">
    <script type="text/javascript" src="bs/js/jquery.min.js"></script>
    <script type="text/javascript" src="bs/js/bootstrap.js"></script>
    <script type="text/javascript" src="js/book/landing.js"></script>
    <script type="text/javascript" src="bs/validform/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="js/book/user_reg_login.js"></script>

</head>
<body>
<div class="container-fullid">
    <%@include file="header.jsp" %>
    <div class="wrapper">
        <div class="main container">
            <h3>
                <span>我的资料</span>
            </h3>
            <div class="content table-responsive">
                <form action="userUpdateById.do" method="post">
                    <table align="center" border="1">
                        <tr>
                            <td>用户Id</td>
                            <td><input type="text" name="userId" value="${param.id}" readonly ></td>
                        </tr>
                        <tr>
                            <td>用户名</td>
                            <td> <input type="text" name="userName"> </td>
                        </tr>
                        <tr>
                            <td>收货人</td>
                            <td><input type="text" name="name"></td>
                        </tr>
                        <tr>
                            <td>性别</td>
                            <td>
                                男<input type="radio" name="sex" value="男">
                                女<input type="radio" name="sex" value="女">
                            </td>
                        </tr>
                        <tr>
                            <td>年龄</td>
                            <td><input type="text" name="age"></td>
                        </tr>
                        <tr>
                            <td>手机号</td>
                            <td><input type="text" name="tell"></td>
                        </tr>
                        <tr>
                            <td>收获地址</td>
                            <td><input type="text" name="address"></td>
                        </tr>
                        <tr>
                            <td> <input type="submit" value="修改"> </td>
                            <td> <input type="reset"> </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>

</body>
</html>