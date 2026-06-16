package pack;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddEvent extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JTextField txtDate;
	private JTextField txtTime;
	private JTextField txtPrice;
	private JTextField txtId;
	private JTextField txtPath;
	private JTextField txtPlace;

	/**
	 * Create the panel.
	 */
	public AddEvent() {
		Data.loadData();
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setLayout(null);
		setVisible(true);
		
		JLabel lblType = new JLabel("Event Type:");
		lblType.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblType.setBounds(26, 52, 97, 28);
		add(lblType);
		
		JLabel lblName = new JLabel("Event Name:");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblName.setBounds(26, 91, 97, 28);
		add(lblName);
		
		JLabel lblDate = new JLabel("Event Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(26, 157, 97, 28);
		add(lblDate);
		
		JLabel lblTime = new JLabel("Event Time:");
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTime.setBounds(26, 190, 97, 28);
		add(lblTime);
		
		JLabel lblPrice = new JLabel("Ticket Price:");
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPrice.setBounds(26, 263, 97, 28);
		add(lblPrice);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(128, 97, 96, 20);
		add(txtName);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(116, 163, 108, 20);
		add(txtDate);
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		txtTime.setBounds(116, 196, 108, 20);
		add(txtTime);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(126, 266, 98, 26);
		add(txtPrice);
		
		JComboBox<String> cbType = new JComboBox<String>();
		cbType.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		cbType.setBackground(new Color(255, 255, 255));
		cbType.setBounds(128, 57, 96, 22);
		add(cbType);
		cbType.addItem("Cinema");
		cbType.addItem("Theatre");
		cbType.addItem("Concert");
		
		
		JButton btnSave = new JButton("Save");
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(126, 28, 3));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String type = cbType.getSelectedItem().toString();
		        String name = txtName.getText();
		        String dateInput = txtDate.getText(); 
		        String timeInput = txtTime.getText();
		        String combined = dateInput + " " + timeInput;
		        String place=txtPlace.getText().toString();
		        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		        java.time.LocalDateTime eventDateTime = java.time.LocalDateTime.parse(combined, formatter);
		        double price = Double.parseDouble(txtPrice.getText());
		        int id=Integer.parseInt(txtId.getText());
		        String posterpath=txtPath.getText();
		        
		        if(posterpath.isEmpty()) {
		            posterpath = "default.jpg"; 
		        }
		        Event event = new Event(id,name,type, eventDateTime , place, posterpath, price);
		        Data.allEvents.add(event);
		        Data.saveData();
				JOptionPane.showMessageDialog(null, "Successfuly saved!");
			}
		});
		btnSave.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnSave.setBounds(116, 353, 88, 22);
		add(btnSave);
		
		JLabel lblEventId = new JLabel("Event ID:");
		lblEventId.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblEventId.setBounds(26, 124, 97, 28);
		add(lblEventId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(128, 128, 96, 20);
		add(txtId);
		
		JLabel lblPath = new JLabel("Poster Path:");
		lblPath.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPath.setBounds(26, 304, 97, 28);
		add(lblPath);
		
		txtPath = new JTextField();
		txtPath.setColumns(10);
		txtPath.setBounds(126, 305, 98, 26);
		add(txtPath);
		
		JLabel lblPlace = new JLabel("Event Place:");
		lblPlace.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPlace.setBounds(26, 227, 97, 28);
		add(lblPlace);
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		txtPlace.setBounds(116, 232, 108, 20);
		add(txtPlace);

	}
}
