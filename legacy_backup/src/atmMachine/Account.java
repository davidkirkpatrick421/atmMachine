package atmMachine;

/**
 * This is the account with private user fields stored to encapsulate data from ATM
 *
 * @author davidkirkpatrick
 */
public class Account {

    private final int pin;
    private double balance;

    /**
     * Constructor for Account class with arguments
     * Encapsulated to prevent direct access to private fields
     *
     * @param pin            - must be provided and must be 4 digits. Set in constructor and not accessible
     * @param initialBalance - balance account is created with. Must be greater than 0.
     * balance - after the initial balance is set, can only be changed via deposit and withdraw methods
     */
    public Account(int pin, double initialBalance) throws IllegalArgumentException {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException("Invalid PIN. Must be 4 digits.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance can't be negative.");
        }
        this.pin = pin;
        this.balance = initialBalance;
    }

    /**
     * Used in constructor to check if pin to set is a valid input (4 digits and greater than 0)
     *
     * @param pin - pin to check
     * @return a true or false to method call in constructor
     */
    private boolean isValidPin(int pin) {
        String pinStr = String.valueOf(pin);
        return pinStr.length() == 4 && pin >= 0;
    }

    /**
     * Method check if input pin matches stored pin when called in the ATM
     *
     * @param inputPin - collected from user in ATM via scanner
     * @return a true or false, if the user-input pin matches constructor-set pin
     */
    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    /**
     * Method to return balance of an account
     *
     * @return a double value - balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * takes user input and adds to balance
     *
     * @param amount - amount to add to balance
     * @return a true or false, if the user-input amount is greater than 0
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        this.balance += amount;
        return true;
    }

    /**
     * takes user input and subtracts from balance
     *
     * @param amount - amount to subtract from balance
     * @return a true or false, if the user-input amount is less than or equal to the balance
     */
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
}
