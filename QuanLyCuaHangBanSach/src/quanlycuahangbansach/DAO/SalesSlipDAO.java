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
import quanlycuahangbansach.Models.SalesSlip;

/**
 *
 * @author PC
 */
public class SalesSlipDAO {
    public static ArrayList<SalesSlip> getAll()
    {
        ArrayList<SalesSlip> listSalesSlip=new ArrayList<SalesSlip>();
        String sql="select *from PhieuBanHang";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                SalesSlip s=new SalesSlip();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
                listSalesSlip.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSalesSlip;
    }
    public static int insertOneSalesSlip(SalesSlip s)
    {
        String sql="exec SP_InsertOneQuittance '"+s.getSoDienThoaiKhachHang()+"','"+s.getMaNhanVien()+"'";
        DataHelper helper=new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int updateOneSalesSlip(SalesSlip s)
    {
        String sql="update PhieuBanHang set GiamGia="+s.getGiamGia()+", SoTienPhaiTra="
                +s.getSoTienPhiaTra()+", TrangThai=1 where MaPhieuBan='"+s.getMaPhieuBanHang()+"';";
        DataHelper helper=new DataHelper();
        int kq=0;
        kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    
    }
    public static ArrayList<SalesSlip> getListSalesSlipByIDSalePerson(String maNhanVien)
    {
        ArrayList<SalesSlip> listSalesSlip=new ArrayList<SalesSlip>();
        String sql="select *from PhieuBanHang where MaNhanVien='"+maNhanVien+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                SalesSlip s=new SalesSlip();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
                listSalesSlip.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSalesSlip;
    }
    public static SalesSlip getLatestSalesSlipByIDSalePerson(String MaNhanVien)
    {
        SalesSlip s=new SalesSlip();
        String sql ="select *from PhieuBanHang where MaNhanVien='"+MaNhanVien+"'  and NgayMua = (select MAX(NgayMua)"
                +" from PhieuBanHang where MaNhanVien='"+MaNhanVien+"');";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static SalesSlip getOneSalesSlipByIDSalesSlip(String MaPhieuBan)
    {
        SalesSlip s=new SalesSlip();
        String sql ="select *from PhieuBanHang where  MaPhieuBan='"+MaPhieuBan+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }
    
    public static ArrayList<SalesSlip> getListSalesSlipByDateValue(String date)
    {
        ArrayList<SalesSlip> ds=new ArrayList<SalesSlip>();
        String sql ="set dateformat dmy select *from PhieuBanHang where NgayMua >='"+date+"' and NgayMua<= GETDATE();";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                SalesSlip s=new SalesSlip();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
                ds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public static ArrayList<SalesSlip> getListSalesSlipByDateValue(String ngayBatDau, String ngayKetThuc)
    {
        ArrayList<SalesSlip> ds=new ArrayList<SalesSlip>();
        String sql ="set dateformat dmy  select *from PhieuBanHang where NgayMua >='"
                +ngayBatDau+"' and NgayMua<= '"+ngayKetThuc+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                SalesSlip s=new SalesSlip();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
                ds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public static ArrayList<SalesSlip> getListSalesSlipByPhoneNumberValue(String phoneNumber)
    {
        ArrayList<SalesSlip> ds=new ArrayList<SalesSlip>();
        
        String sql ="select *from PhieuBanHang where  SDT_KH='"+phoneNumber+"';";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                SalesSlip s=new SalesSlip();
                s.setMaNhanVien(resultSet.getString("MaNhanVien"));
                s.setMaPhieuBanHang(resultSet.getString("MaPhieuBan"));
                s.setSoDienThoaiKhachHang(resultSet.getString("SDT_KH"));
                s.setTongTien(resultSet.getFloat("TongTien"));
                s.setNgayMua(resultSet.getString("NgayMua"));
                s.setGiamGia(Float.valueOf(resultSet.getString("GiamGia")));
                s.setSoTienPhiaTra(Float.valueOf(resultSet.getString("SoTienPhaiTra")));
                s.setTrangThai(resultSet.getString("TrangThai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesSlipDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
}
