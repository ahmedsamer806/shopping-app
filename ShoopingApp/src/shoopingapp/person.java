package shoopingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class person {
     public boolean status_if_data_in_table=false;
     static public boolean seller_is_login=false;
     static public boolean user_is_login=false;
     public static String company_name;
     public  static String user_name;
     //==Regestriation Function takes the inputs from gui and add it to data base//
     public void Registeration(String name,String email,String address,String area,String phone,String password){
         //First Check account type 1 for customer 2 for seller 3 for shipping
         
         
             try {
                 Connection myConn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
                 Statement mystamt=myConn.createStatement();
                 name="'"+name+"'";
                 email="'"+email+"'";
                 address="'"+address+"'";
                 area="'"+area+"'";
                 password="'"+password+"'";
                 phone="'"+phone+"'";
                 String sql="insert into customer"+"(Email, UserName, Password, Address, Area, PhoneNumber)"+"values ("+email+","+name+","+password+","+address+","+area+","+phone+")";
  
                 mystamt.executeUpdate(sql); 
                 
             } 
             catch (Exception ex) {
                 status_if_data_in_table=true;
                 Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
                 
             }
            
         
         
  
     }
    
            
     public static void login(String input,String password){
      
         try {
             Connection myConn;
             myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
              Statement mystamt=myConn.createStatement();
              ResultSet myRs3=mystamt.executeQuery("select*from seller");
               while(myRs3.next()){
                   System.out.println(myRs3.getString("UserName"));
  
                      if((input.equals(myRs3.getString("UserName"))||input.equals(myRs3.getString("Email")))&&password.equals(myRs3.getString("Password")))
                      {
                          seller_is_login=true;
                          user_is_login=false;
                          company_name=myRs3.getString("CompanyName");
                          
                      }
                 
                     }
               
                      }
               
                      
          
         catch (SQLException ex) {
             Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
        try {
             Connection myConn;
               myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
              Statement mystamt=myConn.createStatement();
              ResultSet myRs1=mystamt.executeQuery("select*from customer");
                 while(myRs1.next()){
                  if((input.equals(myRs1.getString("UserName"))||input.equals(myRs1.getString("Email")))&&password.equals(myRs1.getString("Password")))
                             {
                               
                                 seller_is_login=false;
                                  user_is_login=true;
                                  user_name=myRs1.getString("UserName");
                             
                             }
                 }
         } catch (SQLException ex) {
            Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
         }
             
         
                
     
     
     }
    
   
}





     
     
     
     
     
     
     
     
     
     
     
     
     
     
    

