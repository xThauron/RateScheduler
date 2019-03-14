package models;

import org.junit.Assert;
import org.junit.Test;

public class TestInterestRate {
    @Test
    public void should_total_rate_value_be_sum_of_capital_value_and_tax_value_and_const_pay() {
        InterestRate interestRate = new InterestRate(1234.23, 321, 100);
        Assert.assertEquals(1655.23, interestRate.getTotalRateValue(), 0.01);

        interestRate = new InterestRate(1, 5, 0);
        Assert.assertEquals(6, interestRate.getTotalRateValue(), 0.01);

        interestRate = new InterestRate(100, 0, 0.1);
        Assert.assertNotEquals(100, interestRate.getTotalRateValue(), 0.01);
    }
}