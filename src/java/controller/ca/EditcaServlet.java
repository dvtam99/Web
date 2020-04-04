/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.ca;

import dal.CaDAO;
import dal.MaMuaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ca;
import model.MaMua;

/**
 *
 * @author Tamdv
 */
public class EditcaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id_r = request.getParameter("id");
        int id = Integer.valueOf(id_r);

        Ca ca = new CaDAO().getGiaca(id);

        request.setAttribute("ca", ca);
        request.getRequestDispatcher("ca/editca.jsp").forward(request, response);
    }

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
        processRequest(request, response);
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
         String id_r = request.getParameter("id");
        String muar = request.getParameter("mua");
        String banr = request.getParameter("ban");
        double mua = Double.valueOf(muar);
        double ban = Double.valueOf(banr);
        int id = Integer.valueOf(id_r);
        Ca c = new Ca();
        c.setId(id);
        c.setTen(ca);
        c.setGiaban(ban);
        c.setGiamua(mua);
        new CaDAO().EditCa(c);
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
