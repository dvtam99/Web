/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dal.TaiKhoanDAO;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import util.SessionUtil;

/**
 *
 * @author Tamdv
 */
public class AuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        TaiKhoanDAO udb = new TaiKhoanDAO();
        TaiKhoan account = udb.getTaiKhoan(username, password);

        if (account != null) {

            SessionUtil.addUserToSession(request, account);
            if (remember != null) {
                Cookie cookyUser = new Cookie("username", username);
                Cookie cookyPassword = new Cookie("password", password);
                cookyUser.setMaxAge(3600);
                cookyPassword.setMaxAge(3600);
                response.addCookie(cookyUser);
                response.addCookie(cookyPassword);
            }
            response.sendRedirect("home");
        } else {

            response.getWriter().write("Tên đăng nhập hoặc mật khẩu sai.");

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
