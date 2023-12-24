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
import quanlycuahangbansach.Models.Customer;

/**
 *
 * @author PC
 */
public class CustomerDAO {
    public static ArrayList<Customer> getAll()
    {
        DataHelper helper=new DataHelper();
        ArrayList<Customer> listCustomer=new ArrayList<Customer>();
        String sql="SELECT *FROM KhachHang";
        ResultSet resultSet= helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Customer customer=new Customer();
                customer.setTenKhachHang(resultSet.getString("TenKhachHang"));
                customer.setSoDienThoai(resultSet.getString("SDT_KH"));
                customer.setSoLuongSachDaMua(resultSet.getInt("SL_SachDaMua"));
                customer.setGiamGia(resultSet.getFloat("GiamGia"));
                listCustomer.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return listCustomer;
    }
    public static Customer getOneByID(String soDienThoai)
    {
        String sql="SELECT *FROM KhachHang WHERE SDT_KH='"+soDienThoai+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        Customer customer=new Customer();
        try {
            if(resultSet.next())
            {
                customer.setTenKhachHang(resultSet.getString("TenKhachHang"));
                customer.setSoDienThoai(resultSet.getString("SDT_KH"));
                customer.setSoLuongSachDaMua(resultSet.getInt("SL_SachDaMua"));
                customer.setGiamGia(resultSet.getFloat("GiamGia"));
                return customer;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return null;
    }
    public static int insertOneCustomer(Customer customer)
    {
        String sql="insert into KhachHang(SDT_KH, TenKhachHang) values ('"+customer.getSoDienThoai()
                +"',N'"+customer.getTenKhachHang()+"')";
        DataHelper helper =new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int updateOneCustomer(Customer customer)
    {
        String sql="UPDATE KhachHang SET TenKhachHang=N'"+customer.getTenKhachHang()
                +"' WHERE SDT_KH='"+customer.getSoDienThoai()+"';";
        DataHelper helper =new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
}
