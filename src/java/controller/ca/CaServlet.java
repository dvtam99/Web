/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ca;

import dal.CaDAO;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ca;

/**
 *
 * @author Tamdv
 */
public class CaServlet extends HttpServlet {

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pagesize = 5;
        String raw_pageindex = request.getParameter("page");
        if (raw_pageindex == null) {
            raw_pageindex = "1";
        }
        int pageindex = Integer.parseInt(raw_pageindex);
        int count = new CaDAO().count();
        int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;

        ArrayList<Ca> ca = new CaDAO().getCaPagging(pageindex, pagesize);
        request.setAttribute("pagecount", pagecount);
        request.setAttribute("pageindex", pageindex);
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("ca/ca.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String ca = request.getParameter("ca");

        //  ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(ca);
        System.out.println("" + ca);
        String muar = request.getParameter("mua");
        String banr = request.getParameter("ban");
        double mua = Double.valueOf(muar);
        double ban = Double.valueOf(banr);
        Ca c = new Ca();
        c.setTen(ca);
        c.setGiaban(ban);
        c.setGiamua(mua);
        new CaDAO().insert(c);
        response.sendRedirect("ca");
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
