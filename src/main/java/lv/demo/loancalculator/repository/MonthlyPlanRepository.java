package lv.demo.loancalculator.repository;

import lv.demo.loancalculator.entity.MonthlyPlan;
import lv.demo.loancalculator.pojo.MonthlyPlanView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonthlyPlanRepository extends JpaRepository<MonthlyPlan, Long> {

    List<MonthlyPlanView> getMonthlyPlanByLoanPlanId(long id);
}
