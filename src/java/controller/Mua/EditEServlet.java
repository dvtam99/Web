/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Mua;

import dal.MaMuaDAO;
import dal.CaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MaMua;
import model.Ca;

/**
 *
 * @author Tamdv
 */
public class EditEServlet extends HttpServlet {

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
        MaMua ma = new MaMuaDAO().getAssizeByID(id);
        ArrayList<Ca> ca = new CaDAO().getCa();
        System.out.println("___"+ma.getCa().getTen());
        request.setAttribute("id", id);
        request.setAttribute("ma", ma);
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("buy/editE.jsp").forward(request, response);
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
        String id_r = request.getParameter("id");
        int id = Integer.valueOf(id_r);
        String weight_r = request.getParameter("weight");
        Double weight = Double.valueOf(weight_r);
        String idFish_r = request.getParameter("fish");
        
        String giamua_r = request.getParameter("giamua");
        Double giamua = Double.valueOf(giamua_r);
        
        int idFish = Integer.valueOf(idFish_r);

        String pos_r = request.getParameter("position");
        int number = Integer.valueOf(pos_r);

        Ca f = new Ca();
        f.setId(idFish);
        
        MaMua a = new MaMua();
        a.setId(id);
        a.setKhoiluong(weight);
        a.setGiamua(giamua);
        a.setCa(f);
        a.setVitri(number);
        
        new MaMuaDAO().Update(a);

        response.sendRedirect("buy");
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
