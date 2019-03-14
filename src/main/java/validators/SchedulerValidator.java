package validators;

import java.util.HashMap;
import java.util.Map;

public class SchedulerValidator {
    private Map<String, String> errors;
    private String creditValue;
    private String interestRateAmount;
    private String interestPercent;
    private String interestConstPay;
    private String interestRateType;

    public SchedulerValidator(String creditValue, String interestRateAmount, String interestPercent, String interestConstPay, String interestRateType) {
        this.creditValue = creditValue;
        this.interestRateAmount = interestRateAmount;
        this.interestPercent = interestPercent;
        this.interestConstPay = interestConstPay;
        this.interestRateType = interestRateType;
        this.errors = new HashMap<String, String>();
    }

    public boolean isValidated() {
        return errors.isEmpty();
    }

    public void validate() {
        if (!isDoubleNonNegative(creditValue)) {
            errors.put("creditValue", "Wartość kredytu nie moze być mniejsza niż zero!");
        }

        if (!isDoubleNonNegative(interestPercent)) {
            errors.put("interestPercent", "Oprocentowanie nie może być ujemne!");
        }

        if (!isDoubleNonNegative(interestConstPay)) {
            errors.put("interestConstPay", "Stała opłata nie może być mniejsza niż zero!");
        }

        if (!isIntegerGreaterThanZero(interestRateAmount)) {
            errors.put("interestRateAmount", "Ilość rat musi być większa niż zero!");
        }

        if (!isValidInterestRateType(interestRateType)) {
            errors.put("interestRateType", "Nieprawidłowy rodzaj oprocentowania!");
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private boolean isDoubleNonNegative(String value) {
        try {
            double valueDouble = Double.parseDouble(value);
            if (valueDouble < 0)
                throw new IllegalArgumentException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isIntegerGreaterThanZero(String value) {
        try {
            double valueDouble = Integer.parseInt(value);
            if (valueDouble <= 0)
                throw new IllegalArgumentException();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidInterestRateType(String value) {
        return value.equals("desc") || value.equals("const");
    }


}
