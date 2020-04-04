<%-- 
    Document   : home
    Created on : Mar 13, 2020, 9:50:37 PM
    Author     : Tamdv 
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>Home </title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <style>
            .fakeimg {
                height: 200px;
                background: #aaa;
            }
        </style>
    </head>
    <body>
        <fmt:setLocale value = "vi_VN"/>
        <div class="jumbotron text-center" style="margin-bottom:0">
            <h1>Mua Bán Thủy Sản</h1>
        </div>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="home">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="buy">Mua</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ban">Bán</a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link" href="ca">Cá</a>
                    </li>  
                    <li class="nav-item ">
                        <a class="nav-link" href="doitac">Đối tác</a>
                    </li>  
                </ul>
            </div>  
            <ul class="navbar-nav float-right">
                <li class="nav-item ">
                    <label class="nav-link"> Hello: ${sessionScope.account.nguoiDung}</label>

                </li>
                <li class="nav-item ">
                    <a class="nav-link" href="logout">Thoát</a>
                </li>
                <i
        </ul>
    </nav>

    <div class="container" style="margin-top:30px">
        <div class="row">
            <div class="col-sm-4">
                <h3 style="color: #007bff"> Ngày: <fmt:formatDate  type="date"  dateStyle ="short" value="${mua.ngay}" /></h3>
                <br>
                <h5>Mua vào: </h5>

                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Khối Lượng: ${mua.tongkg} kg</li>
                    <li class="list-group-item">Chi ra: <fmt:formatNumber value="${mua.tongtien}" type="CURRENCY"/> </li>
                    <li class="list-group-item">Người bán: ${mua.doitac.ten}</li>

                </ul>
                <br>
                <br>

                <br>
                <h5>Bán ra: </h5>

                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Khối Lượng: ${ban.tongkg} kg</li>
                    <li class="list-group-item">Chi ra: <fmt:formatNumber value="${ban.tongtien}" type="CURRENCY"/></li>
                    <li class="list-group-item">Người cân: ${mua.doitac.ten}</li>

                </ul>
                <br>
                <br>
                <hr class="d-sm-none">
            </div>
            <div class="col-sm-8">
                <form method="post" action="home">
                    <input value="capnhat" type="text" name="cp" hidden>
                    <button type="submit" class="btn-outline-success float-right">Cập Nhật</button>
                </form>
                
                <h3>Tồn kho:  ${baocao.tonkho} kg</h3> 
                <h3>Dư :  ${baocao.khoiluong} kg</h3> 
                <h3>Doanh thu: <fmt:formatNumber value="${baocao.chenhlech}" type="CURRENCY"/></h3> 
            </div>
        </div>
    </div>

    <div class="jumbotron text-center" style="margin-bottom:0">
        <p>Footer</p>
    </div>

</body>
</html>
