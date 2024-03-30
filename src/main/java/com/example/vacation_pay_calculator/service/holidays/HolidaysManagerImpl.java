package com.example.vacation_pay_calculator.service.holidays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

/**
 * HolidaysManagerImpl is responsible for managing holidays and checking if a given date
 * is a holiday or a weekend day.
 */
@Component
public class HolidaysManagerImpl implements HolidaysManager {

    private Set<LocalDate> holidays; // Set to store holiday dates

    /**
     * Constructor to initialize the HolidaysManagerImpl with a set of holidays.
     * This set is populated with some default holidays.
     */
    public HolidaysManagerImpl() {
        this.holidays = new HashSet<>();
        initHolidays();
    }

    /**
     * Initializes the set of holidays with default holiday dates.
     */
    private void initHolidays() {
        addHoliday(1, 1);  // New Year's Day
        addHoliday(3, 8);  // International Women's Day
        addHoliday(5, 1);  // Spring and Labor Day
        addHoliday(5, 9);  // Victory Day
        addHoliday(6, 12); // Russia Day
    }

    /**
     * Adds a holiday to the set.
     *
     * @param month The month of the holiday
     * @param day   The day of the holiday
     */
    private void addHoliday(int month, int day) {
        holidays.add(LocalDate.of(0, month, day)); // Adding holiday with year 0 (ignoring year)
    }

    /**
     * Checks if the given date is a holiday.
     *
     * @param date The date to check
     * @return true if the date is a holiday, false otherwise
     */
    public boolean isHoliday(LocalDate date) {
        return holidays.contains(date.withYear(0)); // Checking if date is in holidays set
    }

    /**
     * Checks if the given date is a weekend day (Saturday or Sunday).
     *
     * @param date The date to check
     * @return true if the date is a weekend day, false otherwise
     */
    public boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
