package lv.demo.loancalculator.service;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.pojo.LoanInput;
import lv.demo.loancalculator.pojo.LoanPlanView;
import lv.demo.loancalculator.pojo.MonthlyPlanView;
import lv.demo.loancalculator.repository.LoanPlanRepository;
import lv.demo.loancalculator.repository.MonthlyPlanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoanCalculationServiceTest {

    @Autowired
    private LoanCalculationService service;
    @Autowired
    private LoanPlanRepository loanPlanRepository;
    @Autowired
    private MonthlyPlanRepository monthlyPlanRepository;

    @Test
    void generateLoanPlan() {
        LoanInput input = LoanInput.builder()
                .years(1)
                .amount(3000)
                .build();

        long id = service.calculateLoan(input, LoanType.HOUSE);
        Optional<LoanPlanView> loanPlanById = loanPlanRepository.getLoanPlanById(id);
        assertThat(loanPlanById).isPresent();

        LoanPlanView actual = loanPlanById.get();
        List<MonthlyPlanView> monthlyPlans = monthlyPlanRepository.getMonthlyPlanByLoanPlanId(id);

        assertThat(actual.getInterestRate()).isEqualTo(0.035);
        assertThat(actual.getRemainingAmount()).isEqualTo(0.05);

        assertThat(monthlyPlans).hasSize(12);

        assertThat(monthlyPlans.get(0).getPayment()).isEqualTo(254.76);
        assertThat(monthlyPlans.get(0).getPrincipal()).isEqualTo(246.01);
        assertThat(monthlyPlans.get(0).getInterest()).isEqualTo(8.75);
        assertThat(monthlyPlans.get(0).getTotalInterest()).isEqualTo(8.75);
        assertThat(monthlyPlans.get(0).getBalance()).isEqualTo(2753.99);

        assertThat(monthlyPlans.get(1).getPayment()).isEqualTo(254.76);
        assertThat(monthlyPlans.get(1).getPrincipal()).isEqualTo(246.73);
        assertThat(monthlyPlans.get(1).getInterest()).isEqualTo(8.03);
        assertThat(monthlyPlans.get(1).getTotalInterest()).isEqualTo(16.78);
        assertThat(monthlyPlans.get(1).getBalance()).isEqualTo(2507.26);

        assertThat(monthlyPlans.get(11).getPayment()).isEqualTo(254.76);
        assertThat(monthlyPlans.get(11).getPrincipal()).isEqualTo(254.02);
        assertThat(monthlyPlans.get(11).getInterest()).isEqualTo(0.74);
        assertThat(monthlyPlans.get(11).getTotalInterest()).isEqualTo(57.17);
        assertThat(monthlyPlans.get(11).getBalance()).isEqualTo(0.05);
    }
}