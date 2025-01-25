import java.util.ArrayList;

class User extends Account{//class name
    private double balance;// Store user balance amount
    private static ArrayList<Account>customer=new ArrayList<>();
    public User(String userId, String userPin) { // Constructor
        super(userId,userPin);
    }

    public User() {} // Default Constructor
    // getters and setters
    public double getBalance() { // user balance getter
        return balance;
    }

    public void setBalance(double balance) { // user balance setter
        this.balance = balance;
    }
    static ArrayList<Account> getAdminList()
    {
        return customer;
    }

    static ArrayList<Account> getUserList() {
        return customer;
    }
}
