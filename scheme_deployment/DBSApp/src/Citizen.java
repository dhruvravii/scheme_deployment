import java.sql.*;

import javax.swing.JOptionPane;

public class Citizen {
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public static String[] display_citizen_details(Connection con, PreparedStatement pst, ResultSet rs, String aadhar_id)
	{
		con = MySQLConnection.connectDB();
		String sql1 = "SELECT citizen.aadhar_id, citizen.name, citizen.state, count(is_eligible.scheme_id), sum(scheme_stats.per_capita_cost) FROM (citizen JOIN is_eligible ON citizen.aadhar_id=is_eligible.aadhar_id  JOIN schemes ON is_eligible.scheme_id=schemes.scheme_id JOIN scheme_stats ON schemes.scheme_id=scheme_stats.scheme_id) where citizen.aadhar_id = ?";
		String sql2 = "SELECT citizen.aadhar_id, citizen.name, citizen.state, count(is_availing.scheme_id), sum(scheme_stats.per_capita_cost) FROM (citizen " 
				+ "JOIN is_availing ON citizen.aadhar_id=is_availing.aadhar_id "		
				+ "JOIN schemes ON is_availing.scheme_id=schemes.scheme_id "
				+ "JOIN scheme_stats ON schemes.scheme_id=scheme_stats.scheme_id) where citizen.aadhar_id = ?";
		String[] entryData = new String[5];
		try {
			pst = con.prepareStatement(sql2);
			pst.setString(1,  aadhar_id);
			rs = pst.executeQuery();
			rs.next();
			if(rs.getString(1)==null) {
				pst = con.prepareStatement(sql1);
				pst.setString(1,  aadhar_id);
				rs = pst.executeQuery();
				rs.next();
				entryData[3]="0";
				entryData[4]="0";
			}
			else {
				entryData[3]=rs.getString("count(is_availing.scheme_id)");
				entryData[4]=rs.getString("sum(scheme_stats.per_capita_cost)");
			}
			entryData[0] = rs.getString(1);
			entryData[1] = rs.getString(2);
			entryData[2] = rs.getString(3);
//			entryData[3] = rs.getString(4);
//			entryData[3] = rs.getString("count(is_availing.scheme_id)");
//			entryData[4] = rs.getString(5);
			System.out.println(entryData[0]);
			System.out.println(entryData[1]);
			System.out.println(entryData[2]);
			System.out.println(entryData[3]);
			System.out.println(entryData[4]);
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return entryData;
	}
}
