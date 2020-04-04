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
import model.Ca;
import model.DoiTac;
import model.MaMua;
import model.Mua;

/**
 *
 * @author Tamdv
 */
public class MuaDAO extends DBContext {

    public Mua getMaNgay(Date ngay) {
        Mua mua = null;
        ArrayList<MaMua> al = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Web.dbo.Ca c INNER JOIN  web.dbo.MaMua mm ON mm.IdCa = c.IdCa\n"
                    + "INNER JOIN Web.dbo.Ma_Mua ON Ma_Mua.IdMa = mm.IdMaMua INNER JOIN \n"
                    + "Web.dbo.Mua m ON m.IdMua = Ma_Mua.IdMua  INNER JOIN \n"
                    + "Web.dbo.DoiTac d ON d.IdDoiTac = m.IdDoiTac WHERE Ngay=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            ResultSet rs = statement.executeQuery();
            mua = new Mua();
            mua.setId(-1);
            while (rs.next()) {

                if (rs.getInt("IdMua") != mua.getId()) {
                    DoiTac dt = new DoiTac();
                    dt.setId(rs.getInt("IdDoiTac"));
                    dt.setTen(rs.getNString("TenDt"));
                    dt.setDiachi(rs.getNString("DiaChi"));
                    dt.setSdt(rs.getString("Sdt"));
                    dt.setLoai(rs.getString("Loai"));

                    mua.setId(rs.getInt("IdMua"));
                    mua.setNgay(rs.getDate("Ngay"));
                    mua.setDoitac(dt);
                    mua.setPhe(rs.getDouble("Phe"));
                    mua.setMamua(al);
                    mua.setChiphi(rs.getDouble("ChiPhi"));
                    mua.setTrangthai(rs.getBoolean("TrangThai"));
                    mua.setTongtien(rs.getDouble("TongTien"));
                    mua.setTongkg(rs.getDouble("TongKg"));

                }
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaNhap"));
                f.setGiaban(rs.getDouble("giaBan"));
                f.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));
                MaMua r = new MaMua();
                r.setId(rs.getInt("IdMaMua"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setGiamua(rs.getDouble("GiaMua"));
                r.setCa(f);
                r.setVitri(rs.getInt("ViTri"));
                al.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mua;
    }

  

    public Mua getMaNgay2(Date ngay) {
        Mua mua = null;
        ArrayList<MaMua> al = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Web.dbo.Mua WHERE Mua.Ngay= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            ResultSet rs = statement.executeQuery();
            mua = new Mua();
            mua.setId(-1);
            while (rs.next()) {

                DoiTac dt = new DoiTac();
                dt.setId(rs.getInt("IdDoiTac"));

                mua.setId(rs.getInt("IdMua"));
                mua.setNgay(rs.getDate("Ngay"));
                mua.setDoitac(dt);
                mua.setPhe(rs.getDouble("Phe"));
                mua.setMamua(al);
                mua.setChiphi(rs.getDouble("ChiPhi"));
                mua.setTrangthai(rs.getBoolean("TrangThai"));
                mua.setTongtien(rs.getDouble("TongTien"));
                mua.setTongkg(rs.getDouble("TongKg"));

            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mua;
    }

    public void insertMua(Mua a) {

        try {
            String sql = "INSERT INTO web.[dbo].[Mua]\n"
                    + "           ([Ngay]\n"
                    + "           ,[IdDoiTac]\n"
                    + "           ,[Phe]\n"
                    + "           ,[ChiPhi]\n"
                    + "           ,[TrangThai]\n"
                    + "           ,[TongTien]"
                    + "           ,[TongKg])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, a.getNgay());
            System.out.println("dal.MuaDAO.insert() " + a.getNgay());
            statement.setInt(2, a.getDoitac().getId());
            statement.setDouble(3, a.getPhe());
            statement.setDouble(4, a.getChiphi());
            statement.setBoolean(5, a.getTrangthai());
            statement.setDouble(6, a.getTongtien());
            statement.setDouble(7, a.getTongkg());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertNgay(Date ngay, boolean tt) {

        try {
            String sql = "INSERT INTO web.[dbo].[Mua]\n"
                    + "           ([Ngay]\n"
                    + "           ,[TrangThai])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, ngay);
            System.out.println("dal.MuaDAO.insert() " + ngay);
            statement.setBoolean(2, tt);

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertMua_ma(int idMua) {

        try {
            String sql = "INSERT INTO web.[dbo].[Ma_Mua]\n"
                    + "           ([IdMa]\n"
                    + "           ,[IdMua])\n"
                    + "     VALUES\n"
                    + "           ((SELECT MAX(IdMaMua) FROM Web.dbo.MaMua),?)";
//mua = new MuaDAO().getMaNgay2(date);
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setInt(1, id);
            statement.setInt(1, idMua);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EditMua(Mua a) {

        try {
            String sql = "UPDATE Web.[dbo].[Mua]  SET [Ngay] =? \n"
                    + " ,[IdDoiTac] = ?  ,[Phe] = ? ,[ChiPhi] =? \n"
                    + " ,[TrangThai] = ?  ,[TongTien] =? \n"
                    + " ,[TongKg] =? WHERE IdMua =? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, a.getNgay());
            System.out.println("dal.MuaDAO.insert() " + a.getTongtien());
            statement.setInt(2, a.getDoitac().getId());
            statement.setDouble(3, a.getPhe());
            statement.setDouble(4, a.getChiphi());
            statement.setBoolean(5, a.getTrangthai());
            statement.setDouble(6, a.getTongtien());
            statement.setDouble(7, a.getTongkg());
            statement.setInt(8, a.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---EditMua---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
