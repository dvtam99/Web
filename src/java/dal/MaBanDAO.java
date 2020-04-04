/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ca;
import model.MaBan;
import model.MaMua;

/**
 *
 * @author Tamdv
 */
public class MaBanDAO extends DBContext {

    public void insert(MaBan a) {

        try {
            String sql = "INSERT INTO web.[dbo].[MaBan]\n"
                    + "           ([KhoiLuong]\n"
                    + "           ,[IdCa]\n"
                    + "           ,[GiaBan]\n"
                    + "           ,[NguoiMua])\n"
                    + "     VALUES   (?,?,?,?)"
                    + "UPDATE Web.[dbo].[Ca] SET [TongKhoiLuong] = ?  WHERE IdCa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, a.getKhoiluong());
            statement.setInt(2, a.getCa().getId());
            statement.setDouble(3, a.getGiaban());
            statement.setNString(4, a.getNguoimua());
            statement.setDouble(5, a.getCa().getTongkhoiluong());
            statement.setInt(6, a.getCa().getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(MaBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<MaBan> getMaByMua(int idMua) {
        ArrayList<MaBan> assizes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM Web.dbo.Ca INNER JOIN Web.dbo.MaBan ON MaBan.IdCa = Ca.IdCa INNER JOIN Web.dbo.Ma_Ban\n"
                    + "ON Ma_Ban.IdBan = MaBan.IdMaBan WHERE IdBan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaBan"));
                f.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));

                MaBan r = new MaBan();
                r.setId(rs.getInt("IdMaBan"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setCa(f);
                r.setGiaban(rs.getDouble("GiaBan"));
                r.setNguoimua(rs.getNString("NguoiBan"));
                assizes.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assizes;
    }

    public MaBan getAssizeByID(int id) {
        MaBan r = null;
        try {
            String sql = "SELECT * FROM Web.dbo.MaBan INNER JOIN Web.dbo.Ca ON Ca.IdCa = MaBan.IdCa WHERE IdMaBan=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaBan"));

                r = new MaBan();
                r.setId(rs.getInt("IdMaBan"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setGiaban(rs.getDouble("GiaBan"));
                r.setCa(f);
                r.setNguoimua(rs.getNString("NguoiMua"));

            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void Update(MaBan a) {

        try {
            String sql = "UPDATE web.[dbo].[MaBan]\n"
                    + "   SET [KhoiLuong] = ?\n"
                    + "      ,[IdCa] = ?\n"
                    + "      ,[GiaBan] = ?\n"
                    + "      ,[NguoiMua] = ?\n"
                    + " WHERE IdMaBan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, a.getKhoiluong());
            statement.setInt(2, a.getCa().getId());
            statement.setDouble(3, a.getGiaban());
            statement.setNString(4, a.getNguoimua());
            statement.setInt(5, a.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---update---" + ex.getMessage());
            Logger.getLogger(MaBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM web.dbo.Ma_Ban WHERE IdBan = ?  DELETE FROM web.[dbo].[MaBan]\n"
                    + "      WHERE IdMaBan=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaBanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
/*

    public void insert(MaMua a) {

        try {
            String sql = "INSERT INTO web.[dbo].[MaMua]\n"
                    + "           ([KhoiLuong]\n"
                    + "           ,[IdCa]\n"
                    + "           ,[GiaMua]\n"
                    + "           ,[ViTri])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, a.getKhoiluong());
            statement.setInt(2, a.getCa().getId());
            statement.setDouble(3, a.getGiamua());
            statement.setInt(4, a.getVitri());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---inset---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<MaMua> getAssize() {
        ArrayList<MaMua> assizes = new ArrayList<>();

        try {
            String sql = "SELECT [IdMaMua]\n"
                    + "      ,[KhoiLuong]\n"
                    + "      ,m.[IdCa]\n"
                    + "      ,[GiaMua]\n"
                    + "      ,[ViTri],TenCa,giaNhap\n"
                    + "  FROM web.[dbo].[MaMua] m JOIN web.dbo.Ca ON Ca.IdCa = m.IdCa";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaNhap"));

                MaMua r = new MaMua();
                r.setId(rs.getInt("IdMaMua"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setCa(f);
                r.setGiamua(rs.getDouble("GiaMua"));
                r.setVitri(rs.getInt("ViTri"));
                assizes.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assizes;
    }

    public ArrayList<MaMua> getMaByMua(int idMua) {
        ArrayList<MaMua> assizes = new ArrayList<>();

        try {
            String sql = "SELECT [IdMaMua] ,[KhoiLuong],m.[IdCa] ,[GiaMua],[ViTri],TenCa,giaNhap\n"
                    + "	  FROM   Web.dbo.Ca  JOIN Web.[dbo].[MaMua] m ON Ca.IdCa = m.IdCa JOIN \n"
                    + "	  Web.dbo.Ma_Mua ON Ma_Mua.IdMa = m.IdMaMua WHERE IdMua =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaNhap"));

                MaMua r = new MaMua();
                r.setId(rs.getInt("IdMaMua"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setCa(f);
                r.setGiamua(rs.getDouble("GiaMua"));
                r.setVitri(rs.getInt("ViTri"));
                assizes.add(r);
            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assizes;
    }

    public MaMua getAssizeByID(int id) {
        MaMua r = null;
        try {
            String sql = "	SELECT [IdMaMua]\n"
                    + "      ,[KhoiLuong]\n"
                    + "      ,m.[IdCa]\n"
                    + "      ,[GiaMua]\n"
                    + "      ,[ViTri],TenCa,giaNhap\n"
                    + "  FROM Web.[dbo].[MaMua] m JOIN Web.dbo.Ca ON Ca.IdCa = m.IdCa WHERE m.IdMaMua = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca f = new Ca();
                f.setId(rs.getInt("IdCa"));
                f.setTen(rs.getNString("TenCa"));
                f.setGiamua(rs.getDouble("giaNhap"));

                r = new MaMua();
                r.setId(rs.getInt("IdMaMua"));
                r.setKhoiluong(rs.getDouble("KhoiLuong"));
                r.setGiamua(rs.getDouble("GiaMua"));
                r.setCa(f);
                r.setVitri(rs.getInt("ViTri"));

            }

        } catch (SQLException ex) {
            System.out.println("---MA-" + ex.getMessage());
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void Update(MaMua a) {

        try {
            String sql = "UPDATE web.[dbo].[MaMua]\n"
                    + "   SET [KhoiLuong] = ?\n"
                    + "      ,[IdCa] = ?\n"
                    + "      ,[GiaMua] = ?\n"
                    + "      ,[ViTri] = ?\n"
                    + " WHERE IdMaMua = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, a.getKhoiluong());
            statement.setInt(2, a.getCa().getId());
            statement.setDouble(3, a.getGiamua());
            statement.setInt(4, a.getVitri());
            statement.setInt(5, a.getId());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("---update---" + ex.getMessage());
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM web.[dbo].[MaMua]\n"
                    + "      WHERE IdMaMua=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 */
