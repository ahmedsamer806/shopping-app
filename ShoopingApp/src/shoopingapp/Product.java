/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoopingapp;
import com.mysql.cj.jdbc.Blob;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import shoopingapp.Buyer;
/**
 *
 * @author DELL
 */
public class Product {
    
    
    
    public void view_products(String type  ,JPanel p1,JLayeredPane layerd){
        try {
            Connection myConn;
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
            Statement mystamt=myConn.createStatement();
            String []element={"laptops","mobilephones","headphones","tv"};
            if(type.equals("5")){View_main_produts(p1,layerd); return;}
            
            ResultSet myRs=mystamt.executeQuery("select * from "+type);
            int countery=0;
            while(myRs.next()){
                try{
                        byte []img=myRs.getBytes("Image");//Read Image From The database
                        ImageIcon image=new ImageIcon(img); //Load Imaga as Icon
                        
                        Blob img2=(Blob) myRs.getBlob("Image");
                        Image im=image.getImage(); //Getting Image
                        JLabel label=new JLabel(); //Creat label to contains this icon
                        //////////////////////////////////////////////////////////
                        //////////////////////////Draw the label///////////////// 
                        label.setBounds(layerd.getX()+10, layerd.getY()+10+countery, 250, 242);//Set the boundry for the label from 10 of the x and 10 of the y of the layerd 
                        Image myImg=im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);//scale the icon 
                        ImageIcon newim=new ImageIcon(myImg);//load the image to icon 
                        label.setIcon(newim);// add the icon to image 
                        String info=myRs.getString("Info");//read the info from the data base
                        String price=myRs.getString("Price");//read the price form the data base 
                        int id=myRs.getInt("Id");
                        JButton b1=new JButton("Add to Cart");//create button of add cart
                        JTextArea t1=new JTextArea();//text area for info
                        JTextArea t2=new JTextArea();//text area for price
                        t1.setText(info);//add the string readed from database
                        t1.setBounds(label.getX()+label.getWidth()+10, label.getY(), 1000, 50);//state the boundry for info text area the width of the icon +10 and the same of y 
                        t2.setText("Price:"+price+" EGP");//add the price in text area t2
                        t2.setBounds(label.getX()+label.getWidth()+10, label.getY()+60, 1000, 50);//state the boundry
                        b1.setBounds(label.getX()+label.getWidth()+10,label.getY()+180,150,50);//state the boundry for the boutton
                        t1.setBackground(Color.WHITE);//background to white
                        t2.setBackground(Color.WHITE);//background to white
                        Font font = new Font(Font.SERIF, Font.BOLD, 20);
                        t1.setFont(font);
                        t1.setBorder(BorderFactory.createEmptyBorder());
                        t2.setFont(font);
                        t2.setBorder(BorderFactory.createEmptyBorder());
                        b1.addActionListener(new ActionListener()
                        {
                            
                          public void actionPerformed (ActionEvent e) { 
                                      Buyer br=new Buyer();
                                      br.Add_to_cart(id, info, price, img2);
                                       
                          }
                        }
                        );
                        if(person.user_is_login){p1.add(b1);}
                        
                        p1.add(t1);
                        p1.add(t2);
                        countery+=252;
                        p1.setPreferredSize(new Dimension(layerd.getX()+countery, layerd.getY()+countery+100));
                        p1.add(label); 
                        } catch(NullPointerException e){ System.out.print("");
                               
                   }
                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    private void  View_main_produts(JPanel p1,JLayeredPane layerd){
        try {
            Connection myConn;
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
            Statement mystamt=myConn.createStatement();
            int countery=0;
            int counter=0;
            int counter2=0;
            Random rand=new Random();
            String []element={"tv","laptops","mobilephones","headphones"};
            while (counter<=10){
                
                ResultSet myRs=mystamt.executeQuery("select * from "+element[(counter+1)%3]);
                while(myRs.next()){
                    try{
                        byte []img=myRs.getBytes("Image");//Read Image From The database
                        Blob img2=(Blob) myRs.getBlob("Image");
                       
                        ImageIcon image=new ImageIcon(img); //Load Imaga as Icon
                        Image im=image.getImage(); //Getting Image
                        JLabel label=new JLabel(); //Creat label to contains this icon
                        //////////////////////////////////////////////////////////
                        //////////////////////////Draw the label///////////////// 
                        label.setBounds(layerd.getX()+10, layerd.getY()+10+countery, 250, 242);//Set the boundry for the label from 10 of the x and 10 of the y of the layerd 
                        Image myImg=im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);//scale the icon 
                        ImageIcon newim=new ImageIcon(myImg);//load the image to icon 
                        label.setIcon(newim);// add the icon to image 
                        String info=myRs.getString("Info");//read the info from the data base
                        String price=myRs.getString("Price");//read the price form the data base 
                        JButton b1=new JButton("Add to Cart");//create button of add cart
                        int id=myRs.getInt("Id");
                        JTextArea t1=new JTextArea();//text area for info
                        JTextArea t2=new JTextArea();//text area for price
                        t1.setText(info);//add the string readed from database
                        t1.setBounds(label.getX()+label.getWidth()+10, label.getY(), 2000, 50);//state the boundry for info text area the width of the icon +10 and the same of y 
                        t2.setText("Price:"+price+" EGP");//add the price in text area t2
                        t2.setBounds(label.getX()+label.getWidth()+10, label.getY()+60, 2000, 50);//state the boundry
                        b1.setBounds(label.getX()+label.getWidth()+10,label.getY()+180,150,50);//state the boundry for the boutton
                        t1.setBackground(Color.WHITE);//background to white
                        t2.setBackground(Color.WHITE);//background to white
                        Font font = new Font(Font.SERIF, Font.BOLD, 20);
                        t1.setFont(font);
                        t1.setBorder(BorderFactory.createEmptyBorder());
                        t2.setFont(font);
                        t2.setBorder(BorderFactory.createEmptyBorder());
                        b1.addActionListener(new ActionListener()
                        {
                            
                          public void actionPerformed (ActionEvent e) { 
                              Buyer br=new Buyer();
                              br.Add_to_cart(id, info, price, img2);
                             
                                       
                          
                          
                          
                          }
                        }
                        );
                        if(person.user_is_login){p1.add(b1);}
                        
                        p1.add(t1);
                        p1.add(t2);
                        countery+=252;
                        p1.setPreferredSize(new Dimension(layerd.getX()+countery, layerd.getY()+countery+100));
                        p1.add(label);
                        if(counter2==1){counter2=0;break; }
                         counter2++;
                        
            
                     
                } catch(NullPointerException e){
                               System.out.print("");
                   }
                 counter++;
               
                
                }
                
            }   } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    public void search(JPanel p1,JLayeredPane layerd,String search){
       try {
            // TODO add your handling code here:
            Connection myConn;
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
            Statement mystamt=myConn.createStatement();
            int countery=0;
            int counterx=0;
            String []element={"tv","laptops","mobilephones","headphones"};
            for (int i=0;i<4;i++){
                ResultSet myRs=mystamt.executeQuery("select * from "+element[i]);
                System.out.println(element[i]);
                while(myRs.next()){
                    if(myRs.getString("Info").contains(search)){
                         byte []img=myRs.getBytes("Image");//Read Image From The database
                        ImageIcon image=new ImageIcon(img); //Load Imaga as Icon
                        Image im=image.getImage(); //Getting Image
                        JLabel label=new JLabel(); //Creat label to contains this icon
                        //////////////////////////////////////////////////////////
                        //////////////////////////Draw the label///////////////// 
                        label.setBounds(layerd.getX()+10, layerd.getY()+10+countery, 250, 242);//Set the boundry for the label from 10 of the x and 10 of the y of the layerd 
                        Image myImg=im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);//scale the icon 
                        ImageIcon newim=new ImageIcon(myImg);//load the image to icon 
                        label.setIcon(newim);// add the icon to image 
                        String info=myRs.getString("Info");//read the info from the data base
                        String price=myRs.getString("Price");//read the price form the data base 
                        JButton b1=new JButton("Add to Cart");//create button of add cart
                        JTextArea t1=new JTextArea();//text area for info
                        JTextArea t2=new JTextArea();//text area for price
                        t1.setText(info);//add the string readed from database
                        t1.setBounds(label.getX()+label.getWidth()+10, label.getY(), 2000, 50);//state the boundry for info text area the width of the icon +10 and the same of y 
                        t2.setText("Price:"+price+" EGP");//add the price in text area t2
                        t2.setBounds(label.getX()+label.getWidth()+10, label.getY()+60, 2000, 50);//state the boundry
                        b1.setBounds(label.getX()+label.getWidth()+10,label.getY()+180,150,50);//state the boundry for the boutton
                        t1.setBackground(Color.WHITE);//background to white
                        t2.setBackground(Color.WHITE);//background to white
                        Font font = new Font(Font.SERIF, Font.BOLD, 20);
                        t1.setFont(font);
                        t1.setBorder(BorderFactory.createEmptyBorder());
                        t2.setFont(font);
                        t2.setBorder(BorderFactory.createEmptyBorder());
                        b1.addActionListener(new ActionListener()
                        {
                            
                          public void actionPerformed (ActionEvent e) { 
                          
                                       
                          
                          
                          
                          }
                        }
                        );
                        if(person.user_is_login){p1.add(b1);}
                        p1.add(t1);
                        p1.add(t2);
                        countery+=252;
                        p1.setPreferredSize(new Dimension(layerd.getX()+countery, layerd.getY()+countery+100));
                        p1.add(label); 
                        }  
                }}
    
        }
            catch (SQLException ex) {
            Logger.getLogger(semiFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    }

   
    
    
    
}
