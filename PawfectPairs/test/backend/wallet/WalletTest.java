//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class WalletTest {
//
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
//
//}



import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import backend.poster.Poster;
import backend.user.User;
import backend.wallet.Wallet;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

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
