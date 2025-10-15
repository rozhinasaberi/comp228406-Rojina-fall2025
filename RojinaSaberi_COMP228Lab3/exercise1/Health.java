public class Health extends Insurance {

    public Health(double monthlyCost) {
        super("Health", monthlyCost); // Call parent to init type="health" and monthlyCost.
    }

    @Override
    public void setInsuranceCost() {
        if (monthlyCost < 0) {
            System.out.printf(
                    "Invalid monthly cost for Health insurance: %.2f. Cost must be non-negative.%n",
                    monthlyCost);
        }

    }

    @Override
    public void displayInfo() {
        System.out.printf("Type: %s%n", getType());
        System.out.printf("Monthly Cost: $%.2f%n", getMonthlyCost());
    }
}
