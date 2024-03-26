package backend.wallet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import backend.wallet.RecurringPayment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class RecurringPaymentTest {

//	// Test case for checking if today is a payment date after the first payment
//	@Test
//	public void testIsTodayAPaymentDate_FirstPayment() {
//	    // Set last payment date to a day before current date
//	    LocalDate lastPaymentDate = LocalDate.now().minusDays(1);
//	    RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);
//	    recurringPayment.setLastPaymentDateToToday(lastPaymentDate);
//
//	    // Check if today is a payment date
//	    assertTrue(recurringPayment.isTodayAPaymentDate());
//	}

	
    // Test case for checking if today is not a payment date after the first payment
    @Test
    public void testIsTodayNotAPaymentDate_FirstPayment() {
        LocalDate currentDate = LocalDate.now();
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);

        // Set last payment date to two days before current date
        recurringPayment.setLastPaymentDateToToday(currentDate.minusDays(2));

        assertFalse(recurringPayment.isTodayAPaymentDate());
    }

    // Test case for checking if today is a payment date for subsequent payments
    @Test
    public void testIsTodayAPaymentDate_SubsequentPayment() {
        LocalDate currentDate = LocalDate.now();
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);

        // Set last payment date to 30 days before current date
        recurringPayment.setLastPaymentDateToToday(currentDate.minusDays(30));

        assertTrue(recurringPayment.isTodayAPaymentDate());
    }

    // Test case for getting the last payment date as a string
    @Test
    public void testGetLastPaymentDateToString() {
        LocalDate currentDate = LocalDate.now();
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);

        // Set last payment date to a specific date
        recurringPayment.setLastPaymentDateToToday(LocalDate.of(2023, 5, 15));

        assertEquals("15-05-2023", recurringPayment.getLastPaymentDateToString());
    }

    // Test case for getting the days between payments
    @Test
    public void testGetDaysBetweenPayments() {
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);
        assertEquals(30, recurringPayment.getDaysBetweenPayments());
    }

    // Test case for getting the payment amount
    @Test
    public void testGetPaymentAmount() {
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);
        assertEquals(100.0, recurringPayment.getPaymentAmount());
    }

    // Test case for getting the poster ID
    @Test
    public void testGetPosterId() {
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);
        assertEquals(1, recurringPayment.getPosterId());
    }

    // Test case for getting the dog ID
    @Test
    public void testGetDogId() {
        RecurringPayment recurringPayment = new RecurringPayment(100.0, 30, 1, 1);
        assertEquals(1, recurringPayment.getDogId());
    }

}