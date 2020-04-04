/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Tamdv
 */
public class Ban {

    private Integer id;
    private ArrayList<MaBan> maban;
    private Date ngay;
    private DoiTac doitac;
    private Double phe;
    private Double chiphi;
    private Double tongkg;
    private Double tongtien;
    private boolean trangthai;

    public ArrayList<MaBan> getMaban() {
        return maban;
    }

    public void setMaban(ArrayList<MaBan> maban) {
        this.maban = maban;
    }

    public Double getTongkg() {
        return tongkg;
    }

    public void setTongkg(Double tongkg) {
        this.tongkg = tongkg;
    }

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien = tongtien;
    }

    public Double getChiphi() {
        return chiphi;
    }

    public void setChiphi(Double chiphi) {
        this.chiphi = chiphi;
    }

    public boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public DoiTac getDoitac() {
        return doitac;
    }

    public void setDoitac(DoiTac doitac) {
        this.doitac = doitac;
    }

    public Double getPhe() {
        return phe;
    }

    public void setPhe(Double phe) {
        this.phe = phe;
    }
 
}
