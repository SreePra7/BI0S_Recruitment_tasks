import java.util.Scanner;

// ============================================================
// INTERFACE
// ============================================================

interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
}


// ============================================================
// ABSTRACT CLASS
// ============================================================

abstract class Person {

    // Encapsulation: private data members
    private String name;
    private int age;

    // Default Constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
    }

    // Parameterized Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Copy Constructor
    public Person(Person other) {
        this.name = other.name;
        this.age = other.age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Abstract method
    public abstract void displayDetails();

    // Normal parent method
    public void introduce() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}


// ============================================================
// FIRST LEVEL OF INHERITANCE
// Person -> BankUser
// ============================================================

class BankUser extends Person {

    protected String userId;

    // Constructor chaining using super()
    public BankUser() {
        super();
        this.userId = "Not Assigned";
    }

    public BankUser(String name, int age, String userId) {
        super(name, age);
        this.userId = userId;
    }

    // Copy constructor
    public BankUser(BankUser other) {
        super(other);
        this.userId = other.userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Method Overriding
    @Override
    public void displayDetails() {
        System.out.println("Bank User: " + getName());
        System.out.println("User ID: " + userId);
    }
}


// ============================================================
// SECOND LEVEL OF INHERITANCE
// BankUser -> Account
// ============================================================

abstract class Account extends BankUser implements Transaction {

    private String accountNumber;
    private double balance;

    // Default Constructor
    public Account() {
        super();
        this.accountNumber = "0000";
        this.balance = 0.0;
    }

    // Parameterized Constructor
    public Account(String name, int age, String userId,
                   String accountNumber, double balance) {
        super(name, age, userId);
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Copy Constructor
    public Account(Account other) {
        super(other);
        this.accountNumber = other.accountNumber;
        this.balance = other.balance;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Setter
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // ========================================================
    // METHOD OVERLOADING
    // Three versions of deposit()
    // ========================================================

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void deposit(double amount, String description) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
            System.out.println("Description: " + description);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void deposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: ₹" + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Withdraw method from interface
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void showAccount() {
        System.out.println("\n----- Account Details -----");
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("User ID: " + userId);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: ₹" + balance);
    }

    public abstract void calculateInterest();
}


// ============================================================
// THIRD LEVEL OF INHERITANCE
// Account -> SavingsAccount
// ============================================================

class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount() {
        super();
        this.interestRate = 4.0;
    }

    public SavingsAccount(String name, int age, String userId,
                          String accountNumber, double balance,
                          double interestRate) {
        super(name, age, userId, accountNumber, balance);
        this.interestRate = interestRate;
    }

    public SavingsAccount(SavingsAccount other) {
        super(other);
        this.interestRate = other.interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = getBalance() * interestRate / 100;
        System.out.println("Savings Interest: ₹" + interest);
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
    }
}


// ============================================================
// HIERARCHICAL INHERITANCE
// Account -> CurrentAccount
// ============================================================

class CurrentAccount extends Account {

    private double minimumBalance;

    public CurrentAccount() {
        super();
        this.minimumBalance = 1000;
    }

    public CurrentAccount(String name, int age, String userId,
                          String accountNumber, double balance,
                          double minimumBalance) {
        super(name, age, userId, accountNumber, balance);
        this.minimumBalance = minimumBalance;
    }

    public CurrentAccount(CurrentAccount other) {
        super(other);
        this.minimumBalance = other.minimumBalance;
    }

    @Override
    public void calculateInterest() {
        System.out.println("Current accounts do not provide regular interest.");
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Account Type: Current Account");
        System.out.println("Minimum Balance: ₹" + minimumBalance);
    }
}


// ============================================================
// MAIN CLASS (Must match file name Bank_Management_System)
// ============================================================

public class Bank_Management_System {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Account account = null;
        boolean running = true;

        while (running) {

            System.out.println("\n=================================");
            System.out.println("      BANK MANAGEMENT SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Display Account Details");
            System.out.println("4. Deposit Money");
            System.out.println("5. Withdraw Money");
            System.out.println("6. Calculate Interest");
            System.out.println("7. Demonstrate Copy Constructor");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter User ID: ");
                    String userId = scanner.nextLine();

                    System.out.print("Enter Account Number: ");
                    String accNo = scanner.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double balance = scanner.nextDouble();

                    System.out.print("Enter Interest Rate: ");
                    double rate = scanner.nextDouble();

                    account = new SavingsAccount(name, age, userId, accNo, balance, rate);
                    System.out.println("Savings account created!");
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();

                    System.out.print("Enter age: ");
                    age = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter User ID: ");
                    userId = scanner.nextLine();

                    System.out.print("Enter Account Number: ");
                    accNo = scanner.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    balance = scanner.nextDouble();

                    System.out.print("Enter Minimum Balance: ");
                    double minimum = scanner.nextDouble();

                    account = new CurrentAccount(name, age, userId, accNo, balance, minimum);
                    System.out.println("Current account created!");
                    break;

                case 3:
                    if (account != null) {
                        account.displayDetails();
                    } else {
                        System.out.println("Please create an account first.");
                    }
                    break;

                case 4:
                    if (account != null) {
                        System.out.println("\n1. Deposit using double");
                        System.out.println("2. Deposit using double + description");
                        System.out.println("3. Deposit using int");

                        System.out.print("Choose deposit method: ");
                        int depositChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (depositChoice == 1) {
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble();
                            account.deposit(amount);

                        } else if (depositChoice == 2) {
                            System.out.print("Enter amount: ");
                            double amount = scanner.nextDouble(); // Fixed: Added 'double' type declaration
                            scanner.nextLine();

                            System.out.print("Enter description: ");
                            String description = scanner.nextLine();

                            account.deposit(amount, description);

                        } else if (depositChoice == 3) {
                            System.out.print("Enter amount: ");
                            int amount = scanner.nextInt();
                            account.deposit(amount);

                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("Please create an account first.");
                    }
                    break;

                case 5:
                    if (account != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        account.withdraw(amount);
                    } else {
                        System.out.println("Please create an account first.");
                    }
                    break;

                case 6:
                    if (account != null) {
                        account.calculateInterest();
                    } else {
                        System.out.println("Please create an account first.");
                    }
                    break;

                case 7:
                    if (account instanceof SavingsAccount) {
                        SavingsAccount original = (SavingsAccount) account;
                        SavingsAccount copy = new SavingsAccount(original);

                        System.out.println("\nOriginal Account:");
                        original.displayDetails();

                        System.out.println("\nCopied Account:");
                        copy.displayDetails();

                    } else if (account instanceof CurrentAccount) {
                        CurrentAccount original = (CurrentAccount) account;
                        CurrentAccount copy = new CurrentAccount(original);

                        System.out.println("\nOriginal Account:");
                        original.displayDetails();

                        System.out.println("\nCopied Account:");
                        copy.displayDetails();

                    } else {
                        System.out.println("Please create an account first.");
                    }
                    break;

                case 8:
                    running = false;
                    System.out.println("Thank you for using the Bank Management System!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}