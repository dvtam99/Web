<%-- 
    Document   : editE
    Created on : Mar 18, 2020, 11:42:58 PM
    Author     : Tamdv 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa</title>

        <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

    </head>
    <body>
        <br>
        <form action="editca" method="post">
            <table > 
                <input name="id" value="${ca.id}" readonly>
                <tr>
                    <td> Cá:  </td>
                    <td>
                        <input name="ca" type="text" value="${ca.ten}"required="">
                    </td>
                </tr>
                <tr>
                    <td> <label for="ban">Giá Mua:   </label></td>
                    <td><input type="number" name="mua" value="${ca.giamua}"required=""></td>
                </tr>                      
                <tr>
                    <td><label for="ban">Giá Bán:   </label></td>
                    <td><input type="number" name="ban" value="${ca.giaban}" required=""></td>
                </tr>
                <tr>
                    <td>
                        <button type="submit" class="btn btn-outline-success"> Thêm</button>
                    <td>
                </tr>
            </table>
        </form>
    </body>
</html>
