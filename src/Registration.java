package cns_mini;


import javax.swing.*;  
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;  
public class Registration extends JFrame implements ActionListener   
{  
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;  
    JTextField tf1, tf2,tf3,tf4, tf5, tf6, tf7;  
    JButton btn1, btn2;  
   // JPasswordField p1, p2;  
    Registration()  
    {  
        setVisible(true);  
        setSize(1000, 1000);  
        setLayout(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setTitle("Registration Form in Java");  
        l1 = new JLabel("Fill the details");  
        l1.setForeground(Color.blue);  
        l1.setFont(new Font("Serif", Font.BOLD, 20));  
        l2 = new JLabel("First Name:");  
        l3 = new JLabel("Last Name:");  
        l4 = new JLabel("Username:");  
        l5 = new JLabel("Email_id:");  
        l8 = new JLabel("Date of Birth:");
        l6 = new JLabel("Phone No:");   
        tf1 = new JTextField();  
        tf2 = new JTextField();  
        tf3 = new JTextField();  
        tf4 = new JTextField();  
        tf5 = new JTextField();  
        tf6 = new JTextField();  
       // tf7 = new JTextField();  
        btn1 = new JButton("Next");  
        btn2 = new JButton("Clear");  
        btn1.addActionListener(this);  
        btn2.addActionListener(this);  
        l1.setBounds(100, 30, 400, 30);  
        l2.setBounds(80, 70, 200, 30);  
        l3.setBounds(80, 110, 200, 30);  
        l4.setBounds(80, 150, 200, 30);  
        l5.setBounds(80, 190, 200, 30);  
        l8.setBounds(80, 230, 200, 30);  
        l6.setBounds(80, 270, 200, 30);  
       // l8.setBounds(80, 310, 200, 30);  
        tf1.setBounds(300, 70, 200, 30);  
        tf2.setBounds(300, 110, 200, 30);  
        tf3.setBounds(300, 150, 200, 30);  
        tf4.setBounds(300, 190, 200, 30);//email  
        tf5.setBounds(300, 230, 200, 30);  
        tf6.setBounds(300, 270, 200, 30);  
       // tf7.setBounds(300, 310, 200, 30);  
        btn1.setBounds(170, 350, 100, 30);  
        btn2.setBounds(350, 350, 100, 30);  
        add(l1);  
        add(l2);  
        add(tf1);  
        add(l3);  
        add(tf2);  
        add(l4);  
        add(tf3);  
        add(l5);  
        add(tf4);  
        add(l8);  
        add(tf5); 
        add(l6); add(tf6);
        add(btn1);  
        add(btn2);  
    }  
    public void actionPerformed(ActionEvent e)   
    {  
        if (e.getSource() == btn1)  
         {   
        	if(!isValid(tf4.getText()))
        	{
        		JOptionPane.showMessageDialog(null,"Enter valid email id","Message",1);
        	}
        	else if(!isValid2(tf6.getText()))
        	{
        		JOptionPane.showMessageDialog(null,"Enter valid phone number","Message",1);
        	}
        	else
        	{
            List<String> det = new ArrayList<>();
            det.add(tf1.getText());  
            det.add(tf2.getText());
            det.add(tf3.getText());
            det.add(tf4.getText());
            det.add(tf5.getText());
            det.add(tf6.getText());
            this.setVisible(false);
           Reg2 r2;
		try {
			r2 = new Reg2(det,1);
			r2.createFrame();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        	}
          }   
          else  
          {  
            tf1.setText("");  
            tf2.setText("");  
            tf3.setText("");  
            tf4.setText("");  
            tf5.setText("");  
            tf6.setText("");  
           // tf7.setText("");  
          }  
    }
	private boolean isValid2(String text) {
		 Pattern p = Pattern.compile("[7-9][0-9]{9}"); 
		// Matcher m = p.matcher(text); 
	        return p.matcher(text).matches();  
	}
	private boolean isValid(String text) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                "[a-zA-Z0-9_+&*-]+)*@" + 
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                "A-Z]{2,7}$"; 
                  
Pattern pat = Pattern.compile(emailRegex); 
if (text == null) 
return false; 
return pat.matcher(text).matches(); 
	} 
}  