package atmMachine;

import java.util.Scanner;

/**
 * This builds the menu interface for the ATM and handles user input via scanner.
 * Calls account methods and menu options.
 * Controls the atm program but instantiated and called in ATMSystem.
 *
 * @author davidkirkpatrick
 */
public class ATMInterface {

    private Account account;
    private Scanner scanner;

    // ASCII Interface Design formatting
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";

    public ATMInterface(Account account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=============================\n");
        System.out.println("-----" + BOLD + "Welcome to the ATM!" + RESET + "-----\n");
        System.out.println("=============================\n");

        if (checkPin()) {
            System.out.println(GREEN + "PIN correct." + RESET);
            displayMenu();
        } else {
            System.out.println(RED + "MAXIMUM PIN ATTEMPTS EXCEEDED.");
        }

        System.out.println("\n=============================");
        System.out.println("      --- Goodbye! ---      ");
        System.out.println("      Have a nice day!      ");
        System.out.println("=============================");

        scanner.close();
    }

    /**
     * Validates user scanner input for a PIN set in ATMSystem constructor.
     * Max attempts are set to 3 and if the user enters an incorrect PIN, they will be prompted to try again.
     *
     * @return - true if valid PIN, false if not.
     */
    private boolean checkPin() {
        final int MAX_ATTEMPTS = 3;
        int userPinAttempts = 0;

        while (userPinAttempts < MAX_ATTEMPTS) {
            System.out.print(RESET + BOLD + "Please enter your PIN - " + RESET);
            int userPin = scanner.nextInt();

            if (account.validatePin(userPin)) {
                return true;
            }
            userPinAttempts++;
            int remainingAttempts = MAX_ATTEMPTS - userPinAttempts;

            if (remainingAttempts > 0) {
                System.out.println();
                System.out.println(RED + "Incorrect PIN");
                System.out.println(YELLOW + "You have " + remainingAttempts + " attempts remaining.");
                System.out.println();
            }
        }
        return false;
    }

    private void displayMenu() {

        MenuOption menuOption;
        do {


            System.out.println();
            System.out.println("=============================");
            System.out.println(BOLD + "   Please select an option" + RESET);
            System.out.println("=============================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.println("=============================");
            System.out.println();
            System.out.print("Enter your choice then press enter: ");
            menuOption = MenuOption.getOption(scanner.nextInt());

            System.out.println();

            // Check if the option is valid (enum validation) - else replace a default message.
            if (menuOption != null)

                switch (menuOption) {
                    case CHECK_BALANCE:
                        checkBalance();
                        System.out.println();
                        break;

                    case DEPOSIT:
                        doDeposit();
                        System.out.println();
                        break;

                    case WITHDRAW:
                        doWithdrawal();
                        System.out.println();
                        break;

                    case EXIT:
                        System.out.println("Thanks for using the ATM");
                        break;
                }

            else {
                System.out.println(YELLOW + "Invalid choice. Please try again." + RESET);
            }

            // menu repeat until option 4 is selected.
        } while (menuOption != MenuOption.EXIT);

    }

    // Methods for menu options - Don't need validation here; handled in Account methods

    /**
     * Prints the current balance of the account to the console. Calls method from the Account class
     */
    private void checkBalance() {
        System.out.printf("Your current balance is £%.2f", account.getBalance());
    }

    /**
     * Handles the deposit of money into the account.
     */
    private void doDeposit() {
        System.out.println("Please enter the amount to deposit: £");
        double amount = scanner.nextDouble();

        if (account.deposit(amount)) {
            System.out.printf(GREEN + "\nYou have deposited £%.2f\n" + RESET, amount);

            System.out.printf("Your new balance is £%.2f", account.getBalance());
        } else {
            System.out.println(RED + "Deposit failed. Must be greater than zero." + RESET);
        }
    }

    /**
     * Handles the withdrawal of money from the account.
     */
    private void doWithdrawal() {
        System.out.println("Please enter the amount to withdraw: £");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {
            System.out.printf(GREEN + "\nYou have withdrawn £%.2f\n" + RESET, amount);

            System.out.printf("Your new balance is £%.2f", account.getBalance());
        } else {
            if (amount <= 0) {
                System.out.println(RED + "Withdrawal failed. Must be greater than 0." + RESET);

            } else {
                System.out.println(RED + "Sorry, you have insufficient funds." + RESET);
                System.out.printf(RED + "Available Balance: £%.2f\n"+ RESET,account.getBalance());
            }

        }
    }


}
