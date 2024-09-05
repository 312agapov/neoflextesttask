package org.example.test;

import org.example.service.CalculatorService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void isWorkingDay_workDay(){
        LocalDate workDay = LocalDate.of(2024,9,5);
        Assertions.assertTrue(calculatorService.isWorkingDay(workDay));
    }

    @Test
    public void isWorkingDay_weekendDay(){
        LocalDate weekendDay = LocalDate.of(2024,9,7);
        Assertions.assertFalse(calculatorService.isWorkingDay(weekendDay));
    }

    @Test
    public void isWorkingDay_holidayDay(){
        LocalDate holidayDay = LocalDate.of(2024,6,12);
        Assertions.assertFalse(calculatorService.isWorkingDay(holidayDay));
    }

    @Test
    public void isWorkingDay_6ofJanuary(){
        LocalDate day = LocalDate.of(2024,1,6);
        Assertions.assertFalse(calculatorService.isWorkingDay(day));
    }

    @Test
    public void getDaysOfWork(){
        String vacationStart = "2024-09-02";
        String vacationEnd = "2024-09-09";
        Assertions.assertEquals(6,calculatorService.getDaysOfWork(vacationStart,vacationEnd));
    }

    @Test
    public void getDaysOfWork_stringParseError() {
        String vacationStartBad = "2024--9-02";
        String vacationEnd = "2024-09-09";
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            LocalDate start = LocalDate.parse(vacationStartBad);
            LocalDate end = LocalDate.parse(vacationEnd);
        });
    }
}
