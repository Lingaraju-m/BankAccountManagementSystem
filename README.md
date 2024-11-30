**---Bank Account Management System---**


A robust Bank Account Management System implemented in Java to manage basic banking operations efficiently. This system is designed to handle account creation, deposits, withdrawals, and transaction history while ensuring data persistence using file handling.

**Features**

1)Account Creation:
    Create new accounts with unique account numbers, account holder names, and initial balances.
2)Deposits:
    Add funds to an account with proper validation.
3)Withdrawals:
    Withdraw funds with overdraft protection and balance validation.
4)Transaction History:
    View a detailed log of all deposits and withdrawals for each account.
5)Account Details:
    Retrieve account information such as balance, account holder name, and transaction history.
6)Data Persistence:
    Save and load account data automatically using a file (accounts.txt).


**Technologies Used**

Programming Language: Java
Development Environment: Visual Studio Code
File Handling: Used for persistent data storage Getting Started


**Follow these steps to set up and run the project on your local system.**

1)Clone the repository to your local system:
    git clone https://github.com/Lingaraju-m/BankAccountManagementSystem.git
    cd BankAccountManagementSystem


2)Compile the Java program:
    javac BankAccountManagementSystem.java
    
3)Run the program:
    java BankAccountManagementSystem



**Usage**
Upon running the program, you will see a menu with the following options:

Create Account: Set up a new account.
Deposit: Add funds to an account.
Withdraw: Withdraw funds from an account.
View Account: Display account details and transaction history.
Exit: Exit the program with an optional confirmation.
Follow the on-screen prompts to interact with the system.

All account data is saved in the accounts.txt file, ensuring it persists across sessions.

**Project Structure**

BankAccountManagementSystem/
├── accounts.txt         # Data file to store account details
├── BankAccountManagementSystem.java  # Main program file



**Future Enhancements**
Add GUI support for a more user-friendly interface.
Implement authentication for account security.
Introduce account deletion functionality.
Add support for multiple account types (savings, current, etc.).


**License**
This project is licensed under the MIT License. You are free to use,modify,and distrute this code as per the terms of the license.