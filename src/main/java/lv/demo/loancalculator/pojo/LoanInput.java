package lv.demo.loancalculator.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LoanInput {

    private final int years;
    private final int months;
    private final double amount;

}
