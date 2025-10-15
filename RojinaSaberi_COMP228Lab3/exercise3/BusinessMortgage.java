

public class BusinessMortgage extends Mortgage {

    public BusinessMortgage(String mortgageNumber, String customerName, double mortgageAmount,
                            double currentPrimeRate, int requestedTermYears) {
        super(mortgageNumber, customerName, mortgageAmount, currentPrimeRate, requestedTermYears);
    }
   
    protected void setInterestRateFromPrime(double currentPrimeRate) {
        interestRate = (currentPrimeRate + 1.0) / 100.0;
    }
}
