package lv.demo.loancalculator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "MONTHLY_PLAN")
public class MonthlyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String monthName;
    private int yearValue;
    private double payment;
    private double principal;
    private double interest;
    private double totalInterest;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "loan_plan_id")
    private LoanPlan loanPlan;

    public static MonthlyPlan initialPlan(double balance) {
        MonthlyPlan monthlyPlan = new MonthlyPlan();
        monthlyPlan.setBalance(balance);
        return monthlyPlan;
    }
}
