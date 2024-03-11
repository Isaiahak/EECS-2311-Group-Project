package test.backend.wallet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import backend.wallet.Wallet;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class WalletTest {

    private Wallet wallet;

    @Before
    public void setUp() {
        // Initialize a wallet with some initial values for testing
        wallet = new Wallet(100.0, true, 24, 123, 50, 0, 0);
        wallet.setOldPaymentDate(LocalDate.now().minusDays(25)); // Set an old payment date for testing recurring payments
    }

	@Test
    public void testDeposit() {
        wallet.deposit(50.0);
        assertEquals(150.0, wallet.getBalance());
    }

    @Test
    public void testDonateSufficientFunds() throws Wallet.FundsTooLow {
        wallet.donate(30.0, 1);
        assertEquals(70.0, wallet.getBalance(), 0.01);
        assertEquals(30.0, wallet.getPosterBalance(1), 0.01);
    }

    @Test(expected = Wallet.FundsTooLow.class)
    public void testDonateInsufficientFunds() throws Wallet.FundsTooLow {
        wallet.donate(150.0, 1); // Trying to donate more than the available balance
    }

    @Test
    public void testRecurringPaymentSuccess() {
        assertTrue(wallet.doRecurringPayment(40.0, LocalDate.now().minusDays(24), 24, 2));
        assertEquals(60.0, wallet.getBalance(), 0.01);
        assertEquals(LocalDate.now(), wallet.getOldPaymentDate());
    }

    @Test
    public void testRecurringPaymentNotDue() {
        assertFalse(wallet.doRecurringPayment(40.0, LocalDate.now().minusDays(20), 24, 2));
        assertEquals(100.0, wallet.getBalance(), 0.01);
        assertEquals(LocalDate.now().minusDays(25), wallet.getOldPaymentDate());
    }

    @Test
    public void testRedoRecurringPayment() {
        // Note: This test doesn't assert any values; it mainly checks if the method can be executed without errors
        wallet.redo(40.0, 24, 2);
        // Ensure that the test waits for scheduled executions (time delay set in redo method)
        try {
            Thread.sleep(6 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPosterWallets() {
        Map<Integer, Double> expectedWallets = new TreeMap<>();
        expectedWallets.put(1, 30.0);
        expectedWallets.put(2, 40.0);
        wallet.setPosterWallets(expectedWallets);
        assertEquals(expectedWallets, wallet.getPosterWallets());
    }
    
    @Test
    public void testDoRecurringPaymentSuccess() {
        boolean result = wallet.doRecurringPayment(40.0, LocalDate.now().minusDays(24), 24, 2);

        assertTrue(result);
        assertEquals(60.0, wallet.getBalance(), 0.01);
        assertEquals(LocalDate.now(), wallet.getOldPaymentDate());
    }

    @Test
    public void testDoRecurringPaymentNotDue() {
        boolean result = wallet.doRecurringPayment(40.0, LocalDate.now().minusDays(20), 24, 2);

        assertFalse(result);
        assertEquals(100.0, wallet.getBalance(), 0.01);
        assertEquals(LocalDate.now().minusDays(25), wallet.getOldPaymentDate());
    }
}
