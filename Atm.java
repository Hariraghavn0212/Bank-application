import java.util.Scanner;
class Atm {
    static void atm(){
        Scanner sc = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter the number: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Admin.adminLogin();
                    break;
                case 2:
                    User.userLogin();
                    break;
                case 3:
                    System.out.println("Exit");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid number:");
            }
        }
    }
}
