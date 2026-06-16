package pack;

import java.time.LocalDateTime;


public class Event {
	
	private int id;
	private String name;
	private String type; //Cinema, theatre, concert
	private String posterPath;
	private LocalDateTime eventTime;
	private String place;
	private double basePrice;
	
	public Event(int id, String name, String type, LocalDateTime eventTime, String place, String posterPath,double basePrice) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.eventTime = eventTime;
		this.place=place;
		this.posterPath= posterPath;
		this.basePrice=basePrice;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	public String getPosterPath() {
		return posterPath;
	}
	
	public LocalDateTime getEventTime() {
		return eventTime;
	}
	public String getPlace() {
		return place;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setEventTime(LocalDateTime eventTime) {
	    this.eventTime = eventTime;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice=basePrice;
	}
	public void setPlace(String place) {
		this.place=place;
	}

}
