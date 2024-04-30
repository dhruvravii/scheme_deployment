import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

public class CreateNewScheme {

	private JFrame frame;
	private JTextField txtCreate;
	private JTextField textSchemeID;
	private JTextField textScheme;
	private JButton btnSubmit;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();
	private JTextField textSchemeType;
	private JTextField textSector;
	private JTextField textMonetary;
	
	/**
	 * Launch the application.
	 */
	public static void CreateNewScheme() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNewScheme window = new CreateNewScheme();
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
	public CreateNewScheme() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCreate = new JTextField();
		txtCreate.setBackground(UIManager.getColor("Button.light"));
		txtCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCreate.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreate.setText("CREATE");
		txtCreate.setBounds(229, 24, 128, 43);
		frame.getContentPane().add(txtCreate);
		txtCreate.setColumns(10);
		txtCreate.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Scheme ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 132, 82, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Scheme Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(23, 204, 82, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblStartdate = new JLabel("Sector: ");
		lblStartdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartdate.setBounds(23, 278, 128, 23);
		frame.getContentPane().add(lblStartdate);
		
		JLabel lblEnddate = new JLabel("Monetary: ");
		lblEnddate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnddate.setBounds(23, 357, 128, 23);
		frame.getContentPane().add(lblEnddate);
		
		JLabel lblSchemeType = new JLabel("Scheme Type: ");
		lblSchemeType.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblSchemeType.setBounds(23, 432, 128, 23);
		frame.getContentPane().add(lblSchemeType);
		
		textSchemeID = new JTextField();
		textSchemeID.setBounds(180, 128, 368, 32);
		frame.getContentPane().add(textSchemeID);
		textSchemeID.setColumns(10);
		
		textScheme = new JTextField();
		textScheme.setColumns(10);
		textScheme.setBounds(180, 195, 368, 32);
		frame.getContentPane().add(textScheme);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int success_flag = 0;
				con = MySQLConnection.connectDB(); 
				
				String sql = "INSERT INTO schemes values(?,?,?,?,?)";
				
				try 
				{
					pst = con.prepareStatement(sql);
					pst.setString(1, textSchemeID.getText());
					pst.setString(2, textScheme.getText());
					pst.setString(3, textSector.getText());
					pst.setString(4, textMonetary.getText());
					pst.setString(5, textSchemeType.getText());
					pst.execute();
					pst.close();
					sql="INSERT INTO managed_by values (?,?)";
					pst=con.prepareStatement(sql);
					pst.setString(1, textSchemeID.getText());
					pst.setString(2, GovernmentPortalScreen.gov_name);
					pst.execute();
					pst.close();
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, ev);
				}
				frame.dispose();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSubmit.setBounds(440, 510, 108, 32);
		frame.getContentPane().add(btnSubmit);
		
		textSchemeType = new JTextField();
		textSchemeType.setColumns(10);
		textSchemeType.setBounds(180, 423, 368, 32);
		frame.getContentPane().add(textSchemeType);
		
		textSector = new JTextField();
		textSector.setColumns(10);
		textSector.setBounds(180, 269, 368, 32);
		frame.getContentPane().add(textSector);
		
		textMonetary = new JTextField();
		textMonetary.setColumns(10);
		textMonetary.setBounds(180, 348, 368, 32);
		frame.getContentPane().add(textMonetary);
		
	}
}
