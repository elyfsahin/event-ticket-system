package pack;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.LineBorder;

public class UpdateEvent extends JPanel {
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);
    private JTextField txtPrice, txtDate, txtTime; 
    private Event selectedEvent;

    public void refreshList() {
        model.clear();
        for (Event e : Data.allEvents) {
            model.addElement(e.getName());
        }
    }

    public UpdateEvent() {
    	Data.loadData();
        setBorder(new LineBorder(new Color(0, 0, 0), 3));
        setLayout(null);
        list.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        
        list.setBounds(20, 20, 200, 300);
        add(list);
        refreshList();

        
        JLabel lblDateTime = new JLabel("Event Date & Time:");
        lblDateTime.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblDateTime.setBounds(230, 16, 110, 25);
        add(lblDateTime);

        
        txtDate = new JTextField();
        txtDate.setBounds(344, 16, 80, 25);
        add(txtDate);

        
        txtTime = new JTextField();
        txtTime.setBounds(430, 16, 60, 25);
        add(txtTime);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        lblPrice.setBounds(283, 60, 41, 25);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(344, 60, 150, 25);
        add(txtPrice);

        
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && list.getSelectedIndex() != -1) {
                selectedEvent = Data.allEvents.get(list.getSelectedIndex());
                
                txtPrice.setText(String.valueOf(selectedEvent.getBasePrice()));

                
                DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("HH:mm");
                
                
                if (selectedEvent.getEventTime() != null) {
                    txtDate.setText(selectedEvent.getEventTime().format(dFormatter));
                    txtTime.setText(selectedEvent.getEventTime().format(tFormatter));
                }
            }
        });

        
        JButton btnSaveUpdate = new JButton("Update and Save");
        btnSaveUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        btnSaveUpdate.setForeground(new Color(255, 255, 255));
        btnSaveUpdate.setBackground(new Color(126, 28, 3));
        btnSaveUpdate.setBounds(283, 126, 150, 30);
        add(btnSaveUpdate);

        btnSaveUpdate.addActionListener(e -> {
            if (selectedEvent != null) {
                try {
                    selectedEvent.setBasePrice(Double.parseDouble(txtPrice.getText()));
                    
                    String combined = txtDate.getText() + " " + txtTime.getText();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

                    LocalDateTime newEventTime = LocalDateTime.parse(combined, formatter);
                    selectedEvent.setEventTime(newEventTime);
                    Data.saveData();
                    refreshList();
                    JOptionPane.showMessageDialog(this, "Event updated successfully!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error! Please check fields");
                }
            }
        });
    }
}