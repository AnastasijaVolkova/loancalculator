package lv.demo.loancalculator.service.factory.contextbuilders;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.service.factory.LoanCalculationContext;

public interface LoanCalculationContextBuilder {

    LoanCalculationContext build();

    LoanType getType();

}
