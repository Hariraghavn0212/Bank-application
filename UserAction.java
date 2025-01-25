import Note.Notes;
import Notes.*;
import java.util.ArrayList;
import java.util.Scanner;

class UserAction {

    public static Account userLogin(ArrayList<Account> accounts, User user, Scanner sc) { // Method user login
        System.out.print("Enter User ID: ");
        String userId = sc.next();
        int attempt = 1;

        for (Account account : accounts) { // enhanced for loop is used to itrate one by one
            if (account instanceof User ){ //check the account is instance of user
                if( account.getId().equals(userId)){ //check both are equal are not
                    while (attempt <= 3) { // It has only 3 attempt
                        System.out.print("Enter your  PIN: ");
                        String userPin = sc.next();

                        if (account.getPin().equals(userPin)) { //check the pin is correct or not
                            System.out.println("User login successful.");
                            return account;// return the user
                        } else {
                            attempt++; // add increment if attempt fails
                            int remainingAttempts = 3 - attempt; // Decreasing remaining attempt
                            if (remainingAttempts == 0) { // Check the remaining attempt
                                System.out.println("User account locked ....");
                                return null;
                            } else {
                                System.out.println("Incorrect pin You have " + remainingAttempts );
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Invalid User id");
        return null;
    }

    public void changePin(Account account, Scanner sc, User user) { // method for change pin
        System.out.println("Enter your Pin :");
        String oldpin= sc.next();

        if (oldpin.equals((account.getPin()))) {// check whether the oldpin is correct
            System.out.println("Enter your new User Pin :");
            String newpin = sc.next();
            account.setPin(newpin); // set the new pin
            System.out.println(" Pin was successfully changed .");
        } else {
            System.out.println("Incorrect PIN...");
        }
    }


    public  void withdraw(User user,Scanner scanner,ArrayList<Notes>cashInventory,Account account)throws CloneNotSupportedException{//withdraw method

        System.out.println("Enter the withdraw amount :");
        Double amt=scanner.nextDouble();
        double amount=amt;
        ArrayList<String> noteCopy=new ArrayList<>();//array list for copy notes
        ArrayList<Notes> NoteDuplicate=new ArrayList<>();//array list for dupilict notes
        for (Notes note:ATM.getNotesArrayList()){//notes array list obj name will check one by one
            NoteDuplicate.add(note.clone());//to get the dupulecit notes to add in clone menthod
        }
        for (int i = 0; i < NoteDuplicate.size() - 1; i++) { //sorting of notes in descending order
            for (int j = i + 1; j < NoteDuplicate.size(); j++) {
                if (NoteDuplicate.get(i).getNoteValue() < NoteDuplicate.get(j).getNoteValue()) {
                    // Swap notes in descending order
                    Notes temp = NoteDuplicate.get(i);
                    NoteDuplicate.set(i, NoteDuplicate.get(j));
                    NoteDuplicate.set(j, temp);
                }
            }
        }

        while (amount != 0)
        {
            boolean denominationFound = false;  // variable to check denomination can be used
            for (Notes note : NoteDuplicate)
            {
                int noteValue = note.getNoteValue();// store the note value
                int availableNotes = note.getNoteCount();// store the note count
                int requiredNotes = (int) (amount / noteValue);// calculate the required note

                if (requiredNotes > 0 && noteValue <= amount && availableNotes > 0)
                {
                    int notesToWithdraw = Math.min(requiredNotes, availableNotes);  // Use the lesser of required and available
                    // Debugging: Show how the amount is changing after each withdrawal
                    System.out.println("Withdrawing " + notesToWithdraw + " notes of " + noteValue);
                    note.setNoteCount(availableNotes - notesToWithdraw);  // Update the note count in the ATM
                    noteCopy.add("You got " + notesToWithdraw + " notes of " + noteValue);
                    denominationFound = true;  // Set the flag to true as we have withdrawn some notes
                }

                // If the withdrawal amount is reduced to 0, break out of the loop
                if (amount == 0)
                {
                    break;
                }
            }

            // If no denomination could fulfill the withdrawal, or the amount is still non-zero
            if (!denominationFound || amount != 0)
            {
                System.out.println("Mistake in denominations. Please try again with a different amount.");
                return;  // Exit withdrawal if no denominations could satisfy the request
            }
        }
    }

    public void deposit(Account account, User user, ArrayList<Notes> NOTES, Scanner sc) {//deposit method
        System.out.print("Enter your deposite amount : ");
        double amount = sc.nextDouble();

        System.out.print("Enter number no of 2000 notes : ");
        int num2000 = sc.nextInt();
        System.out.print("Enter number no of 500 notes : ");
        int num500= sc.nextInt();
        System.out.print("Enter number no of 200 notes : ");
        int num200= sc.nextInt();
        System.out.print("Enter number no of 100 notes : ");
        int num100 = sc.nextInt();

        double totalAmount = (num100 * 100) + (num200 * 200) + (num500 * 500) + (num2000 * 2000);

        if (amount == totalAmount) {
            addToCashInventory(NOTES, new Note100(num100));
            addToCashInventory(NOTES, new Note200(num200));
            addToCashInventory(NOTES, new Note500(num500));
            addToCashInventory(NOTES, new Note2000(num2000));

            user.setBalance(user.getBalance() + totalAmount);
            ATM.setatmbalance(ATM.getatmbalance()+totalAmount);
            // Add transaction for the user
            Transaction transaction = new Transaction("Deposit", amount,account.getId());
            account.addTransaction(transaction);

            System.out.println("Deposit amount successful.....");

        }
        else {
            System.out.println("Deposit amount does not match the denomination counts.");
        }
    }


    private void addToCashInventory(ArrayList<Notes> cashInventory, Notes newNote) { // method to add money to inventory
        boolean value = false;

        for (Notes note : cashInventory) { // Iterates the notes from cashInventory
            if (note.getNoteValue() == newNote.getNoteValue()) { // check the  note value to new note value
                note.setNoteCount(note.getNoteCount() + newNote.getNoteCount()); // set the old not value + new note vale
                value = true;
                break;
            }
        }
        if (!value) { // if not found
            cashInventory.add(newNote); // add new note
        }
    }

    public void viewTransactions(Account account) {//view transation method for user
        if(account.getTransactions().isEmpty())
        {
            System.out.println("No transaction in this account ");
        }else {
            System.out.println("User Transactions:");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }
}
