package backend.wallet;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import backend.database.Database;
import backend.dog.Dog;
import backend.poster.Poster;

public class Wallet {
	private double balance; 
	private int userid;
	private HashMap<Integer, RecurringPayment> recurringPayments = new HashMap<Integer, RecurringPayment>(); // dogid, payment

	public Wallet(double balance, int userid) {
		this.balance = balance;
		this.userid = userid;

	}
	
	public void addRecurringPayment(RecurringPayment payment) {
		this.recurringPayments.put(payment.getDogId(), payment);
		
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public void donate (double amount, Poster poster){
		
		this.balance = this.balance-amount;
		poster.depositDonation(amount);
	}
	
	public void makeRecurringPayments(Hashtable<Integer, Poster> posters){
		for(RecurringPayment recurrPay : this.recurringPayments.values()) {
			if(recurrPay.isTodayAPaymentDate()) {
					donate(recurrPay.getPaymentAmount(), posters.get(recurrPay.getPosterId()));

				recurrPay.setLastPaymentDateToToday(LocalDate.now());
			}
		}
	}

	public void deposit (double amount)
	{
		if (amount<=0)
			throw new IllegalArgumentException();
		else
		this.balance += amount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public class FundsTooLow extends Exception { 
		//	    public FundsTooLow(String errorMessage, Throwable causeError) {
		//	        super(errorMessage,causeError);
		//	    }
		public FundsTooLow(String errorMessage) {
			super(errorMessage);
		}

	}

	public void removeRecurringPayment(int dogid) {
		this.recurringPayments.remove(dogid);
	}

	public HashMap<Integer, RecurringPayment> getRecurringPayments() {
		return this.recurringPayments; 
	}

	public Set<Integer> getRecurringPaymentsDogsList () {
		 
		 return  this.recurringPayments.keySet();
	}

	public void setRecurringPayments(HashMap<Integer,RecurringPayment> map){
		this.recurringPayments = map;

	}
}