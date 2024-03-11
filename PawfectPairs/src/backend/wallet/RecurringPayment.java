package backend.wallet;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecurringPayment {
	double paymentAmount;
	int daysBetweenPayments; 
	LocalDate lastPaymentDate;
	int posterId;
	
	public RecurringPayment(double paymentAmount, int daysBetweenPayments, String lastPaymentDate, int posterId) {
		this.paymentAmount = paymentAmount;
		this.daysBetweenPayments = daysBetweenPayments;
		this.posterId = posterId; 

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    this.lastPaymentDate = LocalDate.parse(lastPaymentDate, formatter);

	} 
	
	public boolean isTodayAPaymentDate() {
		
		LocalDate currentDate = LocalDate.now();
		
		if(lastPaymentDate.plusDays(daysBetweenPayments).equals(currentDate)) return true;
		
		return false;
	}
	
	

}
