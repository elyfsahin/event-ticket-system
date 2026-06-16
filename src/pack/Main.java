package pack;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
 	public static void main(String[] args) {
 		
 		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setResizable(false);
		setTitle("EVENT TICKET SYSTEM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setForeground(new Color(129, 36, 20));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblNewLabel.setBounds(237, 35, 279, 80);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(129, 36, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				SwingUtilities.getWindowAncestor(btnLogin).dispose();
				
			}
		});
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		btnLogin.setBounds(283, 139, 189, 55);
		btnLogin.setFocusPainted(false);
		contentPane.add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpFrame signUp = new SignUpFrame();
				signUp.setVisible(true);
			}
		});
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(129, 36, 20));
		btnSignUp.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		btnSignUp.setBounds(283, 218, 189, 55);
		btnLogin.setFocusPainted(false);
		contentPane.add(btnSignUp);

	}

}
