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
        if (isDoubleNegative(creditValue)) {
            errors.put("creditValue", "Wartość kredytu nie moze być mniejsza niż zero!");
        }

        if (isDoubleNegative(interestPercent)) {
            errors.put("interestPercent", "Oprocentowanie nie może być ujemne!");
        }

        if (isDoubleNegative(interestConstPay)) {
            errors.put("interestConstPay", "Stała opłata nie może być mniejsza niż zero!");
        }

        if (!isIntegerGreaterThanZero(interestRateAmount)) {
            errors.put("interestRateAmount", "Ilość rat musi być większa niż zero!");
        }

        if (!isValidInterestRateType(interestRateType)) {
            errors.put("type", "Nieprawidłowy rodzaj oprocentowania!");
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private boolean isDoubleNegative(String value) {
        try {
            double valueDouble = Double.parseDouble(value);
            if (valueDouble < 0)
                throw new IllegalArgumentException();
            return false;
        } catch (Exception e) {
            return true;
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
        return value.equals("DESC") || value.equals("CONST");
    }


}
