package lv.demo.loancalculator.controller;

import lombok.RequiredArgsConstructor;
import lv.demo.loancalculator.enums.LoanType;
import lv.demo.loancalculator.pojo.LoanInput;
import lv.demo.loancalculator.pojo.LoanPlanView;
import lv.demo.loancalculator.service.LoanCalculationService;
import lv.demo.loancalculator.service.LoanRepositoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LoanCalculatorController {

    private final LoanCalculationService service;
    private final LoanRepositoryService repositoryService;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping({"loan/new"})
    public String createNewLoan(Model model) {
        model.addAttribute("loanInput", new LoanInput());
        return "selectLoan.html";
    }

    @PostMapping("loan/calculate")
    public String generateLoanPlan(@RequestParam(value = "type") LoanType type, @ModelAttribute LoanInput loanInput) {
        long id = service.calculateLoan(loanInput, type);
        return String.format("redirect:/loan/%d", id);
    }

    @GetMapping("loan/{id}")
    public String getLoanPlan(@PathVariable long id, Model model) {
        LoanPlanView loanPlan = repositoryService.getLoanPlan(1);
        model.addAttribute("loanPlan", loanPlan);
        return "loanPlan.html";
    }
}
