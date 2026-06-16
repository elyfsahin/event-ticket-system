package pack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class HomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeFrame frame = new HomeFrame(null);
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
	public HomeFrame(User user) {
		Data.loadData();
		setTitle("Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choose An Event Category");
		lblNewLabel.setForeground(new Color(129, 36, 20));
		lblNewLabel.setBounds(134, 25, 388, 42);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		contentPane.add(lblNewLabel);
		
		JButton btnCinema = new JButton("Cinema");
		btnCinema.setBackground(new Color(129, 36, 20));
		btnCinema.setForeground(new Color(255, 255, 255));
		btnCinema.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnCinema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Event> cinemaEvents = new ArrayList<>();
				
				for (Event event : Data.allEvents) {
					if(event.getType().equals("Cinema")) {
						cinemaEvents.add(event);
					}
					
				}
				if (!cinemaEvents.isEmpty()) {
		            EventFrame cinema = new EventFrame(cinemaEvents,user);
		            cinema.setVisible(true);
		            SwingUtilities.getWindowAncestor(btnCinema).dispose();
				}else {
					JOptionPane.showMessageDialog(null, "There's no available show right now.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		});
		btnCinema.setBounds(268, 89, 129, 42);
		btnCinema.setFocusPainted(false);
		contentPane.add(btnCinema);
		
		JButton btnTheatre = new JButton("Theatre");
		btnTheatre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Event> theatreEvents = new ArrayList<>();
				
				for (Event event : Data.allEvents) {
					if(event.getType().equals("Theatre")) {
						theatreEvents.add(event);
					}
					
				}
				if (!theatreEvents.isEmpty()) {
		            EventFrame cinema = new EventFrame(theatreEvents,user);
		            cinema.setVisible(true);
		            SwingUtilities.getWindowAncestor(btnTheatre).dispose();
				}else {
					JOptionPane.showMessageDialog(null, "There's no available show right now.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnTheatre.setForeground(new Color(255, 255, 255));
		btnTheatre.setBackground(new Color(129, 36, 20));
		btnTheatre.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnTheatre.setFocusPainted(false);
		btnTheatre.setBounds(268, 142, 129, 42);
		
		contentPane.add(btnTheatre);
		
		JButton btnConcert = new JButton("Concert");
		btnConcert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Event> concertEvents = new ArrayList<>();
				
				for (Event event : Data.allEvents) {
					if(event.getType().equals("Concert")) {
						concertEvents.add(event);
					}
					
				}
				if (!concertEvents.isEmpty()) {
		            EventFrame cinema = new EventFrame(concertEvents,user);
		            cinema.setVisible(true);
		            SwingUtilities.getWindowAncestor(btnConcert).dispose();
				}else {
					JOptionPane.showMessageDialog(null, "There's no available show right now.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnConcert.setForeground(new Color(255, 255, 255));
		btnConcert.setBackground(new Color(129, 36, 20));
		btnConcert.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		btnConcert.setBounds(268, 205, 129, 42);
		btnConcert.setFocusPainted(false);
		contentPane.add(btnConcert);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(126, 28, 3));
		panel.setBounds(0, 0, 94, 332);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnTicket = new JButton("My Tickets");
		btnTicket.setForeground(new Color(126, 28, 3));
		btnTicket.setBackground(new Color(255, 255, 255));
		btnTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HistoryFrame history = new HistoryFrame(user);
				history.setVisible(true);
				dispose();
			}
		});
		btnTicket.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnTicket.setBounds(4, 249, 84, 22);
		btnTicket.setFocusPainted(false);
		panel.add(btnTicket);
		
		JButton btnLog = new JButton("Log Out");
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLog.setForeground(new Color(126, 28, 3));
		btnLog.setBackground(new Color(240, 240, 240));
		btnLog.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnLog.setBounds(4, 282, 84, 22);
		btnLog.setFocusPainted(false);
		panel.add(btnLog);

	}
}
