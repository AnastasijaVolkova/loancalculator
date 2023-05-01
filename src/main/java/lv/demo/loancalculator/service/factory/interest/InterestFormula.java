package lv.demo.loancalculator.service.factory.interest;

import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.monetary.CalculableValue;

public interface InterestFormula {

    CalculableValue calculateInterest(LoanCalculationContext context);

}
