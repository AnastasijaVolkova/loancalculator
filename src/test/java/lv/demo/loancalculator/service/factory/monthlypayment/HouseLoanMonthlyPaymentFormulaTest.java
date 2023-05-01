package lv.demo.loancalculator.service.factory.monthlypayment;

import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HouseLoanMonthlyPaymentFormulaTest {

    private final MonthlyPaymentFormula formula = HouseLoanMonthlyPaymentFormula.init();

    @Test
    void calculateMonthlyPayment() {
        LoanCalculationContext context = LoanCalculationContext.builder()
                .interestRate(CalculableValue.of(0.045))
                .totalMonths(360)
                .balance(CalculableValue.of(165000))
                .build();
        CalculableValue actual = formula.calculateMonthlyPayment(context);

        assertThat(actual.rounded().doubleValue()).isEqualTo(836.03);
    }
}