 import java.util.ArrayList;
    public class Account {
        private String accountid;//this var is used for store account id
        private String pin;//this var is used for store account pin
        private ArrayList<Transaction> transactions = new ArrayList<>();//arraylist is used to store transactions

        public Account(String accountId, String pin) { // Constructor to store id and pin
            this.accountid = accountId;
            this.pin = pin;
        }
        public Account() { // Constructor to store transaction
            this.transactions = new ArrayList<>();
        }

        // Getters and Setters
        public String getId() {
            return accountid;
        }


        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public ArrayList<Transaction> getTransactions() {
            return transactions;
        }

        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
        }

        public void viewTransactions() { // Method to view transaction
            if (transactions.isEmpty()) { // Check if the transaction is empty or not
                System.out.println("No Transactions History");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            }
        }

        public void setTransactions(Transaction transactions) {
            this.transactions.add(transactions);
        }
    }
