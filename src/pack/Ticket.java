package pack;

public class Ticket {
	private int ticketId;
	private Event eventRef;
	private String priceTier;
	private double finalCost;
	private boolean isConfirmed;
	
	public Ticket(Event eventRef, String priceTier) {
		
		int totalTickets = 0;
	    for (User u : Data.registeredUsers) {
	        totalTickets += u.getPurchaseHistory().size();
	    }
		
		this.ticketId= totalTickets + 1;
		this.eventRef = eventRef;
		this.priceTier = priceTier;
		this.finalCost = calculateCost(eventRef.getBasePrice(), priceTier);
		this.isConfirmed = false;
	}
	
	public double calculateCost(double basePrice,String priceTier) {
		final double transactionFee=10.5;
		double cost=basePrice;
		
		if(priceTier.equals("Student")) {
			cost*=0.6;
		}
		
		cost+=transactionFee;
		return cost;
	}
	
	public void confirmPurchase() {
        this.isConfirmed = true;
	}
	
	public double getFinalCost() {
        return finalCost;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Event getEventRef() {
        return eventRef;
    }

    public String getPriceTier() {
        return priceTier;
    }
    
    public boolean isConfirmed() {
        return isConfirmed;
    }

}
