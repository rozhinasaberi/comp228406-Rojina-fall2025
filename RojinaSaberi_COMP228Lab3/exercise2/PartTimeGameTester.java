public class PartTimeGameTester extends GameTester {
    private int hoursWorked;

    public PartTimeGameTester(String name, int hoursWorked) {
        super(name, "Part-time");
        this.hoursWorked = hoursWorked;
    }
  
    public double CalculateSalary() {
        return hoursWorked * 20.0;  // $20/hour
    }
}
