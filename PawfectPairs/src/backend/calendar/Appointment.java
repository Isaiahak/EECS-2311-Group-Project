package backend.calendar;

import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
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
	
	public Appointment(Appointment appointment) {
		// TODO Auto-generated constructor stub
		this.posterID = appointment.getPosterID();
		this.dogID = appointment.getDogID();
		this.date = appointment.getDate();
		this.userID = appointment.getUserID();
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

	@Override
	public String toString() {
		return "Appointment [posterID=" + posterID + ", dogID=" + dogID + ", date=" + date + ", userID=" + userID + "]";
	}

	public static boolean checkIfBefore (LocalDate currentSelectedDate) {
		java.util.Date utilDate = Date.from(currentSelectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());


		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


		java.sql.Date nowSQL=Date.valueOf(LocalDate.now());
		LocalDate nowLocal = LocalDate.now();
		LocalDate appointmentDate = sqlDate.toLocalDate();
		return sqlDate.before(nowSQL)&&appointmentDate.getMonthValue()==nowLocal.getMonthValue();

	}
	public boolean leapYear (LocalDate date) {
		// Get the YearMonth object for the LocalDate
		YearMonth yearMonth = YearMonth.from(date);

		// Check if the year of the YearMonth is a leap year
		boolean isLeapMonth = yearMonth.isLeapYear();
		return isLeapMonth;
	}

	@Override
	public boolean equals(Object o) {
		if(this.dogID == ((Appointment) o).getDogID()  && this.date.equals(((Appointment) o).getDate())) return true;
		
		return false;
		
	}
	
}
