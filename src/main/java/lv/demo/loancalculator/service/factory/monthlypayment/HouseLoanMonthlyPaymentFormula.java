package lv.demo.loancalculator.service.factory.monthlypayment;

import lombok.NoArgsConstructor;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;

@NoArgsConstructor(staticName = "init")
public class HouseLoanMonthlyPaymentFormula implements MonthlyPaymentFormula {

    @Override
    public CalculableValue calculateMonthlyPayment(LoanCalculationContext context) {
        CalculableValue monthlyInterestRate = context.getInterestRate().divide(12);
        CalculableValue intermediateRate = (monthlyInterestRate.plus(1)).pow(context.getTotalMonths());
        return (monthlyInterestRate.multiply(intermediateRate)).divide((intermediateRate.minus(1))).multiply(context.getBalance()).rounded();
    }
}
