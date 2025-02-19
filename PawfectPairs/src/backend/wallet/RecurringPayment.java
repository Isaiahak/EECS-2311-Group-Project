package backend.wallet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class RecurringPayment {
	private double paymentAmount;
	private int daysBetweenPayments; 
	private LocalDate lastPaymentDate;
	private int posterId;
	private int dogId; 

		public RecurringPayment(double paymentAmount, int daysBetweenPayments, int dogId, int posterId) { // init first time
		this.paymentAmount = paymentAmount;
		this.daysBetweenPayments = daysBetweenPayments;
		this.dogId = dogId;
		this.posterId = posterId;
        this.lastPaymentDate = LocalDate.now();
        } 
	
	public RecurringPayment(double paymentAmount, int daysBetweenPayments, int dogId, int posterId, String lastPaymentDate) { // from db
		this.paymentAmount = paymentAmount;
		this.daysBetweenPayments = daysBetweenPayments;
		this.dogId = dogId;
		this.posterId = posterId; 

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    this.lastPaymentDate = LocalDate.parse(lastPaymentDate, formatter);

	} 
	
	public int getDaysBetweenPayments() {
		return this.daysBetweenPayments;
	}
	
	public boolean isTodayAPaymentDate() {
		
		LocalDate currentDate = LocalDate.now();
		if(lastPaymentDate.plusDays(daysBetweenPayments).compareTo(currentDate) <= 0) return true;
		
		return false;
	}
	
	public String getLastPaymentDateToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.lastPaymentDate.format(formatter);
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setLastPaymentDateToToday(LocalDate today) {
		this.lastPaymentDate = today;
	}

	public int getPosterId() {
		return this.posterId;
	}

	public int getDogId() {
		return this.dogId;
	}
}
