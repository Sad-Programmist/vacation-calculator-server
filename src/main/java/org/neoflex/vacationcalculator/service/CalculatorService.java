package org.neoflex.vacationcalculator.service;

import lombok.AllArgsConstructor;
import org.neoflex.vacationcalculator.data.dto.VacationPayDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class CalculatorService {

    private double calculateVacationByDates(double averageSalary, LocalDate vacationStartDate,
                                            LocalDate vacationEndDate, boolean isSixDayWorkWeek) {
        int workingDays = 0;
        int vacationDays = (int) ChronoUnit.DAYS.between(vacationStartDate, vacationEndDate) + 1;
        LocalDate currentDate = vacationStartDate.minusYears(1);

        int weekend = 6;
        if (isSixDayWorkWeek)
            weekend = 7;

        while (!currentDate.isAfter(vacationStartDate)) {
            if (currentDate.getDayOfWeek().getValue() < weekend)
                workingDays++;

            currentDate = currentDate.plusDays(1);
        }

        return averageSalary * 12 / workingDays * vacationDays;
    }

    public VacationPayDto calculateVacation(double averageSalary, int vacationDays, LocalDate vacationStartDate,
                                            LocalDate vacationEndDate, boolean isSixDayWorkWeek) {
        if (vacationStartDate != null && vacationEndDate != null) {
            if (vacationEndDate.isBefore(vacationStartDate) || averageSalary < 0)
                return null;

            return new VacationPayDto(calculateVacationByDates(averageSalary, vacationStartDate,
                    vacationEndDate, isSixDayWorkWeek));
        } else {
            if (vacationDays < 1)
                return null;
            return new VacationPayDto(averageSalary / 29.3 * vacationDays);
        }
    }
}
