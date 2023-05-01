package lv.demo.loancalculator.pojo;

public interface MonthlyPlanView {

    String getMonthName();
    int getYearValue();
    double getPayment();
    double getPrincipal();
    double getInterest();
    double getTotalInterest();
    double getBalance();

}
