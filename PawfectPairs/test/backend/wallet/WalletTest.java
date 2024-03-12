import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;
class WalletTest {
	public Wallet wallet;
	public Poster poster;
	public User user;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		user= new User("a", "a");
		wallet = new Wallet(50.0,user.getUserID());
		poster =new Poster(8, "testingPoster", 1, "123456789", "testingPoster@hotmail.com", 0.0);

	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void testDeposit() {
		wallet.deposit(50.0);
		assertEquals(100.0, wallet.getBalance());
	}

	@Test
	public void testDonateSufficientFunds() {
		wallet.donate(30.0, poster);
		assertEquals(20.0, wallet.getBalance(), "balance is incorrect");
	}

	@Test
	public void testDonateInsufficientFunds() throws Wallet.FundsTooLow {
		wallet.donate(150.0, poster); // Trying to donate more than the available balance
	}


}
/*
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.Before;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;

public class WalletTest {

	public Wallet wallet;
	public Poster poster;
	public User user;
	@Before
	public void setUp() {
		// Initialize a wallet with some initial values for testing
		// wallet = new Wallet(100.0, true, 24, 123, 50, 0, 0);
		user= new User("a", "a");
		wallet = new Wallet(50.0,user.getUserID());
		poster =new Poster(8, "testingPoster", 1, "123456789", "testingPoster@hotmail.com", 0.0);
	}

	@Test
	public void testDeposit() {
		wallet.deposit(50.0);
		assertEquals(100.0, wallet.getBalance());
	}

	@Test
	public void testDonateSufficientFunds() throws Wallet.FundsTooLow {
		wallet.donate(30.0, poster);
		assertEquals(70.0, wallet.getBalance(), 0.01);
	}

	@Test(expected = Wallet.FundsTooLow.class)
	public void testDonateInsufficientFunds() throws Wallet.FundsTooLow {
		wallet.donate(150.0, poster); // Trying to donate more than the available balance
	}

	
}
*/