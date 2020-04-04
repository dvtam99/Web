/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.BanDAO;
import dal.BaoCaoDAO;
import dal.CaDAO;
import dal.MuaDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Ban;
import model.BaoCao;
import model.Ca;
import model.MaMua;
import model.Mua;
import model.TaiKhoan;
import util.SessionUtil;

/**
 *
 * @author Tamdv
 */
public class HomeServlet extends BaseRequiredAuthenticationController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        java.util.Date uDate = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date homnay = new java.sql.Date(uDate.getTime());
        Mua mua = new MuaDAO().getMaNgay(homnay);
        Ban ban = new BanDAO().getMaNgay(homnay);
        BaoCao baoCao = new BaoCaoDAO().getBaoCao(homnay);
        if (baoCao == null) {
            baoCao = getBaocao(baoCao, ban, mua, homnay, 0);
        }
        TaiKhoan taikhoan = SessionUtil.getUserFromSession(request);
        request.setAttribute("taikhoan", taikhoan);
        request.setAttribute("mua", mua);
        request.setAttribute("ban", ban);
        request.setAttribute("baocao", baoCao);
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String btn = request.getParameter("cp");

        java.util.Date uDate = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date homnay = new java.sql.Date(uDate.getTime());
        Mua mua = new MuaDAO().getMaNgay(homnay);
        Ban ban = new BanDAO().getMaNgay(homnay);
        BaoCao baoCao = new BaoCaoDAO().getBaoCao(homnay);

        if (btn.equals("capnhat")) {
            baoCao = getBaocao(baoCao, ban, mua, homnay, 1);
        }
        processGet(request, response);
    }

    private BaoCao getBaocao(BaoCao baoCao, Ban ban, Mua mua, Date homnay, int check) {

        if (check == 0) {
            baoCao = new BaoCao();
        }
        baoCao.setBan(ban);
        baoCao.setMua(mua);
        baoCao.setKhoiluong(mua.getTongkg() - ban.getTongkg() - tonkho());
        baoCao.setChenhlech(ban.getTongtien() - mua.getTongtien());
        baoCao.setTonkho(tonkho());
        baoCao.setNgay(homnay);
        if (check == 0) {
            new BaoCaoDAO().insertBan(baoCao);
        } else {
            new BaoCaoDAO().EditBaoCao(baoCao);
        }
        return baoCao;
    }

    public double tonkho() {
          ArrayList<Ca> ca = new CaDAO().getCa();
        double t = 0;
        for (Ca c : ca) {
            t += c.getTongkhoiluong();
            System.out.println(""+t);
        }
        return t;
    }
}
