/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlycuahangbansach.DLL.DataHelper;
import quanlycuahangbansach.Models.InvoiceDetails;

/**
 *
 * @author PC
 */
public class InvoiceDetailsDAO {
    public static ArrayList<InvoiceDetails> getAll()
    {
        ArrayList<InvoiceDetails> danhSachChiTietPhieuBanHang=new ArrayList<InvoiceDetails>();
        DataHelper helper=new DataHelper();
        String sql="select *from ChiTietPhieuBanHang";
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                InvoiceDetails invoice=new InvoiceDetails();
                invoice.setMaPhieuBan(resultSet.getString("MaPhieuBan"));
                invoice.setMaSach(resultSet.getString("MaSach"));
                invoice.setSoLuong(resultSet.getInt("SoLuong"));
                invoice.setTongTien(resultSet.getFloat("SoTien"));
                danhSachChiTietPhieuBanHang.add(invoice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return danhSachChiTietPhieuBanHang;
    }
    public static ArrayList<InvoiceDetails> getListByIDSalesSlip(String maPhieuBan)
    {
        ArrayList<InvoiceDetails> danhSachChiTietPhieuBanHang=new ArrayList<InvoiceDetails>();
        DataHelper helper=new DataHelper();
        String sql="SELECT *FROM ChiTietPhieuBanHang where MaPhieuBan='"+maPhieuBan+"'";
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                InvoiceDetails invoice=new InvoiceDetails();
                invoice.setMaPhieuBan(resultSet.getString("MaPhieuBan"));
                invoice.setMaSach(resultSet.getString("MaSach"));
                invoice.setSoLuong(resultSet.getInt("SoLuong"));
                invoice.setTongTien(resultSet.getFloat("SoTien"));
                danhSachChiTietPhieuBanHang.add(invoice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return danhSachChiTietPhieuBanHang;
    }
    public static int insertOneInvoiceDetails(InvoiceDetails invoice)
    {
        String sql="insert into ChiTietPhieuBanHang(MaSach, MaPhieuBan, SoLuong) values('"+invoice.getMaSach()
                +"','"+invoice.getMaPhieuBan()+"',"+invoice.getSoLuong()+");";
        DataHelper helper=new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int updateOneInvoiceDetails(InvoiceDetails invoice)
    {
        String sql="update ChiTietPhieuBanHang set SoLuong="+invoice.getSoLuong()
                +" where MaPhieuBan='"+invoice.getMaPhieuBan()+"' and MaSach='"+invoice.getMaSach()+"';";
        DataHelper helper=new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int deleteOneInvoiceDetails(InvoiceDetails invoice)
    {
        String sql="delete ChiTietPhieuBanHang where MaPhieuBan='"+invoice.getMaPhieuBan()+"' and MaSach='"+invoice.getMaSach()+"';";
        DataHelper helper=new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        return kq;
    }

}
