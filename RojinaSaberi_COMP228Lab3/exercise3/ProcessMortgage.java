import java.util.Scanner;

public class ProcessMortgage {    // Main application class

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Mortgage[] mortgages = new Mortgage[3]; //array of 3 mortgage

        System.out.println("    " + MortgageConstants.BANK_NAME + " Mortgage Processing :"); // Header

        double primeRate;   // Will store the current prime rate entered by user
        while (true) {
            System.out.print("Enter current prime rate : ");
            if (scanner.hasNextDouble()) {
                primeRate = scanner.nextDouble();
                scanner.nextLine();
                if (primeRate >= 0.0 && primeRate <= 100.0)
                    break;
                System.out.println("Please enter a rate between 0 and 100.");
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        for (int i = 0; i < mortgages.length; i++) {   // Repeat for each (3 times)
            System.out.println("\n    Enter information of Mortgage " + (i + 1) + "    ");

            char mortgageType;
            while (true) {
                System.out.print("Enter mortgage type (B for Business, P for Personal): ");
                String input = scanner.nextLine().trim().toUpperCase();  // Normalize whitespace and case
                if (input.equals("B") || input.equals("BUSINESS")) {
                    mortgageType = 'B';
                    break;
                } else if (input.equals("P") || input.equals("PERSONAL")) {
                    mortgageType = 'P';
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'B' or 'P'.");
                }
            }

            System.out.print("Enter mortgage number: ");
            String mortgageNumber = scanner.nextLine();

            System.out.print("Enter customer name: ");
            String customerName = scanner.nextLine();

            double amount;
            while (true) {
                System.out.print("Enter mortgage amount: ");
                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                    scanner.nextLine();
                    if (amount >= 0.01 && amount <= MortgageConstants.MAX_MORTGAGE_AMOUNT)
                        break;
                    System.out.println(
                            "Please enter a value between 0.01 and " + MortgageConstants.MAX_MORTGAGE_AMOUNT + ".");
                } else {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine();
                }
            }

            int term;
            while (true) {
                System.out.print("Enter term in years (1, 3, or 5; others become 1): ");
                if (scanner.hasNextInt()) {
                    int entered = scanner.nextInt();
                    scanner.nextLine();
                    term = (entered == 3 || entered == 5) ? entered : 1; // force to 1 if not 3 or 5
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a whole number.");
                    scanner.nextLine();
                }
            }

            if (mortgageType == 'B') {
                mortgages[i] = new BusinessMortgage(mortgageNumber, customerName, amount, primeRate, term);    // BusinessMortgage: interest = prime + 1%
            } else {
                mortgages[i] = new PersonalMortgage(mortgageNumber, customerName, amount, primeRate, term);    // PersonalMortgage: interest = prime + 2%
            }
        }

        System.out.println("\n=== Mortgage Information ===");
        int i = 0;
        while (i < mortgages.length) {
            System.out.println(mortgages[i].getMortgageInfo());
            i++;
        }

        scanner.close();
    }

}