package lv.demo.loancalculator.pojo;

import java.util.List;

public interface LoanPlanView {

    double getInterestRate();
    double getRemainingAmount();
    List<MonthlyPlanView> getMonthlyPlans();
}
