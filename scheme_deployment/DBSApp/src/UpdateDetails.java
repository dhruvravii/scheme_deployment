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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateDetails {

	private JFrame frame;
	private JTextField txtCreate;
	private JTextField textName;
	private JTextField textDistrict;
	private JTextField textState;
	private JTextField textArea;
	private JButton btnNewButton;
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String aadhar_id;

	/**
	 * Launch the application.
	 */
	public static void UpdateDetails() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDetails window = new UpdateDetails();
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
	public UpdateDetails() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		aadhar_id=CitizenAadhar.aadhar_id;
		frame = new JFrame();
		frame.setBounds(0, 0, 600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCreate = new JTextField();
		txtCreate.setBackground(UIManager.getColor("Button.light"));
		txtCreate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCreate.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreate.setText("UPDATE");
		txtCreate.setBounds(229, 24, 128, 43);
		frame.getContentPane().add(txtCreate);
		txtCreate.setColumns(10);
		txtCreate.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 132, 82, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("District");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(23, 204, 82, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblStartdate = new JLabel("State");
		lblStartdate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStartdate.setBounds(23, 278, 128, 23);
		frame.getContentPane().add(lblStartdate);
		
		JLabel lblEnddate = new JLabel("Area");
		lblEnddate.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEnddate.setBounds(23, 357, 128, 23);
		frame.getContentPane().add(lblEnddate);
		
		textName = new JTextField();
		textName.setBounds(180, 128, 368, 32);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		textDistrict = new JTextField();
		textDistrict.setColumns(10);
		textDistrict.setBounds(180, 195, 368, 32);
		frame.getContentPane().add(textDistrict);
		
		textState = new JTextField();
		textState.setColumns(10);
		textState.setBounds(180, 269, 368, 32);
		frame.getContentPane().add(textState);
		
		textArea = new JTextField();
		textArea.setColumns(10);
		textArea.setBounds(180, 348, 368, 32);
		frame.getContentPane().add(textArea);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = MySQLConnection.connectDB();
				
				String sql = "UPDATE citizen SET name=?, state=?, district=?, area=? WHERE aadhar_id=?";
				
				try
				{
					pst = con.prepareStatement(sql);
					pst.setString(1, textName.getText());
					pst.setString(2, textState.getText());
					pst.setString(3, textDistrict.getText());
					pst.setString(4, textArea.getText());
					pst.setString(5, aadhar_id);
					pst.execute();
					pst.close();
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, e1);
				}
				frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(440, 458, 108, 32);
		frame.getContentPane().add(btnNewButton);
	}
}
