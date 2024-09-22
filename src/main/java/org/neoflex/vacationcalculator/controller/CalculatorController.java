package org.neoflex.vacationcalculator.controller;

import lombok.AllArgsConstructor;
import org.neoflex.vacationcalculator.data.dto.VacationPayDto;
import org.neoflex.vacationcalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/vacation")
@CrossOrigin(value = "http://localhost:3000", allowCredentials = "true")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/calculate")
    public ResponseEntity<VacationPayDto> calculateVacation(@RequestParam double averageSalary,
                                                            @RequestParam int vacationDays,
                                                            @RequestParam LocalDate vacationStartDate,
                                                            @RequestParam LocalDate vacationEndDate,
                                                            @RequestParam boolean isSixDayWorkWeek) {
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays, vacationStartDate,
                vacationEndDate, isSixDayWorkWeek);
        if (vacationPayDto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(vacationPayDto, HttpStatus.OK);
    }
}
