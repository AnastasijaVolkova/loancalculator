package lv.demo.loancalculator.service.factory.monthlypayment;

import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;

public interface MonthlyPaymentFormula {

    CalculableValue calculateMonthlyPayment(LoanCalculationContext context);

}
