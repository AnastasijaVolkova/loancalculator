package lv.demo.loancalculator.service.factory.contextbuilders;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;

/**
 * Make sure implementation is annotated with either @Service or @Component
 * so that Spring collects all implementation into the factory list
 */
public interface LoanCalculationContextBuilder {

    LoanCalculationContext build();

    LoanType getType();

}
