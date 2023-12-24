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
import quanlycuahangbansach.Models.GoodsReceivedNote;

/**
 *
 * @author PC
 */
public class GoodsReceivedNoteDAO {
    public static ArrayList<GoodsReceivedNote> getAll(String maPhieuNhap)
    {
        ArrayList<GoodsReceivedNote> ds=new ArrayList<GoodsReceivedNote>();
        DataHelper helper=new DataHelper();
        String sql="select*from ChiTietNhapSach where MaPhieuNhap='"+maPhieuNhap+"';";
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                GoodsReceivedNote g=new GoodsReceivedNote();
                g.setMaPhieuNhap(resultSet.getString("MaPhieuNhap"));
                g.setMaSach(resultSet.getString("MaSach"));
                g.setSoLuong(resultSet.getInt("SoLuong"));
                ds.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GoodsReceivedNoteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public static int insertOneGoodsReceivedNode(GoodsReceivedNote g)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="insert into ChiTietNhapSach(MaPhieuNhap, MaSach, SoLuong) values "+
                "('"+g.getMaPhieuNhap()+"','"+g.getMaSach()+"',"+g.getSoLuong()+")";
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int updateOneGoodsReceivedNode(GoodsReceivedNote g)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="update ChiTietNhapSach set SoLuong="+g.getSoLuong()
                +" where MaPhieuNhap='"+g.getMaPhieuNhap()+"' and MaSach='"+g.getMaSach()+"';";
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int deleteOneGoodsReceivedNode(GoodsReceivedNote g)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="delete ChiTietNhapSach where MaPhieuNhap='"+g.getMaPhieuNhap()+"'and MaSach='"+g.getMaSach()+"';";
        kq=helper.executeUpdate(sql);
        return kq;
    }
    public static int deleteListGoodsReceivedNoteByIDGoodsDeliveryNote(String maPhieuNhap)
    {
        int kq=0;
        DataHelper helper=new DataHelper();
        String sql="delete ChiTietNhapSach where MaPhieuNhap='"+maPhieuNhap+"';";
        kq=helper.executeUpdate(sql);
        return kq;
    }
}
