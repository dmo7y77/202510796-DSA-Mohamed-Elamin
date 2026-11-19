import java.util.ArrayList;
import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int correctPin = 1234;
        int attempts = 0;
        int maxAttempts = 3;
        boolean loggedIn = false;

        // Loop for PIN login (up to 3 attempts)
        while (attempts < maxAttempts) {
            System.out.print("Enter your PIN: ");
            int enteredPin = scanner.nextInt();
            attempts++;

            if (enteredPin == correctPin) {
                loggedIn = true;
                System.out.println("Login successful!\n");
                break;
            } else {
                System.out.println("Wrong PIN. Attempts left: " + (maxAttempts - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many wrong attempts. Card blocked.");
            scanner.close();
            return;
        }

        double balance = 1000.0; // starting balance
        ArrayList<String> transactions = new ArrayList<>();
        int choice = 0;

        // Main menu loop until user chooses Exit (5)
        while (choice != 5) {
            System.out.println("\n--- ATM Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transactions");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Current Balance: $" + balance);
            } else if (choice == 2) {
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                if (amount > 0) {
                    balance += amount;
                    transactions.add("Deposited: $" + amount);
                    System.out.println("Deposit successful!");
                } else {
                    System.out.println("Invalid amount.");
                }
            } else if (choice == 3) {
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                if (amount > 0 && amount <= balance) {
                    balance -= amount;
                    transactions.add("Withdrew: $" + amount);
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance or invalid amount.");
                }
            } else if (choice == 4) {
                System.out.println("\n--- Transaction History ---");
                if (transactions.isEmpty()) {
                    System.out.println("No transactions yet.");
                } else {
                    for (String t : transactions) {
                        System.out.println(t);
                    }
                }
            } else if (choice == 5) {
                System.out.println("Thank you for using the ATM. Goodbye!");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}