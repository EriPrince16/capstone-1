import javax.swing.text.DateFormatter;
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

public class BankofMillions {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Transaction> ledger; // Created an ArrayList with the type Transaction and named it ledger
//        ledger = loadLedger(); // Assigning ledger to the ArrayList that returns loadLedger
        displayHome();


    }

    // Show the Home Menu
    public static void displayHome() {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("\n===== Welcome to Bank of Millions =====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) View Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter choice: ");
            Scanner scanner = new Scanner(System.in);
            String userChoice = scanner.nextLine().toUpperCase();
            System.out.println("You have selected: " + userChoice);

            if (userChoice.equals("D")) {
                addTransaction(true);
            } else if (userChoice.equals("P")) {
                addTransaction(false);
            } else if (userChoice.equals("L")) {
                ledgerMenu();
            } else if (userChoice.equals("X")) {
                break;
            } else {
                System.out.println("Invalid input! Try again.");
            }
        }


    }

    // Method to deposit or make a payment
    public static void addTransaction(boolean isDeposit) {
        String filePath = "src/main/resources/ledger.csv";

        System.out.println("Enter description of the transaction: ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor: ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the amount: ");
        double amount = scanner.nextDouble();

        if (!isDeposit) {
            amount = -amount;

        }


        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd|HH:mm:ss");
        String logDateTime = localDateTime.format(dateTimeFormatter);

        // Format the transaction line
        String transactionLine = description + "|" + vendor + "|" + amount;

        try {
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write("\n" + logDateTime + "|" + transactionLine);
            fileWriter.close();
            String line;
            System.out.println("Your transaction was successful!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public static void ledgerMenu() {
        while (true) {
            System.out.println("\n===== Ledger Menu =====");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");
            String userChoice = scanner.nextLine().toUpperCase();

            if (userChoice.equals("A")) {
                displayTransaction(userChoice);
            } else if (userChoice.equals("D")) {
                displayTransaction(userChoice);

            } else if (userChoice.equals("P")) {
                displayTransaction(userChoice);

            } else if (userChoice.equals("R")) {
                displayReports();
            } else if (userChoice.equals("H")) {
                displayHome();

            }
        }
    }


    public static void displayTransaction(String userChoice) {
        if (userChoice.equals("A")) { // If the user chose to view all transactions
            for (Transaction transactions : loadLedger()) { // For each transactions of the ArrayList (loadLedger())
                System.out.println(transactions.toString()); // Print out all transactions
            }
        } else if (userChoice.equals("D")) { // If the user chose to view all transactions by Deposit
            for (Transaction transactions : loadLedger()) { // For each transaction of the ArrayList (loadLedger())
                String [] parts = transactions.toString().split("\\|"); //Converts each transactions into a string and split them by "\\|" into an Array
                double amount = Double.parseDouble(parts[4]); // Grabbed the 4th index and turned it from a String into a double
                if (amount > 0) { // If the amount is a positive number
                    System.out.println(transactions.toString()); // Print out all transactions with a postive amount
                }
            }
        } else if (userChoice.equals("P")) { // If the user chose to view all transactions by Payments
            for (Transaction transactions : loadLedger()) { // For each transaction of the ArrayList (loadLedger())
                String [] parts = transactions.toString().split("\\|"); // Converts each transactions into a string and split them by "\\|" into an Array
                double amount = Double.parseDouble(parts[4]); // Grabbed the 4th index and turned it from a String into a double
                if (amount < 0) { // If the amount is a negative number
                    System.out.println(transactions.toString()); // Print out all transactions with a negative amount
                }
            }
        }


    }

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

            } else if (choice.equals("2")) {
                showMonthToDate();

            } else if (choice.equals("3")) {

            } else if (choice.equals("4")) {

            } else if (choice.equals("5")) {

            } else if (choice.equals("0")) {
                break;
            }
        }
    }

    public static void showMonthToDate() {
        System.out.println("\n===== Month to Date =====");



    }


    public static ArrayList<Transaction> loadLedger() { // Created a method to
        ArrayList<Transaction> ledger = new ArrayList<Transaction>();
        String filePath = "src/main/resources/ledger.csv";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0], dateFormatter);
                LocalTime time = LocalTime.parse(parts[1], timeFormatter);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
                ledger.add(newTransaction);


            }

        } catch (IOException e) {
            System.out.println("Error!");
        }


        return ledger;
    }

}
