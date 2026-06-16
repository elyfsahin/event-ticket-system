package pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class EventFrame extends JFrame {

    private User user;

    public EventFrame(List<Event> cinemaEvents, User user) {
        super("Events");
        this.user = user;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 650); 
        setLocationRelativeTo(null); 
        
        
        getContentPane().setLayout(new BorderLayout());


        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnBack = new JButton(" < Back to Categories ");
        btnBack.setBackground(new Color(129, 36, 20)); 
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        
        btnBack.addActionListener(e -> {
            new HomeFrame(user).setVisible(true); 
            dispose(); 
        });
        topPanel.add(btnBack);
        getContentPane().add(topPanel, BorderLayout.NORTH); 

        
        Map<String, List<Event>> movies = groupEventsByMovie(cinemaEvents);
        JPanel moviesPanel = new JPanel(new GridLayout(0, 3, 20, 20)); 
        moviesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Map.Entry<String, List<Event>> entry : movies.entrySet()) {
            String movieName = entry.getKey();
            Event representativeEvent = entry.getValue().get(0); 
            JPanel movieCard = createMovieCard(movieName, representativeEvent.getPosterPath(), entry.getValue());
            moviesPanel.add(movieCard);
        }

        JScrollPane scrollPane = new JScrollPane(moviesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        getContentPane().add(scrollPane, BorderLayout.CENTER); 
    }

    
    private Map<String, List<Event>> groupEventsByMovie(List<Event> events) {
        Map<String, List<Event>> grouped = new LinkedHashMap<>();
        for (Event event : events) {
            grouped.computeIfAbsent(event.getName(), k -> new ArrayList<>()).add(event);
        }
        return grouped;
    }

    private JPanel createMovieCard(String movieName, String posterPath, List<Event> sessions) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        try {
            URL imageUrl = getClass().getResource("/" + posterPath); 
            if (imageUrl != null) {
                ImageIcon icon = new ImageIcon(imageUrl);
                Image originalImage = icon.getImage();
                
                int width = 150;
                int height = 225;

                java.awt.image.BufferedImage resizedImg = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = resizedImg.createGraphics();
                g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.drawImage(originalImage, 0, 0, width, height, null);
                g2.dispose();

                JLabel imageLabel = new JLabel(new ImageIcon(resizedImg));
                JLabel nameLabel = new JLabel(movieName);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
                nameLabel.setMaximumSize(new Dimension(150, 20));
                
                imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                
                card.add(imageLabel);
                card.add(Box.createVerticalStrut(5));
                card.add(nameLabel);
                card.add(Box.createVerticalStrut(5));
            } else {
                card.add(new JLabel("Poster not found: " + movieName));
            }

            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showMovieDetails(movieName, sessions);
                }
            });
        } catch (Exception ex) {
            card.add(new JLabel("Load Error"));
            ex.printStackTrace();
        }
        return card;
    }
    private void showMovieDetails(String movieName, List<Event> sessions) {
        JDialog detailsDialog = new JDialog(this, movieName + " - Sessions", true); 
        detailsDialog.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        detailsDialog.setSize(450, 300);
        detailsDialog.setLocationRelativeTo(this);
        
        JLabel title = new JLabel("Sessions");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        detailsDialog.getContentPane().add(title);
        
        for (Event event : sessions) {
            LocalDateTime time = event.getEventTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YY HH:mm");
            String sessionTimeText = time.format(formatter);
            
            JButton sessionButton = new JButton(sessionTimeText + " (" + event.getType() + ")"); 
            sessionButton.setForeground(new Color(255, 255, 255));
            sessionButton.setBackground(new Color(126, 28, 3));
            sessionButton.setFocusPainted(false);
            
            sessionButton.addActionListener(e -> {
               
            	
                detailsDialog.setVisible(false);
                

                
                int response = JOptionPane.showConfirmDialog(this, 
                    movieName + " " + sessionTimeText + " " + event.getPlace() + " , do you confirm?", 
                    "Session Confirm", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    
                    TicketDialog ticketWindow = new TicketDialog(this, event, user);
                    this.setVisible(false);
                    ticketWindow.setVisible(true);
                    
                    detailsDialog.dispose(); 
                    this.dispose(); 
                } else {
                    
                    detailsDialog.dispose();
                }
            });
            detailsDialog.getContentPane().add(sessionButton);
        }
        detailsDialog.setVisible(true);
    }
}