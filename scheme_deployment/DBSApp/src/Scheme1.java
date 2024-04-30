import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class Scheme1 {

	private JFrame frame;
	private JTextField txtScheme;
	private JTextField txtStatistics;
	private JTable table;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();
	private JTextField textSchemeID;
	private JTextField textBeneficiaryCount;
	private JTextField textEligibleCount;
	private JTextField textPenetrationRatio;
	private JTextField textCostPerCapita;
	private JTextField textEstimatedCost;

	/**
	 * Launch the application.
	 */
	
	public void updateTable() {
		
		con = MySQLConnection.connectDB();
		
		if(con != null) 
		{
			String sql = "SELECT is_availing.aadhar_id, name, start_date, end_date FROM is_availing JOIN citizen ON is_availing.aadhar_id = citizen.aadhar_id WHERE scheme_id = 1";
		
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				Object[] columnData = new Object[4];
				
				model.setRowCount(0);
				while(rs.next())
				{
					columnData[0] = rs.getString("aadhar_id");
					columnData[1] = rs.getString("name");
					columnData[2] = rs.getString("start_date");
					columnData[3] = rs.getString("end_date");
					
					model.addRow(columnData);
				}
				rs.close();
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Error in updating table");
			}
		}
	}
	public static void Scheme1Screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scheme1 window = new Scheme1();
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
	public Scheme1() {
		initialize();
		
		Object col[] = {"aadhar_id", "name", "start_date", "end_date"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		String scheme_id = "1";
		EligibilityCriteria.Scheme1Eligible(con, pst, scheme_id);
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtScheme = new JTextField();
		txtScheme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtScheme.setHorizontalAlignment(SwingConstants.CENTER);
		txtScheme.setText("Scheme 1");
		txtScheme.setBounds(503, 33, 161, 54);
		frame.getContentPane().add(txtScheme);
		txtScheme.setColumns(10);
		txtScheme.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(52, 158, 368, 524);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtStatistics = new JTextField();
		txtStatistics.setBackground(UIManager.getColor("Button.light"));
		txtStatistics.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		txtStatistics.setText("STATISTICS");
		txtStatistics.setBounds(103, 36, 158, 49);
		panel.add(txtStatistics);
		txtStatistics.setColumns(10);
		
		textSchemeID = new JTextField();
		textSchemeID.setBounds(165, 117, 177, 35);
		panel.add(textSchemeID);
		textSchemeID.setColumns(10);
		textSchemeID.setText("1");
		
		textBeneficiaryCount = new JTextField();
		textBeneficiaryCount.setColumns(10);
		textBeneficiaryCount.setBounds(165, 179, 177, 35);
		panel.add(textBeneficiaryCount);
		textBeneficiaryCount.setText(SchemeStatistics.count_beneficiaries(con, pst, rs, "1"));
		
		textEligibleCount = new JTextField();
		textEligibleCount.setColumns(10);
		textEligibleCount.setBounds(165, 242, 177, 35);
		panel.add(textEligibleCount);
		textEligibleCount.setText(SchemeStatistics.count_eligible(con, pst, rs, "1"));
		
		textPenetrationRatio = new JTextField();
		textPenetrationRatio.setColumns(10);
		textPenetrationRatio.setBounds(165, 303, 177, 35);
		panel.add(textPenetrationRatio);
		textPenetrationRatio.setText(SchemeStatistics.penetration_rate(con, pst, rs, "1"));
		
		textCostPerCapita = new JTextField();
		textCostPerCapita.setColumns(10);
		textCostPerCapita.setBounds(165, 369, 177, 35);
		panel.add(textCostPerCapita);
		textCostPerCapita.setText("1200");
		
		textEstimatedCost = new JTextField();
		textEstimatedCost.setColumns(10);
		textEstimatedCost.setBounds(165, 436, 177, 35);
		panel.add(textEstimatedCost);
		textEstimatedCost.setText(SchemeStatistics.estimated_cost(con, pst, rs, "1", "1200"));
		
		JLabel lblNewLabel = new JLabel("Scheme ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(21, 116, 120, 36);
		panel.add(lblNewLabel);
		
		JLabel lblNoOfBeneficiaries = new JLabel("No. of beneficiaries:");
		lblNoOfBeneficiaries.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoOfBeneficiaries.setBounds(21, 178, 120, 36);
		panel.add(lblNoOfBeneficiaries);
		
		JLabel lblNoEligible = new JLabel("No. eligible:");
		lblNoEligible.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNoEligible.setBounds(21, 241, 120, 36);
		panel.add(lblNoEligible);
		
		JLabel lblPenetrationRatio = new JLabel("Penetration ratio:");
		lblPenetrationRatio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPenetrationRatio.setBounds(21, 302, 120, 36);
		panel.add(lblPenetrationRatio);
		
		JLabel lblCostPerCapita = new JLabel("Cost per capita:");
		lblCostPerCapita.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCostPerCapita.setBounds(21, 368, 120, 36);
		panel.add(lblCostPerCapita);
		
		JLabel lblEstimatedCost = new JLabel("Estimated cost:");
		lblEstimatedCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstimatedCost.setBounds(21, 435, 120, 36);
		panel.add(lblEstimatedCost);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(503, 158, 622, 460);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 602, 440);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Aadhar_ID", "Name", "Start Date", "End Date"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		btnRefresh.setBounds(1040, 114, 85, 21);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateSchemeEntry cse = new CreateSchemeEntry();
				cse.CreateSchemeEntry();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(503, 652, 137, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindSchemeEntry fse = new FindSchemeEntry();
				fse.FindSchemeEntry();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFind.setBounds(664, 652, 137, 41);
		frame.getContentPane().add(btnFind);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				DeleteSchemeEntry dse = new DeleteSchemeEntry();
//				dse.DeleteSchemeEntry();
				DefaultTableModel mod = (DefaultTableModel)table.getModel();
				
				con = MySQLConnection.connectDB();
				int row = table.getSelectedRow();
				String aadhar = new String();
				String startDate = new String();
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
					aadhar = table.getModel().getValueAt(row,0).toString();
					startDate = table.getModel().getValueAt(row,2).toString();
					mod.removeRow(table.getSelectedRow());
				}
				String sql = "DELETE FROM is_availing WHERE aadhar_id = ? AND start_date = ?";
				try {
					pst = con.prepareStatement(sql);
					pst.setString(1, aadhar);
					pst.setString(2, startDate);
					pst.execute();
					pst.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setBounds(830, 652, 137, 41);
		frame.getContentPane().add(btnDelete);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateSchemeEntry use = new UpdateSchemeEntry();
				use.UpdateSchemeEntry();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setBounds(988, 652, 137, 41);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Update Statistics");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBeneficiaryCount.setText(SchemeStatistics.count_beneficiaries(con, pst, rs, "1"));
				textPenetrationRatio.setText(SchemeStatistics.penetration_rate(con, pst, rs, "1"));
				textEstimatedCost.setText(SchemeStatistics.estimated_cost(con, pst, rs, "1", "1200"));
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(52, 703, 137, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		
	}
}
