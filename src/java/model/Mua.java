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
public class Mua {

    private Integer id;
    private ArrayList<MaMua> mamua;
    private Date ngay;
    private DoiTac doitac;
    private Double phe;
    private Double chiphi;
    private Double tongtien;
     private Double tongkg;
    private boolean trangthai;

    public Mua() {
    }

    public Mua(Integer id, ArrayList<MaMua> mamua, Date ngay, DoiTac doitac, Double phe, Double chiphi, Double tongcong, boolean trangthai) {
        this.id = id;
        this.mamua = mamua;
        this.ngay = ngay;
        this.doitac = doitac;
        this.phe = phe;
        this.chiphi = chiphi;
        this.tongtien = tongcong;
        this.trangthai = trangthai;
    }

    public Double getTongkg() {
        return tongkg;
    }

    public void setTongkg(Double tongkg) {
        this.tongkg = tongkg;
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

    public ArrayList<MaMua> getMamua() {
        return mamua;
    }

    public void setMamua(ArrayList<MaMua> mamua) {
        this.mamua = mamua;
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

    public Double getTongtien() {
        return tongtien;
    }

    public void setTongtien(Double tongtien) {
        this.tongtien =tongtien;
    }

//    public Double thanhtoan() {
//        Double thanhtoan = 0.0;
//        thanhtoan = getTongtien()+chiphi;
//        return thanhtoan - phe;
//    }

}
