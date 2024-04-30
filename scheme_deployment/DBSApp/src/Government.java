import java.sql.*;

import javax.swing.JOptionPane;

public class Government 
{
	public static String[] display_government_details(Connection con, PreparedStatement pst, ResultSet rs, String gov_name)
	{
		con = MySQLConnection.connectDB();
		String sql = "select gov.no_of_residents, gov.no_of_districts, count(managed_by.scheme_id) FROM gov\r\n"
				+ "JOIN managed_by ON gov.gov_name=managed_by.gov_name\r\n"
				+ "JOIN gov_stats ON gov.gov_name=gov_stats.gov_name where managed_by.gov_name = ?";
		String sql1 = "Select no_of_residents, no_of_districts from gov where gov_name = ?";
//		String sql = "SELECT citizen.aadhar_id, citizen.name, citizen.state, count(is_availing.scheme_id), sum(scheme_stats.per_capita_cost) FROM (citizen " 
//				+ "JOIN is_availing ON citizen.aadhar_id=is_availing.aadhar_id "
//				+ "JOIN schemes ON is_availing.scheme_id=schemes.scheme_id "
//				+ "JOIN scheme_stats ON schemes.scheme_id=scheme_stats.scheme_id) where citizen.aadhar_id = ?";
		String[] entryData = new String[8];
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,  gov_name);
			rs = pst.executeQuery();
			rs.next();
			if(rs.getString(1) == null)
			{
				pst = con.prepareStatement(sql1);
				pst.setString(1, gov_name);
				rs = pst.executeQuery();
				rs.next();
				entryData[0] = gov_name;
				entryData[1] = rs.getString(1);
				entryData[2] = rs.getString(2);
				entryData[3] = "0";
			}
			else
			{
			entryData[0] = gov_name;
			entryData[1] = rs.getString(1);
			entryData[2] = rs.getString(2);
			entryData[3] = rs.getString(3);
			}
//			entryData[3] = rs.getString("count(is_availing.scheme_id)");
			sql = "select managed_by.scheme_id, scheme_stats.per_capita_cost from managed_by join scheme_stats on scheme_stats.scheme_id = managed_by.scheme_id where gov_name = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1,  gov_name);
			double total_cost = 0;
			int count_availing;
			int cpc;
			rs = pst.executeQuery();
			if(rs.next() == false)
			{
				System.out.println("Empty result set");
			}
			else
			{
				do {
					count_availing = Integer.parseInt(SchemeStatistics.count_beneficiaries(con, pst, rs, rs.getString(1)));
					cpc = Integer.parseInt(rs.getString(2));
					total_cost = total_cost + count_availing*cpc;
				}while(rs.next());
			}
			rs.close();
			entryData[4] = Double.toString(total_cost);
			sql = "Select * from gov_stats where gov_name = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, gov_name);
			rs = pst.executeQuery();
			if(rs.next() == false)
			{
				System.out.println("Empty result set");
			}
			else
			{
			entryData[5] = rs.getString(2);
			entryData[6] = Double.toString(Double.parseDouble(entryData[5]) - Double.parseDouble(entryData[4]));
			}
			System.out.println(entryData[0]);
			System.out.println(entryData[1]);
			System.out.println(entryData[2]);
			System.out.println(entryData[3]);
			System.out.println(entryData[4]);
			System.out.println(entryData[5]);
			System.out.println(entryData[6]);
			System.out.println("lllll" + rs.getString(1));
//			System.out.println(entryData[4]);
			rs.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e);
		}
		return entryData;
	}

}
