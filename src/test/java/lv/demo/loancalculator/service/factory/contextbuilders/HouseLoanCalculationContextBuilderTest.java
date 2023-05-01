package lv.demo.loancalculator.service.factory.contextbuilders;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.service.factory.interest.HouseLoanInterestFormula;
import lv.demo.loancalculator.service.factory.monthlypayment.HouseLoanMonthlyPaymentFormula;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HouseLoanCalculationContextBuilderTest {

    private final LoanCalculationContextBuilder builder = new HouseLoanCalculationContextBuilder();

    @Test
    void buildContext() {
        LoanCalculationContext actual = builder.build();

        assertThat(actual.getInterestRate()).isEqualToComparingFieldByField(CalculableValue.of(0.035));
        assertThat(actual.getInterestFormula()).isEqualToComparingFieldByField(HouseLoanInterestFormula.init());
        assertThat(actual.getMonthlyPaymentFormula()).isEqualToComparingFieldByField(HouseLoanMonthlyPaymentFormula.init());
        assertThat(actual.getYear()).isZero();
        assertThat(actual.getTotalMonths()).isZero();
        assertThat(actual.getBalance()).isNull();
    }

    @Test
    void getType() {
        LoanType actual = builder.getType();
        assertThat(actual).isEqualTo(LoanType.HOUSE);
    }
}