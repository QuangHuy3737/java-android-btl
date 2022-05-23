package com.example.th2.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String sach,tomtat,tacgia,nxb,favourite;

    public Item() {
    }

    public Item(int id, String sach, String tomtat, String tacgia, String nxb, String favourite) {
        this.id = id;
        this.sach = sach;
        this.tomtat = tomtat;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.favourite = favourite;
    }

    public Item(String sach, String tomtat, String tacgia, String nxb, String favourite) {
        this.sach = sach;
        this.tomtat = tomtat;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSach() {
        return sach;
    }

    public void setSach(String sach) {
        this.sach = sach;
    }

    public String getTomtat() {
        return tomtat;
    }

    public void setTomtat(String tomtat) {
        this.tomtat = tomtat;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }
}
