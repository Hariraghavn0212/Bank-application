import Note.Notes;//importing notepackage
import java.util.ArrayList;//importing arraylist
import java.util.Scanner;//importing scanner
public class ATM {
    private static ArrayList<Notes> notes = new ArrayList<>();//array list for notes
    private static double atmBalance; //variable for atm balance
    private static ArrayList<Account> account= new ArrayList<>();//account array list
    public static void start() throws CloneNotSupportedException {//start method
        Scanner sc = new Scanner(System.in);//Scanner object
        AdminAction adminAction = new AdminAction(); // object for adminAction
        UserAction userAction = new UserAction();// object for userAction

        account.add(new Admin("Admin", "123")); // Adding default admin

        while (true) { // Using while loop for choosing whose login
            System.out.println("Welcome \n1. Admin Login\n2. User Login\n3. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            if (choice == 1) {
                if (AdminAction.adminLogin(account, sc) != null) {
                    adminOptions(sc, adminAction);
                } else {
                    System.out.println("Invalid Admin ID....");
                }
            } else if (choice == 2) {
                User user=new User();
                Account users = UserAction.userLogin(account,user, sc);
                if (users!= null) {
                    userOptions(sc,userAction,users,user);
                } else {
                    System.out.println("User Login Failed....");
                }
            } else if (choice == 3) {
                System.out.println("Thank you....");
                break;
            } else {
                System.out.println("Invalid choice....please Try again.");
            }
        }
    }


    private static void adminOptions(Scanner sc, AdminAction adminAction) {  // Method to show Admin options
        while (true) {  // Using while loop for choosing the action
            System.out.println("Admin Actions:\n1. Add User\n2. Delete User\n3. View Users\n4. Deposite cash to ATM\n5. View Transactions\n6. ATM Balance\n7. Exit");
            int choice = sc.nextInt();  // Get Admin choice
            Account accounts = new Account();
            if (choice == 1) {  // Add user
                adminAction.addUser(account, sc);  // Call addUser method from AdminAction
            } else if (choice == 2) {  // Delete user
                adminAction.deleteUser(account, sc);  // Call deleteUser method from AdminAction
            } else if (choice == 3) {  // View users
                adminAction.viewUsers(account);  // Call viewUsers method from AdminAction
            } else if (choice == 4) {  // Add money to ATM
                adminAction.depositecashtoatm(notes,accounts, sc);  // Call add moneyTo ATM method from AdminAction
            } else if (choice == 5) {  // View transactions
                adminAction.viewTransaction(account);
            } else if (choice == 6) {  // View ATM balance
                atmBalance = AdminAction.ATMBalance(notes);  // Calculate ATM balance
                System.out.println("Current Balance of ATM: " + atmBalance);  // Display ATM balance
            } else if (choice == 7) {  // Exit Admin session
                System.out.println("Admin logged out");
                break;
            } else {  // Invalid choice
                System.out.println("Invalid choice... please Try again.");
            }
        }
    }

    private static void userOptions(Scanner sc, UserAction userAction, Account account,User user) throws CloneNotSupportedException {  // Method to show User options
        while (true) {  // Using while loop for choosing user action
            System.out.println("User Actions:\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. View Transactions\n5. Change pin\n6. Exit");
            int choice = sc.nextInt();  // Get User choice
            if (choice == 1) {  // Withdraw money
                userAction.withdraw(user,sc,notes,account);  // Call withdraw method from UserAction
            } else if (choice == 2) {  // Deposit money
                userAction.deposit(account,user,notes, sc);  // Call deposit method from UserAction
            } else if (choice == 3) {  // Check balance
                System.out.println("Current Balance:" + user.getBalance());  // Display user's current balance
            } else if (choice == 4) {  // View transactions
                userAction.viewTransactions(account);  // Call viewTransactions method from UserAction
            } else if (choice == 5) {  // Change pin
                userAction.changePin(account, sc, user);  // Call changepin method from UserAction
            } else if (choice == 6) {  // Exit User session
                System.out.println("User logged out...");
                break;  // Break the loop and log out
            } else {  // Invalid choice
                System.out.println("Invalid choice. Try again.");
            }
        }
    }
    public static ArrayList<Notes>getNotesArrayList()
    {
        return notes;
    }
    public static void setNotesArrayList(ArrayList<Notes>notesArrayList)
    {
        notes=notesArrayList;
    }
    public static double getatmbalance()//getter method for atm balance
    {
        return atmBalance;
    }
    public static void setatmbalance(double balance)//setter method for atm balance
    {
        atmBalance=balance;
    }
}
