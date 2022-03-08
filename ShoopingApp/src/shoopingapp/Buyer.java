
package shoopingapp;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.sql.*;
public class Buyer extends person{
    public int creditCardNo;
    
    String username;
    Buyer()
    {
        this.username=super.user_name;
    }
    public void Add_to_cart(int id,String info,String price,Blob img)
    {
        try{
             Connection myConn;
             myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
             PreparedStatement mystamt1=null;
//         Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
////         Statement myst= conn.createStatement();
////         String query= "insert into cart"+"(id,Info, Price, Image,UserName)"+ "values ('"+id+"','"+ info+"','"+price+"','"+img+"','"+this.username+"')";
////         myst.executeUpdate(query);
                mystamt1=myConn.prepareStatement("insert into cart" +"(id,Info, Price, Image,UserName) values(?,?,?,?,?)");
                mystamt1.setInt(1, id);
                mystamt1.setString(2, info);
                mystamt1.setString(3, price);
                mystamt1.setBlob(4, img);
                mystamt1.setString(5, this.username);
                mystamt1.executeUpdate();
         
         
         
         
         
         
         System.out.println("added successfully");
//         conn.close();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
