public abstract class GameTester {
    protected String name;
    protected String status;
    protected boolean isFullTime;

    // Constructor
    public GameTester(String name, String status) {
        this.name = name;
        this.status = status;
    }

    // Abstract method to determine salary
    public abstract double CalculateSalary();

    // Common method to display info
    public void displayInfo() {
        System.out.println("Gamer Name: " + name);
        System.out.println("Status: " + status);
    }
}
