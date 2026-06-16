package pack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TicketDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private Event selectedEvent;
	private User currentUser;
	private Ticket currentTicket;
	


	public TicketDialog(java.awt.Frame parent, Event event, User user) {
		super(parent,true);
		this.selectedEvent=event;
		this.currentUser=user;
		setBounds(100, 100, 608, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		

		
		{
			JLabel lblMovie = new JLabel("Movie:");
			lblMovie.setFont(new Font("Times New Roman", Font.PLAIN, 17));
			lblMovie.setBounds(26, 44, 63, 14);
			contentPanel.add(lblMovie);
		}
		
		JLabel lblMovieName = new JLabel("");
		lblMovieName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblMovieName.setBounds(80, 44, 339, 14);
		contentPanel.add(lblMovieName);
		
		JLabel lblMovieDate = new JLabel("");
		lblMovieDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblMovieDate.setBounds(80, 69, 118, 14);
		contentPanel.add(lblMovieDate);
		
		lblMovieName.setText(selectedEvent.getName());
		java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM HH:mm");
	    lblMovieDate.setText(selectedEvent.getEventTime().format(formatter));
		
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblDate.setBounds(26, 69, 44, 14);
		contentPanel.add(lblDate);
		
	
	
		
		JPanel pricePanel = new JPanel();
		pricePanel.setBounds(10, 162, 554, 151);
		contentPanel.add(pricePanel);
		pricePanel.setLayout(null);
		pricePanel.setVisible(false);
		
		JLabel lblPrice = new JLabel("Ticket Price:");
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblPrice.setBounds(21, 11, 96, 14);
		pricePanel.add(lblPrice);
		
		JLabel lblFee = new JLabel("Transaction Fee:");
		lblFee.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblFee.setBounds(21, 36, 121, 14);
		pricePanel.add(lblFee);
		
		JLabel lblFinalPrice = new JLabel("Final Price:");
		lblFinalPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblFinalPrice.setBounds(21, 61, 86, 14);
		pricePanel.add(lblFinalPrice);
		
		JLabel lblTicketPrice = new JLabel((String) null);
		lblTicketPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTicketPrice.setBounds(116, 11, 118, 14);
		pricePanel.add(lblTicketPrice);
		
		JLabel lblTFee = new JLabel((String) null);
		lblTFee.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblTFee.setBounds(142, 36, 118, 14);
		pricePanel.add(lblTFee);
		
		JLabel lblFPrice = new JLabel((String) null);
		lblFPrice.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblFPrice.setBounds(105, 61, 348, 14);
		pricePanel.add(lblFPrice);
		
		JButton btnStudent = new JButton("Student");
		btnStudent.setForeground(new Color(255, 255, 255));
		btnStudent.setBackground(new Color(126, 28, 3));
		btnStudent.setFocusPainted(false);
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentTicket = new Ticket(selectedEvent, "Student");
				lblTicketPrice.setText(selectedEvent.getBasePrice() +" TL(Base)");
				lblTFee.setText("10.5 TL");
				lblFPrice.setText(currentTicket.getFinalCost()+" TL(%40 Discount + Transaction Fee)");
				pricePanel.setVisible(true);
				
				revalidate(); 
			    repaint();
			}
		});
		
		btnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnStudent.setBounds(26, 117, 88, 22);
		btnStudent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		contentPanel.add(btnStudent);
		
		
		
		{
			JButton btnAdult = new JButton("Adult");
			btnAdult.setForeground(new Color(255, 255, 255));
			btnAdult.setBackground(new Color(126, 28, 3));
			btnAdult.setFocusPainted(false);
			btnAdult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentTicket = new Ticket(selectedEvent, "Adult");
					lblTicketPrice.setText(selectedEvent.getBasePrice() +" TL");
					lblTFee.setText("10.5 TL");
					lblFPrice.setText(currentTicket.getFinalCost()+" TL");
					pricePanel.setVisible(true);
					
					revalidate(); 
				    repaint();
					
				}
			});
			btnAdult.setFont(new Font("Times New Roman", Font.PLAIN, 16));
			btnAdult.setBounds(122, 117, 88, 22);
			contentPanel.add(btnAdult);
		}
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(255, 255, 255));
				okButton.setBackground(new Color(126, 28, 3));
				okButton.setFocusPainted(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
			
						if (currentTicket == null) {
							JOptionPane.showMessageDialog(TicketDialog.this, "Please select a user type");
							return;
						}
						
						
						PaymentDialog payment = new PaymentDialog(TicketDialog.this, currentTicket, currentUser);
						
					
						setVisible(false); 
						        
						payment.setVisible(true);
						        
						       
					}
				});
					
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(255, 255, 255));
				cancelButton.setBackground(new Color(126, 28, 3));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
