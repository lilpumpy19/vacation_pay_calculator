package com.example.vacation_pay_calculator.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents a request for vacation pay calculation.
 */
@Data
@AllArgsConstructor
public class VacationPayRequest {

    /**
     * The average salary of the employee.
     */
    @NotNull(message = "Salary must be specified")
    @Min(value = 1, message = "Salary must be greater than 0")
    private double averageSalary;

    /**
     * The number of vacation days.
     */
    @NotNull(message = "Vacation days must be specified")
    @Min(value = 1, message = "Vacation days must be greater than 0")
    private int vacationDays;

    /**
     * The start date of the vacation.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
}
