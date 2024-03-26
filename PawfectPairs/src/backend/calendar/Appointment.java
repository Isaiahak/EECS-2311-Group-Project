package backend.calendar;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class Appointment{
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
	public LocalDate getLocalDate() {
		return this.date.toLocalDate();
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

	public static boolean checkIfBefore (LocalDate currentSelectedDate) {
		java.util.Date utilDate = Date.from(currentSelectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


		java.sql.Date nowSQL=Date.valueOf(LocalDate.now());
		LocalDate nowLocal = LocalDate.now();
		LocalDate appointmentDate = sqlDate.toLocalDate();
		return sqlDate.before(nowSQL)&&appointmentDate.getMonthValue()==nowLocal.getMonthValue();

	}

}
