package backend.calendar;

import java.sql.Date;

public class Appointment {
	private int posterID;
	private int dogID;
	private Date date;
	private int userID;
	
	
	
	public Appointment(int posterID, int dogID, Date date, int userID) {
		super();
		this.posterID = posterID;
		this.dogID = dogID;
		this.date = date;
		this.userID = userID;
	}
	
	public int getPosterID() {
		return posterID;
	}
	public void setPosterID(int posterID) {
		this.posterID = posterID;
	}
	public int getDogID() {
		return dogID;
	}
	public void setDogID(int dogID) {
		this.dogID = dogID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	

}
