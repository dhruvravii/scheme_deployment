import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EligibleSchemes {

	private JFrame frame;
	private JTable table;
	static String aadhar_id;
	DefaultTableModel model = new DefaultTableModel();
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	/**
	 * Launch the application.
	 */
	public static void EligibleSchemes() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EligibleSchemes window = new EligibleSchemes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EligibleSchemes() {
		initialize();
		Object col[] = {"Scheme ID", "Scheme", "Sector", "Monetary", "Individual/Household", "Managing Govt."};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		
		updateTable();
	}
	
public void updateTable() {
		
		con = MySQLConnection.connectDB();
		
		if(con != null) 
		{
			String sql = "SELECT schemes.scheme_id, scheme_name, sector, monetary, scheme_type, gov_name FROM schemes "
					+ "JOIN is_eligible ON is_eligible.scheme_id=schemes.scheme_id "
					+ "JOIN citizen ON is_eligible.aadhar_id=citizen.aadhar_id "
					+ "JOIN managed_by ON schemes.scheme_id=managed_by.scheme_id " 
					+ "WHERE is_eligible.aadhar_id=? AND (managed_by.gov_name=citizen.state OR managed_by.gov_name='Central') "
					+ "AND schemes.scheme_id NOT IN (SELECT scheme_id FROM is_availing WHERE aadhar_id= ?)";
			
		
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1,  CitizenAadhar.aadhar_id);
				pst.setString(2,  CitizenAadhar.aadhar_id);
				System.out.println("CitizenAadhar ID is "+CitizenAadhar.aadhar_id);				
				rs = pst.executeQuery();
				Object[] columnData = new Object[6];
				
				model.setRowCount(0);
				while(rs.next())
				{
					columnData[0] = rs.getString(1);
					columnData[1] = rs.getString(2);
					columnData[2] = rs.getString(3);
					columnData[3] = rs.getString(4);
					columnData[4] = rs.getString(5);
					columnData[5] = rs.getString(6);
//					System.out.println(columnData[0]);
//					System.out.println(columnData[1]);
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
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1470, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 1012, 731);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1012, 731);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Scheme_ID", "Scheme", "Sector", "Monetary", "Individual/Household", "Managing Government"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(63);
		table.getColumnModel().getColumn(1).setPreferredWidth(146);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(104);
		table.getColumnModel().getColumn(5).setPreferredWidth(154);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Apply for a New Scheme");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(1032, 295, 373, 110);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSchemeEntry cse=new CreateSchemeEntry();
				cse.CreateSchemeEntry();
			}
		});
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		btnRefresh.setBounds(1361, 10, 85, 21);
		frame.getContentPane().add(btnRefresh);
		
	}
	
}
