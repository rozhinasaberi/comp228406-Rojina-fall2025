public abstract class Insurance {
    protected String type;
    protected double monthlyCost;

    public Insurance(String type, double monthlyCost) {
        this.type = type;
        this.monthlyCost = monthlyCost;
    }

    // getters
    public String getType() {
        return type;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }

    // abstract methods
    public abstract void setInsuranceCost();

    public abstract void displayInfo();
}
