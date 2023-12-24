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
public class Position {
    private String maChucVu;
    private String tenChucVu;

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }
    
    public Position()
    {
        this.maChucVu="";
        this.tenChucVu="";
    }
    public Position(String maChucVu, String tenChucVu) {
        this.maChucVu = maChucVu;
        this.tenChucVu = tenChucVu;
    }
    @Override
    public String toString()
    {
        return this.getTenChucVu();
    }
}
