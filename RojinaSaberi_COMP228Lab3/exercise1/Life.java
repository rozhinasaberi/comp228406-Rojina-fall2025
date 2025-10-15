public class Life extends Insurance {

    public Life(double monthlyCost) {
        super("Life", monthlyCost); // Call parent to init type="Life" and monthlyCost.
    }

    @Override
    public void setInsuranceCost() {
        if (monthlyCost < 0) {
            System.out.printf(
                    "Invalid monthly cost for Life insurance: %.2f. Cost must be non-negative.%n",
                    monthlyCost);
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("Type: %s%n", getType());
        System.out.printf("Monthly Cost: $%.2f%n", getMonthlyCost());
    }
}
