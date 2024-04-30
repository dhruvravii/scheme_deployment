import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CitizenAadhar {

	private JFrame frame;
	private JTextField textAadhar;
	static String aadhar_id;
    Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	/**
	 * Launch the application.
	 */
	public static void CitizenAadhar() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CitizenAadhar window = new CitizenAadhar();
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
	public CitizenAadhar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Aadhar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(36, 109, 96, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textAadhar = new JTextField();
		textAadhar.setBounds(154, 111, 224, 30);
		frame.getContentPane().add(textAadhar);
		textAadhar.setColumns(10);
	
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aadhar_id=textAadhar.getText();
				CitizenPortalScreen.aadhar_id=aadhar_id;
				if(EligibilityCriteria.AadharEligible(con, pst, rs, aadhar_id)==1) {
					CitizenPortalScreen cps = new CitizenPortalScreen();
					cps.CitizenPortal();
				}
				else {
					JOptionPane.showMessageDialog(null, "Aadhar not eligible!");
				}
				frame.dispose();
			}
		});
		btnNewButton.setBounds(310, 209, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
	}
}
