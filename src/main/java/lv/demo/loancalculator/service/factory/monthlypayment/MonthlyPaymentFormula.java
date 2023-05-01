package lv.demo.loancalculator.service.factory.monthlypayment;

import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;

/**
 * Common interface to implement monthly payment formula for each specific loan type
 */
public interface MonthlyPaymentFormula {

    CalculableValue calculateMonthlyPayment(LoanCalculationContext context);

}
