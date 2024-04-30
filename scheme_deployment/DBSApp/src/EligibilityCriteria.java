import java.sql.*;

import javax.swing.JOptionPane;
public class EligibilityCriteria {
	
	public static  void Scheme1Eligible(Connection con, PreparedStatement pst, String scheme_id) 
	{
		con = MySQLConnection.connectDB();
		String sql = "insert into is_eligible(aadhar_id, scheme_id) select aadhar_id, ? from citizen where bank_account = 1";
		try
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, scheme_id);
			pst.execute();
			pst.close();	
		}
		 catch (Exception e1) 
		{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	public static  void Scheme2Eligible(Connection con, PreparedStatement pst, String scheme_id) 
	{
		con = MySQLConnection.connectDB();
		String sql = "insert into is_eligible(aadhar_id, scheme_id) select aadhar_id, ? from citizen where bank_account = 1 and emp_type='Unemployed'";
		try
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, scheme_id);
			pst.execute();
			pst.close();	
		}
		 catch (Exception e1) 
		{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	
	public static  void Scheme3Eligible(Connection con, PreparedStatement pst, String scheme_id) 
	{
		con = MySQLConnection.connectDB();
		String sql = "insert into is_eligible(aadhar_id, scheme_id) select aadhar_id, ? from citizen where area = 'Rural' and household_income<800000";
		try
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, scheme_id);
			pst.execute();
			pst.close();	
		}
		 catch (Exception e1) 
		{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	
	public static  void Scheme4Eligible(Connection con, PreparedStatement pst, String scheme_id) 
	{
		con = MySQLConnection.connectDB();
		String sql = "insert into is_eligible(aadhar_id, scheme_id) select aadhar_id, ? from citizen where gender='F' and emp_type='Unemployed' and state='Karnataka'";
		try
		{
			pst = con.prepareStatement(sql);
			pst.setString(1, scheme_id);
			pst.execute();
			pst.close();	
		}
		 catch (Exception e1) 
		{
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1);
		}
	}
	
	public static int AadharEligible(Connection con, PreparedStatement pst, ResultSet rs, String aadhar_id)
	{
		con = MySQLConnection.connectDB();
		String sql = "select aadhar_id from citizen where aadhar_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, aadhar_id);
			rs = pst.executeQuery();
			rs.next();
			if(aadhar_id.equals(rs.getString(1)))
			{
				return 1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return 0;
		
	}
	
	public static int GovernmentNameEligible(Connection con, PreparedStatement pst, ResultSet rs, String gov_name)
	{
		con = MySQLConnection.connectDB();
		String sql = "select gov_name from gov where gov_name = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, gov_name);
			rs = pst.executeQuery();
			rs.next();
			if(gov_name.equals(rs.getString(1)))
			{
				return 1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return 0;
		
	}
}
