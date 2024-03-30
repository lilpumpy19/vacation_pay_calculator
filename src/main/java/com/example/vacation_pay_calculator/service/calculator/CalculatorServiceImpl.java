package com.example.vacation_pay_calculator.service.calculator;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.vacation_pay_calculator.model.VacationPayRequest;
import com.example.vacation_pay_calculator.service.holidays.HolidaysManager;

import lombok.RequiredArgsConstructor;

/**
 * CalculatorServiceImpl is responsible for calculating vacation pay based on the provided
 * VacationPayRequest, taking into account holidays and weekends.
 */
@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private static final double AVG_MONTH_DAYS = 29.3; // Average number of days in a month
    private final HolidaysManager holidaysManager; // HolidaysManager for checking holidays and weekends

    /**
     * Calculates the vacation pay based on the provided VacationPayRequest.
     *
     * @param vacationPayRequest The request containing average salary, vacation days, and start date
     * @return The calculated vacation pay
     */
    public double calculateVacationPay(VacationPayRequest vacationPayRequest) {
        if (vacationPayRequest.getStartDate() != null) {
            return calculateWithDate(vacationPayRequest);
        } else {
            return calculateWithoutDate(vacationPayRequest);
        }
    }

    /**
     * Calculates the vacation pay without considering a specific start date.
     *
     * @param vacationPayRequest The request containing average salary and vacation days
     * @return The calculated vacation pay
     */
    private double calculateWithoutDate(VacationPayRequest vacationPayRequest) {
        return vacationPayRequest.getAverageSalary() / AVG_MONTH_DAYS * vacationPayRequest.getVacationDays();
    }

    /**
     * Calculates the vacation pay considering a specific start date.
     * Deducts holidays and weekends from the total vacation days.
     *
     * @param vacationPayRequest The request containing average salary, vacation days, and start date
     * @return The calculated vacation pay
     */
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

    /**
     * Checks if the given date is a holiday or a weekend day.
     *
     * @param date The date to check
     * @return true if the date is a holiday or a weekend day, false otherwise
     */
    private boolean isHolidayOrWeekendDay(LocalDate date) {
        return holidaysManager.isWeekend(date) || holidaysManager.isHoliday(date);
    }
}
