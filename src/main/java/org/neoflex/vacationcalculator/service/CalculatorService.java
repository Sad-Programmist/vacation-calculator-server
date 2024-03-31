package org.neoflex.vacationcalculator.service;

import lombok.AllArgsConstructor;
import org.neoflex.vacationcalculator.data.dto.VacationPayDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class CalculatorService {
    public VacationPayDto calculateVacation(double averageSalary, LocalDate vacationStartDate, LocalDate vacationEndDate) {
        if (vacationStartDate == null || vacationEndDate == null ||
                vacationEndDate.isBefore(vacationStartDate) || averageSalary < 0) {
            return null;
        }

        int workingDays = 0;
        int vacationDays = (int) ChronoUnit.DAYS.between(vacationStartDate, vacationEndDate) + 1;
        LocalDate currentDate = vacationStartDate.minusYears(1);

        while (!currentDate.isAfter(vacationStartDate)) {
            if (currentDate.getDayOfWeek().getValue() < 7) {
                workingDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return new VacationPayDto(averageSalary * 12 / workingDays * vacationDays);
    }
}
