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

public class CreateSchemeEntry {

	private JFrame frame;
	private JTextField txtCreate;
	private JTextField textAadhar;
	private JTextField textScheme;
	private JButton btnNewButton;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	public static void CreateSchemeEntry() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateSchemeEntry window = new CreateSchemeEntry();
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
	public CreateSchemeEntry() {
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
		
		JLabel lblNewLabel = new JLabel("AADHAR:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 132, 82, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SCHEME_ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(23, 204, 82, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblStartdate = new JLabel("START_DATE:");
		lblStartdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartdate.setBounds(23, 278, 128, 23);
		frame.getContentPane().add(lblStartdate);
		
		JLabel lblEnddate = new JLabel("END_DATE:");
		lblEnddate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnddate.setBounds(23, 357, 128, 23);
		frame.getContentPane().add(lblEnddate);
		
		textAadhar = new JTextField();
		textAadhar.setBounds(180, 128, 368, 32);
		frame.getContentPane().add(textAadhar);
		textAadhar.setColumns(10);
		
		textScheme = new JTextField();
		textScheme.setColumns(10);
		textScheme.setBounds(180, 195, 368, 32);
		frame.getContentPane().add(textScheme);
		
		final JDateChooser startDate = new JDateChooser();
		startDate.setBounds(180, 269, 368, 32);
		frame.getContentPane().add(startDate);
		
		final JDateChooser endDate = new JDateChooser();
		endDate.setBounds(180, 348, 368, 32);
		frame.getContentPane().add(endDate);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int success_flag = 0;
				con = MySQLConnection.connectDB(); 
				
				String sql = "SELECT * FROM is_eligible";
				
				try 
				{
					pst = con.prepareStatement(sql);
//					pst.setString(1, textAadhar.getText());
//					pst.setString(2, textScheme.getText());
					rs = pst.executeQuery();
//					rs.next();
//					if(rs.getString(1)==null) {
//						
//					}
					sql = "INSERT INTO is_availing(aadhar_id, scheme_id, start_date, end_date) values(?,?,?,?)";
					pst = con.prepareStatement(sql);
					pst.setString(1, textAadhar.getText());
					pst.setString(2, textScheme.getText());
					pst.setString(3, ((JTextField)startDate.getDateEditor().getUiComponent()).getText());
					pst.setString(4, ((JTextField)endDate.getDateEditor().getUiComponent()).getText());
					while(rs.next())
					{
						if(rs.getString(1).equals(textAadhar.getText()) && rs.getString(2).equals(textScheme.getText()))
						{
							pst.execute();
							success_flag = 1;
							pst.close();
							JOptionPane.showMessageDialog(null, "Data updated, press refresh to view changes");
							break;
						}
					}
					if(success_flag == 0) 
					{
						JOptionPane.showMessageDialog(null, "Not Eligible!");
					}
				}
				catch(Exception ev)
				{
					JOptionPane.showMessageDialog(null, ev);
				}
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(440, 458, 108, 32);
		frame.getContentPane().add(btnNewButton);
		
	}
}
