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
import model.MaMua;
import model.Ca;

/**
 *
 * @author Tamdv
 */
public class MaMuaDAO extends DBContext {

    public void insert(MaMua a) {

        try {
            String sql = "INSERT INTO web.[dbo].[MaMua]\n"
                    + "           ([KhoiLuong]\n"
                    + "           ,[IdCa]\n"
                    + "           ,[GiaMua]\n"
                    + "           ,[ViTri])\n"
                    + "     VALUES  (?,?,?,?)"
                    + "UPDATE Web.[dbo].[Ca] SET [TongKhoiLuong] = ?  WHERE IdCa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, a.getKhoiluong());
            statement.setInt(2, a.getCa().getId());
            statement.setDouble(3, a.getGiamua());
            statement.setInt(4, a.getVitri());
            statement.setDouble(5, a.getCa().getTongkhoiluong());
            statement.setInt(6, a.getCa().getId());
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

    public boolean delete(int id) {
        try {
            String sql = " DELETE FROM Web.[dbo].[Ma_Mua]\n"
                    + "      WHERE IdMa =? DELETE FROM web.[dbo].[MaMua]\n"
                    + "      WHERE IdMaMua=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }
}
