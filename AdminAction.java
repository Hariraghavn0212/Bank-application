import Note.Notes;//importing notes
import Notes.Note100;//importing 100 rs notes
import Notes.Note200;//importing 200 rs notes
import Notes.Note500;//importing 500 rs notes
import Notes.Note2000;//importing 2000 rs notes

import java.util.ArrayList;
import java.util.Scanner;

class AdminAction extends ATM{//In this Class we are Extending the Atm class
    public static Account adminLogin(ArrayList<Account> accounts, Scanner sc) { // Method to Admin Login
        System.out.print("Enter Admin ID: ");
        String adminId = sc.next();
        int tries = 1;
        for (Account account : accounts) {
            if (account instanceof Admin) { // check account is instance of admin
                if (account.getId().equals(adminId)) { // Check admin id is valid
                    while (tries <= 3) {//allow only for 3 attempts
                        System.out.print("Enter Admin pin: ");
                        String adminPin = sc.next();
                        if (account.getPin().equals(adminPin)) { // Check admin pin is valid
                            System.out.println("Admin login successful.");
                            return account; // return admin
                        } else {
                            tries++; // add incrementer
                            int remainingtries = 3 - tries; // Decreasing remaining attempt
                            if (remainingtries == 0) { // Check the remaining attempt
                                System.out.println("User try is completed");
                                return null;
                            } else {
                                System.out.println("Incorrect PIN. You have " + remainingtries+ " attempts left....");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void addUser(ArrayList<Account> allAccounts, Scanner sc) { // Method to add a user
        System.out.print("Enter new User ID: ");
        String userId = sc.next();
        for (Account acc : allAccounts) { // Iterates the user information from list
            if (acc.getId().equals(userId)) { // Check if User ID already exists
                System.out.println("User ID already exists");
                return;
            }
        }
        System.out.print("Enter new User PIN: ");
        String userPin = sc.next();
        User newUser = new User(userId, userPin); //  user details in User variable to be stored
        allAccounts.add(newUser); // adding the new user in allAccount array list as object
        System.out.println("User added successfully.");
    }

    public void deleteUser(ArrayList<Account> accounts, Scanner sc) { // Method to delete a user
        System.out.print("Enter User ID to delete: ");
        String userId = sc.next();

        boolean userFound = false;
        for (Account account : accounts) { //  the user details will store in account
            if (account.getId().equals(userId)) { // Check wheater the given id is exsit or not
                accounts.remove(account); // Delete the user from user list
                System.out.println("User " + userId + " deleted successfully.");
                userFound = true;
                break;
            }
        }

        if (!userFound) { // if user not found
            System.out.println("User with ID " + userId + " not found.");
        }
    }

    public void viewUsers(ArrayList<Account> accounts) { // Method to view all user
        System.out.println("List of Users:");
        for (Account account : accounts) { // Iterates the user information from list
            if (account instanceof User) { // check if account is instance of user is there
                System.out.println("User ID: " + account.getId() + "\tBalance: " + ((User) account).getBalance() + "\n");
            }
        }
    }


    public void depositecashtoatm(ArrayList<Notes> note, Account loginedAdmin, Scanner sc) {
        for (Notes notes : note) {
            System.out.println(notes.getNoteValue() + " " + notes.getNoteCount()); //to view how many notes and note count
        }
        System.out.println("Enter the amount of money to add to the ATM:");
        double total = sc.nextDouble();
        System.out.print("Enter number of  denomination notes 2000: ");
        int num2000 = sc.nextInt();
        System.out.print("Enter number of  denomination notes 500: ");
        int num500 = sc.nextInt();
        System.out.print("Enter number of denomination notes 200: ");
        int num200 = sc.nextInt();
        System.out.print("Enter number of  denomination notes 100: ");
        int num100 = sc.nextInt();

        double totalAmount = (num100 * 100) + (num200 * 200) + (num500 * 500) + (num2000 * 2000);

        if (totalAmount == total) { //to check the entered amount and denomation amount is equal
            System.out.println("Total amount to be added to ATM: " + totalAmount);

            addToCashNotes(note, new Note100(num100)); // Add money to cash Inventory with count
            addToCashNotes(note, new Note200(num200));
            addToCashNotes(note, new Note500(num500));
            addToCashNotes(note, new Note2000(num2000));

            ATM.setatmbalance(ATM.getatmbalance() + totalAmount);// Adding transaction in account
            System.out.println("Money added successfully to ATM.");
            loginedAdmin.setTransactions(new Transaction("Deposit", totalAmount, loginedAdmin.getId()));
            for (Notes notes : note) {
                System.out.println("The notes are"+notes.getNoteValue() + " " + notes.getNoteCount()); //to view how many notes and note count
            }
        } else {
            System.out.println("Deposit amount does not match the denomination amount");
        }
    }
    private void addToCashNotes(ArrayList<Notes> Note, Notes newNote) { // Method for  add the cash for inventory
        boolean found = false;
        for (Notes note : Note) { // Iterates the notes from Note
            if (note.getNoteValue() == newNote.getNoteValue()) { // check the get note value to new note value
                note.setNoteCount(note.getNoteCount() + newNote.getNoteCount()); // set the old not value + new note vale
                found = true;
                break;
            }
        }
        if (!found) { // if not found
            Note.add(newNote); // add new note
        }
    }
    public static double ATMBalance(ArrayList<Notes> NOte) { // method to check ATM balance
        double totalBalance = 0;
        for (Notes note : NOte) { // Iterates the notes from Note
            totalBalance += note.getNoteCount() * note.getNoteValue();
        }
        return totalBalance;
    }

    public void viewTransaction(ArrayList<Account>accounts) {
        System.out.println("Enter option 1.user\n2.admin");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice==1) // if the input is user then print user transactions
        {
            for (Account user : accounts) // go through the List
            {

                if (user instanceof User) // if the transaction is empty then print empty
                {
                    System.out.println("Transaction for user "+user.getId());
                    if(user.getTransactions().isEmpty())//If the Transaction is empty
                    {
                        System.out.println("NO transation is found ");
                    }
                    else{
                        for(Transaction transaction : user.getTransactions())
                        {
                            System.out.println(transaction);

                        }
                        return;
                    }

                }
            }

        }

        if (choice==2) // if the input is user then print user transactions
        {
            for (Account user : accounts) // go through the List
            {

                if (user instanceof Admin) // if the transaction is empty then print empty
                {
                    System.out.println("Transaction for user "+user.getId());
                    if(user.getTransactions().isEmpty())
                    {
                        System.out.println("No transation is found ");
                    }
                    else{
                        for(Transaction transaction : user.getTransactions())
                        {
                            System.out.println(transaction);

                        }
                        return;
                    }

                }
            }
        }
    }
}
