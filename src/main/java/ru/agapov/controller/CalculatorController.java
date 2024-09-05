package org.example.controller;

import org.example.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
//@RequestMapping(path = "/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @GetMapping(path = "/calculate")
    public ResponseEntity<Float> calculateVacationPay(@RequestParam float yearSalary, @RequestParam int daysOfVacation){
        if (daysOfVacation <=0 || yearSalary <=0){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>((yearSalary/365)*daysOfVacation, HttpStatus.OK);
    }

    @GetMapping(path = "/calculate+")
    public ResponseEntity<Float> calculateVacationPayByDate(@RequestParam float yearSalary, @RequestParam String firstDayOfVacation, @RequestParam String lastDayOfVacation){
        if (yearSalary <=0){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>((yearSalary/calculatorService.calculateWorkDays())
                *calculatorService.getDaysOfWork(firstDayOfVacation,lastDayOfVacation), HttpStatus.OK);
    }
}
