import java.io.*;
import java.util.*;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<String> transactionHistory;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Account created with initial balance: " + balance);
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction("Deposited: " + amount + ", New Balance: " + balance);
            System.out.println("Amount deposited successfully. Current balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction("Withdrew: " + amount + ", New Balance: " + balance);
            System.out.println("Amount withdrawn successfully. Current balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    // Add transaction to history
    private void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    // Display transaction history
    public void displayTransactionHistory() {
        System.out.println("Transaction History for Account: " + accountNumber);
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    @Override
    public String toString() {
        return accountNumber + "," + accountHolderName + "," + balance;
    }

    public static BankAccount fromString(String data) {
        String[] parts = data.split(",");
        return new BankAccount(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}

public class BankAccountManagementSystem {
    private static final String DATA_FILE = "accounts.txt";
    private Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccountManagementSystem() {
        loadAccounts();
    }

    // Load accounts from file
    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                BankAccount account = BankAccount.fromString(line);
                accounts.put(account.getAccountNumber(), account);
            }
        } catch (IOException e) {
            System.out.println("No existing accounts found. Starting fresh.");
        }
    }

    // Save accounts to file
    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (BankAccount account : accounts.values()) {
                writer.write(account.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    // Create a new account
    public void createAccount(String accountNumber, String accountHolderName, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists.");
        } else if (initialBalance < 0) {
            System.out.println("Initial balance cannot be negative.");
        } else {
            BankAccount account = new BankAccount(accountNumber, accountHolderName, initialBalance);
            accounts.put(accountNumber, account);
            saveAccounts();
            System.out.println("Account created successfully.");
        }
    }

    // Perform deposit
    public void deposit(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.deposit(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Perform withdrawal
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            account.withdraw(amount);
            saveAccounts();
        } else {
            System.out.println("Account not found.");
        }
    }

    // View account details
    public void viewAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder Name: " + account.getAccountHolderName());
            System.out.println("Balance: " + account.getBalance());
            account.displayTransactionHistory();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Main Menu
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Bank Account Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Account");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accNumber = scanner.next();
                    System.out.print("Enter Account Holder Name: ");
                    scanner.nextLine(); // Consume newline
                    String accHolder = scanner.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = scanner.nextDouble();
                    createAccount(accNumber, accHolder, initialBalance);
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    accNumber = scanner.next();
                    System.out.print("Enter Amount to Deposit: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(accNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accNumber = scanner.next();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(accNumber, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accNumber = scanner.next();
                    viewAccount(accNumber);
                    break;
                case 5:
                    System.out.print("Are you sure you want to exit? (yes/no): ");
                    String confirm = scanner.next();
                    if (confirm.equalsIgnoreCase("yes")) {
                        System.out.println("Exiting system. Thank you!");
                        scanner.close();
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        BankAccountManagementSystem system = new BankAccountManagementSystem();
        system.showMenu();
    }
}
