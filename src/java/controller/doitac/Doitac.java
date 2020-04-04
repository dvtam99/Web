/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.doitac;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.DoiTacDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DoiTac;

/**
 *
 * @author Tamdv
 */
public class Doitac extends BaseRequiredAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<DoiTac> dtmua = new DoiTacDAO().getNguoiBan("mua");
        ArrayList<DoiTac> dtban = new DoiTacDAO().getNguoiBan("ban");
        dtban.addAll(dtmua);
        request.setAttribute("doitac", dtban);
        request.getRequestDispatcher("doitac/doitac.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String ten = request.getParameter("ten");
        String sdt = request.getParameter("sdt");
        String add = request.getParameter("add");
        String loai = request.getParameter("loai");
        DoiTac c = new DoiTac(0, ten, sdt, add, loai);
        new DoiTacDAO().insert(c);
        response.sendRedirect("doitac");
    }

}
