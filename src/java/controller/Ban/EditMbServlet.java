/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Ban;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.CaDAO;
import dal.MaBanDAO;
import dal.MaMuaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ca;
import model.MaBan;
import model.MaMua;

/**
 *
 * @author Tamdv
 */
public class EditMbServlet extends BaseRequiredAuthenticationController {

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
        MaBan ma = new MaBanDAO().getAssizeByID(id);
        ArrayList<Ca> ca = new CaDAO().getCa();
        System.out.println("___" + ma.getCa().getTen());
        request.setAttribute("id", id);
        request.setAttribute("ma", ma);
        request.setAttribute("ca", ca);
        request.getRequestDispatcher("ban/editMb.jsp").forward(request, response);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String id_r = request.getParameter("id");
        int id = Integer.valueOf(id_r);
        String weight_r = request.getParameter("weight");
        Double weight = Double.valueOf(weight_r);
        String idFish_r = request.getParameter("fish");

        String giamua_r = request.getParameter("giamua");
        Double giamua = Double.valueOf(giamua_r);

        int idFish = Integer.valueOf(idFish_r);

        String pos_r = request.getParameter("position");
        // int number = Integer.valueOf(pos_r);

        Ca f = new Ca();
        f.setId(idFish);

        MaBan a = new MaBan();
        a.setId(id);
        a.setKhoiluong(weight);
        a.setGiaban(giamua);
        a.setCa(f);
        a.setNguoimua(pos_r);

        new MaBanDAO().Update(a);

        response.sendRedirect("ban");
    }

}
