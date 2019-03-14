package helpers;

import models.InterestRate;
import models.RateSchedulerDetails;

import java.util.ArrayList;

public interface IRateScheduler {
    ArrayList<InterestRate> getInterestRateList();

    void generateInterestRateList(RateSchedulerDetails rateSchedulerDetails);
}