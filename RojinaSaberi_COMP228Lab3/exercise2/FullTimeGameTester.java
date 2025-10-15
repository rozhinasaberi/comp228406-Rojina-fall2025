public class FullTimeGameTester extends GameTester {

    public FullTimeGameTester(String name) {
        super(name, "Full-time");
    }

    @Override
    public double CalculateSalary() {
        return 3000.0;  // base salary
    }
}
