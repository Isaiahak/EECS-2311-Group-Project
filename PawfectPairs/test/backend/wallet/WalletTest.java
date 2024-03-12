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
	public void testPosterFunds(){
		wallet.donate(10.0, poster); 
		double actual= poster.getBalance();
		assertEquals(10.0, actual);
	}


}