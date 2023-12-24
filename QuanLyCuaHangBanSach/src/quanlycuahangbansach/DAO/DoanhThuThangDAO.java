/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlycuahangbansach.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import quanlycuahangbansach.DLL.DataHelper;

/**
 *
 * @author PC
 */
public class DoanhThuThangDAO {
    public static float getRevenue1()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=1;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue2()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=2;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue3()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=3;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue4()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=4;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue5()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=5;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue6()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=6;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue7()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=7;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue8()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=8;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue9()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=9;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue10()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=10;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue11()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=11;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
    
    public static float getRevenue12()
    {
        String sql="select sum(PhieuBanHang.SoTienPhaiTra) as DoanhThu "+
                "from PhieuBanHang where Month(PhieuBanHang.NgayMua)=12;";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        float dt=0;
        try {
            while(resultSet.next())
            {
                dt=resultSet.getFloat("DoanhThu");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoanhThuThangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dt;
    }
   
}
