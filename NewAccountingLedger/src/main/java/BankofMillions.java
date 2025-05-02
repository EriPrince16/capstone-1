import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;

// This is the name of the class. A class is like a blueprint for creating objects.
// It can contain variables (called fields) and actions (called methods).
public class BankofMillions {

    // This is a field. A field is a variable that belongs to the class.
    // We are creating a Scanner object. An object is something made from a class.
    // Scanner helps us get input from the keyboard.
    static Scanner scanner = new Scanner(System.in);

    // This is the main method. A method is a block of code that does something.
    // The main method is where the program starts when we run it.
    public static void main(String[] args) {
        // Creating a variable named ledger. It is an ArrayList that will store many Transaction objects.
        // An ArrayList is like a flexible list that can grow or shrink as needed.
        ArrayList<Transaction> ledger;

        // This line is commented out for now. It would normally load all previous transactions into the ledger list.
        // ledger = loadLedger();

        // This line calls the displayHome method to show the home screen of the bank app.
        displayHome();
    }

    // This method shows the Home Menu where the user can choose actions.
    // It uses a loop to keep showing the menu until the user chooses to exit.
    public static void displayHome() {
        // Creating a boolean variable to control the loop
        boolean keepGoing = true;

        // This is a while loop. It keeps running as long as keepGoing is true.
        while (keepGoing) {
            // Display menu options to the user
            System.out.println("\n===== Welcome to Bank of Millions =====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) View Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter choice: ");

            // Getting user input and turning it into uppercase letters
            String userChoice = scanner.nextLine().toUpperCase();

            // Show what the user picked
            System.out.println("You have selected: " + userChoice);

            // This is an if-else statement to check what the user chose
            if (userChoice.equals("D")) {
                // If the user picked D, we call the method to add a deposit
                addTransaction(true);
            } else if (userChoice.equals("P")) {
                // If the user picked P, we call the method to make a payment
                addTransaction(false);
            } else if (userChoice.equals("L")) {
                // If the user picked L, we show the ledger menu
                ledgerMenu();
            } else if (userChoice.equals("X")) {
                // If the user picked X, we break the loop and exit the app
                break;
            } else {
                // If the user typed something else, we show an error
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    // This method is used to add a transaction (deposit or payment)
    // A parameter is something we pass into a method to use. isDeposit tells us if it's a deposit or not.
    public static void addTransaction(boolean isDeposit) {
        // This is a String variable that holds the path to the file where we save transactions
        String filePath = "src/main/resources/ledger.csv";

        // Asking the user to enter transaction information
        System.out.println("Enter description of the transaction: ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();

        // If the transaction is a payment, we make the amount negative
        if (!isDeposit) {
            amount = -amount;
        }

        // Getting the current date and time
        LocalDateTime localDateTime = LocalDateTime.now();
        // Formatting the date and time in a readable way
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String logDateTime = localDateTime.format(dateTimeFormatter);

        // Putting all the transaction info into one line to save in the file
        String transactionLine = description + "|" + vendor + "|" + amount;

        // Try to write the transaction to the file
        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // true means we are adding to the file
            fileWriter.write("\n" + logDateTime + "|" + transactionLine); // writing the data to the file
            fileWriter.close(); // closing the file after writing
            System.out.println("Your transaction was successful!");
        } catch (Exception e) {
            // If something goes wrong, we stop the program and show the error
            throw new RuntimeException(e);
        }
    }

    // This method shows the ledger menu where the user can view different types of transactions
    public static void ledgerMenu() {
        while (true) { // Keep showing the menu until the user goes back
            System.out.println("\n===== Ledger Menu =====");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            String userChoice = scanner.nextLine().toUpperCase();

            // Based on the choice, call the correct method
            if (userChoice.equals("A") || userChoice.equals("D") || userChoice.equals("P")) {
                displayTransaction(userChoice); // Show matching transactions
            } else if (userChoice.equals("R")) {
                displayReports(); // Go to reports menu
            } else if (userChoice.equals("H")) {
                displayHome(); // Go back to main home menu
            }
        }
    }

    // This method shows transactions based on the user's choice
    public static void displayTransaction(String userChoice) {
        // If the user picked A, we show all transactions
        if (userChoice.equals("A")) {
            // This is a for-each loop. It goes through every item in the list one by one.
            for (Transaction transactions : loadLedger()) {
                System.out.println(transactions.toString()); // Print each transaction
            }
        } else if (userChoice.equals("D")) {
            // Show only deposit transactions
            for (Transaction transactions : loadLedger()) {
                String[] parts = transactions.toString().split("\\|"); // Split the string into pieces
                double amount = Double.parseDouble(parts[4]); // Turn the amount into a number
                if (amount > 0) { // Only show if the amount is positive
                    System.out.println(transactions.toString());
                }
            }
        } else if (userChoice.equals("P")) {
            // Show only payment transactions
            for (Transaction transactions : loadLedger()) {
                String[] parts = transactions.toString().split("\\|");
                double amount = Double.parseDouble(parts[4]);
                if (amount < 0) { // Only show if the amount is negative
                    System.out.println(transactions.toString());
                }
            }
        }
    }

    // This method shows the Reports Menu
    public static void displayReports() {
        while (true) {
            System.out.println("\n===== Reports =====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                showMonthToDate(); // Call method to show current month transactions
            } else if (choice.equals("2")) {
                // (Code for this will be added later)
            } else if (choice.equals("3")) {
                // (Code for this will be added later)
            } else if (choice.equals("4")) {
                // (Code for this will be added later)
            } else if (choice.equals("5")) {
                // (Code for this will be added later)
            } else if (choice.equals("0")) {
                break; // Exit the reports menu
            }
        }
    }

    // This method will show transactions for the current month (to be completed later)
    public static void showMonthToDate() {
        System.out.println("\n===== Month to Date =====");
        // (More code will go here in the future)
    }

    // This method loads all transactions from the file and puts them into an ArrayList
    public static ArrayList<Transaction> loadLedger() {
        // Create an empty list to store all transactions
        ArrayList<Transaction> ledger = new ArrayList<Transaction>();
        String filePath = "src/main/resources/ledger.csv"; // Location of the file
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            // This while loop keeps running as long as there are lines to read
            while ((line = bufferedReader.readLine()) != null) {
                // Split each line into parts based on the "|" symbol
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0], dateFormatter); // Convert date from String to Date
                LocalTime time = LocalTime.parse(parts[1], timeFormatter); // Convert time from String to Time
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                // Create a new Transaction object using the parts
                Transaction newTransaction = new Transaction(date, time, description, vendor, amount);

                // Add this transaction to the ledger list
                ledger.add(newTransaction);
            }
        } catch (IOException e) {
            System.out.println("Error!"); // Show error if something goes wrong
        }

        // Return the list of transactions
        return ledger;
    }
}
