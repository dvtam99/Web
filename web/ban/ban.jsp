<%-- 
    Document   : buyjsp
    Created on : Mar 16, 2020, 9:01:26 PM
    Author     : Tamdv 
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bán hàng</title>
        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </head>
    <body>

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


    <c:set var="fish" value="${requestScope.ca}"></c:set>
    <fmt:setLocale value = "vi_VN"/>
    <div class="container">
        <br>
        <h1>Bán - <fmt:formatDate type="date"  dateStyle = "long" value="${mua.ngay}" />  </h1>

        <div id="doingay"></div>
        <input type="date" value="${homnay}" id="date" onchange="doingay()">


        <div class="float-right">          
            <a href='javascript:scroll(0,9999999)' title='Xuống cuối trang'>Xuống cuối</a>
        </div>
        <br>
        <hr>

        <table class="table"  id="myTable" style="border: 2px solid black">
            <thead class="thead-dark">
                <tr id="initTable">
                    <th>Stt</th>
                    <th>Cá</th>
                    <th>Số lượng (kg)</th>
                    <th>Đơn giá</th>
                    <th>Người mua</th>
                    <th>Thành tiền</th>
                    <th></th>
                </tr>

            </thead>
            <tbody>
                <c:forEach items="${requestScope.maMua}" var="a" varStatus="loop">
                    <tr>
                        <td>${loop.index+1}</td>
                        <td>${a.ca.ten}</td>
                        <td>${a.khoiluong}</td>                         
                        <td><fmt:formatNumber value = "${a.giaban}" type = "currency"/></td>
                        <td>${a.nguoimua}</td>                            
                        <td><fmt:formatNumber value = "${a.thanhTien()}" type = "currency"/></td>
                        <td><a href="editMB?id=${a.id}" >Sửa</a><a href="delMb?id=${a.id}" style="color: red"> Xóa</a></td>

                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th> </th>
                    <th>Tổng cộng</th>
                    <th><h4>${tongKg} Kg</h4></th>
                    <th></th>
                    <th>    </th>
                    <th><fmt:formatNumber value = "${tongTien}" type = "currency"/></th>                  
                    <th>    </th>
                </tr>
            </tfoot>
        </table>
        <br/>



        <hr>
        <div class="d-flex  justify-content-between">
            <c:if test="${mua.ngay.toString() == homnay.toString()}">
                <form action="ban" method="post">
                    <table > 
                        <tr>
                            <td> Cá:  </td>
                            <td>
                                <select name="fish"  onchange="giamua()">
                                    <option value="0">Chọn Cá</option>
                                    <c:forEach items="${ca}" var="c" >   
                                        <c:if test="${c.tongkhoiluong > 0}">
                                            <option name="fish" title="${c.giaban}" value="${c.id}" >${c.ten} - ${c.tongkhoiluong}</option>
                                        </c:if>
                                    </c:forEach>            
                                </select>
                                <input name="idMua" value="${mua.id}" hidden>
                            </td>
                        </tr>

                        <tr>
                            <td> <label for="weight">Số Lượng:   </label></td>
                            <td><input type="text" name="weight" ></td>
                        </tr>
                        <tr>
                            <td><label for="position">Người Mua:   </label></td>
                            <td><input type="nguoiMua" name="position"></td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" class="btn btn-outline-success"> Thêm Mã</button>
                            <td>
                        </tr>
                    </table>
                </form>
            </c:if>
            <form action="ban" method="post">
                <table >
                    <input name="idMua" value="${mua.id}" hidden>
                    <tr>
                        <td>Ngày:</td> 
                        <td>  <input type="date" value="${mua.ngay}" name="date"> </td>
                    </tr>
                    <tr>
                        <td>Người Cân:  </td>
                        <td>
                            <select name="chuAo">
                                <option value="0">Người cân</option>
                                <c:forEach items="${requestScope.doitac}" var="s" >
                                    <option name="chuAO" value="${s.id}" <c:if test="${s.id eq mua.doitac.id}">selected</c:if>>${s.ten}-${s.diachi}</option>
                                </c:forEach>            
                            </select>Hoặc
                            <a href="aadSupplier" >Thêm</a>
                        </td>
                    </tr>

                    <tr>
                        <td> <label for="phe"  id="lblPhe">Phế:   </label></td>
                        <td><input type="text"  id="phe"  onchange="pheT()" size="2">%<input type="text"value="${mua.phe}"  id="phe2" size="11ss" disabled ><input type="text" name="phe" id="phe3" hidden>đ</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><label for="chiPhi">Chi Phí:   </label></td>
                        <td><input type="text" name="chiPhi" value="<fmt:formatNumber value = "${mua.chiphi}" />" onchange="pheT()" id="chiphi">đ</td>
                    </tr>
                    <tr>
                        <td>Thu về: </td>
                        <td><input type="text" name="thanhtoan" id="thanhtoan" value="<fmt:formatNumber value = "${mua.tongtien}" />">đ </td>
                    </tr>
                    <tr>
                        <td > &nbsp;<input type="radio" name="trangThai" value="1" <c:if test="${mua.trangthai}">checked=""</c:if> ><label style=" color: red" >Đã Thanh Toán </label></td>
                        <td> &nbsp;<input type="radio" name="trangThai" value="0"  <c:if test="${!mua.trangthai}">checked=""</c:if> <c:if test="${mua.id==null}">checked=""</c:if>>Chưa Thanh Toán</td>
                        </tr>
                        <tr>
                            <td>
                                    <button type="submit" class="btn btn-outline-success"  <c:if test="${mua.ngay.toString() != homnay.toString()}">disabled</c:if>>Lưu</button>
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
        <script>

            function doingay() {

                var ngay = document.getElementById("date").value;
                var x = "<a href=\"ban?date=" + ngay + "\">Ngày " + ngay + "</a>"
                document.getElementById("doingay").innerHTML = x;

            }
            function formatNumber(num) {
                return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
            }
            function pheT() {

                var chiphi = document.getElementById("chiphi").value;
                var phe = document.getElementById("phe").value;
                var tien = ${tongTien} * parseInt(phe) / 100;
                var thanhtoan = ${tongTien} - tien;
                var tongmua = ${tongTien} - tien - parseInt(chiphi);

                document.getElementById("phe2").value = formatNumber(tien);
                document.getElementById("phe3").value = tien;
                document.getElementById("thanhtoan").value = formatNumber(tongmua);

            }

    </script>
</body>
</html>
