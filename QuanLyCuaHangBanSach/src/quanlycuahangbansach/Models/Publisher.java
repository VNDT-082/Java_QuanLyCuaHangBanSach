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
public class Publisher {
    private String maNhaXuatBan;
    private String tenNhaXuatBan;

    public String getMaNhaXuatBan() {
        return maNhaXuatBan;
    }

    public void setMaNhaXuatBan(String maNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }
    
    public Publisher()
    {
        this.maNhaXuatBan="NXB001";
        this.tenNhaXuatBan="Không xác định được nhà xuất bản";
    }

    public Publisher(String maNhaXuatBan, String tenNhaXuatBan) {
        this.maNhaXuatBan = maNhaXuatBan;
        this.tenNhaXuatBan = tenNhaXuatBan;
    }
    
    @Override
    public String toString()
    {
        return this.tenNhaXuatBan;
    }
}
