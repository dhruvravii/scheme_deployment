import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class GovernmentSchemeStatistics {

	private JFrame frame;
	DefaultTableModel model = new DefaultTableModel();
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void GovernmentSchemeStatistics() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GovernmentSchemeStatistics window = new GovernmentSchemeStatistics();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public void updateTable() {
		
		con = MySQLConnection.connectDB();
		
		if(con != null) 
		{
			String sql = "SELECT managed_by.scheme_id, schemes.scheme_name, scheme_stats.per_capita_cost from managed_by join scheme_stats on managed_by.scheme_id = scheme_stats.scheme_id"+
						" join schemes on scheme_stats.scheme_id=schemes.scheme_id where gov_name = ?";
			
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1,  GovernmentPortalScreen.gov_name);			
				rs = pst.executeQuery();
				Object[] columnData = new Object[7];
				
				model.setRowCount(0);
				while(rs.next())
				{
					columnData[0] = rs.getString(1);
					columnData[1] = rs.getString(2);
					columnData[2] = SchemeStatistics.count_beneficiaries(con, pst, rs, rs.getString(1));
					columnData[3] = SchemeStatistics.count_eligible(con, pst, rs, rs.getString(1));
					columnData[4] = SchemeStatistics.penetration_rate(con, pst, rs, rs.getString(1));
					columnData[5] = rs.getString(3);
					columnData[6] = SchemeStatistics.estimated_cost(con, pst, rs, rs.getString(1), rs.getString(3));
					System.out.println(columnData[0]);
					System.out.println(columnData[1]);
//					
					model.addRow(columnData);
				}
				rs.close();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error in updating table");
			}
		}
	}

	/**
	 * Create the application.
	 */
	public GovernmentSchemeStatistics() {
		initialize();
		
		Object col[] = {"scheme_id", "scheme_name", "#beneficiaries", "#eligible", "penetration_ratio", "cost_per_capita", "estimated_cost"};
		model.setColumnIdentifiers(col);
		table_1.setModel(model);
		
//		String scheme_id = "1";
//		EligibilityCriteria.SchemeEligible(con, pst, scheme_id);
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1470, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(42, 65, 1352, 604);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1352, 604);
		panel.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"scheme_id", "scheme_name", "#beneficiaries", "#eligible", "penetration_ratio", "cost_per_capita", "estimated_cost"
				}
			));
		scrollPane.setViewportView(table_1);
		
		
	}

}