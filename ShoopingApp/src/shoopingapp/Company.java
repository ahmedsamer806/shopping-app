/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shoopingapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static shoopingapp.person.company_name;
/**
 *
 * @author DELL
 */
public class Company extends person{
     public boolean Add_product_status=false;
     public void Registeration(String name,String email,String address,String phone,String password,String CompanyName,int CompanyId ){
               try {
                     super.company_name=CompanyName;
                     Connection myConn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
                    Statement mystamt=myConn.createStatement();
                    ResultSet myRs=mystamt.executeQuery("select*from seller");
                     while(myRs.next()){if(myRs.getInt("SellerId")==CompanyId&&myRs.getString("UserName")==name&&myRs.getString("email")==email){status_if_data_in_table=true;}}
                         name="'"+name+"'";
                        email="'"+email+"'";
                        address="'"+address+"'";
                         password="'"+password+"'";
                        CompanyName="'"+CompanyName+"'";
                         phone="'"+phone+"'";
                     String sql="insert into seller"+"(SellerId, Phone, CompanyName, UserName, Password, Address, email)"+"values ("+CompanyId+","+phone+","+CompanyName+","+name+","+password+","+address+","+email+")";
                    mystamt.executeUpdate(sql); 
                 
             } catch (SQLException ex) {
                 super.status_if_data_in_table=true;
                 Logger.getLogger(person.class.getName()).log(Level.SEVERE, null, ex);
                 
             }
    
    }
      void Add_products(String info,String price,String symbol,InputStream in,String type ){
         try {
             Random Rand=new Random();
             int id=Rand.nextInt(100000);
             Connection myConn;
             myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/datausage","root","root99");
             PreparedStatement mystamt1=null;
             if(type.equals("tv")){
             mystamt1=myConn.prepareStatement("insert into "+type+" (Id, Info, Price,Image,CompanyName) values(?,?,?,?,?)");
                mystamt1.setInt(1, id);
                mystamt1.setString(2, info);
                mystamt1.setString(3, "EGP"+price);
                mystamt1.setBlob(4, in);
                mystamt1.setString(5, super.company_name);
                mystamt1.executeUpdate();
             }
             else{
             mystamt1=myConn.prepareStatement("insert into "+type+" (Id, Info, Price, PriceSymbol, Image,CompanyName) values(?,?,?,?,?,?)");
                mystamt1.setInt(1, id);
                mystamt1.setString(2, info);
                mystamt1.setString(3, price);
                mystamt1.setString(4, symbol);
                mystamt1.setBlob(5, in);
                mystamt1.setString(6, company_name);
                mystamt1.executeUpdate();
             }
             
         } catch (SQLException ex) {
             Add_product_status=true;
             Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
         }
     
       
     
     }
     public void vieew_my_product(JPanel p1,JLayeredPane layerd){
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
                    if(super.company_name.equals(myRs.getString("CompanyName"))){
                        //label.setBounds(0,countery, 300, 242);
                        ////////////////////read Image///////////////////////////////
                        byte []img=myRs.getBytes("Image");
                        ImageIcon image=new ImageIcon(img);
                        Image im=image.getImage();
                        JLabel label=new JLabel(); //Creat label to contains this icon
                        //////////////////////////////////////////////////////////
                        //////////////////////////Draw the label///////////////// 
                        label.setBounds(layerd.getX()+10, layerd.getY()+10+countery, 250, 242);
                        Image myImg=im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
                        ImageIcon newim=new ImageIcon(myImg);
                        label.setIcon(newim);
                        String elem=element[i];
                        /////////////////////////////////////////////////PUT INFO AND PRICE/////////////////////////////////
                        String info=myRs.getString("Info");
                        String price=myRs.getString("Price");
                        int Id=myRs.getInt("Id");
                        String Comapnyname=super.company_name;
                        JButton b1=new JButton("Modfiy");
                        counterx+=label.getHeight();
                        JTextArea t1=new JTextArea();
                        JTextArea t2=new JTextArea();
                        t1.setText(info);
                        t1.setBounds(label.getX()+label.getWidth()+10, label.getY(), 2000, 50);
                        t2.setText("Price:"+price+" EGP");
                        t2.setBounds(label.getX()+label.getWidth()+10, label.getY()+60, 2000, 50);
                        b1.setBounds(label.getX()+label.getWidth()+10,label.getY()+180,150,50);
                        JButton b2=new JButton("Save");
                        b2.setBounds(label.getX()+label.getWidth()+300,label.getY()+180,150,50);
                        p1.add(b2);
                        b1.addActionListener(new ActionListener()
                        {
                            
                          public void actionPerformed (ActionEvent e) {    
                                try {
                                    Company c3=new Company();
                                    String sql="delete from "+elem+" where Id="+Id;
                                    int rowAffected=mystamt.executeUpdate(sql);
                                    System.out.println(rowAffected);
                                    t1.setText("Remove this text then insert your product info");
                                    t2.setText("Remove this text then insert your product price the press sava");
                                    InputStream in;
                                    final JFileChooser fileChooser = new JFileChooser();
                                    String tx3;
                                    //JOptionPane.showMessageDialog(layerd,fileChooser,"Choosee your icon",4);
                                    //JOptionPane.showMessageDialog(fileChooser, "Choose the icon");
                                    JOptionPane.showInputDialog(fileChooser);
                                    tx3=JOptionPane.showInputDialog("Enter your price symbol");
                                    in = new FileInputStream(fileChooser.getSelectedFile().getPath());
                                    c3.save_button_action(b2, elem, t1, t2,in,tx3);
                                    System.out.println(fileChooser.getSelectedFile().getPath());
                                } catch (SQLException ex) {
                                    Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (FileNotFoundException ex) {
                                  Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
                              }
                                    }
                        });
                        
                       
                        t1.setBackground(Color.WHITE);
                        t2.setBackground(Color.WHITE);
                        Font font = new Font(Font.SERIF, Font.BOLD, 20);
                        t1.setFont(font);
                        t1.setBorder(BorderFactory.createEmptyBorder());
                        t2.setFont(font);
                        t2.setBorder(BorderFactory.createEmptyBorder());
                        p1.add(b1);
                        p1.add(t1);
                        p1.add(t2);
                        countery+=252;
                        p1.setPreferredSize(new Dimension(layerd.getX()+countery, layerd.getY()+countery+100));
                        p1.add(label);
        }}
        }
        }
            catch (SQLException ex) {
            Logger.getLogger(semiFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
     
     
     private void save_button_action(JButton b2,String elem,JTextArea t1,JTextArea t2,InputStream in,String tx3){
           b2.addActionListener(new ActionListener(){ 
                                        public void actionPerformed (ActionEvent e) {
                                                Company c1=new Company();
                                                System.out.println(elem);
                                                String tx1= t1.getText();
                                                String tx2 =t2.getText();
                                                c1.Add_products(tx1, tx2,tx3, in, elem);
                                            
                                        }});
     
     
     }
     
    
}
