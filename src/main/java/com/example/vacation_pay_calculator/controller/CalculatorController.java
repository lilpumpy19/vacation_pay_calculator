package com.example.vacation_pay_calculator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.vacation_pay_calculator.model.VacationPayRequest;
import com.example.vacation_pay_calculator.service.calculator.CalculatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CalculatorController {

    final CalculatorService calculatorService;



     @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(@Valid @RequestBody VacationPayRequest vacationPayRequest) {
        try {
            double resultValue = calculatorService.calculateVacationPay(vacationPayRequest);
            return ResponseEntity.ok(resultValue);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    

}
