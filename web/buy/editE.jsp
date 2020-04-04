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
    
         <form action="editE" method="post">
             <input name="id" value="${id}" hidden="">
                <label>Ngày: ${requestScope.date}</label>
                <br/>
                <label for="fish">Cá:   </label>
                <select name="fish">
                    <option value="0">Chọn Cá</option>
                    <c:forEach items="${requestScope.ca}" var="f" >
                        <option  value="${f.id}" <c:if test="${f.id eq ma.ca.id}">selected</c:if>> ${f.getTen()}</option>
                    </c:forEach>            
                </select>
                <br/>
                <label for="weight">Số Lượng:   </label>
                <input type="text" name="weight" value="${ma.khoiluong}" >
                <br/>
                <br/>
                <label for="giamua">Giá Mua:   </label>
                <input type="text" name="giamua" value="${ma.giamua}" >
                <br/>
                <label for="position">Vị Trí:   </label>
                <input type="number" name="position" value="${ma.vitri}">
                <br/>


                <button type="submit">Lưu Mã</button>
         </form>
    </body>
</html>
