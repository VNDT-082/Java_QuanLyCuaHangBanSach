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
import quanlycuahangbansach.Models.Publisher;

/**
 *
 * @author PC
 */
public class PublisherDAO {
    public static ArrayList<Publisher> getAll()
    {
        ArrayList<Publisher> ds=new ArrayList<Publisher>();
        DataHelper helper=new DataHelper();
        try {
            String sql="select*from NhaXuatBan";
            ResultSet resultSet=helper.executeQuery(sql);
            while(resultSet.next())
            {
                Publisher nxb=new Publisher();
                nxb.setMaNhaXuatBan(resultSet.getString(1));
                nxb.setTenNhaXuatBan(resultSet.getString(2));
                ds.add(nxb);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return ds;
    }
    public static Publisher getOneById(String value)
    {
        Publisher nhaXuatBan=new Publisher();
        DataHelper helper=new DataHelper();
        String sql="SELECT*FROM NhaXuatBan where MaNXB='"+value+"'";
        ResultSet resultSet=helper.executeQuery(sql);
        
        try {
            while (resultSet.next()) 
            {
                nhaXuatBan.setMaNhaXuatBan(resultSet.getString("MaNXB"));
                nhaXuatBan.setTenNhaXuatBan(resultSet.getString("TenNXB"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return nhaXuatBan;
    }
    public static int insert(String tenNhaXuatBan)
    {
        String sql="exec SP_InsertOnePublisher N'"+tenNhaXuatBan+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int upDate(String maNhaXuatBan, String tenNhaXuatBan)
    {
        String sql="update NhaXuatBan set TenNXB=N'"+tenNhaXuatBan+"' where MaNXB='"+maNhaXuatBan+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int deleteOneById(String maNhaXuatBan)
    {
        String sql="delete NhaXuatBan where MaNXB="+maNhaXuatBan;
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    
}
