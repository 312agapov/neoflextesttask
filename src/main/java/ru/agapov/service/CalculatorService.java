package org.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class CalculatorService {

    private final int workDaysInYear = 248;

    private static final List<LocalDate> HOLIDAY_LIST = List.of(
            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 5),
            LocalDate.of(2024, 1, 6),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 1, 8),
            LocalDate.of(2024, 2, 23),
            LocalDate.of(2024, 2, 24),
            LocalDate.of(2024, 2, 25),
            LocalDate.of(2024, 3, 8),
            LocalDate.of(2024, 3, 9),
            LocalDate.of(2024, 3, 10),
            LocalDate.of(2024, 4, 28),
            LocalDate.of(2024, 4, 29),
            LocalDate.of(2024, 4, 30),
            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),
            LocalDate.of(2024, 5, 10),
            LocalDate.of(2024, 5, 11),
            LocalDate.of(2024, 5, 12),
            LocalDate.of(2024, 6, 12),
            LocalDate.of(2024, 11, 3),
            LocalDate.of(2024, 11, 4),
            LocalDate.of(2024, 12, 29),
            LocalDate.of(2024, 12, 30),
            LocalDate.of(2024, 12, 31)
    );

    public boolean isWorkingDay(LocalDate day){
        DayOfWeek dayOfWeek = day.getDayOfWeek();
        if(dayOfWeek == dayOfWeek.SATURDAY || dayOfWeek == dayOfWeek.SUNDAY || HOLIDAY_LIST.contains(day)){
            return false;
        } else {
            return true;
        }
    }

    public int calculateWorkDays(){
        LocalDate yearStart = LocalDate.of(2024,1,1);
        LocalDate yearEnd = LocalDate.of(2024,12,31);
        LocalDate currentDay = yearStart;
        int countWorkDays = 0;
        while(!currentDay.isAfter(yearEnd)){
            if(isWorkingDay(currentDay)){
                countWorkDays++;
            }
            currentDay = currentDay.plusDays(1);
        }
        return countWorkDays;
    }

    public int getDaysOfWork(String firstDayOfVacation, String lastDayOfVacation){
        int workDays = 0;
        LocalDate firstDay,lastDay;
        try{
            firstDay = LocalDate.parse(firstDayOfVacation);
            lastDay = LocalDate.parse(lastDayOfVacation);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("Некорректный формат даты. Используйте YYYY-MM-DD.");
        }
        LocalDate currentDay = firstDay;
        while(!currentDay.isAfter(lastDay)){
            if(isWorkingDay(currentDay)){
                workDays++;
            }
            currentDay = currentDay.plusDays(1);
        }
        return workDays;
    }

}

