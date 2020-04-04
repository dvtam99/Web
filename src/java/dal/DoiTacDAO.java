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
import model.DoiTac;

/**
 *
 * @author Tamdv
 */
public class DoiTacDAO extends DBContext {

    public ArrayList<DoiTac> getNguoiBan(String loai) {
        ArrayList<DoiTac> suppliers = new ArrayList<>();

        try {
            String sql = "SELECT [IdDoiTac]\n"
                    + "      ,[TenDt]\n"
                    + "      ,[DiaChi]\n"
                    + "      ,[Sdt]\n"
                    + "      ,[Loai]\n"
                    + "  FROM web.[dbo].[DoiTac] WHERE Loai =? ";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loai);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                DoiTac s = new DoiTac();
                s.setId(rs.getInt("iddoitac"));
                s.setTen(rs.getNString("TenDt"));
                s.setSdt(rs.getString("sdt"));
                s.setDiachi(rs.getNString("diachi"));
                s.setLoai(rs.getString("loai"));
                suppliers.add(s);
            }

        } catch (SQLException ex) {
            System.out.println("-------- getSupplier:" + ex.getMessage());
            Logger.getLogger(DoiTacDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return suppliers;
    }

    public void insert(DoiTac c) {

        try {
            String sql = "INSERT INTO web.[dbo].[DoiTac]\n"
                    + "           ([TenDt],[DiaChi] ,[Sdt] ,[Loai])\n"
                    + "     VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setNString(1, c.getTen());
            statement.setNString(2, c.getDiachi());
            statement.setString(3, c.getSdt());
            statement.setString(4, c.getLoai());
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("-------- getFish:" + ex.getMessage());
            Logger.getLogger(CaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
