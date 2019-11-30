package cns_mini;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Reg2 implements ActionListener {
	public int count=1,n1;
	
	class ImageComponent extends Component   {

	      BufferedImage img;

	      public void paint(Graphics g) {
	         g.drawImage(img, 0, 0, null);
	      }

	      public ImageComponent(String path) {
	         try {
	            img = ImageIO.read(new File(path));
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }

	      public Dimension getPreferredSize() {
	         if (img == null) {
	            return new Dimension(100,100);
	         } else {
	            return new Dimension(img.getWidth(), img.getHeight());
	         }
	      }     
	}
	int num=5;
	int totalImage=25;
	//public ArrayList<String> fileNames;
	JButton b[];
	TextField tf[];
	//Panel p[];
	Frame mf;
	Database d;
	List<String> det;
	List<String> img;
	List<String> pwd;
	public Reg2(List<String> det2,int a) throws ClassNotFoundException, SQLException {
		img = new ArrayList<String>();
		pwd = new ArrayList<String>();
		b=new JButton[25];
	//	tf=new TextField[25];
		//p=new Panel[25];
		det = det2;
		d = new Database();
		n1=a;
		//listFilesForFolder(new File("/home/ubuntu/Desktop/images"));
		
	}
	
	public void createFrame() throws IOException {
		mf=new Frame("Image panel");
		mf.setSize(1000, 1000);
		mf.addWindowListener(new WindowAdapter() {
	         public void windowClosing(WindowEvent windowEvent){
	            System.exit(0);
	         }        
	    });  
		GridLayout g = new GridLayout(4,4);
		mf.setLayout(g);
		List<String> rd = genRand();
		for(int i=0;i<rd.size();i++) {
			img.add(rd.get(i)+".jpeg");
			BufferedImage buttonIcon = ImageIO.read(new File("/home/ubuntu/Desktop/images/"+rd.get(i)+".jpeg"));
			b[i]=new JButton(new ImageIcon(buttonIcon));
			b[i].addActionListener(this);
		   // tf[i]=new TextField();
			mf.add(b[i]);
		}
		mf.setLayout(g);
		mf.setVisible(true);
		pwd.add(String.join(",",rd));
	}
	
	private List<String> genRand() {
		List<String> rand = new ArrayList<>();
		for(int i=0;i<16;i++)
		{
			String n = String.valueOf((int) (Math.random()*(50)+1));
			if(rand.contains(n))
			{
				i--;
				continue;
			}
			else
				rand.add(n);
		}
		return rand;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			int n = Arrays.asList(b).indexOf(e.getSource());
			pwd.add(AES.encrypt(img.get(n)));
			 String name = JOptionPane.showInputDialog(mf, "Enter idetifier", null);
			 System.out.println(AES.encrypt(name));
			 pwd.add(AES.encrypt(name));
			count++;
			System.out.println(n+"ABCDEF");
			if(count>3 && n1==1)
			{
				mf.setVisible(false);
				try {
					d.add(det,pwd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				count=1;
				/*JDialog d1 = new JDialog(mf, "Dialog Box"); 
	            JLabel l = new JLabel("Registration Sucessful"); 
	        	Font myFont = new Font("Serif",Font.PLAIN,18);
				l.setFont (myFont);
	            d1.add(l); 
	            d1.setSize(300, 200); 
	            d1.setVisible(true);*/
				JOptionPane.showMessageDialog(null,"Registration Successful","Message",1);
				
			}
			else if(count>3 && n1==0)
			{
				try {
					d.update(det,pwd);
					JOptionPane.showMessageDialog(null,"Updation Successful","Message",1);
					count = 1;
					mf.setVisible(false);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		
	}
}
