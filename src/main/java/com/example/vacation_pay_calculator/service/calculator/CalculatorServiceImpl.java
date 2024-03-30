package com.example.vacation_pay_calculator.service.calculator;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.vacation_pay_calculator.model.VacationPayRequest;
import com.example.vacation_pay_calculator.service.holidays.HolidaysManager;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private static final double AVG_MONTH_DAYS = 29.3;
    private final HolidaysManager holidaysManager;

    public double calculateVacationPay(VacationPayRequest vacationPayRequest) {
        if (vacationPayRequest.getStartDate() != null) {
            return calculateWithDate(vacationPayRequest);
        } else {
            return calculateWithoutDate(vacationPayRequest);
        }
    }

    private double calculateWithoutDate(VacationPayRequest vacationPayRequest) {
        return vacationPayRequest.getAverageSalary() / AVG_MONTH_DAYS * vacationPayRequest.getVacationDays();
    }

    private double calculateWithDate(VacationPayRequest vacationPayRequest) {
        LocalDate startDate = vacationPayRequest.getStartDate();
        int vacationDays = vacationPayRequest.getVacationDays();

        for (int i = 0; i < vacationPayRequest.getVacationDays(); i++) {
            if (isHolidayOrWeekendDay(startDate)) {
                vacationDays--;
            }
            startDate = startDate.plusDays(1);
        }

        return vacationDays * vacationPayRequest.getAverageSalary() / AVG_MONTH_DAYS;

    }

    private boolean isHolidayOrWeekendDay(LocalDate date) {
        return holidaysManager.isWeekend(date) || holidaysManager.isHoliday(date);
    }
}
