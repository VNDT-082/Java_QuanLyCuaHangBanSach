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
public class SalePerson {
    private String maNhanVien;
    private String tenNhanVien;
    public  String gioiTinh;
    private String ngaySinh;
    private String ngayVaoLam;
    private String diaChi;
    private String maChucVu;
    private String sdt;
    private String tenDangNhap;
    private String hinhAnh;
    private String matKhau;
    public  String loaiTaiKhoan;
    private String canCuocCongDan;
    public String trangThai;

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getGioiTinh() {
        String gt="";
        if(this.gioiTinh.equals("0"))
        {
            gt="Nữ";
        }
        else if(this.gioiTinh.equals("1"))
        {
            gt = "Nam";
        }
        return gt;
    }

    public void setGioiTinh(String gioiTinh) {
        if(gioiTinh.equals("Nam")||gioiTinh.equals("1"))
        {
            this.gioiTinh= "1";
        }
        else if(gioiTinh.equals("Nữ")||gioiTinh.equals("0"))
        {
            this.gioiTinh="0";
        }
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(String ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        String s="";
        if(this.loaiTaiKhoan.equals("0"))
            s ="Tài khoản nhân viên";
        else if(this.loaiTaiKhoan.equals("1"))
            s ="Tài khoản quản lý";
        return s;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        if(loaiTaiKhoan.equals("Tài khoản nhân viên") || loaiTaiKhoan.equals("0"))
            this.loaiTaiKhoan="0";
        else if(loaiTaiKhoan.equals("Tài khoản quản lý") || loaiTaiKhoan.equals("1"))
            this.loaiTaiKhoan="1";
    }

    public String getCanCuocCongDan() {
        return canCuocCongDan;
    }

    public void setCanCuocCongDan(String canCuocCongDan) {
        this.canCuocCongDan = canCuocCongDan;
    }

    public String getTrangThai() {
        if(this.trangThai.equals("0"))
            this.trangThai="Đã khóa";
        else if(this.trangThai.equals("1"))
            this.trangThai="Đang mở";
        return this.trangThai;
    }

    public void setTrangThai(String trangThai) {
        if(this.trangThai.equals("Đã khóa"))
            this.trangThai="0";
        else if(this.trangThai.equals("Đang mở"))
            this.trangThai="1";
    }

    public SalePerson()
    {
        this.maNhanVien     = "";
        this.tenNhanVien    = "";
        this.gioiTinh       = "0";
        this.ngaySinh       = "";
        this.ngayVaoLam     = "";
        this.diaChi         = "";
        this.maChucVu       = "NVBH";
        this.sdt            = "";
        this.tenDangNhap    = "";
        this.hinhAnh        = "h1.png";
        this.matKhau        = "";
        this.loaiTaiKhoan   = "0";
        this.canCuocCongDan = "";
        this.trangThai      ="1";
    
    }
    public SalePerson(String maNhanVien, String tenNhanVien, String gioiTinh, String ngaySinh, String ngayVaoLam,
            String diaChi, String maChucVu, String sdt, String tenDangNhap, String hinhAnh, String matKhau,
            String loaiTaiKhoan, String canCuocConDan, String trangThai) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.ngayVaoLam = ngayVaoLam;
        this.diaChi = diaChi;
        this.maChucVu = maChucVu;
        this.sdt = sdt;
        this.tenDangNhap = tenDangNhap;
        this.hinhAnh = hinhAnh;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.canCuocCongDan=canCuocConDan;
        this.trangThai=trangThai;
    }  
    @Override
    public String toString()
    {
        String s=this.getMaNhanVien()+" - "+this.getTenNhanVien();
        return s;
    }
}
