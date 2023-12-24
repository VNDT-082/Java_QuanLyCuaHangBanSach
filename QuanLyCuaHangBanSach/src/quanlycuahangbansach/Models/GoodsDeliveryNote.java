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
public class GoodsDeliveryNote {
    private String maPhieuNhap;
    private String maNCC;
    private String maNhanVien;
    private String ngayNhap;

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
     public GoodsDeliveryNote()
     {
         this.maNCC="";
         this.maNhanVien="";
         this.maPhieuNhap="";
         this.ngayNhap="";
     }
     
    public GoodsDeliveryNote(String maPhieuNhap, String maNCC, String maNhanVien, String ngayNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.maNCC = maNCC;
        this.maNhanVien = maNhanVien;
        this.ngayNhap = ngayNhap;
    }
    @Override
    public String toString()
    {
        String s=this.getMaPhieuNhap()+"-"+ this.getMaNCC()+"-"+this.getMaNhanVien()+"-"+this.getNgayNhap();
        return s;
    }
}
