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
public class MaBan {

    private Integer id;
    private String nguoimua;
    private Double khoiluong;
    private Ca ca;
    private Double giaban;

    public String getNguoimua() {
        return nguoimua;
    }

    public void setNguoimua(String nguoimua) {
        this.nguoimua = nguoimua;
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

    public Double getGiaban() {
        return giaban;
    }

    public void setGiaban(Double giaban) {
        this.giaban = giaban;
    }

    public Double thanhTien() {
        return this.giaban * this.khoiluong;
    }
}
