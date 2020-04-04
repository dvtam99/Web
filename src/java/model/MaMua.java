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
public class MaMua {

    private Integer id;
    private int vitri;
    private Double khoiluong;
    private Ca ca;
    private Double giamua;

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getKhoiluong() {
        return khoiluong;
    }

    public void setKhoiluong(Double khoiluong) {
        this.khoiluong = khoiluong;
    }

    public Ca getCa() {
        return ca;
    }

    public void setCa(Ca ca) {
        this.ca = ca;
    }

    public double getMoney() {
        return this.khoiluong * this.ca.getGiamua();
    }

    public Double getGiamua() {
        return giamua;
    }

    public void setGiamua(Double giamua) {
        this.giamua = giamua;
    }

    public Double thanhTien() {
        return this.giamua * this.khoiluong;
    }
}
