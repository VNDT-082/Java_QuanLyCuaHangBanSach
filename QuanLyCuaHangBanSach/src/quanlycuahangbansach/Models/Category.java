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
public class Category {
    private String maTheLoai;
    private String tenTheLoai;

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public Category()
    {
        this.maTheLoai="TL001";
        this.tenTheLoai="Không xác định thể loại";
    }
    public Category(String maTheLoai, String tenTheLoai) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
    }
    public Category(Category category) {
        this.maTheLoai = category.maTheLoai;
        this.tenTheLoai = category.tenTheLoai;
    }
    
    
    @Override
    public String toString()
    {
        return this.getTenTheLoai();
    }
}
