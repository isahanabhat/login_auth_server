/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.login_auth_server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONObject;

/**
 *
 * @author bhats
 */
public class DatabaseReader {
    
    public String MySQLConnector(String query, String username, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users",
                                                        "root","5li6TzCr$0");
            Statement state = con.createStatement();
            ResultSet rs = state.executeQuery(query);
            
            JSONObject j = new JSONObject();
            
            if(!rs.isBeforeFirst()){
                j.put("status", "FAILURE");
            }
            
            while(rs.next()){
                System.out.println(rs.getString(1)+"  "+rs.getString(2));
                if(username.equals(rs.getString(1)) && password.equals(rs.getString(2))){
                    j.put("status", "SUCCESS");
                } else {
                    j.put("status", "FAILURE");
                }
            }    
            
            String response = j.toString();
            
            con.close();
            
            return response;
            
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
