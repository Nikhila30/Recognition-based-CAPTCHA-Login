package cns_mini;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

public class Open extends JFrame implements ActionListener{

	Database d;
	List<String> de;
	JButton b,b1;
	public Open(List<String> det) throws ClassNotFoundException, SQLException {
		d = new Database();
		JLabel l = new JLabel("Welcome "+det.get(4));
		Font myFont = new Font("Serif",Font.PLAIN,22);
		l.setFont (myFont);
		 b = new JButton("Update Password");
		 b1 = new JButton("Logout");
		setLayout(null);
		l.setBounds(50,50,500,30);
		b.setBounds(200,100,100,30);
		b1.setBounds(310,100,100,30);
		b.addActionListener(this);
		b1.addActionListener(this);
		setSize(500,500);
		add(l); add(b); add(b1);
		setTitle("Open Page");
		de =det;
		//System.out.println(de);
		setVisible(true);
		
	
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == b) {
			String name = JOptionPane.showInputDialog(this, "Enter the 3 idetifiers", null);
		//	System.out.println(de.get(0)+de.get(1)+de.get(2)+name+"A");
			if(!name.equals(de.get(0)+de.get(1)+de.get(2)))
			{
				JOptionPane.showMessageDialog(null,"Invalid identifiers. Updation Failed","Message",1);
			}
			else
			{
				 try {
					Reg2 r = new Reg2(de.subList(3,4),0);
					r.createFrame();
					//r.setVisible(false);
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else
		{
			setVisible(false);
		}
		
	}

}
