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

public class SignUpFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNam;
	private JTextField txtSur;
	private JTextField txtEmail;
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpFrame frame = new SignUpFrame();
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
	public SignUpFrame() {
		Data.loadData();
		setTitle("SIGN UP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 326, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblName.setBounds(53, 40, 74, 36);
		contentPane.add(lblName);
				
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSurname.setBounds(38, 82, 89, 36);
		contentPane.add(lblSurname);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblEmail.setBounds(53, 118, 74, 36);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblPassword.setBounds(37, 153, 90, 36);
		contentPane.add(lblPassword);
		
		txtNam = new JTextField();
		txtNam.setColumns(10);
		txtNam.setBounds(123, 47, 130, 27);
		contentPane.add(txtNam);

		txtSur = new JTextField();
		txtSur.setColumns(10);
		txtSur.setBounds(123, 89, 130, 27);
		contentPane.add(txtSur);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(123, 125, 130, 27);
		contentPane.add(txtEmail);
		
		txtPass = new JTextField();
		txtPass.setColumns(10);
		txtPass.setBounds(123, 160, 130, 27);
		contentPane.add(txtPass);
		
		JButton btnSignUpConfirm = new JButton("Sign Up");
		btnSignUpConfirm.setForeground(new Color(255, 255, 255));
		btnSignUpConfirm.setBackground(new Color(126, 28, 3));
		btnSignUpConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtNam.getText();
				String surname = txtSur.getText();
				String email= txtEmail.getText();
				String password = txtPass.getText();
				
				User userr = new User(name,surname,email,password);
				
				if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "You must fill all the blanks");
				}
				else if(userr.signUp()) {
					JOptionPane.showMessageDialog(null, "Registration Successful!");
					Data.saveData();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "User with this e-mail exists");
				}
			}
		});
		btnSignUpConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSignUpConfirm.setBounds(143, 198, 88, 22);
		contentPane.add(btnSignUpConfirm);

	}

}
