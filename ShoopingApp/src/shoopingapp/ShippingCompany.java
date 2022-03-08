/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoopingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class ShippingCompany extends person{
    public void Registeration(int ShippingId, String Name, String Email, String Address,String Phone,String Area){
         try {
                 Connection myConn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
                 Statement mystamt=myConn.createStatement();
                 Name="'"+Name+"'";
                 Email="'"+Email+"'";
                 Address="'"+Address+"'";
                 Area="'"+Area+"'";
                 Phone="'"+Phone+"'";
                 String sql="insert into shipping"+"(ShippingId, Name, Email, Address, Phone, Area)"+"values ("+ShippingId+","+Name+","+Email+","+Address+","+Phone+","+Area+")";
                 mystamt.executeUpdate(sql); 
                 
             } catch (SQLException ex) {
                 super.status_if_data_in_table=true;
                 Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
                 
             }
    
    }
    
}
