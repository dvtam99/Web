/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Mua;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.MaMuaDAO;
import dal.CaDAO;
import dal.DoiTacDAO;
import dal.MuaDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MaMua;
import model.Ca;
import model.DoiTac;
import model.Mua;

/**
 *
 * @author Tamdv
 */
public class MuaServlet extends BaseRequiredAuthenticationController {

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
        String date_r = request.getParameter("date");
        // date ngay tren url
        Date date = null;
        if (date_r == null) {

            java.util.Date uDate = new java.util.Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = new java.sql.Date(uDate.getTime());
            dateFormat.format(date);
        } else {
            date = Date.valueOf(date_r);
        }

        // homnay : ngay hom nay
        java.util.Date uDate = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date homnay = new java.sql.Date(uDate.getTime());

        dateFormat.format(homnay);
        //  ds doi tac
        ArrayList<DoiTac> doitac = new DoiTacDAO().getNguoiBan("ban");
        // ds ca
        ArrayList<Ca> ca = new CaDAO().getCa();

        // ds ma mua
        // ArrayList<MaMua> maMua = new MaMuaDAO().getAssize();
            Mua mua = new MuaDAO().getMaNgay2(date);
            //
            if (mua.getId() == -1 || mua == null) {

                new MuaDAO().insertNgay(date, false);
                mua = new MuaDAO().getMaNgay2(date);
                System.err.println("idMua = -1" + mua.getId());
            } else {

                mua = new MuaDAO().getMaNgay(date);
            }
        if (mua.getMamua() == null && mua.getPhe() == null) {
            System.out.println("controller.Mua.");
            mua = new MuaDAO().getMaNgay2(date);
        }
        ArrayList<MaMua> maMua = null;
        double tongKg = 0, tongTien = 0;
        // tong tien & khoi luong

        if (mua.getId() >= 0) {
            maMua = mua.getMamua();
            for (MaMua a : maMua) {
                tongKg += a.getKhoiluong();
                tongTien += a.thanhTien();
            }
            tongKg = Math.floor(tongKg * 10) / 10;
        }
        request.setAttribute("tongTien", tongTien);
        request.setAttribute("tongKg", tongKg);
        request.getSession().setAttribute("tongKg", tongKg);
        request.getSession().setAttribute("tongTien", tongTien);
        request.setAttribute("homnay", homnay);
        request.setAttribute("doitac", doitac);
        request.setAttribute("ca", ca);
        request.setAttribute("maMua", maMua);
        request.setAttribute("mua", mua);
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

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("buy/buy.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date_r = request.getParameter("date");
        String idMua_r = request.getParameter("idMua");
        int idMua = Integer.valueOf(idMua_r);
        if (date_r != null) {
            // them mua
            Date date = Date.valueOf(date_r);
            String idAo = request.getParameter("chuAo");
            if (idAo == null || "".equals(idAo)) {
                idAo = "1";
            }
            int chuAo = Integer.valueOf(idAo);
            String phe_r = request.getParameter("phe");
            if (phe_r == null || phe_r == "") {
                phe_r = "0";
            }
            double phe = Double.valueOf(phe_r);

            double tongkg = (double) request.getSession().getAttribute("tongKg");
            String chiphi_r = request.getParameter("chiPhi");
            if (chiphi_r == null || chiphi_r == "") {
                chiphi_r = "0";
            }
            double chiphi = Double.valueOf(chiphi_r);

            double tongtien = (double) request.getSession().getAttribute("tongTien");
            tongtien += chiphi; 
            String thanhtoan_r = request.getParameter("trangThai");
            boolean thanhtoan = false;
            if (thanhtoan_r.equals("1")) {
                thanhtoan = true;
            }

            DoiTac dt = new DoiTac();
            dt.setId(chuAo);

            Mua mua = new Mua();
            mua.setId(idMua);
            mua.setChiphi(chiphi);
            mua.setDoitac(dt);
            System.out.println(date + " 3");
            mua.setNgay(date);
            mua.setPhe(phe);
            mua.setTongtien(tongtien);
            mua.setTrangthai(thanhtoan);
            mua.setTongkg(tongkg);
            new MuaDAO().EditMua(mua);

        } else {
            // them ma mua
            String weight_r = request.getParameter("weight");
            Double weight = Double.valueOf(weight_r);
            String idFish_r = request.getParameter("fish");
            int idFish = Integer.valueOf(idFish_r);
            String pos_r = request.getParameter("position");
            int number = Integer.valueOf(pos_r);

            Ca f = new CaDAO().getGiaca(idFish);
            f.setId(idFish);
            f.setTongkhoiluong(f.getTongkhoiluong() + weight);
            System.out.println("111 " + f.getTongkhoiluong());

            MaMua a = new MaMua();
            a.setKhoiluong(weight);
            a.setGiamua(f.getGiamua());
            a.setCa(f);
            a.setVitri(number);

            new MaMuaDAO().insert(a);
            new MuaDAO().insertMua_ma(idMua);

        }
        processRequest(request, response);

        request.getRequestDispatcher("buy/buy.jsp").forward(request, response);
    }
}
