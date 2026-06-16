package pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class AdminPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		Data.loadData();
		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(239, 11, 530, 444);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnlogout = new JButton("Log Out");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnlogout.setForeground(new Color(255, 255, 255));
		btnlogout.setBackground(new Color(126, 28, 3));
		btnlogout.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnlogout.setBounds(94, 423, 97, 32);
		contentPane.add(btnlogout);
		btnlogout.setVisible(false);

		JButton btnAdd = new JButton("Add Event");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(126, 28, 3));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					btnlogout.setVisible(true);
				    
				    panel.removeAll(); 
				    panel.setLayout(new BorderLayout());
				    
				    
				    AddEvent addEventPage = new AddEvent();
				    
				   
				   
				    panel.add(addEventPage, java.awt.BorderLayout.CENTER); 
				    
				    panel.revalidate();
				    panel.repaint();
		
				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnAdd.setBounds(43, 78, 148, 51);
		btnAdd.setFocusPainted(false);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update Event");
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(126, 28, 3));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnlogout.setVisible(true);
				panel.removeAll();
		        panel.setLayout(new BorderLayout());
		        UpdateEvent upPanel = new UpdateEvent();
		        panel.add(upPanel, BorderLayout.CENTER);
		        panel.revalidate();
		        panel.repaint();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnUpdate.setBounds(43, 155, 148, 51);
		btnUpdate.setFocusPainted(false);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete Event");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setBackground(new Color(126, 28, 3));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnlogout.setVisible(true);
				panel.removeAll();
				panel.setLayout(new BorderLayout());
			    DeleteEvent delPanel = new DeleteEvent();
			    delPanel.refreshList(); 
			    panel.add(delPanel, BorderLayout.CENTER);
			    delPanel.refreshList();
			    panel.revalidate();
			    panel.repaint();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnDelete.setBounds(43, 232, 148, 51);
		btnDelete.setFocusPainted(false);
		contentPane.add(btnDelete);
		
		
	
		
	
	}
}
