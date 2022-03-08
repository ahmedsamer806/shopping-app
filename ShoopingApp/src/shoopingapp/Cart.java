package shoopingapp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Cart {
    double total_cost=0;
    double rt=0;
    static String price;
    static String oldprice="";
    static String []prices;
    static double cost=0;
    static void view_product(JPanel pane,String user, JScrollPane layerd)
    {
        try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
                Statement myst= conn.createStatement();
                ResultSet myr=myst.executeQuery("select * from cart");
                int x=0;
                int y=0;   
                System.out.println("in cart class");
                int counter=0;
                while(myr.next())
                {
                    String u=myr.getString("UserName");
                    
                    
                    if(u.equals(user))
                    {
                     
                    String Price=myr.getString("Price"); 
                    //System.out.println(Price);
                    price=Price;
                    if(price.contains("EGP")){price=price.replaceAll("EGP", "");price=price.strip();}
                    price=price.replaceAll(",", "");
                    price=price.concat(oldprice);
                    oldprice="aa"+price;
                    System.out.println("pricehereeeee"+price);
                    byte []image=myr.getBytes("Image");
                    ImageIcon im=new ImageIcon(image);
                    Image img=im.getImage();
                    JLabel label=new JLabel();
//                    label.setLocation(10, 10+y);    
//                    label.setBounds(10, 15+y, 216, 250);
                    label.setBounds(10, 10+y, 250, 242);
                    Image myimg=img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon i=new ImageIcon(myimg);
                    label.setIcon(i);
                    pane.add(label);
                    String info=myr.getString("Info");
                    JButton b=new JButton("Remove");
                    b.addActionListener(new ActionListener()
                        { 
                          public void actionPerformed (ActionEvent e) {
                             Delete_product(user, info);
                          }
                        }
                        );
                    x+=label.getHeight();
                    JTextArea a1=new JTextArea();
                    JTextArea a2=new JTextArea();
                    a1.enableInputMethods(false);
                    a2.enableInputMethods(false);
                    a1.setBounds(label.getWidth()+10, label.getY(), 2000, 50);
                    a2.setBounds(label.getWidth()+10, label.getY()+60, 2000, 50);
                    b.setBounds(label.getWidth()+10,label.getY()+180,150,50);
//                    a1.setLocation(300,50+y);
//                    a1.setBounds(300, 50+y, 500, 20);
//                    a2.setLocation(300,80+y);
//                    a2.setBounds(300, 80+y, 80, 20);
//                    b.setLocation(300,120+y);
//                    b.setBounds(300, 120+y, 100, 20);
                    a1.setText(info);
                    a2.setText(Price);
                    Font font = new Font(Font.SERIF, Font.BOLD, 20);
                        a1.setFont(font);
                        a1.setBorder(BorderFactory.createEmptyBorder());
                        a2.setFont(font);
                        a2.setBorder(BorderFactory.createEmptyBorder());
                    y+=270;
                   //pane.setPreferredSize(new Dimension(s.getX(),s.getY()+y));
                    pane.add(a1);
                    pane.add(a2);
                    pane.add(b);

                    
                    
                    //System.out.println(oldprice);
                    
                    }
                    
                   
                    
            }
                
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
    }
    static void Delete_product(String user, String inf)
    {
        try{
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/datausage","root","root99");
            Statement myst= conn.createStatement();
            myst.executeUpdate("delete from cart where UserName='"+user+"' and Info='"+inf+"'");
        }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    public double calaculate_cost(){
        
         prices=price.split("aa");
         if(prices.length==0){return 0.0;}
         else {
             for(int i=0;i<prices.length;i++){
                 rt=Double.parseDouble(prices[i]);
                 cost=cost+rt;
                 
         }

               return cost;
    
         }
    
}
}
