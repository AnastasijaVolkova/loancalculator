package lv.demo.loancalculator.service.factory;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.contextbuilders.HouseLoanCalculationContextBuilder;
import lv.demo.loancalculator.service.factory.interest.HouseLoanInterestFormula;
import lv.demo.loancalculator.service.factory.monthlypayment.HouseLoanMonthlyPaymentFormula;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;


class LoanCalculationContextFactoryTest {

    @Test
    void getHouseLoanContext() {
        LoanCalculationContextFactory factory = new LoanCalculationContextFactory(Collections.singletonList(new HouseLoanCalculationContextBuilder()));

        LoanCalculationContext actual = factory.getCalculationContext(LoanType.HOUSE);

        assertThat(actual.getInterestRate()).isEqualToComparingFieldByField(CalculableValue.of(0.035));
        assertThat(actual.getInterestFormula()).isEqualToComparingFieldByField(HouseLoanInterestFormula.init());
        assertThat(actual.getMonthlyPaymentFormula()).isEqualToComparingFieldByField(HouseLoanMonthlyPaymentFormula.init());
    }

    @Test
    void getNoContextWithError() {
        LoanCalculationContextFactory factory = new LoanCalculationContextFactory(new ArrayList<>());

        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            try {
                factory.getCalculationContext(LoanType.HOUSE);
            } catch (HttpClientErrorException ex) {
                assertThat(ex.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
                assertThat(ex.getMessage()).isEqualTo("400 Loan type HOUSE doesn't exist");
                throw ex;
            }
        });
    }
}