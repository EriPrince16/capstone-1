import java.time.LocalDate;
import java.time.LocalTime;

// This is the Transaction class. A class is a blueprint for creating objects.
public class Transaction {

    // These are fields. Fields are variables that store data inside the object.
    // Each field represents a piece of information about a transaction.
    LocalDate date;        // Date of the transaction
    LocalTime time;        // Time of the transaction
    String description;    // Description of the transaction
    String vendor;         // Vendor name involved in the transaction
    double amount;         // The amount of the transaction (positive for deposits, negative for payments)

    // This is a constructor. A constructor is a special method that creates an object with specific values.
    // When you create a Transaction object, you need to provide a date, time, description, vendor, and amount.
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        // The 'this' keyword refers to the current object, and the parameter names (e.g., 'date') are the values passed in.
        this.date = date;          // Sets the date for this transaction
        this.time = time;          // Sets the time for this transaction
        this.description = description; // Sets the description of the transaction
        this.vendor = vendor;      // Sets the vendor involved in the transaction
        this.amount = amount;      // Sets the amount of the transaction (deposit or payment)
    }

    // These are getter methods. A getter method allows us to access (read) the value of a private field.
    // We use these methods to get the value of the fields outside the class.

    public double getAmount() {
        // This returns the value stored in the 'amount' field
        return amount;
    }

    // These are setter methods. A setter method allows us to change (update) the value of a field.
    // We use these methods to set the value of fields outside the class.

    public void setAmount(double amount) {
        // This sets the value of the 'amount' field to the new value passed as a parameter
        this.amount = amount;
    }

    // Getter and setter methods for 'vendor' field
    public String getVendor() {
        return vendor; // This returns the value stored in the 'vendor' field
    }

    public void setVendor(String vendor) {
        this.vendor = vendor; // This sets the 'vendor' field to the new value
    }

    // Getter and setter methods for 'description' field
    public String getDescription() {
        return description; // This returns the value stored in the 'description' field
    }

    public void setDescription(String description) {
        this.description = description; // This sets the 'description' field to the new value
    }

    // Getter and setter methods for 'time' field
    public LocalTime getTime() {
        return time; // This returns the value stored in the 'time' field
    }

    public void setTime(LocalTime time) {
        this.time = time; // This sets the 'time' field to the new value
    }

    // Getter and setter methods for 'date' field
    public LocalDate getDate() {
        return date; // This returns the value stored in the 'date' field
    }

    public void setDate(LocalDate date) {
        this.date = date; // This sets the 'date' field to the new value
    }

    // This is the toString method. It converts the object into a string that is easy to read.
    // The 'toString' method is used when you want to print out the object in a readable format.
    @Override
    public String toString() {
        // This combines the fields into a single string, separating them with "|"
        // Example: "2025-05-01|12:30:00|Purchase at store|Store A|50.0"
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
}