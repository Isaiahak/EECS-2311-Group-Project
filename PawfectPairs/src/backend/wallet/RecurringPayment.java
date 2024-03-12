package backend.wallet;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import backend.wallet.Wallet.FundsTooLow;

public class RecurringPayment {
	double paymentAmount;
	int daysBetweenPayments; 
	LocalDate lastPaymentDate;
	int posterId;
	int dogId; 
	

	public RecurringPayment(double paymentAmount, int daysBetweenPayments, int dogId, int posterId) { // init first time
		this.paymentAmount = paymentAmount;
		this.daysBetweenPayments = daysBetweenPayments;
		this.dogId = dogId;
		this.posterId = posterId; 
		
        this.lastPaymentDate = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        this.lastPaymentDate = currentDate.format(formatter);
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
		
		if(lastPaymentDate.plusDays(daysBetweenPayments).equals(currentDate)) return true;
		
		return false;
	}
	
	public String getLastPaymentDateToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Format the date to a string
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
