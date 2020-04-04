/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ban;
import model.BaoCao;
import model.DoiTac;
import model.MaBan;
import model.Mua;

/**
 *
 * @author Tamdv
 */
public class BaoCaoDAO extends DBContext {

    public BaoCao getBaoCao(Date ngay) {
        BaoCao baocao = null;
        try {
            String sql = "SELECT * FROM Web.dbo.BaoCao WHERE BaoCao.Ngay= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ban b = new Ban();
                b.setId(rs.getInt("IdBan"));
                Mua m = new Mua();
                m.setId(rs.getInt("IdMua"));
                baocao = new BaoCao();
                baocao.setId(rs.getInt("IdBaoCao"));
                baocao.setNgay(rs.getDate("Ngay"));
                baocao.setBan(b);
                baocao.setMua(m);
                baocao.setChenhlech(rs.getDouble("ChenhLech"));
                baocao.setKhoiluong(rs.getDouble("KhoiLuong"));
                baocao.setTonkho(rs.getDouble("TonKho"));
            }

        } catch (SQLException ex) {
            System.out.println("---getMaNgay2-" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return baocao;
    }

    public void insertBan(BaoCao a) {

        try {
            String sql = "INSERT INTO Web.[dbo].[BaoCao] ([IdMua] ,[IdBan],[ChenhLech] ,[Khoiluong] ,[Ngay] ,[TonKho])\n"
                    + "     VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getMua().getId());
            statement.setInt(2, a.getBan().getId());
            statement.setDouble(3, a.getChenhlech());
            statement.setDouble(4, a.getKhoiluong());
            statement.setDate(5, a.getNgay());
            statement.setDouble(6, a.getTonkho());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---insertBan---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditBaoCao(BaoCao a) {

        try {
            //  System.err.println("BanEdit "+a.);
            String sql = "UPDATE web.[dbo].[BaoCao]"
                    + "  SET [IdMua] = ?"
                    + "     ,[IdBan] = ?"
                    + "    ,[ChenhLech] = ?"
                    + "     ,[Khoiluong] = ?"
                    + "    ,[Ngay] =?"
                    + "     ,[TonKho] = ?"
                    + " WHERE BaoCao.IdBaoCao =? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getMua().getId());
            statement.setInt(2, a.getBan().getId());
            statement.setDouble(3, a.getChenhlech());
            statement.setDouble(4, a.getKhoiluong());
            statement.setDate(5, a.getNgay());
            statement.setDouble(6, a.getTonkho());
            statement.setInt(7, a.getId());
            statement.executeUpdate();
            System.out.println("ok edit bc "+a.getId());
        } catch (SQLException ex) {
            System.out.println("---EditMua---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
