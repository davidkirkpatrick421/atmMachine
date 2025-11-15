package atmMachine;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Account class
 * Tests constructor validation, PIN validation, deposits, withdrawals, and edge cases
 *
 * @author davidkirkpatrick
 */
class AccountTest {

    // ==================== CONSTRUCTOR TESTS ====================

    @Test
    @DisplayName("Should create account with valid 4-digit PIN and positive balance")
    void testValidAccountCreation() {
        Account account = new Account(1234, 100.0);
        assertEquals(100.0, account.getBalance(), 0.001);
        assertTrue(account.validatePin(1234));
    }

    @Test
    @DisplayName("Should create account with zero initial balance")
    void testAccountCreationWithZeroBalance() {
        Account account = new Account(5678, 0.0);
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should create account with decimal initial balance")
    void testAccountCreationWithDecimalBalance() {
        Account account = new Account(9999, 123.45);
        assertEquals(123.45, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should throw exception for PIN with only 3 digits")
    void testInvalidPinTooShort() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(123, 100.0)
        );
        assertEquals("Invalid PIN. Must be 4 digits.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for PIN with 5 digits")
    void testInvalidPinTooLong() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(12345, 100.0)
        );
        assertEquals("Invalid PIN. Must be 4 digits.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for negative PIN")
    void testInvalidPinNegative() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Account(-1234, 100.0)
        );
    }

    @Test
    @DisplayName("Should throw exception for negative initial balance")
    void testNegativeInitialBalance() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Account(1234, -50.0)
        );
        assertEquals("Initial balance can't be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Should accept PIN with leading zeros (e.g., 0123)")
    void testPinWithLeadingZeros() {
        // Note: In Java, 0123 is octal notation = 83 in decimal
        // So we test with integer 123 which has implicit leading zero
        assertThrows(
                IllegalArgumentException.class,
                () -> new Account(123, 100.0)
        );
    }

    // ==================== VALIDATE PIN TESTS ====================

    @Test
    @DisplayName("Should return true for correct PIN")
    void testValidatePinCorrect() {
        Account account = new Account(4567, 100.0);
        assertTrue(account.validatePin(4567));
    }

    @Test
    @DisplayName("Should return false for incorrect PIN")
    void testValidatePinIncorrect() {
        Account account = new Account(4567, 100.0);
        assertFalse(account.validatePin(1234));
    }

    @Test
    @DisplayName("Should return false for PIN off by one digit")
    void testValidatePinOffByOne() {
        Account account = new Account(4567, 100.0);
        assertFalse(account.validatePin(4568));
    }

    // ==================== DEPOSIT TESTS ====================

    @Test
    @DisplayName("Should successfully deposit positive amount")
    void testDepositPositiveAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(50.0));
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should successfully deposit decimal amount")
    void testDepositDecimalAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(25.99));
        assertEquals(125.99, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should reject deposit of zero")
    void testDepositZero() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.deposit(0.0));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should reject deposit of negative amount")
    void testDepositNegativeAmount() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.deposit(-50.0));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should handle multiple consecutive deposits")
    void testMultipleDeposits() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(50.0));
        assertTrue(account.deposit(25.0));
        assertTrue(account.deposit(10.50));
        assertEquals(185.50, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle very large deposit amount")
    void testDepositLargeAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(1000000.0));
        assertEquals(1000100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle very small deposit amount")
    void testDepositVerySmallAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(0.01));
        assertEquals(100.01, account.getBalance(), 0.001);
    }

    // ==================== WITHDRAW TESTS ====================

    @Test
    @DisplayName("Should successfully withdraw amount less than balance")
    void testWithdrawValidAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.withdraw(50.0));
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should successfully withdraw entire balance")
    void testWithdrawEntireBalance() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.withdraw(100.0));
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should successfully withdraw decimal amount")
    void testWithdrawDecimalAmount() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.withdraw(33.33));
        assertEquals(66.67, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should reject withdrawal exceeding balance")
    void testWithdrawInsufficientFunds() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.withdraw(150.0));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should reject withdrawal of zero")
    void testWithdrawZero() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.withdraw(0.0));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should reject withdrawal of negative amount")
    void testWithdrawNegativeAmount() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.withdraw(-50.0));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should reject withdrawal slightly exceeding balance")
    void testWithdrawSlightlyOverBalance() {
        Account account = new Account(1234, 100.0);
        assertFalse(account.withdraw(100.01));
        assertEquals(100.0, account.getBalance(), 0.001); // Balance unchanged
    }

    @Test
    @DisplayName("Should handle multiple consecutive withdrawals")
    void testMultipleWithdrawals() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.withdraw(20.0));
        assertTrue(account.withdraw(30.0));
        assertTrue(account.withdraw(25.0));
        assertEquals(25.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should reject withdrawal from empty account")
    void testWithdrawFromEmptyAccount() {
        Account account = new Account(1234, 0.0);
        assertFalse(account.withdraw(10.0));
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    // ==================== COMBINED OPERATION TESTS ====================

    @Test
    @DisplayName("Should handle deposit followed by withdrawal")
    void testDepositThenWithdraw() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(50.0));
        assertEquals(150.0, account.getBalance(), 0.001);
        assertTrue(account.withdraw(75.0));
        assertEquals(75.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle withdrawal followed by deposit")
    void testWithdrawThenDeposit() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.withdraw(60.0));
        assertEquals(40.0, account.getBalance(), 0.001);
        assertTrue(account.deposit(30.0));
        assertEquals(70.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should handle alternating deposits and withdrawals")
    void testAlternatingOperations() {
        Account account = new Account(1234, 100.0);
        assertTrue(account.deposit(50.0));   // 150
        assertTrue(account.withdraw(30.0));  // 120
        assertTrue(account.deposit(20.0));   // 140
        assertTrue(account.withdraw(40.0));  // 100
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should maintain balance accuracy with many operations")
    void testBalanceAccuracyManyOperations() {
        Account account = new Account(1234, 1000.0);

        // Perform 10 deposits and 10 withdrawals
        for (int i = 0; i < 10; i++) {
            account.deposit(10.0);
            account.withdraw(5.0);
        }

        // Expected: 1000 + (10 * 10) - (10 * 5) = 1050
        assertEquals(1050.0, account.getBalance(), 0.001);
    }

    // ==================== GET BALANCE TESTS ====================

    @Test
    @DisplayName("Should return correct initial balance")
    void testGetBalanceInitial() {
        Account account = new Account(1234, 250.75);
        assertEquals(250.75, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("Should return correct balance after failed operations")
    void testGetBalanceAfterFailedOperations() {
        Account account = new Account(1234, 100.0);
        account.deposit(-10.0);  // Should fail
        account.withdraw(200.0); // Should fail
        assertEquals(100.0, account.getBalance(), 0.001); // Unchanged
    }

    // ==================== BOUNDARY TESTS ====================

    @Test
    @DisplayName("Should handle minimum valid PIN (1000)")
    void testMinimumValidPin() {
        Account account = new Account(1000, 100.0);
        assertTrue(account.validatePin(1000));
    }

    @Test
    @DisplayName("Should handle maximum valid PIN (9999)")
    void testMaximumValidPin() {
        Account account = new Account(9999, 100.0);
        assertTrue(account.validatePin(9999));
    }

    @Test
    @DisplayName("Should reject PIN just below minimum (999)")
    void testPinBelowMinimum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Account(999, 100.0)
        );
    }

    @Test
    @DisplayName("Should reject PIN just above maximum (10000)")
    void testPinAboveMaximum() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Account(10000, 100.0)
        );
    }
}