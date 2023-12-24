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
public class InvoiceDetails {
    private String maPhieuBan;
    private String maSach;
    private int soLuong;
    private float tongTien;

    public String getMaPhieuBan() {
        return maPhieuBan;
    }

    public void setMaPhieuBan(String maPhieuBan) {
        this.maPhieuBan = maPhieuBan;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public InvoiceDetails()
    {
        this.maSach="";
        this.maPhieuBan="";
        this.soLuong=0;
        this.tongTien=0;
    }
    public InvoiceDetails(String maPhieuBan, String maSach, int soLuong, float tongTien) {
        this.maPhieuBan = maPhieuBan;
        this.maSach = maSach;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }
    public InvoiceDetails(InvoiceDetails invoice)
    {
        this.maPhieuBan=invoice.getMaPhieuBan();
        this.maSach=invoice.getMaSach();
        this.soLuong=invoice.getSoLuong();
        this.tongTien=invoice.getTongTien();
    }
    
    
}
