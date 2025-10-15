import java.util.Scanner;

public class GameTesterApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("-- Game Application--");
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter type of Full-time or Part-time: ");
        String type = input.nextLine().trim().toLowerCase();

        GameTester tester;

        if (type.equals("fulltime") || type.equals("full-time") || type.equals("full time")) {
            tester = new FullTimeGameTester(name);
        } else if (type.equals("parttime") || type.equals("part-time") || type.equals("part time")) {
            System.out.print("Enter hours worked: ");
            int hours = input.nextInt();
            tester = new PartTimeGameTester(name, hours);
        } else {
            System.out.println("Invalid type entered.");
            input.close();
            return;
        }

        System.out.println("\n--- Gamer Details ---");
        tester.displayInfo();
        System.out.println("Calculated Salary: $" + tester.CalculateSalary());

        input.close();
    }
}
