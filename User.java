import java.util.ArrayList;
import java.util.Scanner;
class User {
    private static ArrayList<ArrayList<String>> userTransactions = new ArrayList<>();
    public static ArrayList<ArrayList<String>> getUserTransactions() {
        return userTransactions;
    }
    public static void userLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        int index = AdminAction.getUsernames().indexOf(username);
        if (index == -1) {
            System.out.println("Username not found");
            return;
        }
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();
        if (!AdminAction.getUserPins().get(index).equals(pin)) {
            System.out.println("Invalid PIN");
            return;
        }
        System.out.println("\nUser login successful");
        userMenu(index);
    }
    private static void userMenu(int index) {
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Cash");
            System.out.println("3. Deposit Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkBalance(index);
                    break;
                case 2:
                    withdrawCash(index);
                    break;
                case 3:
                    depositCash(index);
                    break;
                case 4:
                    changePin(index);
                    break;
                case 5:
                    viewTransactionHistory(index);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void checkBalance(int index) {
        System.out.println("Your current balance is: " + AdminAction.getUserBalances().get(index));
    }

    private static void withdrawCash(int index) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        int amount = sc.nextInt();

        if (amount > AdminAction.getUserBalances().get(index)) {
            System.out.println("Insufficient balance");
        } else {
            AdminAction.getUserBalances().set(index, AdminAction.getUserBalances().get(index) - amount);
            ensureUserTransactionList(index).add("Withdrew: " + amount);
            System.out.println("Withdrawal successful");
        }
    }
    private static void depositCash(int index) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of 2000 notes: ");
        int notes2000 = sc.nextInt();
        System.out.print("Enter number of 500 notes: ");
        int notes500 = sc.nextInt();
        System.out.print("Enter number of 200 notes: ");
        int notes200 = sc.nextInt();
        System.out.print("Enter number of 100 notes: ");
        int notes100 = sc.nextInt();
        int totalAmount = (notes2000 * 2000) + (notes500 * 500) + (notes200 * 200) + (notes100 * 100);
        AdminAction.getUserBalances();
        ensureUserTransactionList(index).add("Deposited: " + totalAmount + " (2000 x " + notes2000 + ", 500 x " + notes500 + ", 200 x " + notes200 + ", 100 x " + notes100 + ")");
        System.out.println("Deposit successful. Total amount deposited: " + totalAmount);
    }
    private static void changePin(int index) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new PIN: ");
        String newPin = sc.nextLine();
        if (newPin.length() == 4) {
            AdminAction.getUserPins().set(index, newPin);
            System.out.println("PIN changed successfully");
        } else {
            System.out.println("Invalid PIN Please enter a 4-digit PIN.");
        }
    }
    private static void viewTransactionHistory(int index) {
        System.out.println("\nTransaction History:");
        ArrayList<String> transactions = new ArrayList<String>();
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
    private static ArrayList<String> ensureUserTransactionList(int index) {
        while (userTransactions.size() <= index) {
            userTransactions.add(new ArrayList<>());
        }
        return userTransactions.get(index);
    }
}
