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
public class Customer {
    private String soDienThoai;
    private String tenKhachHang;
    private int soLuongSachDaMua;
    private float giamGia;

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public int getSoLuongSachDaMua() {
        return soLuongSachDaMua;
    }

    public void setSoLuongSachDaMua(int soLuongSachDaMua) {
        this.soLuongSachDaMua = soLuongSachDaMua;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }
    public Customer()
    {
        this.soDienThoai="";
        this.soLuongSachDaMua=0;
        this.giamGia=0;
        this.tenKhachHang="";
    }

    public Customer(String soDienThoai, String tenKhachHang, int soLuongSachDaMua, float giamGia) {
        this.soDienThoai = soDienThoai;
        this.tenKhachHang = tenKhachHang;
        this.soLuongSachDaMua = soLuongSachDaMua;
        this.giamGia = giamGia;
    }
    
}
