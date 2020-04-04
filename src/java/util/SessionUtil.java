/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TaiKhoan;

/**
 *
 * @author Tamdv
 */
public class SessionUtil {

    public static void addUserToSession(HttpServletRequest request, TaiKhoan account) {
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
    }

    public static TaiKhoan getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (TaiKhoan) session.getAttribute("account");
    }

    public static void removeUserFromSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("account", null);
        Cookie cookyUser = new Cookie("username", "");
        cookyUser.setMaxAge(-1);
        Cookie cookyPass = new Cookie("password", "");
        cookyPass.setMaxAge(-1);
        response.addCookie(cookyUser);
        response.addCookie(cookyPass);
        
         
    }
    ///

}
