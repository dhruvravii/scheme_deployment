import java.sql.*;

import javax.swing.JOptionPane;
public class SchemeStatistics {
	public static String count_eligible(Connection con, PreparedStatement pst, ResultSet rs, String scheme_id)
	{
		con = MySQLConnection.connectDB();
		String sql = "SELECT COUNT(*) FROM is_eligible where scheme_id = ?";
		String count = new String();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,  scheme_id);
			rs = pst.executeQuery();
			rs.next();
			count = rs.getString(1);
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return count;
	}
	
	public static String count_beneficiaries(Connection con, PreparedStatement pst, ResultSet rs, String scheme_id)
	{
		con = MySQLConnection.connectDB();
		String sql = "SELECT COUNT(*) FROM is_availing where scheme_id = ?";
		String count = new String();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,  scheme_id);
			rs = pst.executeQuery();
			rs.next();
			count = rs.getString(1);
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return count;
	}
	
	public static String penetration_rate(Connection con, PreparedStatement pst, ResultSet rs, String scheme_id)
	{
		con = MySQLConnection.connectDB();
		String sql1 = "SELECT COUNT(*) FROM is_eligible where scheme_id = ?";
		String sql2 = "SELECT COUNT(*) FROM is_availing where scheme_id = ?";
		String pr = new String();
		double count_elgb;
		double count_bene;
		try {
			pst = con.prepareStatement(sql1);
			pst.setString(1,  scheme_id);
			rs = pst.executeQuery();
			rs.next();
			count_elgb = Double.parseDouble(rs.getString(1));
			pst = con.prepareStatement(sql2);
			pst.setString(1, scheme_id);
			rs = pst.executeQuery();
			rs.next();
			count_bene = Double.parseDouble(rs.getString(1));
			pr = Double.toString(count_bene/ count_elgb);
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return pr;
	}
	
	public static String estimated_cost(Connection con, PreparedStatement pst, ResultSet rs, String scheme_id, String cpc)
	{
		con = MySQLConnection.connectDB();
		String sql = "SELECT COUNT(*) FROM is_availing where scheme_id = ?";
		String estc = new String();
		int count_bene;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,  scheme_id);
			rs = pst.executeQuery();
			rs.next();
			count_bene = Integer.parseInt(rs.getString(1));
			estc = Double.toString(count_bene * Double.parseDouble(cpc));
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return estc;	
	}
}
