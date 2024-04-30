import java.sql.*;
import javax.swing.*;
public class MySQLConnection {
	public static Connection connectDB()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scheme_dep", "root", "akshit123");
//			JOptionPane.showMessageDialog(null, "Connection made!");
			return con;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error in establishing connection!");
			return null;
		}
	  }

}
