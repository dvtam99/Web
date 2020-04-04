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
import model.Ban;
import model.Ca;

/**
 *
 * @author Tamdv
 */
public class CaDAO extends DBContext {

    public ArrayList<Ca> getCa() {
        ArrayList<Ca> ca = new ArrayList<>();

        try {
            String sql = "SELECT [IdCa]\n"
                    + "      ,[TenCa]\n"
                    + "      ,[giaNhap]\n"
                    + "      ,[anh]\n"
                    + "      ,[giaBan],"
                    + "TongKhoiLuong\n"
                    + "  FROM web.[dbo].[Ca]";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Ca s = new Ca();
                s.setId(rs.getInt("idCa"));
                s.setTen(rs.getNString("TenCa"));
                s.setGiaban(rs.getDouble("giaBan"));
                s.setGiamua(rs.getDouble("giaNhap"));
                s.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));
                s.setImg(rs.getNString("anh"));
                ca.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("-------- getFish:" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }

    public ArrayList<Ca> getCaPagging(int pageindex, int pagesize) {
        ArrayList<Ca> ca = new ArrayList<>();

        try {
            String sql = "SELECT  [IdCa]\n"
                    + "      ,[TenCa]\n"
                    + "      ,[giaNhap]\n"
                    + "      ,[anh]\n"
                    + "      ,[TongKhoiLuong]\n"
                    + "      ,[giaBan] FROM\n"
                    + "  (SELECT *,ROW_NUMBER() OVER (ORDER BY IdCa ASC) as row_num FROM  web.dbo.Ca)  Ca WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pageindex);
            ps.setInt(2, pagesize);
            ps.setInt(3, pageindex);
            ps.setInt(4, pagesize);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ca s = new Ca();
                s.setId(rs.getInt("idCa"));
                s.setTen(rs.getNString("TenCa"));
                s.setGiaban(rs.getDouble("giaBan"));
                s.setGiamua(rs.getDouble("giaNhap"));
                s.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));
                s.setImg(rs.getNString("anh"));
                ca.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("-------- getFish:" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }

    public Ca getGiaca(int id) {
        Ca ca = null;

        try {
            String sql = "SELECT [IdCa]\n"
                    + "      ,[TenCa]\n"
                    + "      ,[giaNhap]\n"
                    + "      ,[anh]\n"
                    + "      ,[TongKhoiLuong]\n"
                    + "      ,[giaBan]\n"
                    + "  FROM web.[dbo].[Ca] where idCa=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ca = new Ca();
                ca.setId(rs.getInt("idCa"));
                ca.setTen(rs.getNString("TenCa"));
                ca.setGiaban(rs.getDouble("giaBan"));
                ca.setGiamua(rs.getDouble("giaNhap"));
                ca.setTongkhoiluong(rs.getDouble("TongKhoiLuong"));
                ca.setImg(rs.getNString("anh"));
            }
        } catch (SQLException ex) {
            System.out.println("-------- getFish:" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }

    public void insert(Ca c) {

        try {
            String sql = "INSERT INTO Web.[dbo].[Ca] ([TenCa] ,[giaNhap],[giaBan], TongKhoiLuong)"
                    + "     VALUES (?,?,?, 0)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setNString(1, c.getTen());
            statement.setDouble(2, c.getGiamua());
            statement.setDouble(3, c.getGiaban());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("-------- getFish:" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int id) {

        try {
            String sql = "DELETE FROM Web.[dbo].[Ca] WHERE IdCa=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaMuaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void EditCa(Ca a) {

        try {
            String sql = "UPDATE Web.[dbo].[Ca]\n"
                    + "   SET [TenCa] = ?\n"
                    + "      ,[giaNhap] = ?\n"
                    + "      ,[giaBan] = ?"
                    + "      , TongKhoiLuong =?\n"
                    + " WHERE IdCa = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, a.getTen());
            statement.setDouble(2, a.getGiamua());
            statement.setDouble(3, a.getGiaban());
            statement.setDouble(4, a.getTongkhoiluong());
            statement.setInt(5, a.getId());
            statement.executeUpdate();
            System.out.println("ok edit mua");
        } catch (SQLException ex) {
            System.out.println("---EditMua---" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count() {
        String sql = "SELECT COUNT(*) as rownum FROM Web.[dbo].[Ca]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

}
