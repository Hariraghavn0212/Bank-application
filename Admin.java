import java.util.Scanner;
import java.util.ArrayList;
class Admin {

    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<Integer> userBalances = new ArrayList<>();
    public static ArrayList<String> userPins = new ArrayList<>(); // To store user PINs
    public static ArrayList<String> transactions = new ArrayList<>();

    public static void adminLogin() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter admin name:");
        String username = sc.nextLine();
        System.out.print("Enter admin password:");
        String password = sc.nextLine();
        AdminAction adminAction = new AdminAction();
        if (username.equals(adminAction.getAdminUsername()) && password.equals(adminAction.getAdminPassword())) {
            System.out.println("\nLogin Successful");
            adminMenu();
        } else {
            System.out.println("Invalid credentials");
        }
    }
    private static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. add user");
            System.out.println("2. delete user");
            System.out.println("3. deposit amount to ATM");
            System.out.println("4. view transactions");
            System.out.println("5. exit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    depositAmount();
                    break;
                case 4:
                    viewTransactions();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("no choice");
            }
        }
    }
    public static void addUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter username to add:");
        String username = sc.nextLine();
        System.out.print("enter PIN (4-no only):");
        String pin = sc.nextLine();
        if (pin.length() == 4) {
            if (AdminAction.getUsernames().contains(username)) {
                System.out.println("Username already exists");
            } else {
                AdminAction.getUsernames().add(username);
                AdminAction.getUserBalances().add(0);
                AdminAction.getUserPins().add(pin);
                System.out.println("User added successfully");
            }
        } else {
            System.out.println("Invalid enter a 4-digit pin");
        }
    }
    public static void deleteUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Username to Delete: ");
        String username = sc.nextLine();
        int index = AdminAction.getUsernames().indexOf(username);
        if (index != -1) {
            AdminAction.getUsernames().remove(index);
            AdminAction.getUserBalances().remove(index);
            AdminAction.getUserPins().remove(index);
            System.out.println("User deleted successfully");
        } else {
            System.out.println("Username not found");
        }
    }
    public static void depositAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no.of 2000 notes: ");
        int notes2000 = sc.nextInt();
        System.out.print("Enter no.of 500 notes: ");
        int notes500 = sc.nextInt();
        System.out.print("Enter no.of 200 notes: ");
        int notes200 = sc.nextInt();
        System.out.print("Enter no.of 100 notes: ");
        int notes100 = sc.nextInt();
        int totalAmount = (notes2000 * 2000) + (notes500 * 500) + (notes200 * 200) + (notes100 * 100);
        AdminAction.getTransactions().add("Admin deposited" + totalAmount + " ATM (2000 x" + notes2000 + ",500 x" + notes500 + ", 200 x " + notes200 + ", 100 x " + notes100 + ")");
        System.out.println("Amount is added succesfully:" + totalAmount);
    }
    public static void viewTransactions() {
        System.out.println("\nTransaction History:");
        for (String transaction : AdminAction.getTransactions()) {
            System.out.println(transaction);
        }
    }
}
