package models.RateScheduler;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RateSchedulerDetails {
    @NotEmpty
    @DecimalMin("1")
    private String creditValueStr;


    private double creditValue;
    private int interestRateAmount;
    private double interestPercent;
    private double interestConstPay;
    private String interestRateType;

    public RateSchedulerDetails(double creditValue, int interestRateAmount, double interestPercent, double interestConstPay, String interestRateType) {
        this.creditValue = creditValue;
        this.interestRateAmount = interestRateAmount;
        this.interestPercent = interestPercent;
        this.interestConstPay = interestConstPay;
        this.interestRateType = interestRateType;
    }


    public double getCreditValue() {
        return creditValue;
    }

    public int getInterestRateAmount() {
        return interestRateAmount;
    }

    public double getInterest() {
        return interestPercent / 100;
    }

    public double getInterestConstPay() {
        return interestConstPay;
    }

    public String getInterestRateType() {
        return interestRateType;
    }
}
