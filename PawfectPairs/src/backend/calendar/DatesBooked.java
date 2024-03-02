package backend.calendar;
import backend.poster.*;
import java.util.Date;

import backend.dog.Dog;
import backend.user.User;

public class DatesBooked {
	private int posterID;
	private int dogID;
	private int userID;
	private Date date;
	

    public DatesBooked(int posterID, int dogID, int userID, Date date ) {
        this.posterID = posterID;
        this.dogID = dogID;
        this.date = date;
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
	
	public boolean isDuplicateBooking(DatesBooked otherBooking) {
        return this.posterID == otherBooking.getPosterID() &&
               this.dogID == otherBooking.getDogID() &&
               this.date.equals(otherBooking.getDate());
    }

    
}
