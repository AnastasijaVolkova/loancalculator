package lv.demo.loancalculator.service.factory.interest;

import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HouseLoanInterestFormulaTest {

    private final InterestFormula formula = HouseLoanInterestFormula.init();

    @Test
    void calculateInterest_1() {
        LoanCalculationContext context = LoanCalculationContext.builder()
                .year(2019)
                .interestRate(CalculableValue.of(0.045))
                .balance(CalculableValue.of(165000))
                .build();
        CalculableValue actual = formula.calculateInterest(context);

        assertThat(actual.rounded().doubleValue()).isEqualTo(618.75);
    }

    @Test
    void calculateInterest_2() {
        LoanCalculationContext context = LoanCalculationContext.builder()
                .year(2019)
                .interestRate(CalculableValue.of(0.045))
                .balance(CalculableValue.of(162564.59))
                .build();
        CalculableValue actual = formula.calculateInterest(context);

        assertThat(actual.rounded().doubleValue()).isEqualTo(609.62);
    }
}