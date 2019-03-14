package helpers;

import models.RateScheduler.InterestRate;
import models.RateScheduler.RateSchedulerDetails;

public class DescRateScheduler extends RateScheduler implements IRateScheduler {
    public DescRateScheduler() {
        super();
    }

    @Override
    public void generateInterestRateList(RateSchedulerDetails rateSchedulerDetails) {
        double capitalValue;
        double taxValue;
        for (int i = 0; i < rateSchedulerDetails.getInterestRateAmount(); i++) {
            capitalValue = rateSchedulerDetails.getCreditValue() / rateSchedulerDetails.getInterestRateAmount();
            taxValue = (rateSchedulerDetails.getCreditValue() - i * capitalValue) * rateSchedulerDetails.getInterest() / rateSchedulerDetails.getInterestRateAmount();
            getInterestRateList().add(new InterestRate(capitalValue, taxValue, rateSchedulerDetails.getInterestConstPay()));
        }
    }
}
