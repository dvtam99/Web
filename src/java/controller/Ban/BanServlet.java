/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Ban;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.BanDAO;
import dal.CaDAO;
import dal.DoiTacDAO;
import dal.MaBanDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ban;
import model.Ca;
import model.DoiTac;
import model.MaBan;

/**
 *
 * @author Tamdv
 */
public class BanServlet extends BaseRequiredAuthenticationController {

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
        // date ngay tren url, nue date null date = hom nay,
        String date_r = request.getParameter("date");
        java.util.Date uDate1 = new java.util.Date();
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new java.sql.Date(uDate1.getTime());
        dateFormat1.format(date);
        if (date_r != null) {
            date = Date.valueOf(date_r);

        }
        // homnay : ngay hom nay
        java.util.Date uDate = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date homnay = new java.sql.Date(uDate.getTime());
        dateFormat.format(homnay);
        //  ds doi tac, ds ca
        ArrayList<DoiTac> doitac = new DoiTacDAO().getNguoiBan("mua");
        ArrayList<Ca> ca = new CaDAO().getCa();

        Ban ban = new BanDAO().getMaNgay2(date);
        //
        if (ban.getId() == -1 || ban == null) {

            new BanDAO().insertNgay(date, false);
            ban = new BanDAO().getMaNgay2(date);
            System.err.println("idMua = -1" + ban.getId());
        } else {

            ban = new BanDAO().getMaNgay(date);
        }
        if (ban.getMaban() == null && ban.getPhe() == null) {
            ban = new BanDAO().getMaNgay2(date);
        }
        ArrayList<MaBan> maBan = null;
        double tongKg = 0, tongTien = 0;
        // tong tien & khoi luong

        if (ban.getId() >= 0) {
            maBan = ban.getMaban();
            for (MaBan a : maBan) {
                tongKg += a.getKhoiluong();
                tongTien += a.thanhTien();
            }
            tongKg = Math.floor(tongKg * 10) / 10;
        }
        request.setAttribute("tongTien", tongTien);
        request.getSession().setAttribute("tongKgB", tongKg);
        request.getSession().setAttribute("tongTienB", tongTien);
        request.setAttribute("tongKg", tongKg);
        request.setAttribute("homnay", homnay);
        request.setAttribute("doitac", doitac);
        request.setAttribute("ca", ca);
        request.setAttribute("maMua", maBan);
        request.setAttribute("mua", ban);
    }

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        request.getRequestDispatcher("ban/ban.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String date_r = request.getParameter("date");
        String idBan_r = request.getParameter("idMua");
        int idBan = Integer.valueOf(idBan_r);
        if (date_r != null) {
            // them mua
            Date date = Date.valueOf(date_r);
            System.out.println(date + " 2");

            String iddt = request.getParameter("chuAo");
            int idDt = Integer.valueOf(iddt);
            String phe_r = request.getParameter("phe");
            if (phe_r == null || phe_r == "") {
                phe_r = "0";
            }
            double phe = Double.valueOf(phe_r);
            String chiphi_r = request.getParameter("chiPhi");
            if (chiphi_r == null || chiphi_r == "") {
                chiphi_r = "0";
            }
            double chiphi = Double.valueOf(chiphi_r);

            String thanhtoan_r = request.getParameter("trangThai");
            boolean thanhtoan = false;
            if (thanhtoan_r.equals("1")) {
                thanhtoan = true;
            }
            double tongTien = (Double) request.getSession().getAttribute("tongTienB");
            if (tongTien < 0) {
                tongTien = 0;
            }
            tongTien -= (chiphi + phe);

            DoiTac dt = new DoiTac();
            dt.setId(idDt);

            Ban ban = new Ban();
            ban.setId(idBan);
            ban.setChiphi(chiphi);
            ban.setDoitac(dt);
            System.out.println(date + " 3");
            ban.setNgay(date);
            ban.setPhe(phe);

            ban.setTongtien(tongTien);
            ban.setTongkg((double) request.getSession().getAttribute("tongKgB"));
            ban.setTrangthai(thanhtoan);
            new BanDAO().EditBan(ban);

        } else {
            // them ma ban
            String weight_r = request.getParameter("weight");
            Double weight = Double.valueOf(weight_r);
            String idFish_r = request.getParameter("fish");
            int idFish = Integer.valueOf(idFish_r);
            String ngMua = request.getParameter("position");
            if ("".equals(ngMua) || ngMua == null) {
                ngMua = "Lẻ";
            }

            Ca f = new CaDAO().getGiaca(idFish);
            f.setId(idFish);
            f.setTongkhoiluong(f.getTongkhoiluong() - weight);

            MaBan a = new MaBan();
            a.setKhoiluong(weight);
            a.setGiaban(f.getGiaban());
            a.setCa(f);
            a.setNguoimua(ngMua);
            System.out.println("---"+ngMua);
            new MaBanDAO().insert(a);
            new BanDAO().insertMua_ma(idBan);

        }
        processRequest(request, response);

        request.getRequestDispatcher("ban/ban.jsp").forward(request, response);
    }

}
