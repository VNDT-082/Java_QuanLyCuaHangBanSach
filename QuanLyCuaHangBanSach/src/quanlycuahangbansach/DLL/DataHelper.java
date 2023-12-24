/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.DLL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class DataHelper {
    public Connection connection;
    public DataHelper()
    {
        try {
            //String strServer="LAPTOP-QTRBNIQ4";
            String strServer="localhost";
            String strDatabase="QuanLyCuaHangBanSach";
            String user="sa";
            String pass="123";
            String className="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(className);
            String urlConnection="jdbc:sqlserver://"+strServer
                    +":1433;databaseName="+strDatabase
                    +";user="+user
                    +";password="+pass;
            connection=DriverManager.getConnection(urlConnection);
            if(connection!=null)
            {
                System.out.println("ket noi thanh cong");
            }
            else
            {
                System.out.println("ket noi that bai");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    public ResultSet executeQuery(String sql)
    {
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultSet;
        
    }
    public int executeUpdate(String sql)
    {
        Statement statement=null;
        int kq=0;
        try {
            statement=connection.createStatement();
            kq=statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kq;
    }

    public void close()
    {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        new DataHelper();
    }
}
