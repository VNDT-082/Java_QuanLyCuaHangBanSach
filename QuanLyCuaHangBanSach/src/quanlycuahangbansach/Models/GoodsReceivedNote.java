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
public class GoodsReceivedNote {
    private String maPhieuNhap;
    private String maSach;
    private int soLuong;

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
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

    public GoodsReceivedNote()
    {
        this.maPhieuNhap="";
        this.maSach="";
        this.soLuong=0;
    }
    public GoodsReceivedNote(String maPhieuNhap, String maSach, int soLuong) {
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.soLuong = soLuong;
    }
    
}
