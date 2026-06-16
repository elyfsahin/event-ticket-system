package pack;


public class Payment {
   
    
    
    private double amount;
    private Ticket ticket;        
    private User user;             
    private boolean isSuccessful;

    
    
    
    public Payment(Ticket ticket, User user) {
       
        this.ticket= ticket;
        this.user = user;
        this.amount = ticket.getFinalCost(); 
        this.isSuccessful = false;
    }
    
    
    private boolean isValidCard(String cardNo, String cvc, String expiryDate) {
        String cleanedCardNo = cardNo.replaceAll("[\\s-]", "");
        
        
        if (cleanedCardNo.length() != 16) return false;
        
       
        if (cvc.length() != 3) return false;
        
       
        if (expiryDate == null || !expiryDate.contains("/")) return false; 
        
        return true; 
    }

   
    public boolean simulateTransaction(String cardNo, String cvc) {
        
        return Math.random() < 0.90; 
    }

    
    public boolean processPayment(Ticket ticket, User user, String cardholderName, String cardNo, String cvc, String expiryDate) {
        
       
        if (!isValidCard(cardNo, cvc, expiryDate)) {
            this.isSuccessful = false;
            return false; 
        }
        
        
      
        boolean transactionResult = simulateTransaction(cardNo, cvc);
        
        this.isSuccessful = transactionResult;

       
        
        if (this.isSuccessful) {
            
           
            this.ticket.confirmPurchase(); 
            this.user.addTicketToHistory(ticket); 
            
            return true;
        } else {
            return false;
        }
    }
    
    
    
    public double getAmount() {
        return this.amount;
    }
    
    public boolean isSuccessful() {
        return this.isSuccessful;
    }
    
    public Ticket getTicket() {
        return this.ticket;
    }

    public User getUser() {
        return this.user;
    }


}