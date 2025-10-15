import java.text.NumberFormat;

public abstract class Mortgage implements MortgageConstants {
    protected String mortgageNumber;
    protected String customerName;
    protected double mortgageAmount;
    protected double interestRate;
    protected int termYears;
    protected abstract void setInterestRateFromPrime(double currentPrimeRate);
// Abstract base class for all mortgages; 
    public Mortgage(String mortgageNumber, String customerName, double mortgageAmount,
            double currentPrimeRate, int requestedTermYears) {

        if (mortgageAmount > MAX_MORTGAGE_AMOUNT) {
            throw new IllegalArgumentException(
                    "Mortgage amount cannot exceed $" + MAX_MORTGAGE_AMOUNT);
        }

        this.mortgageNumber = mortgageNumber;
        this.customerName = customerName;
        this.mortgageAmount = mortgageAmount;

        if (requestedTermYears != SHORT_TERM && requestedTermYears != MEDIUM_TERM && requestedTermYears != LONG_TERM) {
            this.termYears = SHORT_TERM;
        } else {
            this.termYears = requestedTermYears;
        }

        setInterestRateFromPrime(currentPrimeRate);
    }
 // total = principal + (principal * rate * years)
    public double getTotalOwed() {
        return mortgageAmount + (mortgageAmount * interestRate * termYears);
    }

    public String getMortgageInfo() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormat = new java.text.DecimalFormat("0.00%");


        return "\n=== " + BANK_NAME + " Mortgage ===" +
                "\nMortgage Number: " + mortgageNumber +
                "\nCustomer Name  : " + customerName +
                "\nAmount         : " + currencyFormat.format(mortgageAmount) +
                "\nInterest Rate  : " + percentFormat.format(interestRate) +
                "\nTerm (years)   : " + termYears +
                "\nTotal Owed     : " + currencyFormat.format(getTotalOwed());
    }
}
