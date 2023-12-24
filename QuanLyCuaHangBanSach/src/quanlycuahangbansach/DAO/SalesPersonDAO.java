/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlycuahangbansach.DLL.DataHelper;
import quanlycuahangbansach.Models.SalePerson;

/**
 *
 * @author PC
 */
public class SalesPersonDAO {
     public static ArrayList<SalePerson> getAll()
    {
        ArrayList<SalePerson> ListSalePerson =new ArrayList<SalePerson>();
        DataHelper helper=new DataHelper();
        String sql="select*from NhanVien";
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while (resultSet.next())
            {
                SalePerson s=new SalePerson();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setTenNhanVien(resultSet.getString("TenNV"));
                s.setGioiTinh(resultSet.getString("GioiTinh"));
                s.setNgaySinh(String.valueOf(LocalDate.parse(resultSet.getDate("NgaySinh").toString()).plusDays(2)));
                //s.setNgaySinh(resultSet.getString("NgaySinh"));
                s.setNgayVaoLam(String.valueOf(LocalDate.parse(resultSet.getDate("NgayVaoLam").toString()).plusDays(2)));
                //s.setNgayVaoLam(resultSet.getString("NgayVaoLam"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSdt(resultSet.getString("SDT"));
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenDangNhap(resultSet.getString("TenDangNhap"));
                s.setHinhAnh(resultSet.getString("HinhAnh"));
                s.setMatKhau(resultSet.getString("MatKhau"));
                s.setLoaiTaiKhoan(resultSet.getString("LoaiTaiKhoan"));
                s.setCanCuocCongDan(resultSet.getString("CCCD"));
                ListSalePerson.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        helper.close();
        return ListSalePerson;
    }
     public static ArrayList<SalePerson> getListSalesPersonByCategory(String maChucVu)
    {
        ArrayList<SalePerson> ListSalePerson =new ArrayList<SalePerson>();
        DataHelper helper=new DataHelper();
        String sql="select*from NhanVien where MaChucVu='"+maChucVu+"'";
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while (resultSet.next())
            {
                SalePerson s=new SalePerson();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setTenNhanVien(resultSet.getString("TenNV"));
                s.setGioiTinh(resultSet.getString("GioiTinh"));
                s.setNgaySinh(String.valueOf(LocalDate.parse(resultSet.getDate("NgaySinh").toString()).plusDays(2)));
                //s.setNgaySinh(resultSet.getString("NgaySinh"));
                s.setNgayVaoLam(String.valueOf(LocalDate.parse(resultSet.getDate("NgayVaoLam").toString()).plusDays(2)));
                //s.setNgayVaoLam(resultSet.getString("NgayVaoLam"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSdt(resultSet.getString("SDT"));
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenDangNhap(resultSet.getString("TenDangNhap"));
                s.setHinhAnh(resultSet.getString("HinhAnh"));
                s.setMatKhau(resultSet.getString("MatKhau"));
                s.setLoaiTaiKhoan(resultSet.getString("LoaiTaiKhoan"));
                s.setCanCuocCongDan(resultSet.getString("CCCD"));
                ListSalePerson.add(s);

            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        helper.close();
        return ListSalePerson;
    }
    public static SalePerson getOneSalePersonByUsername(String UserName)
    {
        DataHelper helper=new DataHelper();
        String sql="select * from NhanVien where TenDangNhap='"+UserName+"'";
        SalePerson s=new SalePerson();
        ResultSet resultSet = helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setTenNhanVien(resultSet.getString("TenNV"));
                s.setGioiTinh(resultSet.getString("GioiTinh"));
                s.setNgaySinh(String.valueOf(LocalDate.parse(resultSet.getDate("NgaySinh").toString()).plusDays(2)));
                //s.setNgaySinh(resultSet.getString("NgaySinh"));
                s.setNgayVaoLam(String.valueOf(LocalDate.parse(resultSet.getDate("NgayVaoLam").toString()).plusDays(2)));
                //s.setNgayVaoLam(resultSet.getString("NgayVaoLam"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSdt(resultSet.getString("SDT"));
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenDangNhap(resultSet.getString("TenDangNhap"));
                s.setHinhAnh(resultSet.getString("HinhAnh"));
                s.setMatKhau(resultSet.getString("MatKhau"));
                s.setLoaiTaiKhoan(resultSet.getString("LoaiTaiKhoan"));
                s.setCanCuocCongDan(resultSet.getString("CCCD"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public static SalePerson getOneSalePersonByIDSalePerson(String maNhanVien)
    {   
        DataHelper helper=new DataHelper();
        String sql="select * from NhanVien where MaNhanVien='"+maNhanVien+"'";
        SalePerson s=new SalePerson();
        ResultSet resultSet = helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setTenNhanVien(resultSet.getString("TenNV"));
                s.setGioiTinh(resultSet.getString("GioiTinh"));
                s.setNgaySinh(String.valueOf(LocalDate.parse(resultSet.getDate("NgaySinh").toString()).plusDays(2)));
                //s.setNgaySinh(resultSet.getString("NgaySinh"));
                s.setNgayVaoLam(String.valueOf(LocalDate.parse(resultSet.getDate("NgayVaoLam").toString()).plusDays(2)));
                //s.setNgayVaoLam(resultSet.getString("NgayVaoLam"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSdt(resultSet.getString("SDT"));
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenDangNhap(resultSet.getString("TenDangNhap"));
                s.setHinhAnh(resultSet.getString("HinhAnh"));
                s.setMatKhau(resultSet.getString("MatKhau"));
                s.setLoaiTaiKhoan(resultSet.getString("LoaiTaiKhoan"));
                s.setCanCuocCongDan(resultSet.getString("CCCD"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    
    public static SalePerson checkLogIn(String Username , String Password){
        DataHelper helper=new DataHelper();

        String query = "select*from NhanVien where TenDangNhap='"+Username+"' ;" ;
        SalePerson s=new SalePerson();
        ResultSet resultSet = helper.executeQuery(query);
        try {
            while (resultSet.next()) 
            {
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setTenNhanVien(resultSet.getString("TenNV"));
                s.setGioiTinh(resultSet.getString("GioiTinh"));
                s.setNgaySinh(resultSet.getString("NgaySinh"));
                
                s.setNgaySinh(String.valueOf(LocalDate.parse(resultSet.getDate("NgaySinh").toString()).plusDays(2)));
                //s.setNgaySinh(resultSet.getString("NgaySinh"));
                s.setNgayVaoLam(String.valueOf(LocalDate.parse(resultSet.getDate("NgayVaoLam").toString()).plusDays(2)));
                //s.setNgayVaoLam(resultSet.getString("NgayVaoLam"));
                s.setDiaChi(resultSet.getString("DiaChi"));
                s.setSdt(resultSet.getString("SDT"));
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenDangNhap(resultSet.getString("TenDangNhap"));
                s.setHinhAnh(resultSet.getString("HinhAnh"));
                s.setMatKhau(resultSet.getString("MatKhau"));
                s.setLoaiTaiKhoan(resultSet.getString("LoaiTaiKhoan"));
                s.setCanCuocCongDan(resultSet.getString("CCCD"));
                if(s.getTenDangNhap().equals(Username) && s.getMatKhau().equals(Password))
                {
                    return s;
                }
                else
                {
                    s=new SalePerson();
                    return s;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesPersonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return null;
 } 
    
    public static int UpdateProfie(SalePerson acc) {
        
        DataHelper helper=new DataHelper();
         String query = "set dateformat dmy Update NhanVien" +
                        " Set TenNV = N'"+acc.getTenNhanVien()+"' , GioiTinh = '"+acc.gioiTinh+"' ," +
                        "NgaySinh = '"+acc.getNgaySinh()+"' , NgayVaoLam = '"+acc.getNgayVaoLam()+"' ," +
                        "DiaChi = N'"+acc.getDiaChi()+"' , SDT = '"+acc.getSdt()+"' , MaChucVu = '"+acc.getMaChucVu()+"' ," +
                        "TenDangNhap = '"+acc.getTenDangNhap()+"' , HinhAnh = '"+acc.getHinhAnh()+"' ," +
                        "MatKhau = '"+acc.getMatKhau()+"' , LoaiTaiKhoan = '"+acc.loaiTaiKhoan+"' , " +
                        "CCCD='"+acc.getCanCuocCongDan()+"' "+
                        "Where MaNhanVien = '"+acc.getMaNhanVien()+"'" ;

         int resultSet=0;
         resultSet= helper.executeUpdate(query);

         return resultSet ;
    }
    
    public static int UpdateProfie_Block(String maNhanVien) {
        
        DataHelper helper=new DataHelper();
         String query = "Update NhanVien Set TrangThai = 0'"+
                        "Where MaNhanVien = '"+maNhanVien+"'" ;

         int resultSet=0;
         resultSet= helper.executeUpdate(query);

         return resultSet ;
    }
    
    public static int insertOneProfie(SalePerson acc) {
        
        DataHelper helper=new DataHelper();
         String query = "exec SP_InsertOneSalePerson N'"+acc.getTenNhanVien()+"', "+acc.gioiTinh+", '"+acc.getNgaySinh()
                +"' , '"+acc.getNgayVaoLam()+"', N'"+acc.getDiaChi()+"', "+acc.getSdt()
                +" , '"+acc.getMaChucVu()+"', '"+acc.getTenDangNhap()+"','"+acc.getHinhAnh()
                +"','"+acc.getMatKhau()+"',"+acc.getCanCuocCongDan()+";" ;

         int resultSet=0;
         resultSet= helper.executeUpdate(query);

         return resultSet ;
    }
    public static void main(String[] args) {
        SalePerson s=SalesPersonDAO.checkLogIn("vonguyenduytan", "123");
        System.out.println(s.getNgaySinh());
        LocalDate l= LocalDate.parse(s.getNgaySinh());
        l.plusDays(2);
        System.out.println(l.plusDays(2));
    }
}

