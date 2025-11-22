package atmMachine;

/**
 *
 * Entry point for the ATM program.
 * Creates an account and starts the ATM interface.
 *
 * @author davidkirkpatrick
 */
public class ATMSystem {

    private static final int DEFAULT_PIN = 1234;
    private static final double INITIAL_BALANCE = 100.0;

    /**
     * ATM program start
     * Creates an account and starts the ATM interface.
     * @param args -
     */
    public static void main(String[] args) {

        Account account1 = new Account(DEFAULT_PIN, INITIAL_BALANCE);

        // TODO: Future Account account2 = new Account(5678, 100);

        ATMInterface atm = new ATMInterface(account1);

        atm.start();

    }
}
