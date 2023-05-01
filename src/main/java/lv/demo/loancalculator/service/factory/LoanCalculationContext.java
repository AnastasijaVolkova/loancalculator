package lv.demo.loancalculator.service.factory;

import lombok.Builder;
import lombok.Getter;
import lombok.With;
import lv.demo.loancalculator.service.factory.interest.InterestFormula;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.service.factory.monthlypayment.MonthlyPaymentFormula;

@Getter
@Builder
public class LoanCalculationContext {

    private final CalculableValue interestRate;
    private final InterestFormula interestFormula;
    private final MonthlyPaymentFormula monthlyPaymentFormula;
    @With
    private final int year;
    @With
    private final int totalMonths;
    @With
    private final CalculableValue balance;

    public CalculableValue calculateInterest() {
        return interestFormula.calculateInterest(this);
    }

    public CalculableValue calculateMonthlyPayment() {
        return monthlyPaymentFormula.calculateMonthlyPayment(this);
    }
}
