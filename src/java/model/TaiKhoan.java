/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author tamdv
 */
public class TaiKhoan {
    private String nguoiDung;
    private String matKhau;
    

    public TaiKhoan(String username, String password) {
        this.nguoiDung = username;
        this.matKhau = password;
        
    }



    public TaiKhoan() {
       
    }

    public String getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

   


    
    
    
}
