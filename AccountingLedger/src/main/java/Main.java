import java.io.FileReader;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "src/main/resources/transactions.csv";

    public static void main(String[] args) {
        // Loop the menu until the user chooses to exit
        while (true) {
            showHomeMenu(); // Display menu options
            String choice = scanner.nextLine().toUpperCase(); // Read user input

            if (choice.equals("D")) {
                addTransaction(true); // Deposit
            } else if (choice.equals("P")) {
                addTransaction(false); // Payment
            } else if (choice.equals("L")) {
                ledgerMenu(); // Ledger screen
            } else if (choice.equals("X")) {
                System.out.println("Exiting... Goodbye!");
                break; // Exit the loop
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Show the Home Menu
    static void showHomeMenu() {
        System.out.println("\n===== Home Menu =====");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment");
        System.out.println("L) View Ledger");
        System.out.println("X) Exit");
        System.out.print("Enter choice: ");
    }

    // Method to add a deposit or a payment
    static void addTransaction(boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        // If it's a payment, make amount negative
        if (!isDeposit) {
            amount = -amount;
        }

        // Get current date and time
        String date = LocalDate.now().toString();
        String time = LocalTime.now().withNano(0).toString(); // Remove nanoseconds

        // Format the transaction line
        String transactionLine = date + "|" + time + "|" + description + "|" + vendor + "|" + amount;

        // Save transaction to the file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(transactionLine + "\n");
            System.out.println("Transaction saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Menu to view the ledger
    static void ledgerMenu() {
        while (true) {
            System.out.println("\n===== Ledger Menu =====");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposit Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().toUpperCase();

            if (choice.equals("A")) {
                displayTransactions("ALL");
            } else if (choice.equals("D")) {
                displayTransactions("DEPOSIT");
            } else if (choice.equals("P")) {
                displayTransactions("PAYMENT");
            } else if (choice.equals("R")) {
                reportsMenu();
            } else if (choice.equals("H")) {
                break; // Go back to home menu
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to display transactions based on filter
    static void displayTransactions(String filter) {
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read each transaction line
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        // Show newest first by looping backward
        for (int i = lines.size() - 1; i >= 0; i--) {
            String[] parts = lines.get(i).split("\\|");
            double amount = Double.parseDouble(parts[4]);

            // Check if we should skip this line based on the filter
            if (filter.equals("DEPOSIT")) {
                if (amount < 0) {
                    continue; // It's a payment, skip it
                }
            }

            if (filter.equals("PAYMENT")) {
                if (amount > 0) {
                    continue; // It's a deposit, skip it
                }
            }

            // Show the line if it matches the filter or is for "ALL"
            System.out.println(lines.get(i));
        }
    }

    // Menu for reports
    static void reportsMenu() {
        while (true) {
            System.out.println("\n===== Reports Menu =====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back to Ledger");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                showMonthToDateReport();
            } else if (choice.equals("2")) {
                showPreviousMonthReport();
            } else if (choice.equals("3")) {
                showYearToDateReport();
            } else if (choice.equals("4")) {
                showPreviousYearReport();
            } else if (choice.equals("0")) {
                break; // Go back to ledger menu
            } else {
                System.out.println("This report is not built yet.");
            }
        }
    }

    // Show transactions from this month only
    static void showMonthToDateReport() {
        System.out.println("\n===== Month to Date Report =====");

        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transaction: " + e.getMessage());
            return;
        }

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatMonth = DateTimeFormatter.ofPattern("MM"); // Format the way you want your month to be
        String thisMonth = today.format(formatMonth); // Setting thisMonth to the current month
        DateTimeFormatter formatYear = DateTimeFormatter.ofPattern("yyyy"); // Format the way you want your year to be
        String thisYear = today.format(formatYear); // Setting thisMonth to the current year
//        LocalDate firstOfMonth = today.withDayOfMonth(1);

        for (String line : lines) {
            String[] parts = line.split("\\|");
            String transactionDate = (parts[0]);
            String[] dateParts = transactionDate.split("-");
            String month = dateParts[1];
            String year = dateParts[0];

            if (thisMonth.equals(month) && thisYear.equals(year)) {
                System.out.println(line);
            }
//            if (!transactionDate.isBefore(firstOfMonth)) {
//                if (!transactionDate.isAfter(today)) {
//                    System.out.println(line);
//                }
//            }
        }
    }

    // Show transactions from the previous month
    static void showPreviousMonthReport()  {
        System.out.println("\n===== Previous Month Report =====");
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate firstOfThisMonth = today.withDayOfMonth(1);
        LocalDate firstOfLastMonth = firstOfThisMonth.minusMonths(1);
        LocalDate endOfLastMonth = firstOfThisMonth.minusDays(1);

        for (String line : lines) {
            String[] parts = line.split("\\|");
            LocalDate transactionDate = LocalDate.parse(parts[0]);

            if (!transactionDate.isBefore(firstOfLastMonth)) {
                if (!transactionDate.isAfter(endOfLastMonth)) {
                    System.out.println(line);
                }
            }
        }
    }

    // Show all transactions from the beginning of the current year to today
    static void showYearToDateReport() {
        System.out.println("\n===== Year to Date Report =====");

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        // Get Jan 1st of the current year
        LocalDate today = LocalDate.now();
        LocalDate firstOfYear = today.withDayOfYear(1);

        for (String line : lines) {
            String [] parts = line.split("\\|");
            if (parts.length != 5) continue;

            LocalDate transactionDate = LocalDate.parse(parts[0]);

            if (!transactionDate.isBefore(firstOfYear)) {
                if (!transactionDate.isAfter(today))
                    System.out.println(line);
            }
        }
    }

    static void showPreviousYearReport () {
        System.out.println("\n===== Previous Year Report =====");

        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
            return;
        }

        int currentYear = LocalDate.now().getYear();
        int previousYear = currentYear - 1;

        for (String line : lines) {
            String[] parts = line.split("\\|");
            LocalDate transactionDate = LocalDate.parse(parts[0]);

            if (transactionDate.getYear() == previousYear) {
                System.out.println(line);
            }
        }
    }

}

