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
import quanlycuahangbansach.Models.GoodsDeliveryNote;

/**
 *
 * @author PC
 */
public class GoodsDeliveryNoteDAO {
    public static ArrayList<GoodsDeliveryNote> getAll()
    {
        ArrayList<GoodsDeliveryNote> ds=new ArrayList<GoodsDeliveryNote>();
        String sql="select*from PhieuNhapHang;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                ds.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public static ArrayList<GoodsDeliveryNote> getListByDateValue(String ngayNhap)
    {
        ArrayList<GoodsDeliveryNote> ds=new ArrayList<GoodsDeliveryNote>();
        String sql="set dateformat dmy select*from PhieuNhapHang where NgayNhap>='"+ngayNhap+"' and NgayNhap<=GETDATE();";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                ds.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public static ArrayList<GoodsDeliveryNote> getListByIDSupplierValue(String maNhaCungCung)
    {
        ArrayList<GoodsDeliveryNote> ds=new ArrayList<GoodsDeliveryNote>();
        String sql="select*from PhieuNhapHang where MaNCC='"+maNhaCungCung+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                ds.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public static ArrayList<GoodsDeliveryNote> getListByIDSalePersonValue(String maNhanVien)
    {
        ArrayList<GoodsDeliveryNote> ds=new ArrayList<GoodsDeliveryNote>();
        String sql="select*from PhieuNhapHang where MaNhanVien='"+maNhanVien+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                ds.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    
    public static GoodsDeliveryNote getListByID(String maPhieuNhap)
    {
        String sql="select*from PhieuNhapHang where MaPhieuNhap='"+maPhieuNhap+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                return g;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static GoodsDeliveryNote getLastGoodsDeliveryNoteByID(String maNhanVien)
    {
        String sql="select*from PhieuNhapHang where MaPhieuNhap=(select MAX(MaPhieuNhap) "+
                "from PhieuNhapHang) and MaNhanVien='"+maNhanVien+"'";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsDeliveryNote g=new GoodsDeliveryNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaNCC(resultSet.getString("MaNCC"));
                g.setMaNhanVien(resultSet.getString("MaNhanVien"));
                g.setNgayNhap(resultSet.getString("NgayNhap"));
                return g;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsDeliveryNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static int insertOneGoodsDeliveryNote(GoodsDeliveryNote g)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="exec SP_InsertOneGoodsDeliveryNote '"+g.getMaNCC()+"', '"+g.getMaNhanVien()+"';";
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int deleteOneGoodsDeliveryNote(GoodsDeliveryNote g)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="delete PhieuNhapHang where MaPhieuNhap='"+g.getMaPhieuNhap()+"';";
        kq=helper.executeUpdate(sql);
        return kq;
    }
}
