package pack;
import java.util.List;
import java.util.ArrayList;

public class User {
	private String name,surname,email,password;
	private List<Ticket> purchaseHistory = new ArrayList<>();

	
	
	
	public User(String name, String surname, String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.purchaseHistory = new ArrayList<>();
	}

	
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getEmail() {
		return email;
	}	
	public String getPassword() {
		return password;
	}
	public List<Ticket> getPurchaseHistory() {
		return purchaseHistory;
	}
	
	
	public boolean signUp() {
		for(int i=0; i<Data.registeredUsers.size(); i++) {
			if(Data.registeredUsers.get(i).getEmail().equals(email)){
				return false;
			}
		}
		Data.registeredUsers.add(this);
		return true;		
	}
	
	public static String loginMessage = "";
	public static User login(String email, String password) {
		for(int i=0; i<Data.registeredUsers.size(); i++) {
			if(Data.registeredUsers.get(i).getEmail().equals(email)) {
				if(Data.registeredUsers.get(i).getPassword().equals(password)){
					loginMessage="Login successful";
					return Data.registeredUsers.get(i);
				}
				else {
					loginMessage="Wrong password";
					return null;
				}
			}
		}
		
		loginMessage="No user found";
		return null;
	}
	
	public static boolean isValidEmail(String email) {
	    if (email == null) {
	        return false;
	    }
	    return email.contains("@") && email.contains(".");
	}
	
	public void addTicketToHistory(Ticket ticket) {
	    this.purchaseHistory.add(ticket);
	}
}