package lv.demo.loancalculator.monetary;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculableValue {

    private double value;

    public static CalculableValue of(double value) {
        return new CalculableValue(value);
    }

    public CalculableValue plus(double otherValue) {
        return new CalculableValue(this.value + otherValue);
    }

    public CalculableValue minus(double otherValue) {
        return new CalculableValue(this.value - otherValue);
    }

    public CalculableValue minus(CalculableValue otherValue) {
        return minus(otherValue.doubleValue());
    }

    public CalculableValue multiply(double otherValue) {
        return new CalculableValue(this.value * otherValue);
    }

    public CalculableValue multiply(CalculableValue otherValue) {
        return multiply(otherValue.doubleValue());
    }

    public CalculableValue divide(double otherValue) {
        return new CalculableValue(this.value / otherValue);
    }

    public CalculableValue divide(CalculableValue otherValue) {
        return divide(otherValue.doubleValue());
    }

    public CalculableValue pow(double exponent) {
        return new CalculableValue(Math.pow(this.value, exponent));
    }

    public CalculableValue rounded() {
        return new CalculableValue(BigDecimal.valueOf(this.value).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public double doubleValue() {
        return this.value;
    }

}
