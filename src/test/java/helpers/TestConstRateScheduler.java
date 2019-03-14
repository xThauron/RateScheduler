package helpers;

import models.InterestRate;
import models.RateSchedulerDetails;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TestConstRateScheduler extends Mockito {
    @Test
    public void should_all_rates_be_equal() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(6000, 12, 20, 0, "const");
        IRateScheduler rateScheduler = new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        double totalValueExpected = rateScheduler.getInterestRateList().get(0).getTotalRateValue();
        for (InterestRate interestRate : rateScheduler.getInterestRateList()) {
            Assert.assertEquals(interestRate.getTotalRateValue(), totalValueExpected, 0.001);
        }
    }

    @Test
    public void should_all_capital_rates_values_be_equal_as_credit_value() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(10000, 26, 3.5, 100, "const");
        IRateScheduler rateScheduler = new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        double sumCapitalValues = 0;
        for (InterestRate interestRate : rateScheduler.getInterestRateList()) {
            sumCapitalValues += interestRate.getCapitalValue();
        }

        Assert.assertEquals(sumCapitalValues, rateSchedulerDetails.getCreditValue(), 0.001);
    }

    @Test
    public void should_be_so_many_rates_as_demanded() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(14132, 15, 15, 93, "const");
        IRateScheduler rateScheduler = new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);
        Assert.assertEquals(rateSchedulerDetails.getInterestRateAmount(), rateScheduler.getInterestRateList().size());
    }

    @Test
    public void should_be_the_same_as_expected() {
        RateSchedulerDetails rateSchedulerDetails = new RateSchedulerDetails(54132, 4, 10, 101.50, "const");
        IRateScheduler rateScheduler = new ConstRateScheduler();
        rateScheduler.generateInterestRateList(rateSchedulerDetails);

        double expectedTotalRateValue = 14490.75;
        double expectedConstPay = 101.50;

        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getCapitalValue(), 13035.95, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getTotalRateValue(), expectedTotalRateValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(0).getTaxValue(), 1353.30, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getCapitalValue(), 13361.85, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getTotalRateValue(), expectedTotalRateValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(1).getTaxValue(), 1027.40, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getCapitalValue(), 13695.90, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getTotalRateValue(), expectedTotalRateValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(2).getTaxValue(), 693.35, 0.1);

        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getCapitalValue(), 14038.30, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getTotalRateValue(), expectedTotalRateValue, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getConstPayValue(), expectedConstPay, 0.1);
        Assert.assertEquals(rateScheduler.getInterestRateList().get(3).getTaxValue(), 350.96, 0.1);
    }
}