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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GovernmentPortalScreen {

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

    public GovernmentPortalScreen() {
        initialize();
        
        Object col[] = {"scheme_id", "scheme_name", "sector", "monetary", "scheme_type"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		updateTable();
    }

    public static void GovernmentPortalScreen() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GovernmentPortalScreen window = new GovernmentPortalScreen();
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


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1470, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
        entryData = new String[8];
        entryData = Government.display_government_details(con, pst, rs, gov_name);

        // Create a panel with a textured background
        JPanel panel = new JPanel() {
//            protected void paintComponent(Graphics g) {
//                //super.paintComponent(g);
//                // Add texture or background image drawing code here
//                // For example, you can draw lines, shapes, or load an image
//
//            }
        };
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // Add a label to the panel
        JLabel label = new JLabel("Government Portal");
        label.setBounds(588, 32, 275, 36);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label);

        // Get statistics based on government name input
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_1.setBounds(746, 132, 684, 478);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 664, 458);
        panel_1.add(scrollPane);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"scheme_id", "scheme_name", "sector", "monetary", "scheme_type"
        	}
        ));
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel = new JLabel("Name:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setBounds(43, 132, 134, 24);
        panel.add(lblNewLabel);
        
        JLabel lblNumberOfResidents = new JLabel("Number of residents:");
        lblNumberOfResidents.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfResidents.setBounds(43, 199, 134, 24);
        panel.add(lblNumberOfResidents);
        
        JLabel lblNumberOfDistricts = new JLabel("Number of districts:");
        lblNumberOfDistricts.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfDistricts.setBounds(43, 266, 134, 24);
        panel.add(lblNumberOfDistricts);
        
        JLabel lblNumberOfSchemes = new JLabel("Number of schemes:");
        lblNumberOfSchemes.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNumberOfSchemes.setBounds(43, 326, 134, 24);
        panel.add(lblNumberOfSchemes);
        
        JLabel lblTotalWelfareDeployed = new JLabel("Total Welfare Deployed:");
        lblTotalWelfareDeployed.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTotalWelfareDeployed.setBounds(43, 396, 144, 24);
        panel.add(lblTotalWelfareDeployed);
        
        JLabel lblTotalBudget = new JLabel("Total budget:");
        lblTotalBudget.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTotalBudget.setBounds(43, 467, 134, 24);
        panel.add(lblTotalBudget);
        
        JLabel lblRemainingBudget = new JLabel("Remaining budget:");
        lblRemainingBudget.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblRemainingBudget.setBounds(43, 523, 134, 24);
        panel.add(lblRemainingBudget);
        
        textName = new JTextField();
        textName.setEditable(false);
        textName.setBounds(236, 132, 407, 24);
        textName.setText(entryData[0]);
        panel.add(textName);
        textName.setColumns(10);
        
        
        textNumberOfResidents = new JTextField();
        textNumberOfResidents.setEditable(false);
        textNumberOfResidents.setColumns(10);
        textNumberOfResidents.setBounds(236, 199, 407, 24);
        textNumberOfResidents.setText(entryData[1]);
        panel.add(textNumberOfResidents);
        
        
        textNumberOfDistricts = new JTextField();
        textNumberOfDistricts.setEditable(false);
        textNumberOfDistricts.setColumns(10);
        textNumberOfDistricts.setBounds(236, 266, 407, 24);
        panel.add(textNumberOfDistricts);
        textNumberOfDistricts.setText(entryData[2]);
        
        textNumberOfSchemes = new JTextField();
        textNumberOfSchemes.setEditable(false);
        textNumberOfSchemes.setColumns(10);
        textNumberOfSchemes.setBounds(236, 326, 407, 24);
        panel.add(textNumberOfSchemes);
        textNumberOfSchemes.setText(entryData[3]);
        
        textTotalWelfareDeployed = new JTextField();
        textTotalWelfareDeployed.setEditable(false);
        textTotalWelfareDeployed.setColumns(10);
        textTotalWelfareDeployed.setBounds(236, 396, 407, 24);
        panel.add(textTotalWelfareDeployed);
        textTotalWelfareDeployed.setText(entryData[4]);
        
        textTotalBudget = new JTextField();
        textTotalBudget.setEditable(false);
        textTotalBudget.setColumns(10);
        textTotalBudget.setBounds(236, 467, 407, 24);
        panel.add(textTotalBudget);
        textTotalBudget.setText(entryData[5]);
        
        textRemainingBudget = new JTextField();
        textRemainingBudget.setEditable(false);
        textRemainingBudget.setColumns(10);
        textRemainingBudget.setBounds(236, 523, 407, 24);
        panel.add(textRemainingBudget);
        textRemainingBudget.setText(entryData[6]);
        
        
        JButton btnNewButton = new JButton("Create");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateNewScheme cns = new CreateNewScheme();
				cns.CreateNewScheme();
			}
		});
        btnNewButton.setBounds(758, 639, 282, 91);
        panel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Delete");
        btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				DeleteSchemeEntry dse = new DeleteSchemeEntry();
//				dse.DeleteSchemeEntry();
				DefaultTableModel mod = (DefaultTableModel)table.getModel();
				
				con = MySQLConnection.connectDB();
				int row = table.getSelectedRow();
				String schemeID = new String();
				if(table.getSelectedRow() == -1)
				{
					if(table.getSelectedRow() == 0)
					{
						JOptionPane.showMessageDialog(null, "No data to be deleted", "Delete Scheme", JOptionPane.OK_OPTION);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Select row to be deleted", "Delete Scheme", JOptionPane.OK_OPTION);
					}
				}
				else
				{
					schemeID = table.getModel().getValueAt(row,0).toString();
					mod.removeRow(table.getSelectedRow());
				}
				String sql1 = "DELETE FROM schemes WHERE scheme_id = ?";
				String sql2 = "DELETE FROM scheme_stats WHERE scheme_id = ?";
				String sql3 = "DELETE FROM is_eligible WHERE scheme_id = ?";
				String sql4 = "DELETE FROM is_availing WHERE scheme_id = ?";
				String sql5 = "DELETE FROM managed_by WHERE scheme_id= ?";
				try {
					pst = con.prepareStatement(sql2);
					pst.setString(1, schemeID);
					pst.execute();
					pst.close();
					pst = con.prepareStatement(sql3);
					pst.setString(1, schemeID);
					pst.execute();
					pst.close();
					pst = con.prepareStatement(sql4);
					pst.setString(1, schemeID);
					pst.execute();
					pst.close();
					pst = con.prepareStatement(sql5);
					pst.setString(1, schemeID);
					pst.execute();
					pst.close();
					pst = con.prepareStatement(sql1);
					pst.setString(1, schemeID);
					pst.execute();
					pst.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
				
			}
		});
        btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnNewButton_1.setBounds(1129, 639, 282, 91);
        panel.add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Refresh");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		updateTable();
        	}
        });
        btnNewButton_2.setBounds(1326, 101, 85, 21);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("View Scheme Statistics");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GovernmentSchemeStatistics gss = new GovernmentSchemeStatistics();
        		gss.GovernmentSchemeStatistics();
        	}
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnNewButton_3.setBounds(459, 666, 184, 55);
        panel.add(btnNewButton_3);
    }
}
