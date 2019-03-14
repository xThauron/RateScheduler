package helpers;

import models.InterestRate;
import models.RateSchedulerDetails;
import org.junit.Assert;
import org.junit.Test;

public class TestDescRateScheduler {
    @Test
    public void should_be_so_many_rates_as_demanded() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(4123, 33, 11, 11, "desc");
        IRateScheduler rateScheduler = new DescRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        Assert.assertEquals(rateSchedulerDetails.getInterestRateAmount(), rateScheduler.getInterestRateList().size());
    }

    @Test
    public void should_be_every_next_rate_smaller_than_before_one() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(12345, 22, 5, 200, "desc");
        IRateScheduler rateScheduler = new DescRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        double beforeOne = Double.MAX_VALUE;
        for (InterestRate interestRate : rateScheduler.getInterestRateList()) {
            Assert.assertTrue(beforeOne > interestRate.getTotalRateValue());
            beforeOne = interestRate.getTotalRateValue();
        }
    }

    @Test
    public void should_be_expected_values() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(6000, 4, 20, 122, "desc");
        IRateScheduler rateScheduler = new DescRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        double expectedCapitalValue = 1500;
        double expectedConstPay = 122;

        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getCapitalValue(), expectedCapitalValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getTotalRateValue(), 1922, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getTaxValue(), 300, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getCapitalValue(), expectedCapitalValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getTotalRateValue(), 1847, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getTaxValue(), 225, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getCapitalValue(), expectedCapitalValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getTotalRateValue(), 1772, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getTaxValue(), 150, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getCapitalValue(), expectedCapitalValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getTotalRateValue(), 1697, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getTaxValue(), 75, 0.1);
    }
}