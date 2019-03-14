package validators;

import org.junit.Assert;
import org.junit.Test;

public class TestSchedulerValidator {
    @Test
    public void should_be_five_errors_for_all_empty_fields() {
        SchedulerValidator schedulerValidator = new SchedulerValidator("", "", "", "", "");
        schedulerValidator.validate();
        Assert.assertEquals(schedulerValidator.getErrors().size(), 5);
    }

    @Test
    public void should_be_positive_number_of_rates() {
        SchedulerValidator schedulerValidator = new SchedulerValidator("12345", "-1", "25", "100", "10");
        schedulerValidator.validate();
        Assert.assertFalse(schedulerValidator.isValidated());
    }
}