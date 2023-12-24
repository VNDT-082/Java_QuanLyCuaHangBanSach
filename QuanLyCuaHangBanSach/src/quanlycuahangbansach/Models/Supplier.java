/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.Models;

/**
 *
 * @author PC
 */
public class Supplier {
    private String maNhaCungCap;
    private String tenNhaCungCap;
    private String diaChi;
    private String SDT;
    private String Email;

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public Supplier()
    {
        this.maNhaCungCap="";
        this.tenNhaCungCap="";
        this.diaChi="";
        this.SDT="";
        this.Email="";
    }
    public Supplier(String maNhaCungCap, String tenNhaCungCap, String diaChi, String SDT, String Email) {
        this.maNhaCungCap = maNhaCungCap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.Email = Email;
    }
    @Override
    public String toString()
    {
        return this.getTenNhaCungCap();
    }
}
