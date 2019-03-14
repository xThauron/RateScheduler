package helpers;

import models.RateScheduler.InterestRate;
import models.RateScheduler.RateSchedulerDetails;

import java.util.ArrayList;

public class RateScheduler implements IRateScheduler {
    private ArrayList<InterestRate> interestRateList;

    RateScheduler() {
        this.interestRateList = new ArrayList<InterestRate>();
    }

    @Override
    public ArrayList<InterestRate> getInterestRateList() {
        return interestRateList;
    }

    @Override
    public void generateInterestRateList(RateSchedulerDetails rateSchedulerDetails) {

    }
}
