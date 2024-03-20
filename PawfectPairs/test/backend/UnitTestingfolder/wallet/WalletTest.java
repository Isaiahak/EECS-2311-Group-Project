import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import backend.dog.Dog;
import backend.poster.Poster;
import backend.user.User;
import backend.wallet.RecurringPayment;
import backend.wallet.Wallet;
class WalletTest {
	public Wallet wallet;
	public Poster poster;
	public User user;
	public Dog dog;
	public Dog dog2;

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
		dog = new Dog("Mr.dog", 1, 3, 0, 1, 0, poster.getUniqueId(), false, "", "Mr.dog is a good dog");
		dog2 = new Dog("dog2", 1, 3, 0, 1, 0, poster.getUniqueId(), false, "", "dog2 is a good dog");
       //  dog = new Dog("Max", 1, 3, 0, 1, 0, poster.getUniqueId(), false,null,"bio");

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

	
	
	


	@Test
	public void recurringPayments(){
		int amountToDonate =1;
		int daysBetweenPayments=7;	
		RecurringPayment r1 =new RecurringPayment(amountToDonate, daysBetweenPayments, dog.getId(), dog.getPosterId());
		
		wallet.addRecurringPayment(r1);
		
		assertTrue(wallet.getRecurringPayments().containsKey(dog.getId()));
	}
	

	@Test
	public void recurringPayments2(){
		int amountToDonate =1;
		int newAmount=5;
		int daysBetweenPayments=7;	
		RecurringPayment r1 =new RecurringPayment(amountToDonate, daysBetweenPayments, dog.getId(), dog.getPosterId());
		wallet.addRecurringPayment(r1);
		
		RecurringPayment r2 =new RecurringPayment(amountToDonate, daysBetweenPayments, dog2.getId(), dog2.getPosterId());
		
		wallet.addRecurringPayment(r2);
		
		
		RecurringPayment r1SameDog =new RecurringPayment(newAmount, daysBetweenPayments, dog.getId(), dog.getPosterId());
wallet.addRecurringPayment(r1SameDog);
assertTrue(wallet.getRecurringPayments().containsKey(dog.getId()));
		assertTrue(wallet.getRecurringPayments().containsKey(dog2.getId()));
		//assertEquals(wallet.getRecurringPayments().get(dog).getPaymentAmount(),newAmount);
		
		

	}
	
//	@Test
//	public void integrationTest(){
//		int amountToDonate =1;
//		int newAmount=5;
//		int daysBetweenPayments=7;	
//		RecurringPayment r1 =new RecurringPayment(amountToDonate, daysBetweenPayments, dog.getId(), dog.getPosterId());
//		wallet.addRecurringPayment(r1);
//		
//		RecurringPayment r2 =new RecurringPayment(amountToDonate, daysBetweenPayments, dog2.getId(), dog2.getPosterId());
//		
//		wallet.addRecurringPayment(r2);
//		
//		
//		RecurringPayment r1SameDog =new RecurringPayment(newAmount, daysBetweenPayments, dog.getId(), dog.getPosterId());
//wallet.addRecurringPayment(r1SameDog);
//assertTrue(wallet.getRecurringPayments().containsKey(dog.getId()));
//assertTrue(wallet.getRecurringPayments().containsKey(dog2.getId()));
//		assertEquals(wallet.getRecurringPayments().get(dog).getPaymentAmount(),newAmount);
//		
//		
//
//	}
//	
	
	
	
	
}