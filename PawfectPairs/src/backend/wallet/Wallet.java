package backend.wallet;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import backend.database.Database;
import backend.poster.Poster;

public class Wallet {
	private double balance; 
	private boolean recurringPayment; 
	private int frequency;//set up to count in 24 days 
	private int userid;
	private Map<Integer, Double> posterWallets = new TreeMap<>();
	private int recurringAmount;
	private int posterToSponsorPending;
	private int recurringPoster;
	LocalDate oldPaymentDate;

	public LocalDate getOldPaymentDate() {
		return oldPaymentDate;
	}
	public void setOldPaymentDate(LocalDate oldPaymentDate) {
		this.oldPaymentDate = oldPaymentDate;
	}
	public int getRecurringAmount() {
		return recurringAmount;
	}
	public void setRecurringAmount(int recurringAmount) {
		this.recurringAmount = recurringAmount;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	//	public Wallet(double balance, boolean recurringPayment, int frequency, int userid, int recurringAmount) {
	//		this.balance = balance;
	//		this.recurringPayment = recurringPayment;
	//		this.frequency = frequency;
	//		this.userid=userid;
	//	}
	public Wallet(double balance, boolean recurringPayment, int frequency, int userid,int recurringAmount, int posterToSponsorPending, int recurringPoster) {
//		super();
		this.balance = balance;
		this.recurringPayment = recurringPayment;
		this.frequency = frequency;
		this.userid = userid;
//		this.posterWallets = posterWallets;
		this.recurringAmount = recurringAmount;
	}
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public void donate (double amount, int posterid) throws FundsTooLow {//withdraw from wallet ->donate to poster
		if (amount>this.balance)
			throw new FundsTooLow("Deposit amount exceeds balance");
		
		this.balance=this.balance-amount;
		//Database.setPosterFunds(amount, poster.getUniqueId());
		//	int keyToUpdate = poster.getUniqueId();
		int keyToUpdate = posterid;
		Double oldValue = posterWallets.getOrDefault(keyToUpdate, 0.0); // Default to 0 if key doesn't exist
		double additionalValue = amount;
		double newValue = oldValue + additionalValue;

		// Updating the treemap with the new value
		posterWallets.put(keyToUpdate, newValue);
		


	}

	public Runnable donateRe (double amount, int posterid) throws FundsTooLow {//withdraw from wallet ->donate to poster
		if (amount>this.balance)
			throw new FundsTooLow("Deposit amount exceeds balance");

		System.out.println("execute start");
		this.balance=this.balance-amount;
		//Database.setPosterFunds(amount, poster.getUniqueId());
		//	int keyToUpdate = poster.getUniqueId();
		int keyToUpdate = posterid;
		Double oldValue = posterWallets.getOrDefault(keyToUpdate, 0.0); // Default to 0 if key doesn't exist
		double additionalValue = amount;
		double newValue = oldValue + additionalValue;

		// Updating the treemap with the new value
		posterWallets.put(keyToUpdate, newValue);
		System.out.println("execute: new balance "+this.balance);
		return () -> System.out.println("Non-static recurring event executed at: " + System.currentTimeMillis());


	}

	public Map<Integer, Double> getPosterWallets() {
		return posterWallets;
	}
	public double getPosterBalance (int posterid) {
		return this.posterWallets.get(posterid);

	}
	public void setPosterWallets(Map<Integer, Double> posterWallets) {
		this.posterWallets = posterWallets;
	}
	public void deposit (double amount)
	{
		this.balance=this.balance+amount;
	}

//
//	public String singlePayment (double amount, int posterid) {//rn set up to return error message if failed or nothing if success
//		//boolean issues= true;
//		try {
//			donate(amount, posterid);
//			//issues=false;
//		}
//		catch (FundsTooLow e) {
//			return e.getMessage();
//
//		}
//		finally {
//			return "";
//		}
//	}

	public String singlePayment(double amount, int posterid) throws FundsTooLow {
	    try {
	        donate(amount, posterid);
	        return ""; // Return an empty string if donation is successful
	    } catch (FundsTooLow e) {
	        throw e; // Re-throw the exception to propagate it up the call stack
	    }
	}
	
	public static void test () {
		System.out.println("cute "+LocalDate.now() + " "+ java.time.LocalDateTime.now());

	}

	public boolean doRecurringPayment (double amount, LocalDate lastDate, int frequency, int posterid) {

		LocalDate current = LocalDate.now();

		int timeRemaininginDays = frequency;
		LocalDate DateToPay = lastDate.plusDays(timeRemaininginDays);

		if(current.equals(DateToPay)) {
			try {
				donate(amount,posterid);
			} catch (FundsTooLow e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setOldPaymentDate(LocalDate.now());

		}
		return current.equals(DateToPay);
	}
	/*	public static void RecurringPayment (double amount, LocalDate initial, int frequency, int posterid) {
//		LocalDate currentDate= LocalDate.now();
//		LocalDate next=initial.plusDays(frequency);
		//if (current==next)
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	        // Schedule a task to run every 1 day
	        //scheduler.scheduleAtFixedRate(Wallet::test, 0, 1, TimeUnit.DAYS);
	        scheduler.scheduleAtFixedRate(Wallet::test, 0, frequency, TimeUnit.MINUTES);

	} */
	/*//BAD
	public void RecurringPayment (double amount, int frequency, int posterid) {
		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		    scheduler.scheduleAtFixedRate(this::
				donateRe(amount, posterid), 0, 1, TimeUnit.DAYS);

//	        // Schedule a non-static method to run every 1 day
//		    scheduler.scheduleAtFixedRate(() -> {
//				try {
//					donateRe(amount, posterid);
//				} catch (FundsTooLow e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}, 0, 1, TimeUnit.DAYS);

	}
	 */

	//timer method
	public void redo (double amount, int frequency, int id ) {
		//		if(this.recurringPayment) {
		//		 ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		//
		//	        // Schedule the non-static method for execution after an initial delay and then at a fixed rate
		//	        int initialDelay = 0; // delay before the first execution
		//	        int period = 1; // time between subsequent executions
		//	        TimeUnit timeUnit = TimeUnit.MINUTES;
		//
		//	        scheduler.scheduleAtFixedRate(() -> {
		//				try {
		//					this.donateRe(amount, frequency);
		//				} catch (FundsTooLow e) {
		//					// TODO Auto-generated catch block
		//					e.printStackTrace();
		//				}
		//			}, initialDelay, period, timeUnit);
		//
		//	        // Sleep for a while to allow scheduled executions
		//	        try {
		////	            Thread.sleep(5000); // Sleep for 5 seconds
		//	     //       Thread.sleep(1); // Sleep for frequency// DIDNT WORK 
		//	            Thread.sleep(1 * 60 * 1000); // Sleep for 1 minute
		//	         //   Thread.sleep(2 * 1000); // Sleep for 30 seconds
		//
		//
		//	        } catch (InterruptedException e) {
		//	            e.printStackTrace();
		//	        }
		//
		//	        // Shutdown the scheduler when done
		//	        scheduler.shutdown();
		//		}
//
//		if (this.recurringPayment) {
//			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//			// Schedule the non-static method for execution after an initial delay and then at a fixed rate
//			int initialDelay = 0; // delay before the first execution
//			int period = this.frequency; // time between subsequent executions (in seconds)
//			TimeUnit timeUnit = TimeUnit.SECONDS;
//
//			scheduler.scheduleAtFixedRate(() -> {
//				try {
//					this.donateRe(amount, this.frequency);
//				} catch (FundsTooLow e) {
//					e.printStackTrace();
//				}
//			}, initialDelay, period, timeUnit);
//
//			// Sleep for a while to allow scheduled executions
//			try {
//				Thread.sleep(5 * 1000); // Sleep for 30 seconds//cant be 0 or nothing works//cant be 60 or program stops resoinding and doesnt update balance on time
//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//			// Shutdown the scheduler when done
//			// scheduler.shutdown(); //!!commented this out
//		}
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean getRecurringPayment() {
		return recurringPayment;
	}
	public void setRecurringPayment(boolean recurringPayment) {
		this.recurringPayment = recurringPayment;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public int getPosterToSponsorPending() {
		return posterToSponsorPending;
	}
	public void setPosterToSponsorPending(int posterToSponsorPending) {
		this.posterToSponsorPending = posterToSponsorPending;
	}


	public int getRecurringPoster() {
		return recurringPoster;
	}
	public void setRecurringPoster(int recurringPoster) {
		this.recurringPoster = recurringPoster;
	}


	public class FundsTooLow extends Exception { 
		//	    public FundsTooLow(String errorMessage, Throwable causeError) {
		//	        super(errorMessage,causeError);
		//	    }
		public FundsTooLow(String errorMessage) {
			super(errorMessage);
		}

	}
}