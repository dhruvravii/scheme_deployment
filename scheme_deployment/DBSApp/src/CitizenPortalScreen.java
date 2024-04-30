import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.SoftBevelBorder;

public class CitizenPortalScreen {
	DefaultTableModel model = new DefaultTableModel();
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JFrame frame;
	private JTextField txtCountRedeemingSchemes;
	private JTextField txtBenefitReceived;
	private JTextField txtName;
	private JTextField txtAadharNumber;
	private JTextField txtState;
	String entryData[];
	static String aadhar_id;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void CitizenPortal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenPortalScreen window = new CitizenPortalScreen();
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
	public CitizenPortalScreen() {
		initialize();
		Object col[] = {"Scheme ID", "Scheme", "Sector", "Monetary", "Individual/Household", "Managing Govt."};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		JButton btnNewButton = new JButton("Update Details");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDetails ud=new UpdateDetails();
				ud.UpdateDetails();
			}
		});
		btnNewButton.setBounds(73, 679, 204, 53);
		frame.getContentPane().add(btnNewButton);
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
public void updateTable() {
		
		con = MySQLConnection.connectDB();
		
		if(con != null) 
		{
			String sql = "SELECT schemes.scheme_id, scheme_name, sector, monetary, scheme_type, gov_name FROM schemes "
					+ "JOIN is_availing ON is_availing.scheme_id=schemes.scheme_id "
					+ "JOIN managed_by ON schemes.scheme_id=managed_by.scheme_id WHERE is_availing.aadhar_id= ?";
		
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1,  CitizenAadhar.aadhar_id);
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
					System.out.println(columnData[0]);
					System.out.println(columnData[1]);
					
					model.addRow(columnData);
				}
				rs.close();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error in updating table");
			}
		}
	}

	private void initialize() {
//		con = MySQLConnection.connectDB();
		frame = new JFrame();
		frame.setBounds(0, 0, 1470, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		entryData=new String[5];
		entryData=Citizen.display_citizen_details(con, pst, rs, aadhar_id);
		
		JButton checkEligibleSchemes = new JButton("Apply for a Scheme");
		checkEligibleSchemes.setBounds(36, 86, 350, 110);
		checkEligibleSchemes.setFont(new Font("Tahoma", Font.PLAIN, 25));
		checkEligibleSchemes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EligibleSchemes es=new EligibleSchemes();
					es.EligibleSchemes();
				}
			});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(checkEligibleSchemes);
		
		JButton btnRemoveAScheme = new JButton("Remove a Scheme");
		btnRemoveAScheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveAScheme.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRemoveAScheme.setBounds(396, 86, 350, 110);
		frame.getContentPane().add(btnRemoveAScheme);
		btnRemoveAScheme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				DeleteSchemeEntry dse = new DeleteSchemeEntry();
//				dse.DeleteSchemeEntry();
				DefaultTableModel mod = (DefaultTableModel)table.getModel();
				
				con = MySQLConnection.connectDB();
				int row = table.getSelectedRow();
				String aadhar = new String();
				String schemeID = new String();
				if(table.getSelectedRow() == -1)
				{
					if(table.getSelectedRow() == 0)
					{
						JOptionPane.showMessageDialog(null, "No data to be deleted", "Scheme 1", JOptionPane.OK_OPTION);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Select row to be deleted", "Scheme 1", JOptionPane.OK_OPTION);
					}
				}
				else
				{
					aadhar = aadhar_id;
					schemeID = table.getModel().getValueAt(row,0).toString();
					mod.removeRow(table.getSelectedRow());
				}
				String sql = "DELETE FROM is_availing WHERE aadhar_id = ? AND scheme_id = ?";
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1, aadhar);
					pst.setString(2, schemeID);
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Successfully deleted Scheme!");
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBounds(782, 66, 603, 652);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
	
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 6, 583, 636);
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
		
		txtCountRedeemingSchemes = new JTextField();
		txtCountRedeemingSchemes.setBounds(287, 505, 170, 46);
		txtCountRedeemingSchemes.setEditable(false);
		txtCountRedeemingSchemes.setText(entryData[3]);
		frame.getContentPane().add(txtCountRedeemingSchemes);
		txtCountRedeemingSchemes.setColumns(10);
		
		txtBenefitReceived = new JTextField();
		txtBenefitReceived.setBounds(287, 586, 170, 46);
		txtBenefitReceived.setEditable(false);
		txtBenefitReceived.setText(entryData[4]);
		txtBenefitReceived.setColumns(10);
		frame.getContentPane().add(txtBenefitReceived);
		
		txtName = new JTextField();
		txtName.setBounds(287, 261, 170, 46);
		txtName.setEditable(false);
		txtName.setText(entryData[0]);
		txtName.setColumns(10);
		frame.getContentPane().add(txtName);
		
		txtAadharNumber = new JTextField();
		txtAadharNumber.setBounds(287, 339, 170, 46);
		txtAadharNumber.setEditable(false);
		txtAadharNumber.setText(entryData[1]);
		txtAadharNumber.setColumns(10);
		frame.getContentPane().add(txtAadharNumber);
		
		txtState = new JTextField();
		txtState.setBounds(287, 422, 170, 46);
		txtState.setEditable(false);
		txtState.setText(entryData[2]);
		txtState.setColumns(10);
		frame.getContentPane().add(txtState);
		
		JLabel lblName = new JLabel("Name of Beneficiary");
		lblName.setBounds(73, 261, 204, 46);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblName);
		
		JLabel lblAadhar = new JLabel("Aadhar Number");
		lblAadhar.setBounds(73, 339, 204, 46);
		lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblAadhar);
		
		JLabel lblCountSchemes = new JLabel("No. of Schemes");
		lblCountSchemes.setBounds(73, 505, 204, 46);
		lblCountSchemes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblCountSchemes);
		
		JLabel lblTotalBenefitReceived = new JLabel("Total Benefit Received");
		lblTotalBenefitReceived.setBounds(73, 586, 204, 46);
		lblTotalBenefitReceived.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblTotalBenefitReceived);
		
		JLabel lblState = new JLabel("State of Residence");
		lblState.setBounds(73, 422, 204, 46);
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblState);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				entryData=Citizen.display_citizen_details(con, pst, rs, aadhar_id);
//				initialize();
				txtName.setText(entryData[0]);
				txtAadharNumber.setText(entryData[1]);
				txtState.setText(entryData[2]);
				txtCountRedeemingSchemes.setText(entryData[3]);
				txtBenefitReceived.setText(entryData[4]);
			}
		});
		btnRefresh.setBounds(1361, 10, 85, 21);
		frame.getContentPane().add(btnRefresh);
	}
}