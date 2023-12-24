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
import quanlycuahangbansach.Models.Product;

/**
 *
 * @author PC
 */
public class ProductDAO {
    public static ArrayList<Product> getAll()
    {
        ArrayList<Product> ds=new ArrayList<Product>();
        DataHelper helper=new DataHelper();
        try {
            String sql="select *from Sach";
            ResultSet resultSet=helper.executeQuery(sql);
            while (resultSet.next()) 
            {
                Product sp=new Product();
                sp.setMaSach(resultSet.getString(1));
                sp.setMaNhaXuatBan(resultSet.getString(2));
                sp.setMaTheLoai(resultSet.getString(3));
                sp.setSerialSach(resultSet.getString(4));
                sp.setTenSach(resultSet.getString(5));
                sp.setSoLuong(Integer.valueOf(resultSet.getString(6)));
                sp.setLuotMua(Integer.valueOf(resultSet.getString(7)));
                sp.setHinhAnh(resultSet.getString(8));
                sp.setGiaBan(Float.valueOf(resultSet.getString(9)));
                sp.setTacGia(resultSet.getString(10));
                ds.add(sp);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return ds;
    }
    public static ArrayList<Product> getAllActive()
    {
        ArrayList<Product> ds=new ArrayList<Product>();
        DataHelper helper=new DataHelper();
        try {
            String sql="select *from Sach where  SoLuong>-1";
            ResultSet resultSet=helper.executeQuery(sql);
            while (resultSet.next()) 
            {
                Product sp=new Product();
                sp.setMaSach(resultSet.getString(1));
                sp.setMaNhaXuatBan(resultSet.getString(2));
                sp.setMaTheLoai(resultSet.getString(3));
                sp.setSerialSach(resultSet.getString(4));
                sp.setTenSach(resultSet.getString(5));
                sp.setSoLuong(Integer.valueOf(resultSet.getString(6)));
                sp.setLuotMua(Integer.valueOf(resultSet.getString(7)));
                sp.setHinhAnh(resultSet.getString(8));
                sp.setGiaBan(Float.valueOf(resultSet.getString(9)));
                sp.setTacGia(resultSet.getString(10));
                ds.add(sp);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return ds;
    }
    public static ArrayList<Product> getListByIDCategoryValue(String maTheLoai)
    {
        ArrayList<Product> ds=new ArrayList<Product>();
        DataHelper helper=new DataHelper();
        try {
            String sql="Select * from Sach where MaTheLoai='"+maTheLoai+"' and  SoLuong>-1;";
            ResultSet resultSet=helper.executeQuery(sql);
            while (resultSet.next()) 
            {
                Product sp=new Product();
                sp.setMaSach(resultSet.getString(1));
                sp.setMaNhaXuatBan(resultSet.getString(2));
                sp.setMaTheLoai(resultSet.getString(3));
                sp.setSerialSach(resultSet.getString(4));
                sp.setTenSach(resultSet.getString(5));
                sp.setSoLuong(Integer.valueOf(resultSet.getString(6)));
                sp.setLuotMua(Integer.valueOf(resultSet.getString(7)));
                sp.setHinhAnh(resultSet.getString(8));
                sp.setGiaBan(Float.valueOf(resultSet.getString(9)));
                sp.setTacGia(resultSet.getString(10));
                ds.add(sp);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return ds;
    }
    public static ArrayList<Product> getListProductByValues(String value)
    {
        ArrayList<Product> ds = new ArrayList<Product>();
        DataHelper helper=new DataHelper();
        String sql="select *from Sach where SerialSach='"+value+"' or TenSach like N'%"+value+"%' and SoLuong>0";
        ResultSet resultSet = helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Product sp=new Product();
                sp.setMaSach(resultSet.getString(1));
                sp.setMaNhaXuatBan(resultSet.getString(2));
                sp.setMaTheLoai(resultSet.getString(3));
                sp.setSerialSach(resultSet.getString(4));
                sp.setTenSach(resultSet.getString(5));
                sp.setSoLuong(Integer.valueOf(resultSet.getString(6)));
                sp.setLuotMua(Integer.valueOf(resultSet.getString(7)));
                sp.setHinhAnh(resultSet.getString(8));
                sp.setGiaBan(Float.valueOf(resultSet.getString(9)));
                sp.setTacGia(resultSet.getString(10));
                ds.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ds;
    }
    public static Product getOneById(String value)
    {
        DataHelper helper=new DataHelper();
        String sql="select*from Sach where MaSach='"+value+"';";
        ResultSet resultSet = helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Product sp=new Product();
                sp.setMaSach(resultSet.getString(1));
                sp.setMaNhaXuatBan(resultSet.getString(2));
                sp.setMaTheLoai(resultSet.getString(3));
                sp.setSerialSach(resultSet.getString(4));
                sp.setTenSach(resultSet.getString(5));
                sp.setSoLuong(Integer.valueOf(resultSet.getString(6)));
                sp.setLuotMua(Integer.valueOf(resultSet.getString(7)));
                sp.setHinhAnh(resultSet.getString(8));
                sp.setGiaBan(Float.valueOf(resultSet.getString(9)));
                sp.setTacGia(resultSet.getString(10));
                return sp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static int insert(String maNhaXuatBan, String maTheLoai, String Serial, String tenSach,
            int soLuong, String hinhAnh, float giaBan, String tacGia)
    {
        String sql="exec SP_InsertOneProduct '"+maNhaXuatBan+"', '"+maTheLoai+"', '"+Serial+"',"
                + "N'"+tenSach+"', "+soLuong+", '"+hinhAnh+"', "+giaBan+", N'"+tacGia+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int upDate(String maSach, String maNhaXuatBan, String maTheLoai, String Serial, String tenSach,
            int soLuong, String hinhAnh, float giaBan, String tacGia)
    {
        String sql="update Sach set MaNXB='"+maNhaXuatBan+"', MaTheLoai='"+maTheLoai+"', SerialSach='"+Serial+"',"+
                " TenSach=N'"+tenSach+"', SoLuong="+soLuong+", HinhAnh='"+hinhAnh+"', GiaBan="+giaBan+","+
                " TacGia=N'"+tacGia+"' where MaSach='"+maSach+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int NgungKinhDoanhMotSanPham(String maSanPham)
    {
        String sql="update Sach set SoLuong=-1 where MaSach='"+maSanPham+"'";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int getCountProduct()
    {
        String sql="select count(Sach.MaSach) as kq from Sach where Sach.SoLuong>-1";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        int dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getInt("kq");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    public static int getSumProductWereSale()
    {
        String sql="select sum(ChiTietPhieuBanHang.SoLuong) as kq from ChiTietPhieuBanHang ";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        int dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getInt("kq");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    public static int getSumProductWereSaleInMonth()
    {
        String sql="select sum(ChiTietPhieuBanHang.SoLuong) as kq from ChiTietPhieuBanHang, PhieuBanHang " +
"where MONTH(GETDATE())=MONTH(PhieuBanHang.NgayMua) and ChiTietPhieuBanHang.MaPhieuBan=PhieuBanHang.MaPhieuBan";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        int dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getInt("kq");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    public static float getMoneyInMonth()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as kq from PhieuBanHang where MONTH(GETDATE())=MONTH(PhieuBanHang.NgayMua)";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getInt("kq");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
}
