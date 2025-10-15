
// InsuranceApp.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsuranceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Insurance> list = new ArrayList<>();

        System.out.println("Insurance Entry");

        while (true) {
            System.out.print("Enter insurance type (Health or Life): ");
            String typeInput = sc.nextLine().trim();

            System.out.print("Enter monthly fee: ");
            double fee = Double.parseDouble(sc.nextLine().trim());

            Insurance ins;
            if (typeInput.equalsIgnoreCase("Health")) {
                ins = new Health(fee);
            } else if (typeInput.equalsIgnoreCase("Life")) {
                ins = new Life(fee);
            } else {
                System.out.println("Unknown type. Please enter 'Health' or 'Life'.");
                continue;
            }

            list.add(ins);

            System.out.print("Add another? (y/n): ");
            if (!sc.nextLine().trim().equalsIgnoreCase("y"))
                break;
        }

        Insurance[] policies = list.toArray(new Insurance[0]);

        System.out.println("\n Insurance list :");
        for (Insurance p : policies) {
            p.setInsuranceCost();
            p.displayInfo();
            System.out.println("---------------------------------");
        }

        sc.close();
    }
}
