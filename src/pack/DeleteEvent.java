package pack;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane; // Ekledik
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class DeleteEvent extends JPanel {

    private static final long serialVersionUID = 1L;
    
    
    private DefaultListModel<String> model;
    private JList<String> list;

    public void refreshList() {
        if (model != null) {
            model.clear();
            for (Event e : Data.allEvents) {
                model.addElement(e.getName() + " | " + e.getEventTime());
            }
        }
    }

    public DeleteEvent() {
    	Data.loadData();
    	setBorder(new LineBorder(new Color(0, 0, 0), 2));
        
        
        model = new DefaultListModel<>();
        setLayout(null);
        list = new JList<>(model);
        list.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        list.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(24, 31, 373, 331);
        add(scrollPane);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(297, 373, 100, 30);
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(126, 28, 3));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        Data.allEvents.remove(selectedIndex);
                        Data.saveData();
                        refreshList(); 
                        JOptionPane.showMessageDialog(null, "Deleted successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an event to delete");
                }
            }
        });
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        add(btnDelete);


        refreshList();
    }
}