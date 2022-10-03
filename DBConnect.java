/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package savage;

import java.sql.*;
/**
 *
 * @author DELL
 */
public class DBConnect {
    public Connection con;
    
    public DBConnect(){
 
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/fix?"+"user=root&password=");
            System.out.println("connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
