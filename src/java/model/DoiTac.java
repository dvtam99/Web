/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tamdv
 */
public class DoiTac {
    private int id;
    private String ten;
    private String sdt;
    private String diachi;
    private String loai;

    public DoiTac(int id, String ten, String sdt, String diachi, String loai) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.diachi = diachi;
        this.loai = loai;
    }

    public DoiTac() {
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    
}
