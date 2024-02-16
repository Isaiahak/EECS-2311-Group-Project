package user;

public class Wallet {
	
	private int balance;
	
	// payment method
	
	private int walletID;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

}
