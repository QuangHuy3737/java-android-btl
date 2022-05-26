package com.example.th2.model;

public class User {
    private String name, password, diaChi, bienSo;
    private int id;



    public User(String name, String password, String diaChi, String bienSo) {
        this.name = name;
        this.password = password;
        this.diaChi = diaChi;
        this.bienSo = bienSo;
    }



    public User(String name, String password, String diaChi, String bienSo, int id) {
        this.name = name;
        this.password = password;
        this.diaChi = diaChi;
        this.bienSo = bienSo;
        this.id = id;
    }

    public User(String name, String bienSo,String diaChi) {
        this.name = name;
        this.bienSo = bienSo;
        this.diaChi = diaChi;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
