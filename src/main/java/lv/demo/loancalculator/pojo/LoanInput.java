package lv.demo.loancalculator.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class LoanInput {

    private int years;
    private int months;
    private double amount;

}
