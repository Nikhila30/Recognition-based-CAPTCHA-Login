package cns_mini;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class Login extends JFrame implements ActionListener 
{
	 JLabel l1;
	 JTextField t1;
	 Database d;
	 JButton b1,b2;
	 Login() throws ClassNotFoundException, SQLException
	 {
	   //setSize(700,700);  
	   d = new Database();
	   setLayout(null);  
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	   setTitle("Login Form ");  
	   l1 = new JLabel("Username");
	   t1 = new JTextField();
	   b1 = new JButton("Register");
	   b2 = new JButton("Next");
	   b1.addActionListener(this); 
	   b2.addActionListener(this); 
	   l1.setBounds(100, 30, 400, 30);
	   t1.setBounds(300, 30, 200, 30);
	   b1.setBounds(100,70,100,30);
	   b2.setBounds(300,70,100,30);
	   add(l1);
	   add(t1);
	   add(b1);
	   add(b2);
	 }
	
	 public static void main(String args[]) throws ClassNotFoundException, SQLException  
	    {  
	        Login l = new Login();  
	        l.setSize(600,300);
	        l.setVisible(true);
	    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==b1)
		{
			 Registration reg = new Registration();
			 reg.setVisible(true);
			 
		}	
		else
		{
			String uname = t1.getText();
			t1.setText("");
			 try {
				if(d.check(uname))
				 {
					 List<String> n = d.retrievePwd(uname);
					 System.out.println(n);
					 new Login2(n);
					 
				 }
				 else {
					 System.out.println("Enter the valid username");
					  JOptionPane.showMessageDialog(this,"Username doesnot exist","Error",JOptionPane.ERROR_MESSAGE);
				 }
			} catch (HeadlessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		
	}
}
