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
public class Ca {

    private Integer id;
    private String ten;
    private double giamua;
    private double giaban;
    private String img;
    private double tongkhoiluong;

    public double getTongkhoiluong() {
        return tongkhoiluong;
    }

    public void setTongkhoiluong(double tongkhoiluong) {
        this.tongkhoiluong = tongkhoiluong;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiamua() {
        return giamua;
    }

    public void setGiamua(double giamua) {
        this.giamua = giamua;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
