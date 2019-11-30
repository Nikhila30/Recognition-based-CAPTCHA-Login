package cns_mini;

import java.sql.*;
import java.util.*;


public class Database  {
	private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  final private String host = "";
	  final private String user = "root";
	  final private String passwd = "password";
	
	Database() throws ClassNotFoundException, SQLException
	{
		 Class.forName("com.mysql.jdbc.Driver");
		 connect = DriverManager.getConnection("jdbc:mysql://localhost/db?"+ "user=" + user + "&password=" + passwd );
		 statement = connect.createStatement();
		 
	}
	boolean check(String uname) throws SQLException
	{
		preparedStatement = connect.prepareStatement("SELECT username from db.details");
		resultSet = preparedStatement.executeQuery();
		List<String> list = new ArrayList<>();
		while(resultSet.next())
			list.add(resultSet.getString("username"));
		if(list.contains(uname))
			return true;
		return false;
				
	}
	public List<String> retrievePwd(String uname) throws SQLException {
		preparedStatement = connect.prepareStatement("SELECT fname,remark,passi1,passi2,passi3,passn1,passn2,passn3 from db.details WHERE username = ? ;");
		preparedStatement.setString(1, uname);
		resultSet = preparedStatement.executeQuery();
		List<String> pwd = new ArrayList<>();
		resultSet.next();
		pwd.add(resultSet.getString("remark"));
		pwd.add(AES.decrypt(resultSet.getString("passi1")));
		pwd.add(AES.decrypt(resultSet.getString("passi2")));
		pwd.add(AES.decrypt(resultSet.getString("passi3")));
		pwd.add(AES.decrypt(resultSet.getString("passn1")));
		pwd.add(AES.decrypt(resultSet.getString("passn2")));
		pwd.add(AES.decrypt(resultSet.getString("passn3")));
		pwd.add(resultSet.getString("fname"));
		return pwd;
		
	}
	public void add(List<String> det, List<String> pwd) throws SQLException {
    preparedStatement = connect.prepareStatement("insert into  db.details values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
    preparedStatement.setString(1, det.get(0));
    preparedStatement.setString(2, det.get(1));
    preparedStatement.setString(3, det.get(2));
    preparedStatement.setString(4, det.get(3));
    preparedStatement.setString(5, det.get(4));
    preparedStatement.setString(6, det.get(5));
    preparedStatement.setString(13, pwd.get(0));
    preparedStatement.setString(7, pwd.get(1));
    preparedStatement.setString(8, pwd.get(2));
    preparedStatement.setString(9, pwd.get(3));
    preparedStatement.setString(10, pwd.get(4));
    preparedStatement.setString(11, pwd.get(5));
    preparedStatement.setString(12, pwd.get(6));    
    preparedStatement.executeUpdate();
    System.out.println("Added successfully");
	}
	public void update(List<String> det, List<String> pwd) throws SQLException {
		System.out.println(pwd);
		System.out.println(det);
		preparedStatement = connect.prepareStatement("update db.details set passi1=?,passn1=? where username = ? ;");
		preparedStatement.setString(1, pwd.get(0));
	    preparedStatement.setString(2, pwd.get(1));
	    preparedStatement.setString(3,det.get(0));
	    preparedStatement.executeUpdate();
	    preparedStatement = connect.prepareStatement("update db.details set passi2=?,passn2=? where username = ? ;");
	    preparedStatement.setString(1, pwd.get(2));
	    preparedStatement.setString(2, pwd.get(3));
	    preparedStatement.setString(3,det.get(0));
	    preparedStatement.executeUpdate();
	    preparedStatement = connect.prepareStatement("update db.details set passi3=?,passn3=? where username = ? ;");
	    preparedStatement.setString(1, pwd.get(4));
	    preparedStatement.setString(2, pwd.get(5));  
	    preparedStatement.setString(3,det.get(0));
	    preparedStatement.executeUpdate();
	}

}
