package com.example.vacation_pay_calculator.service.holidays;

import java.time.LocalDate;

public interface HolidaysManager {
    boolean isHoliday(LocalDate date);

    boolean isWeekend(LocalDate date);
}
