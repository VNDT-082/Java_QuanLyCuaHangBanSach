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
import quanlycuahangbansach.Models.Supplier;

/**
 *
 * @author PC
 */
public class SupplierDAO {
    public static ArrayList<Supplier> getAll()
    {
        String sql="select *from NhaCungCap";
        ArrayList<Supplier> dsNhaCungCap=new ArrayList<Supplier>();
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Supplier s=new Supplier();
                s.setMaNhaCungCap(resultSet.getString("MaNCC"));
                s.setTenNhaCungCap(resultSet.getString("TenNCC"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSDT(resultSet.getString("SDT"));
                s.setEmail(resultSet.getString("Email"));
                dsNhaCungCap.add(s);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNhaCungCap;
    }
     public static Supplier getOneByID(String maNhaCungCap)
    {
        String sql="select *from NhaCungCap where MaNCC='"+maNhaCungCap+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Supplier s=new Supplier();
                s.setMaNhaCungCap(resultSet.getString("MaNCC"));
                s.setTenNhaCungCap(resultSet.getString("TenNCC"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSDT(resultSet.getString("SDT"));
                s.setEmail(resultSet.getString("Email"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupplierDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
