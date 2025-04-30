public class Transaction {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor to initialize all fields
    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getter and setter methods for each field
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    // Method to convert the transaction to CSV format (for saving in a file)
    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }

    // Optional: Override toString() for better readability when printing transactions
    @Override
    public String toString() {
        return date + " " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}
