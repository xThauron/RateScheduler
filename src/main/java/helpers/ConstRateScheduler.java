package helpers;

import models.RateScheduler.InterestRate;
import models.RateScheduler.RateSchedulerDetails;

public class ConstRateScheduler extends RateScheduler implements IRateScheduler {
    public ConstRateScheduler() {
        super();
    }

    @Override
    public void generateInterestRateList(RateSchedulerDetails rateSchedulerDetails) {
        double capitalValue;
        double taxValue;
        double pomq = rateSchedulerDetails.getInterest() / rateSchedulerDetails.getInterestRateAmount();
        double q = 1 + pomq;
        double qBefore = 1;
        double qn = Math.pow(q, rateSchedulerDetails.getInterestRateAmount());

        for (int i = 0; i < rateSchedulerDetails.getInterestRateAmount(); i++) {
            capitalValue = rateSchedulerDetails.getCreditValue() * ((qBefore * q - qBefore) / (qn - 1));
            taxValue = rateSchedulerDetails.getCreditValue() * ((qn - qBefore) / (qn - 1)) * pomq;
            getInterestRateList().add(new InterestRate(capitalValue, taxValue, rateSchedulerDetails.getInterestConstPay()));
            qBefore = qBefore * q;
        }
    }
}
