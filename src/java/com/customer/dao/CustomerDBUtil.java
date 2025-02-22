package com.customer.dao;

import com.customer.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDBUtil {
    
    public static List<Customer> validate(String userName, String password){
        
        ArrayList<Customer> cus = new ArrayList<>();
        
        //Create Db Connection
        
        String url = "jdbc:mysql://localhost:3306/mc";
        String user = "root";
        String pass = "admin";
        
        //Validate
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection con = DriverManager.getConnection(url,user,pass);
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM mc.customer WHERE username='"+userName+"' AND password='"+password+"'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String NIC = rs.getString(4);
                String phone = rs.getString(5);
                String userU = rs.getString(6);
                String passU = rs.getString(7);
                String role = rs.getString(8);
                
                Customer c = new Customer(id,name,email,NIC,phone,userU,passU,role);
                cus.add(c);
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return cus;
        
    }  
    
    
}
