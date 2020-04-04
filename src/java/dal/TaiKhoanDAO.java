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
import model.TaiKhoan;

/**
 *
 * @author Tamdv
 */
public class TaiKhoanDAO extends DBContext {

    public TaiKhoan getTaiKhoan(String user, String password) {
        TaiKhoan u = null;
        try {
            String sql = "SELECT [nguoiDung] ,[matKhau]\n"
                    + "  FROM web.[dbo].[taikhoan] WHERE nguoiDung = ? AND matKhau=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                u = new TaiKhoan();
                u.setNguoiDung(rs.getString("nguoiDung"));
                u.setMatKhau(rs.getString("matKhau"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public ArrayList<TaiKhoan> getTaikhoan() {
        ArrayList<TaiKhoan> taikhoans = new ArrayList<>();
        try {
            String sql = "SELECT [nguoiDung] ,[matKhau]\n"
                    + "  FROM web.[dbo].[taikhoan]";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                TaiKhoan u = new TaiKhoan();
                u.setNguoiDung(rs.getString("nguoiDung"));
                u.setMatKhau(rs.getString("matKhau"));
                taikhoans.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaiKhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taikhoans;
    }

}
