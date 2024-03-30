package com.example.vacation_pay_calculator.service.calculator;

import com.example.vacation_pay_calculator.model.VacationPayRequest;

public interface CalculatorService {

    double calculateVacationPay(VacationPayRequest vacationPayRequest);
}
