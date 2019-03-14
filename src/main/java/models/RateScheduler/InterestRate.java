package models.RateScheduler;

public class InterestRate {
    private double capitalValue;
    private double taxValue;
    private double constPayValue;

    public InterestRate(double capitalValue, double taxValue, double constPayValue) {
        this.capitalValue = capitalValue;
        this.taxValue = taxValue;
        this.constPayValue = constPayValue;
    }

    public double getCapitalValue() {
        return capitalValue;
    }

    public double getTaxValue() {
        return taxValue;
    }


    public double getConstPayValue() {
        return constPayValue;
    }

    public double getTotalRateValue() {
        return capitalValue + taxValue + constPayValue;
    }
}
