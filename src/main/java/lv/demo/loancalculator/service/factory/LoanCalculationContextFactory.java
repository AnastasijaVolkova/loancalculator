package lv.demo.loancalculator.service.factory;

import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.service.factory.contextbuilders.LoanCalculationContextBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LoanCalculationContextFactory {

    private final Map<LoanType, LoanCalculationContextBuilder> contextBuilderMap;

    public LoanCalculationContextFactory(List<LoanCalculationContextBuilder> contextBuilders) {
        this.contextBuilderMap = contextBuilders.stream()
                .collect(Collectors.toMap(LoanCalculationContextBuilder::getType, Function.identity()));
    }

    public LoanCalculationContext getCalculationContext(LoanType loanType) {
        return Optional.ofNullable(contextBuilderMap.get(loanType))
                .map(LoanCalculationContextBuilder::build)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(400), String.format("Loan type %s doesn't exist", loanType)));
    }
}
