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
import quanlycuahangbansach.Models.Category;

/**
 *
 * @author PC
 */
public class CategoryDAO {
    public static ArrayList<Category> getAll()
    {
        ArrayList<Category> listCategory=new ArrayList<Category>();
        ResultSet resultSet;
        DataHelper helper=new DataHelper();
        String sql="SELECT*FROM TheLoai";
        resultSet=helper.executeQuery(sql);
        try {
            while(resultSet.next())
            {
                Category tl=new Category();
                tl.setMaTheLoai(resultSet.getString(1));
                tl.setTenTheLoai(resultSet.getString(2));
                listCategory.add(tl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return listCategory;
    }
    public static Category getOneById(String value)
    {
        String sql="SELECT*FROM TheLoai where MaTheLoai='"+value+"'";
        DataHelper helper=new DataHelper();
        ResultSet resultSet=helper.executeQuery(sql);
        Category category=new Category();
        try {
            while (resultSet.next()) 
            {
                category.setMaTheLoai(resultSet.getString(1));
                category.setTenTheLoai(resultSet.getString(2));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        helper.close();
        return category;
    }
    public static int insert(String tenTheLoai)
    {
        String sql="EXEC SP_InsertOneCategory N'"+tenTheLoai+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int upDate(String maTheLoai, String tenTheLoai)
    {
        String sql="update TheLoai set TenTheLoai=N'"+tenTheLoai+"' where MaTheLoai='"+maTheLoai+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    public static int deleteOneById(String maTheLoai)
    {
        String sql="delete TheLoai where MaTheLoai='"+maTheLoai+"';";
        DataHelper helper=new DataHelper();
        int kq=helper.executeUpdate(sql);
        helper.close();
        return kq;
    }
    
}
