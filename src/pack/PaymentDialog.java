package pack;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PaymentDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private Ticket ticket;
    private User user;

    
    private CardLayout cardLayout;
    private JPanel cards;

    
    private JTextField txtName;
    private JTextField txtCardNo;
    private JTextField txtCvc;
    private JTextField txtExpiry;

    
    private JLabel lblTicketId;
    private JLabel lblEventName;
    private JLabel lblEventTime;
    private JLabel lblPriceTier;
    private JLabel lblTotal;

    public PaymentDialog(Window parent, Ticket ticket, User user) {
        super(parent, "Payment", ModalityType.APPLICATION_MODAL);
        this.ticket = ticket;
        this.user = user;

        setSize(520, 360);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        cards.add(buildPaymentFormPanel(), "FORM");
        cards.add(buildSuccessPanel(), "SUCCESS");

        setContentPane(cards);
        cardLayout.show(cards, "FORM");
    }

   
    private JPanel buildPaymentFormPanel() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("Enter Card Information");
        title.setFont(new Font("Times New Roman", Font.BOLD, 22));
        root.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        root.add(form, BorderLayout.CENTER);

        form.add(new JLabel("Amount:"));
        JLabel lblAmount = new JLabel(ticket.getFinalCost() + " TL");
        lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 16));
        form.add(lblAmount);

        form.add(new JLabel("Cardholder Name:"));
        txtName = new JTextField();
        form.add(txtName);

        form.add(new JLabel("Card No (16 digits):"));
        txtCardNo = new JTextField();
        form.add(txtCardNo);

        form.add(new JLabel("CVC (3 digits):"));
        txtCvc = new JTextField();
        form.add(txtCvc);

        form.add(new JLabel("Expiry (MM/YY):"));
        txtExpiry = new JTextField();
        form.add(txtExpiry);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnPay = new JButton("Pay");
        btnPay.setForeground(new Color(255, 255, 255));
        btnPay.setBackground(new Color(126, 28, 3));
        btnPay.setFocusPainted(false);
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setForeground(new Color(255, 255, 255));
        btnCancel.setBackground(new Color(126, 28, 3));
        btnCancel.setFocusPainted(false);
        buttons.add(btnPay);
        buttons.add(btnCancel);
        root.add(buttons, BorderLayout.SOUTH);

        getRootPane().setDefaultButton(btnPay);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	HomeFrame home = new HomeFrame(user);
            	home.setVisible(true);
            	dispose();
            }
            
        });
        btnPay.addActionListener(e -> onPay());

        return root;
    }

    private void onPay() {
        String name = txtName.getText().trim();
        String cardNo = txtCardNo.getText().trim();
        String cvc = txtCvc.getText().trim();
        String expiry = txtExpiry.getText().trim();

        if (name.isEmpty() || cardNo.isEmpty() || cvc.isEmpty() || expiry.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        Payment payment = new Payment(ticket, user);
        boolean success = payment.processPayment(ticket, user, name, cardNo, cvc, expiry);

        if (!success) {
            JOptionPane.showMessageDialog(this,
                    "Payment Failed!\nCheck card info (16 digits, CVC 3 digits, expiry MM/YY).");
            return;
        }
        
     
        
        
        fillTicketInfo();
        cardLayout.show(cards, "SUCCESS");
    }

   
    private JPanel buildSuccessPanel() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(new EmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("✅ Purchased!");
        title.setFont(new Font("Times New Roman", Font.BOLD, 24));
        root.add(title, BorderLayout.NORTH);

        JPanel info = new JPanel(new GridLayout(5, 2, 10, 10));
        root.add(info, BorderLayout.CENTER);

        info.add(new JLabel("Ticket ID:"));
        lblTicketId = new JLabel("-");
        info.add(lblTicketId);

        info.add(new JLabel("Event:"));
        lblEventName = new JLabel("-");
        info.add(lblEventName);

        info.add(new JLabel("Date/Time:"));
        lblEventTime = new JLabel("-");
        info.add(lblEventTime);

        info.add(new JLabel("Type:"));
        lblPriceTier = new JLabel("-");
        info.add(lblPriceTier);

        info.add(new JLabel("Total Paid:"));
        lblTotal = new JLabel("-");
        lblTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
        info.add(lblTotal);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnClose = new JButton("Close");
        btnClose.setForeground(new Color(255, 255, 255));
        btnClose.setBackground(new Color(126, 28, 3));
        btnClose.setFocusPainted(false);
        buttons.add(btnClose);
        root.add(buttons, BorderLayout.SOUTH);

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	for (User u : Data.registeredUsers) {
            	    if (u.getName().equals(user.getName())) {
            	        u.getPurchaseHistory().add(ticket); 
            	        break;
            	    }
            	}
            	Data.saveData();
            	
            	JOptionPane.showMessageDialog(null, "Ticket saved to purchase history");
            	HomeFrame home = new HomeFrame(user); 
                home.setVisible(true);
            	dispose();
            	SwingUtilities.getWindowAncestor(btnClose).dispose();
            }
        });
        
        return root;
    }

    private void fillTicketInfo() {
        lblTicketId.setText(String.valueOf(ticket.getTicketId()));
        lblEventName.setText(ticket.getEventRef().getName());

        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        lblEventTime.setText(ticket.getEventRef().getEventTime().format(fmt));

        
        lblPriceTier.setText(ticket.getPriceTier());

        lblTotal.setText(ticket.getFinalCost() + " TL");
    }
}

