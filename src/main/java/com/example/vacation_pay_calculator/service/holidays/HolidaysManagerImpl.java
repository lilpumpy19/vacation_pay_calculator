package com.example.vacation_pay_calculator.service.holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class HolidaysManagerImpl implements HolidaysManager {

    private Set<LocalDate> holidays;

    public HolidaysManagerImpl() {
        this.holidays = new HashSet<>();
        initHolidays();
    }

    private void initHolidays() {
        addHoliday(1, 1); // Новый год
        addHoliday(3, 8); // Международный женский день
        addHoliday(5, 1); // Праздник Весны и Труда
        addHoliday(5, 9); // День Победы
        addHoliday(6, 12); // День России
    }

    private void addHoliday(int month, int day) {
        holidays.add(LocalDate.of(0, month, day));
    }

    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date.withYear(0));
    }

    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
