package lv.demo.loancalculator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "LOAN_PLAN")
public class LoanPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    double interestRate;
    double remainingAmount;
    @OneToMany(mappedBy = "loanPlan")
    List<MonthlyPlan> monthlyPlans;
}
