import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GovernmentID {

	private JFrame frame;
	private JTextField textGovId;
	static String gov_name;

	/**
	 * Launch the application.
	 */
	
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public static void GovernmentID() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GovernmentID window = new GovernmentID();
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
	public GovernmentID() {
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
		
		JLabel lblNewLabel = new JLabel("Enter Government Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(36, 109, 96, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textGovId = new JTextField();
		textGovId.setBounds(161, 117, 208, 19);
		frame.getContentPane().add(textGovId);
		textGovId.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gov_name = textGovId.getText();
				GovernmentPortalScreen.gov_name = gov_name;
				if(EligibilityCriteria.GovernmentNameEligible(con, pst, rs, gov_name) == 1)
				{
				GovernmentPortalScreen gps = new GovernmentPortalScreen();
				gps.GovernmentPortalScreen();
				frame.dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Not a valid Government name!");
				}
			}
		});
		btnNewButton.setBounds(310, 209, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		
	}

}
