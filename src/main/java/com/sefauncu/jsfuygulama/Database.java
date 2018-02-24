
package com.sefauncu.jsfuygulama;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    
    public boolean checkUser(String username,String password) throws SQLException{
    Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;
        try {
            
        connection = getConnection();
       
        
        String sql= "Select * from user Where username=? AND password=?";
        ps = connection.prepareStatement(sql);
        ps.setString(1, username);
         ps.setString(2, password);
        
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                result=true;
            
            }
          
    }catch (SQLException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE,null,ex);

    }finally{
            
            ps.close();
            connection.close();
        
        }
        return result;
    }

    
    
    private Connection getConnection(){

        System.out.println("mysql testing");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver nerede");
            e.printStackTrace();
            return null;
        }
        System.out.println("mysql registered");
        Connection connection = null;
        
        try {
            connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/test","root","");
            
        } catch (SQLException e) {
             System.out.println("jdbc driver nerede");
            e.printStackTrace();
            return null;
        }
    
        if(connection != null){
            System.out.println("başarılı");
            
        }else{
            System.out.println("hatalı");
        }
        return connection;
    }

}