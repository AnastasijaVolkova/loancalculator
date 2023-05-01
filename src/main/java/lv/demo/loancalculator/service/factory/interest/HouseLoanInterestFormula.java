package lv.demo.loancalculator.service.factory.interest;

import lombok.NoArgsConstructor;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.monetary.CalculableValue;

import java.time.Year;

@NoArgsConstructor(staticName = "init")
public class HouseLoanInterestFormula implements InterestFormula {

    @Override
    public CalculableValue calculateInterest(LoanCalculationContext context) {
        int daysInYear = Year.isLeap(context.getYear()) ? 366 : 365;
        return context.getBalance()
                .multiply(context.getInterestRate())
                .divide(daysInYear)
                .multiply(daysInYear / 12.0)
                .rounded();
    }

}
