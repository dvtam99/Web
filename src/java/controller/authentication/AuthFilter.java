/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TaiKhoan;
import util.SessionUtil;

/**
 *
 * @author Tamdv
 */
public class AuthFilter implements Filter {
    
   /**
     *
     * @param arg0
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        PrintWriter out = resp.getWriter();
        TaiKhoan user = SessionUtil.getUserFromSession((HttpServletRequest) req);

        if (user != null) {
            chain.doFilter(req, resp);//sends request to next resource  
        } else {
//            String dot = "";
//            HttpServletRequest request = (HttpServletRequest) req;
             HttpServletResponse res = (HttpServletResponse) resp;
//            String str = request.getServletPath();
//            String[] c = str.split("");
//            for (String string : c) {
//                if (string.equals("/")) {
//                    dot += ".";
//                }
//            }
           // out.print("You must login!");
           res.sendRedirect("login");
          //  request.getRequestDispatcher(dot +"/login");
            //  RequestDispatcher rd = req.getRequestDispatcher(dot + "/login"); dot +  
            // rd.include(req, resp);
        }

    }

    @Override
    public void destroy() {
    }
}
