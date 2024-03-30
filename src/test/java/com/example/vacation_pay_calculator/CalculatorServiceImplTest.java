package com.example.vacation_pay_calculator;

import com.example.vacation_pay_calculator.model.VacationPayRequest;
import com.example.vacation_pay_calculator.service.calculator.CalculatorService;
import com.example.vacation_pay_calculator.service.calculator.CalculatorServiceImpl;
import com.example.vacation_pay_calculator.service.holidays.HolidaysManager;
import com.example.vacation_pay_calculator.service.holidays.HolidaysManagerImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;

class CalculatorServiceImplTest {

    @Test
    void calculateVacationPay_WithoutStartDate_ShouldReturnCorrectValue() {
        CalculatorService calculatorService = new CalculatorServiceImpl(Mockito.mock(HolidaysManager.class));
        VacationPayRequest request = new VacationPayRequest(16000.0, 14, null);
        double expectedResult = 16000.0 * 14 / 29.3;

        double result = calculatorService.calculateVacationPay(request);

        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void calculateVacationPay_WithStartDate_ShouldReturnCorrectValue() {
        HolidaysManagerImpl holidaysManager = Mockito.mock(HolidaysManagerImpl.class);
        CalculatorService calculatorService = new CalculatorServiceImpl(holidaysManager);

        Mockito.when(holidaysManager.isHoliday(LocalDate.of(2022, Month.JANUARY, 1))).thenReturn(true);
        Mockito.when(holidaysManager.isWeekend(LocalDate.of(2022, Month.JANUARY, 2))).thenReturn(true);
        Mockito.when(holidaysManager.isWeekend(LocalDate.of(2022, Month.JANUARY, 8))).thenReturn(true);
        Mockito.when(holidaysManager.isWeekend(LocalDate.of(2022, Month.JANUARY, 9))).thenReturn(true);
        

        VacationPayRequest request = new VacationPayRequest(16000.0, 14, LocalDate.of(2022, Month.JANUARY, 1));
        double expectedResult = 16000.0 * (14-4) / 29.3;

        double result = calculatorService.calculateVacationPay(request);

        Assertions.assertEquals(expectedResult, result);
    }
}
