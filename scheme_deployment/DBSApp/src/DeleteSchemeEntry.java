import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;

public class DeleteSchemeEntry {

	private JFrame frame;
	private JTextField txtCreate;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void DeleteSchemeEntry() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteSchemeEntry window = new DeleteSchemeEntry();
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
	public DeleteSchemeEntry() {
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
		txtCreate.setText("DELETE");
		txtCreate.setBounds(229, 24, 128, 43);
		frame.getContentPane().add(txtCreate);
		txtCreate.setColumns(10);
		txtCreate.setEditable(false);
		
		textField = new JTextField();
		textField.setBounds(180, 128, 368, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("AADHAR:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(23, 132, 82, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME:");
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
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(180, 195, 368, 32);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(180, 269, 368, 32);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(180, 348, 368, 32);
		frame.getContentPane().add(textField_3);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(440, 458, 108, 32);
		frame.getContentPane().add(btnNewButton);
	}
}
