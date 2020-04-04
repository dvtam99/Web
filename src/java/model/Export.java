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
public class Export {

    private Integer id;
    private ArrayList<MaMua> assize;
    private Date date;
    private DoiTac supplier;
    private Double discount;
    private Double fee;
    private Double ortherFee;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<MaMua> getAssize() {
        return assize;
    }

    public void setAssize(ArrayList<MaMua> assize) {
        this.assize = assize;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DoiTac getSupplier() {
        return supplier;
    }

    public void setSupplier(DoiTac supplier) {
        this.supplier = supplier;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getOrtherFee() {
        return ortherFee;
    }

    public void setOrtherFee(Double ortherFee) {
        this.ortherFee = ortherFee;
    }

    public Double subtotalBuy() {
        Double total = null;
        for (MaMua s : this.assize) {
            total += (s.getKhoiluong() * s.getCa().getGiamua());
        }
        return total;
    }
    
     public Double total() {
               
        return subtotalBuy()*(100-this.discount)+this.fee+this.ortherFee ;
    }

}
