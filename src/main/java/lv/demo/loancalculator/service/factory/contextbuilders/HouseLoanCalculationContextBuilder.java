package lv.demo.loancalculator.service.factory.contextbuilders;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.service.factory.interest.HouseLoanInterestFormula;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.monthlypayment.HouseLoanMonthlyPaymentFormula;
import org.springframework.stereotype.Component;

@Component
public class HouseLoanCalculationContextBuilder implements LoanCalculationContextBuilder {

    @Override
    public LoanCalculationContext build() {
        return LoanCalculationContext.builder()
                .interestRate(CalculableValue.of(0.035))
                .interestFormula(HouseLoanInterestFormula.init())
                .monthlyPaymentFormula(HouseLoanMonthlyPaymentFormula.init())
                .build();
    }

    @Override
    public LoanType getType() {
        return LoanType.HOUSE;
    }
}
