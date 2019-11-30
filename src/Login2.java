package cns_mini;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Login2 extends JFrame implements ActionListener {
	
	JPanel f = new JPanel(new GridLayout(4,4,0,0));
	JPanel f1 = new JPanel();
	JLabel l = new JLabel("Password");
	JPasswordField textField = new JPasswordField();
	//textField.setBounds(100,450,30,30);
	JButton ok = new JButton("OK");
	JButton forgot = new JButton("Forgot Password");
	String Pass;
	List<String> de;
	int c=0;
	//textField.setBounds(0, 0, 150, 10);

	public Login2(List<String> n) throws IOException {

		super.setTitle("Password Page");
		super.setSize(1010, 1010);
		//super.setBounds(1000,1000,500,500);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		ok.addActionListener(this);
		forgot.addActionListener(this);
		// setting grid layout with rows, cols, hgap, and vgap
		setLayout(null);
		de = n;
		init(n);
	}

	private void init(List<String> n) throws IOException 
	{
		List<String> first = new ArrayList<>(n.subList(1, 7));
		char[] pwd = new char[3];
		List<String> rd =  new ArrayList<String>(Arrays.asList(n.get(0).split(",")));
		Collections.shuffle(rd);
		//System.out.println(rd);
		List<String> c = getAlphaNumericString();
		for(int i=0;i<16;i++) {
			//BufferedImage buttonIcon = ImageIO.read(new File("/home/ubuntu/Desktop/images/"+rd.get(i)));
			JLabel label = new JLabel();	// setting icon
			label.setIcon(new ImageIcon(
					new ImageIcon("/home/ubuntu/Desktop/images/" + rd.get(i)+".jpeg").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
			
			//System.out.println(first+"first");
			int i1 = first.indexOf(rd.get(i)+".jpeg");
			//System.out.println(i1);
			if(i1>-1)
			{
			//System.out.println(i1);
			pwd[i1] = c.get(i).charAt(Integer.parseInt(first.get(3+i1))-1);
			//System.out.println(pwd[i1]);
			//System.out.println(c);
			}
			label.setText(c.get(i));
			label.setVerticalTextPosition(JLabel.BOTTOM);
			label.setHorizontalTextPosition(JLabel.CENTER);
			Font myFont = new Font("Serif",Font.PLAIN,22);
			label.setFont (myFont);

			f.add(label);
		}
		//f.setVisible(true);
		//f1.add(ok);
		f.setBounds(0,0,1000,930);
		f.setVisible(true);
     	add(f);
		//add(f);
		textField.setBounds(300,930,200,40);
		l.setBounds(50,930,200,40);
		l.setFont (l.getFont ().deriveFont (25.0f));
		ok.setBounds(600,930,100,40);
		add(l); add(textField); add(ok);
		Pass = String.valueOf(pwd);
		System.out.println(Pass);
	}
/*	private List<String> genRand(List<String> rand) {
		
		for(int i=0;i<13;i++)
		{
			String n = String.valueOf((int) (Math.random()*(50)+1))+".jpeg";
			if(rand.contains(n))
			{
				i--;
				continue;
			}
			else
				rand.add(n);
		}
		Collections.shuffle(rand);
		return rand;
	}*/

	static List<String> getAlphaNumericString() 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        List<String> cap = new ArrayList<>();
  
        // create StringBuffer size of AlphaNumericString 
        for(int j=0;j<8;j++) {
        StringBuilder sb = new StringBuilder(8); 
  
        for (int i = 0; i < 8; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length()* Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString .charAt(index)); 
        } 
  
         cap.add(sb.toString());
         cap.add(sb.toString());
         }
        Collections.shuffle(cap);
        return cap; 
    }

	@Override
	public void actionPerformed(ActionEvent ae) {
		String uPass = textField.getText();
		if(Pass.equals(uPass)&& c<3)
		{
			/*JDialog d = new JDialog(this, "dialog Box"); 
            JLabel l = new JLabel("Login Successful"); 
            d.add(l); 
            d.setSize(200, 200); 
            d.setVisible(true);*/
			JOptionPane.showMessageDialog(null,"Login Successful","Message",1);
			try {
				new Open(de.subList(3,8));
				setVisible(false);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(c>2)
		{
			/*JDialog d = new JDialog(this, "dialog Box"); 
            JLabel l = new JLabel("No.of tries limit Exceeded!!"); 
            d.add(l); 
            d.setSize(100, 100); 
            d.setVisible(true);*/
			JOptionPane.showMessageDialog(null,"No.of tries Limit Exceeded","Warning",1);
		}
		else
		{
			c++;
			/*JDialog d = new JDialog(this, "dialog Box"); 
            JLabel l = new JLabel("Incorrect Password. Try Again"); 
            d.add(l); 
            d.setSize(250, 200); 
            d.setVisible(true);*/
			JOptionPane.showMessageDialog(null,"Incorrect Password. Try Again","Message",1);
			textField.setText("");
		}
		
	} 
}