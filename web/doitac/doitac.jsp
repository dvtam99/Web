<%-- 
    Document   : home   
    Created on : Mar 13, 2020, 9:50:37 PM
    Author     : Tamdv 
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đối tác</title>
        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

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

            </ul>
        </nav>



        <fmt:setLocale value = "vi_VN"/>
        <div class="container">


            <div class="float-right">   

                <a href='javascript:scroll(0,9999999)' title='Xuống cuối trang'>Xuống cuối</a>
            </div>
            <br>
            <hr>

            <table class="table"  id="myTable" style="border: 2px solid black">
                <thead class="thead-dark">
                    <tr id="initTable">
                        <th>Id</th>
                        <th>Tên</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Loại</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.doitac}" var="ca" >
                        <tr>
                            <td>${ca.id}</td>
                            <td>${ca.ten}</td>
                            <td>${ca.sdt}</td>                    
                            <td>${ca.diachi}</td>    
                            <td>${ca.loai}</td>             
                            <td><a href="editdt?id=${ca.id}" >Sửa</a>
                                <a href="editdt?id=${ca.id}" style="color: red"> Xóa</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                    <tr>
                        <th> </th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                </tfoot>
            </table>
            <br/>

            <hr>
            <div class="d-flex  justify-content-between">

                <form action="doitac" method="post">
                    <table > 
                        <tr>
                            <td> Tên:  </td>
                            <td>
                                <input name="ten" type="text" required="">
                            </td>
                        </tr>
                        <tr>
                            <td> <label for="ban">Sđt:   </label></td>
                            <td><input type="tel" name="sdt" required=""></td>
                        </tr>                      
                        <tr>
                            <td><label for="ban">Địa chỉ:   </label></td>
                            <td><input type="text" name="add" required=""></td>
                        </tr>
                        <tr>
                            <td><label for="ban">Loại:   </label></td>
                            <td><input type="radio" name="loai"  value="mua"required="">Mua
                                <input type="radio" name="loai" value="ban" required="">Bán
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" class="btn btn-outline-success"> Thêm</button>
                            <td>
                        </tr>
                    </table>
                </form>

            </div>
            <a href="#"h class="float-right">Lên đầu</a>
            <br>
        </div>

        <div class="jumbotron text-center" style="margin-bottom:0">
            <p>Footer</p>
        </div>

    </body>
</html>
