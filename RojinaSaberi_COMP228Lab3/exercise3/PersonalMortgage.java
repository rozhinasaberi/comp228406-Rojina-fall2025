
public class PersonalMortgage extends Mortgage {

    public PersonalMortgage(String mortgageNumber, String customerName, double mortgageAmount,
                            double currentPrimeRate, int requestedTermYears) {
        super(mortgageNumber, customerName, mortgageAmount, currentPrimeRate, requestedTermYears);
    }
    protected void setInterestRateFromPrime(double currentPrimeRate) {
        interestRate = (currentPrimeRate + 2.0) / 100.0;
    }
}
