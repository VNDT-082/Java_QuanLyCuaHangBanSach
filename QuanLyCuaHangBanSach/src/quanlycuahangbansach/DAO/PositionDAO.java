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
import quanlycuahangbansach.Models.Position;

/**
 *
 * @author PC
 */
public class PositionDAO {
    public static ArrayList<Position> getAll()
    {
        ArrayList<Position> ds=new ArrayList<Position>();
        String sql="select*from ChucVu";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Position s=new Position();
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenChucVu(resultSet.getString("TenChucVu"));
                ds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ds;
    }
    public static Position getOnePositionByID(String maChucVu)
    {
        String sql="select*from ChucVu where MaChucVu='"+maChucVu+"'";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Position s=new Position();
                s.setMaChucVu(resultSet.getString("MaChucVu"));
                s.setTenChucVu(resultSet.getString("TenChucVu"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PositionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
