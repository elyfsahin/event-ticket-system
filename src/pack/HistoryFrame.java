package pack;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class HistoryFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;

    public HistoryFrame(User user) {
        setTitle("Purchase History - " + user.getName());
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());


        String[] columns = {"Ticket ID", "Event Name", "Place", "Category", "Date", "Price"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Ticket t : user.getPurchaseHistory()) {
            Object[] row = {
                t.getTicketId(),
                t.getEventRef().getName(),
                t.getEventRef().getPlace(),
                t.getEventRef().getType(),
                t.getEventRef().getEventTime().format(fmt),
                t.getFinalCost() + " TL"
            };
            model.addRow(row); 
        }

        
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        
        JButton btnBack = new JButton("Back to Home");
        btnBack.addActionListener(e -> {
            new HomeFrame(user).setVisible(true);
            dispose();
        });
        getContentPane().add(btnBack, BorderLayout.SOUTH);
    }
}