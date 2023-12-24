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
public class SalesSlip {

    private String maPhieuBanHang;
    private String soDienThoaiKhachHang;
    private String maNhanVien;
    private float tongTien;
    private String ngayMua;
    private float giamGia;
    private float soTienPhiaTra;
    private String trangThai;

    public String getTrangThai() {
        String s ="";
        if(this.trangThai.equals("0"))
        {
            s="Chưa thanh toán";
        }
        else
        {
            s="Đã thanh toán";
        }
        return s;
    }

    public void setTrangThai(String trangThai) {
        if(trangThai.equals("Chưa thanh toán"))
        {
            this.trangThai="0";
        }
        else if(trangThai.equals("Đã thanh toán"))
        {
             this.trangThai="1";
        }
    }

    public String getMaPhieuBanHang() {
        return maPhieuBanHang;
    }

    public void setMaPhieuBanHang(String maPhieuBanHang) {
        this.maPhieuBanHang = maPhieuBanHang;
    }

    public String getSoDienThoaiKhachHang() {
        return soDienThoaiKhachHang;
    }

    public void setSoDienThoaiKhachHang(String soDienThoaiKhachHang) {
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public float getSoTienPhiaTra() {
        return soTienPhiaTra;
    }

    public void setSoTienPhiaTra(float soTienPhiaTra) {
        this.soTienPhiaTra = soTienPhiaTra;
    }

    public SalesSlip() {
        this.maNhanVien = "";
        this.maPhieuBanHang = "";
        this.soDienThoaiKhachHang = "";
        this.tongTien = 0;
        this.ngayMua = "xx/xx/xxxx";
        this.giamGia = 0;
        this.soTienPhiaTra = 0;
        this.trangThai = "Chưa thanh toán";
    }

    public SalesSlip(String maPhieuBanHang, String soDienThoaiKhachHang, String maNhanVien,
            float tongTien, float giamGia, float soTienPhiaTra, String trangThai) {
        this.maPhieuBanHang = maPhieuBanHang;
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
        this.maNhanVien = maNhanVien;
        this.tongTien = tongTien;
        this.giamGia = giamGia;
        this.soTienPhiaTra = soTienPhiaTra;
        this.trangThai = trangThai;
    }

    public SalesSlip(SalesSlip s) {
        this.maPhieuBanHang = s.getMaPhieuBanHang();
        this.soDienThoaiKhachHang = s.getSoDienThoaiKhachHang();
        this.maNhanVien = s.getMaNhanVien();
        this.tongTien = s.getTongTien();
        this.giamGia = s.getGiamGia();
        this.soTienPhiaTra = s.getSoTienPhiaTra();
        this.trangThai = s.getTrangThai();
    }

    @Override
    public String toString()
    {
        String s=this.maPhieuBanHang+"_"+this.maNhanVien+"_"+this.soDienThoaiKhachHang;
        return s;
    }
}
