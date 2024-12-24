import java.util.ArrayList;

public  class AdminAction {
    private String adminUsername = "Admin";
    private String adminPassword = "admin123";
    public String getAdminUsername() {
        return adminUsername;
    }
    public String getAdminPassword() {
        return adminPassword;
    }
    public static ArrayList<String> getUsernames() {
        return Admin.usernames;
    }
    public static ArrayList<Integer> getUserBalances() {
        return Admin.userBalances;
    }
    public static ArrayList<String> getUserPins() {
        return Admin.userPins;
    }
    public static ArrayList<String> getTransactions() {
        return Admin.transactions;
    }
}
