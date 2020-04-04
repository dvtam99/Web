/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Tamdv
 */
public class BaoCao {
    private int Id;
    private Mua mua;
    private Ban ban;
    private double chenhlech;
    private double khoiluong;
    private double tonkho;
    private Date ngay;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Mua getMua() {
        return mua;
    }

    public void setMua(Mua mua) {
        this.mua = mua;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public double getChenhlech() {
        return chenhlech;
    }

    public void setChenhlech(double chenhlech) {
        this.chenhlech = chenhlech;
    }

    public double getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(double khoiluong) {
        this.khoiluong = khoiluong;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public double getTonkho() {
        return tonkho;
    }

    public void setTonkho(double tonkho) {
        this.tonkho = tonkho;
    }
    
   
}
