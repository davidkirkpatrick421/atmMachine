package atmMachine;

import java.util.Scanner;

public class ATMMachine {

    public static void main(String[] args) {

    // Implement scanner
    Scanner scanner = new Scanner(System.in);

    // Declare and initiate variables
    int storedPin;
    double balance;

    balance = 100;
    storedPin = 1234;

    int userChoice; // initiated with scanner class below
    int userPin; // initiated with scanner class below

    // PIN validation and initiation of constant max attempts var
    final int MAX_ATTEMPTS = 3;
    int userPinAttempts = 0;
    boolean pinCorrect = false;

		while (userPinAttempts < MAX_ATTEMPTS && !pinCorrect) { // Validation of PIN check. Attempts must be less than
        // max attempts AND pin must match stored PIN.

        // Check user PIN - if matches, enter else reject
        System.out.print("Hello! Please enter your PIN. ");

        userPin = scanner.nextInt();

        if (storedPin == userPin) {
            pinCorrect = true;
            System.out.println("PIN correct.");

            // IF PIN correct - RUN
            do {

                // Present user menu - switch statement
                System.out.println("");
                System.out.println("Please select an option:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Exit");

                System.out.println("");
                System.out.print("Enter your choice: then press enter ");
                userChoice = scanner.nextInt();

                // System.out.println(userChoice);

                switch (userChoice) {
                    case 1:
                        System.out.println("");
                        System.out.printf("Your balance is: £%.2f\n", balance);
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: £");
                        double deposit = scanner.nextDouble();
                        balance = balance + deposit;

                        System.out.println("");
                        System.out.printf("You deposited %.2f. New balance: £%.2f\n", deposit, balance);

                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: £");
                        double withdrawal = scanner.nextDouble();

                        balance = balance - withdrawal;

                        if (balance >= withdrawal) {
                            System.out.println("");
                            System.out.printf("You withdrew %.2f. New balance: £%.2f\n", withdrawal, balance);
                        } else {
                            System.out.println("");
                            System.out.println("Sorry, you have insufficient funds.");
                        }

                        break;

                    case 4:
                        System.out.println("");
                        System.out.println("Thanks for using the ATM");
                        break;

                    default:
                        System.out.println("");
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

            } while (userChoice != 4);

        } else { // IF PIN is not correct

            userPinAttempts++;

            int remainingAttempts = MAX_ATTEMPTS - userPinAttempts;
            if (remainingAttempts > 0) {

                System.out.println("");
                System.out.println("Sorry, incorrect PIN.\n");
                System.out.println("You have "+remainingAttempts+" attempts remaining.\n");
            } else {
                System.out.println("");
                System.out.println("Maximum PIN attempts exceeded.");
            }
        }
    }

		System.out.println("Thank you and Goodbye!");

    // close scanner
		scanner.close();
}
}
