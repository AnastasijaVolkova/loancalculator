package lv.demo.loancalculator.repository;

import lv.demo.loancalculator.entity.LoanPlan;
import lv.demo.loancalculator.pojo.LoanPlanView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanPlanRepository extends JpaRepository<LoanPlan, Long> {

    Optional<LoanPlanView> getLoanPlanById(long id);

}
