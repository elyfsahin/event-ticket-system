package pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMail;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		Data.loadData();
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 220);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMail = new JLabel("E-Mail:");
		lblMail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMail.setBounds(71, 46, 83, 50);
		contentPane.add(lblMail);
		
		txtMail = new JTextField();
		txtMail.setBounds(151, 63, 154, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(60, 84, 94, 50);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(151, 101, 154, 20);
		contentPane.add(txtPassword);
		
		JButton btnLoginConfirm = new JButton("Login");
		btnLoginConfirm.setForeground(new Color(255, 255, 255));
		btnLoginConfirm.setBackground(new Color(126, 28, 3));
		btnLoginConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String email = txtMail.getText();
				String password = txtPassword.getText();
				
				User user = User.login(email, password);
				if(user!=null) {
					if(User.isValidEmail(email)) {
						JOptionPane.showMessageDialog(null,"Login successful!");
						dispose();
					}
					if(email.equals("admin@gmail.com") && password.equals("1234")) {
						AdminPage admin = new AdminPage();
						admin.setVisible(true);
					}
					else {
					HomeFrame home = new HomeFrame(user);
					home.setVisible(true);
					}
				}

				else {
					JOptionPane.showMessageDialog(null,User.loginMessage);
				}

			}
			
		});
		btnLoginConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnLoginConfirm.setBounds(173, 139, 94, 20);
		contentPane.add(btnLoginConfirm);

	}
}
