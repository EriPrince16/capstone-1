# 💰 Bank of Millions

**Bank of Millions** is a beginner-friendly Java banking application that lets users add deposits and payments, view their ledger, and generate basic reports. It's ideal for learning file handling, object-oriented programming, and working with dates and times in Java.

---

## 📦 Features

- ✅ Add **Deposits** and **Payments**
- 📄 View **All**, **Deposit-only**, or **Payment-only** transactions
- 📊 Access a **Reports Menu** (some features coming soon)
- 💾 Stores data in a local `ledger.csv` file using `|`-delimited format

---

## 🛠️ Requirements

- Java 17 or higher
- Command line or an IDE (like IntelliJ, Eclipse, or VS Code)

---

## 📁 Project Structure

BankofMillions.java # Main application file
Transaction.java # Represents individual transactions
src/main/resources/
└── ledger.csv # Stores transactions (created if not present)

yaml
Copy
Edit

---

## ▶️ How to Run

1. **Clone or download** this repository.
2. Navigate to the project folder.
3. Make sure `ledger.csv` exists in `src/main/resources/` (create it if not).
4. Compile the code:
   ```bash
   javac BankofMillions.java Transaction.java
Run the program:

bash
Copy
Edit
java BankofMillions
🧪 Example Usage
mathematica
Copy
Edit
===== Welcome to Bank of Millions =====
D) Add Deposit
P) Make Payment
L) View Ledger
X) Exit
Enter choice: D
Enter description of the transaction: Salary
Enter the vendor: Employer Inc
Enter the amount: 3000
Your transaction was successful!
🧠 Learning Highlights
Classes and Objects

File I/O with FileReader, BufferedReader, and FileWriter

Date and Time with LocalDateTime and DateTimeFormatter

Control Flow with Loops and Conditionals

User Input with Scanner

👤 Author
Made with 💻 by [Million Bereket]

![](/Users/1mill/Desktop/Screenshot 2025-05-02 at 9.04.25 AM.png)
![](/Users/1mill/Desktop/Screenshot 2025-05-02 at 9.04.49 AM.png)
![](/Users/1mill/Desktop/Screenshot 2025-05-02 at 9.05.08 AM.png)