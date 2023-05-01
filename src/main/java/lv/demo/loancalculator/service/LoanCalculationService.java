package lv.demo.loancalculator.service;

import lombok.RequiredArgsConstructor;
import lv.demo.loancalculator.entity.LoanPlan;
import lv.demo.loancalculator.entity.MonthlyPlan;
import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.monetary.CalculableValue;
import lv.demo.loancalculator.pojo.LoanInput;
import lv.demo.loancalculator.repository.LoanPlanRepository;
import lv.demo.loancalculator.repository.MonthlyPlanRepository;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;
import lv.demo.loancalculator.service.factory.LoanCalculationContextFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class LoanCalculationService {

    private final LoanCalculationContextFactory factory;
    private final LoanPlanRepository loanPlanRepository;
    private final MonthlyPlanRepository monthlyPlanRepository;

    public long calculateLoan(LoanInput loanInput, LoanType loanType) {
        LoanCalculationContext calculationContext = factory.getCalculationContext(loanType);
        CalculableValue loanAmount = CalculableValue.of(loanInput.getAmount());
        int totalMonths = loanInput.getYears() * 12 + loanInput.getMonths();
        CalculableValue monthlyPayment = calculateMonthlyPayment(loanAmount, calculationContext, totalMonths);

        LoanPlan loanPlan = new LoanPlan();
        loanPlan.setInterestRate(calculationContext.getInterestRate().doubleValue());
        List<MonthlyPlan> monthlyPlans = getMonthlyPlans(totalMonths, calculationContext.withBalance(loanAmount), monthlyPayment, loanPlan);
        loanPlan.setMonthlyPlans(monthlyPlans);
        loanPlanRepository.save(loanPlan);
        monthlyPlanRepository.saveAll(monthlyPlans);
        return loanPlan.getId();
    }

    private List<MonthlyPlan> getMonthlyPlans(int totalMonths, LoanCalculationContext context,
                                             CalculableValue monthlyPayment, LoanPlan loanPlan) {
        List<MonthlyPlan> monthlyPlans = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        MonthlyPlan lastMonthlyPlan = IntStream.rangeClosed(1, totalMonths)
                .boxed()
                .map(month -> initMonthlyPlan(currentDate, month, monthlyPayment))
                .peek(monthlyPlan -> monthlyPlan.setLoanPlan(loanPlan))
                .reduce(MonthlyPlan.initialPlan(context.getBalance().doubleValue()),
                        (prev, current) -> calculateInterestPrincipalAndBalance(prev, current, context, monthlyPlans));

        loanPlan.setRemainingAmount(lastMonthlyPlan.getBalance());
        return monthlyPlans;
    }

    private MonthlyPlan initMonthlyPlan(LocalDate currentDate, int month, CalculableValue monthlyPayment) {
        LocalDate paymentDate = currentDate.plusMonths(month);
        MonthlyPlan monthlyPlan = new MonthlyPlan();
        monthlyPlan.setMonthName(paymentDate.getMonth().name());
        monthlyPlan.setYearValue(paymentDate.getYear());
        monthlyPlan.setPayment(monthlyPayment.doubleValue());
        return monthlyPlan;
    }

    private CalculableValue calculateMonthlyPayment(CalculableValue loanAmount, LoanCalculationContext context, int totalMonth) {
        return context
                .withTotalMonths(totalMonth)
                .withBalance(loanAmount)
                .calculateMonthlyPayment();
    }

    private MonthlyPlan calculateInterestPrincipalAndBalance(MonthlyPlan prev, MonthlyPlan current,
                                                             LoanCalculationContext context,
                                                             List<MonthlyPlan> monthlyPlans) {
        CalculableValue interest = calculateInterest(context, current, prev);
        CalculableValue principle = CalculableValue.of(current.getPayment()).minus(interest);
        current.setInterest(interest.doubleValue());
        current.setTotalInterest(interest.plus(prev.getTotalInterest()).doubleValue());
        current.setPrincipal(principle.rounded().doubleValue());
        current.setBalance(CalculableValue.of(prev.getBalance()).minus(principle).rounded().doubleValue());

        monthlyPlans.add(current);
        return current;
    }

    private CalculableValue calculateInterest(LoanCalculationContext context, MonthlyPlan current, MonthlyPlan prev) {
        return context
                .withYear(current.getYearValue())
                .withBalance(CalculableValue.of(prev.getBalance()))
                .calculateInterest();
    }
}
