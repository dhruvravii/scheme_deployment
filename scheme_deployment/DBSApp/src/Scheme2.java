import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Scheme2 {

	private JFrame frame;
	private JTextField txtScheme;

	/**
	 * Launch the application.
	 */
	public static void Scheme2Screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scheme2 window = new Scheme2();
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
	public Scheme2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1470, 800);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtScheme = new JTextField();
		txtScheme.setHorizontalAlignment(SwingConstants.CENTER);
		txtScheme.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtScheme.setText("Scheme 2");
		txtScheme.setBounds(675, 30, 124, 73);
		frame.getContentPane().add(txtScheme);
		txtScheme.setColumns(10);
		txtScheme.setEditable(false);
		
//		JButton btnNewButton = new JButton("Close");
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//			}
//		});
//		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnNewButton.setBounds(685, 187, 103, 43);
//		frame.getContentPane().add(btnNewButton);
	}

}
