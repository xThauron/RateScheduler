package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRateSchedulerDetails {

    @Test
    public void should_interest_be_interest_percent_divided_by_one_hundred() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(10, 20, 12, 100, "const");
        Assert.assertEquals(0.12, rateSchedulerDetails.getInterest(), 0.01);

        rateSchedulerDetails = new RateSchedulerDetails(0, 0, 3.5, 0, "const");
        Assert.assertEquals(0.035, rateSchedulerDetails.getInterest(), 0.001);
    }
}