package helpers;

import models.RateScheduler.InterestRate;
import models.RateScheduler.RateSchedulerDetails;

import java.util.ArrayList;

public interface IRateScheduler {
    ArrayList<InterestRate> getInterestRateList();

    void generateInterestRateList(RateSchedulerDetails rateSchedulerDetails);
}