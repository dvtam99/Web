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
import model.Ca;
import model.DoiTac;
import model.MaBan;

/**
 *
 * @author Tamdv
 */
public class BanDAO extends DBContext {

    public Ban getMaNgay(Date ngay) {
        Ban ban = null;
        ArrayList<MaBan> al = new ArrayList<>();
        try {
            String sql = "SELECT b.*, dt.*, mb.*, c.* FROM Web.[dbo].[Ca] c INNER JOIN Web.dbo.MaBan mb ON mb.IdCa = c.IdCa\n"
                    + "  INNER JOIN Web.dbo.Ma_Ban ON Ma_Ban.IdBan = mb.IdMaBan INNER JOIN Web.dbo.Ban b\n"
                    + "  ON b.IdBan = Ma_Ban.IdMa INNER JOIN Web.dbo.DoiTac dt ON dt.IdDoiTac = b.IdDoiTac WHERE b.Ngay =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            ResultSet rs = statement.executeQuery();
            ban = new Ban();
            ban.setId(-1);
            while (rs.next()) {

                if (rs.getInt("IdBan") != ban.getId()) {
                    DoiTac dt = new DoiTac();
                    dt.setId(rs.getInt("IdDoiTac"));
                    dt.setTen(rs.getNString("TenDt"));
                    dt.setDiachi(rs.getNString("DiaChi"));
                    dt.setSdt(rs.getString("Sdt"));
                    dt.setLoai(rs.getString("Loai"));

                    ban.setId(rs.getInt("IdBan"));
                    ban.setNgay(rs.getDate("Ngay"));
                    ban.setDoitac(dt);
                    ban.setPhe(rs.getDouble("Phe"));
                    ban.setMaban(al);
                    ban.setChiphi(rs.getDouble("ChiPhi"));
                    ban.setTrangthai(rs.getBoolean("TrangThai"));
                    ban.setTongkg(rs.getDouble("TongKg"));
                    ban.setTongtien(rs.getDouble("TongTien"));

                }
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaNhap"));
                f.setGiaban(rs.getDouble("giaBan"));
                f.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));
                MaBan r = new MaBan();
                r.setId(rs.getInt("IdMaBan"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setGiaban(rs.getDouble("GiaBan"));
                r.setCa(f);
                r.setNguoimua(rs.getString("NguoiMua"));
                al.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("---getMaNgay-" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

    public Ban getMaNgay2(Date ngay) {
        Ban ban = null;
        ArrayList<MaBan> al = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Web.dbo.Ban WHERE Ban.Ngay= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            ResultSet rs = statement.executeQuery();
            ban = new Ban();
            ban.setId(-1);
            while (rs.next()) {

                DoiTac dt = new DoiTac();
                dt.setId(rs.getInt("IdDoiTac"));

                ban.setId(rs.getInt("IdBan"));
                ban.setNgay(rs.getDate("Ngay"));
                ban.setDoitac(dt);
                ban.setPhe(rs.getDouble("Phe"));
                ban.setMaban(al);
                ban.setChiphi(rs.getDouble("ChiPhi"));
                ban.setTrangthai(rs.getBoolean("TrangThai"));
                ban.setTongkg(rs.getDouble("TongKg"));
                ban.setTongtien(rs.getDouble("TongTien"));

            }

        } catch (SQLException ex) {
            System.out.println("---getMaNgay2-" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ban;
    }

    public void insertBan(Ban a) {

        try {
            String sql = "INSERT INTO Web.[dbo].[Ban]\n"
                    + "           ([Ngay]\n"
                    + "           ,[IdDoiTac]\n"
                    + "           ,[Phe]\n"
                    + "           ,[ChiPhi]\n"
                    + "           ,[TrangThai]\n"
                    + "           ,[TongKg]\n"
                    + "           ,[TongTien])\n"
                    + "     VALUES ()";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, a.getNgay());
            statement.setInt(2, a.getDoitac().getId());
            statement.setDouble(3, a.getPhe());
            statement.setDouble(4, a.getChiphi());
            statement.setBoolean(5, a.getTrangthai());
            statement.setDouble(6, a.getTongkg());
            statement.setDouble(7, a.getTongtien());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---insertBaoCao---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertNgay(Date ngay, boolean tt) {

        try {
            String sql = "INSERT INTO web.[dbo].[Ban]\n"
                    + "           ([Ngay]\n"
                    + "           ,[TrangThai])\n"
                    + "     VALUES    (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            System.out.println("dal.MuaDAO.insert() " + ngay);
            statement.setBoolean(2, tt);

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertMua_ma(int idMua) {

        try {
            String sql = "INSERT INTO Web.[dbo].[Ma_Ban]\n"
                    + "           ([IdMa],[IdBan])\n"
                    + "     VALUES (?,(SELECT MAX(IdMaBan) FROM Web.dbo.MaBan))";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idMua);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditBan(Ban a) {

        try {
          //  System.err.println("BanEdit "+a.);
            String sql = "UPDATE Web.[dbo].[Ban]  SET [Ngay] =?  ,[IdDoiTac] =  ?,[Phe] =? \n"
                    + "  ,[ChiPhi] =  ?,[TrangThai] = ? ,[TongKg] =  ?,[TongTien] = ?\n"
                    + " WHERE IdBan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, a.getNgay());
            statement.setInt(2, a.getDoitac().getId());
            statement.setDouble(3, a.getPhe());
            statement.setDouble(4, a.getChiphi());
            statement.setBoolean(5, a.getTrangthai());
            statement.setDouble(6, a.getTongkg());
            statement.setDouble(7, a.getTongtien());
            statement.setInt(8, a.getId());
            statement.executeUpdate();
            System.out.println("ok edit mua");
        } catch (SQLException ex) {
            System.out.println("---EditMua---" + ex.getMessage());
            Logger.getLogger(BanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
