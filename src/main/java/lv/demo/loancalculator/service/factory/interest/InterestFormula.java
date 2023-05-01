package lv.demo.loancalculator.service.factory.interest;

import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.monetary.CalculableValue;

/**
 * Common interface to implement interest formula for each specific loan type
 */
public interface InterestFormula {

    CalculableValue calculateInterest(LoanCalculationContext context);

}
