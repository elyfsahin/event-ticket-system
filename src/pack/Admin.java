package pack;
import java.time.LocalDateTime;
import java.util.List;


public class Admin extends User{

	public Admin(String name, String surname, String email, String password) {
		super(name, surname, email, password);
	}
	
	public boolean addEvent(Event event) {
	
		for (Event e : Data.allEvents) {
			if(e.getId()==event.getId()) {
				return false;
			}
		}
		
		Data.allEvents.add(event);
		return true;
	}
	
	public boolean updateEventTime(int eventId, LocalDateTime newTime) {
		for (Event event : Data.allEvents) {
			if(event.getId()==eventId) {
				event.setEventTime(newTime);
				return true;
			}
		}
		return false;
		
	}
	public boolean updateEventPrice(int eventId, double newPrice) {
	    for (Event event : Data.allEvents) {
	        if (event.getId() == eventId) {
	            event.setBasePrice(newPrice); 
	            return true;
	        }
	    }
	    return false; 
	}
	
	public boolean deleteEvent(int eventId) {
		boolean isRemoved = Data.allEvents.removeIf(event -> event.getId() == eventId);
		return isRemoved;
	}

}
