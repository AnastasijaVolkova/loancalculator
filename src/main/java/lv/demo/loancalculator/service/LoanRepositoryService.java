package lv.demo.loancalculator.service;

import lombok.RequiredArgsConstructor;
import lv.demo.loancalculator.pojo.LoanPlanView;
import lv.demo.loancalculator.repository.LoanPlanRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class LoanRepositoryService {

    private final LoanPlanRepository repository;

    public LoanPlanView getLoanPlan(long id) {
       return repository.getLoanPlanById(id)
               .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(400)));
    }
}
