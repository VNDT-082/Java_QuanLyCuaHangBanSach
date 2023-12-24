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
public class Product {
    private String maSach;
    private String maNhaXuatBan;
    private String maTheLoai;
    private String serialSach;
    private String tenSach;
    private int soLuong;
    private int luotMua;
    private String hinhAnh;
    private float giaBan;
    private String tacGia;

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getSerialSach() {
        return serialSach;
    }

    public void setSerialSach(String serialSach) {
        this.serialSach = serialSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getLuotMua() {
        return luotMua;
    }

    public void setLuotMua(int luotMua) {
        this.luotMua = luotMua;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    

    public Product() {
        this.maSach="";
        this.maNhaXuatBan="";
        this.maTheLoai="";
        this.serialSach="";
        this.tenSach="";
        this.soLuong=0;
        this.luotMua=0;
        this.hinhAnh="GiaThichNguPhapTiengAnh.png";
        this.giaBan=0;
        this.tacGia="Tác giả không xác định";
    }
    public Product(String maSach, String maNhaXuatBan, String maTheLoai, String serialSach, String tenSach,
            int soLuong, int luotMua, String hinhAnh, float giaBan, String tacGia) 
    {
        this.maSach = maSach;
        this.maNhaXuatBan = maNhaXuatBan;
        this.maTheLoai = maTheLoai;
        this.serialSach = serialSach;
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.luotMua = luotMua;
        this.hinhAnh = hinhAnh;
        this.giaBan=giaBan;
        this.tacGia=tacGia;
    }
    public Product(Product product)
    {
        this.maSach = product.getMaSach();
        this.maNhaXuatBan = product.getMaNhaXuatBan();
        this.maTheLoai = product.getMaTheLoai();
        this.serialSach = product.getSerialSach();
        this.tenSach = product.getTenSach();
        this.soLuong = product.getSoLuong();
        this.luotMua = product.getLuotMua();
        this.hinhAnh = product.getHinhAnh();
        this.giaBan=product.getGiaBan();
        this.tacGia=product.getTacGia();
    }
    @Override
    public String toString()
    {
        String s=this.getMaSach()+" - "+this.getTenSach();
        return s;
    }
}
