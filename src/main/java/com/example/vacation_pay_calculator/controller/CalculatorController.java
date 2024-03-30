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

/**
 * Controller to handle vacation pay calculation requests.
 */
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    /**
     * Endpoint to calculate vacation pay based on the provided request.
     * @param vacationPayRequest The request containing average salary, vacation days, and start date.
     * @return ResponseEntity containing the calculated vacation pay amount.
     */
    @GetMapping("/calculate")
    public ResponseEntity<?> calculateVacationPay(@Valid @RequestBody VacationPayRequest vacationPayRequest) {
        try {
            double resultValue = calculatorService.calculateVacationPay(vacationPayRequest);
            return ResponseEntity.ok(resultValue);
        } catch (RuntimeException ex) {
            // If an exception occurs during calculation, return an internal server error with the exception message
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
