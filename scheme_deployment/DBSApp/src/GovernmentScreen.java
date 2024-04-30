import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GovernmentScreen {

    private JFrame frame;
    private JTable table;
    private JTextField textName;
    private JTextField textNumberOfResidents;
    private JTextField textNumberOfDistricts;
    private JTextField textNumberOfSchemes;
    private JTextField textTotalWelfareDeployed;
    private JTextField textTotalBudget;
    private JTextField textRemainingBudget;
    private JTextField textWelfareGrowth;
    
    Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	static String gov_name;
	String entryData[];

	DefaultTableModel model = new DefaultTableModel();
    /**
     * Launch the application.
     */
	
public void updateTable() {
		
		con = MySQLConnection.connectDB();
		
		if(con != null) 
		{
			String sql = "select managed_by.scheme_id, schemes.scheme_name, schemes.sector, schemes.monetary, schemes.scheme_type from managed_by join schemes on managed_by.scheme_id = schemes.scheme_id where managed_by.gov_name = ?";
		
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, gov_name);
				rs = pst.executeQuery();
				Object[] columnData = new Object[5];
				
				model.setRowCount(0);
				while(rs.next())
				{
					columnData[0] = rs.getString("scheme_id");
					columnData[1] = rs.getString("scheme_name");
					columnData[2] = rs.getString("sector");
					columnData[3] = rs.getString("monetary");
					columnData[4] = rs.getString("scheme_type");
					
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
	 * Launch the application.
	 */
	public void GovernmentScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GovernmentScreen window = new GovernmentScreen();
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
	public GovernmentScreen() {
		 initialize();
	        
	        Object col[] = {"scheme_id", "scheme_name", "sector", "monetary", "scheme_type"};
			model.setColumnIdentifiers(col);
			table.setModel(model);
			
			updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1470, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBounds(746, 132, 684, 478);
        panel_1.setLayout(null);
        frame.getContentPane().add(panel_1);
        
        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(43, 132, 134, 24);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNumberOfResidents = new JLabel("Number of residents:");
        lblNumberOfResidents.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfResidents.setBounds(43, 199, 134, 24);
        frame.getContentPane().add(lblNumberOfResidents);
        
        JLabel lblNumberOfDistricts = new JLabel("Number of districts:");
        lblNumberOfDistricts.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfDistricts.setBounds(43, 266, 134, 24);
        frame.getContentPane().add(lblNumberOfDistricts);
        
        JLabel lblNumberOfSchemes = new JLabel("Number of schemes:");
        lblNumberOfSchemes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfSchemes.setBounds(43, 326, 134, 24);
        frame.getContentPane().add(lblNumberOfSchemes);
        
        JLabel lblTotalWelfareDeployed = new JLabel("Total Welfare Deployed:");
        lblTotalWelfareDeployed.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTotalWelfareDeployed.setBounds(43, 396, 144, 24);
        frame.getContentPane().add(lblTotalWelfareDeployed);
        
        JLabel lblTotalBudget = new JLabel("Total budget:");
        lblTotalBudget.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTotalBudget.setBounds(43, 467, 134, 24);
        frame.getContentPane().add(lblTotalBudget);
        
        JLabel lblRemainingBudget = new JLabel("Remaining budget:");
        lblRemainingBudget.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblRemainingBudget.setBounds(43, 523, 134, 24);
        frame.getContentPane().add(lblRemainingBudget);
        
        JLabel lblT = new JLabel("Welfare Growth:");
        lblT.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblT.setBounds(43, 586, 134, 24);
        frame.getContentPane().add(lblT);
        
    	textName = new JTextField();
    	textName.setEditable(false);
        textName.setBounds(236, 132, 407, 24);
        textName.setText(entryData[0]);
        frame.getContentPane().add(textName);
        textName.setColumns(10);
        
        
        textNumberOfResidents = new JTextField();
        textNumberOfResidents.setEditable(false);
        textNumberOfResidents.setColumns(10);
        textNumberOfResidents.setBounds(236, 199, 407, 24);
        textNumberOfResidents.setText(entryData[1]);
        frame.getContentPane().add(textNumberOfResidents);
        
        
        textNumberOfDistricts = new JTextField();
        textNumberOfDistricts.setEditable(false);
        textNumberOfDistricts.setColumns(10);
        textNumberOfDistricts.setBounds(236, 266, 407, 24);
        frame.getContentPane().add(textNumberOfDistricts);
//        textNumberOfDistricts.setText(entryData[2]);
        
        textNumberOfSchemes = new JTextField();
        textNumberOfSchemes.setEditable(false);
        textNumberOfSchemes.setColumns(10);
        textNumberOfSchemes.setBounds(236, 326, 407, 24);
        frame.getContentPane().add(textNumberOfSchemes);
//        textNumberOfSchemes.setText(entryData[3]);
        
        textTotalWelfareDeployed = new JTextField();
        textTotalWelfareDeployed.setEditable(false);
        textTotalWelfareDeployed.setColumns(10);
        textTotalWelfareDeployed.setBounds(236, 396, 407, 24);
        frame.getContentPane().add(textTotalWelfareDeployed);
//        textTotalWelfareDeployed.setText(entryData[4]);
        
        textTotalBudget = new JTextField();
        textTotalBudget.setEditable(false);
        textTotalBudget.setColumns(10);
        textTotalBudget.setBounds(236, 467, 407, 24);
        frame.getContentPane().add(textTotalBudget);
//        textTotalBudget.setText(entryData[5]);
        
        textRemainingBudget = new JTextField();
        textRemainingBudget.setEditable(false);
        textRemainingBudget.setColumns(10);
        textRemainingBudget.setBounds(236, 523, 407, 24);
        frame.getContentPane().add(textRemainingBudget);
//        textRemainingBudget.setText(entryData[6]);
        
        textWelfareGrowth = new JTextField();
        textWelfareGrowth.setEditable(false);
        textWelfareGrowth.setColumns(10);
        textWelfareGrowth.setBounds(236, 586, 407, 24);
        frame.getContentPane().add(textWelfareGrowth);
	}
	


}
