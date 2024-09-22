package org.neoflex.vacationcalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.neoflex.vacationcalculator.data.dto.VacationPayDto;
import org.neoflex.vacationcalculator.service.CalculatorService;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest
class VacationCalculatorApplicationTests {

    @Test
    @DisplayName("Стандартный случай с датами 6 дневная рабочая неделя")
    void testSixDayWorkWeek() {
        // Условия
        double averageSalary = 10000;
        int vacationDays = 10;
        LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
        LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);
        boolean isSixDayWorkWeek = true;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays, vacationStartDate,
                vacationEndDate, isSixDayWorkWeek);

        // Проверка результатов
        double expected = 2292.99;
        double epsilon = 0.01;
        assertEquals(expected, vacationPayDto.getVacationPay(), epsilon);
    }

    @Test
    @DisplayName("Стандартный случай с количеством дней")
    void testVacationDays() {
        // Условия
        double averageSalary = 10000;
        int vacationDays = 10;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                null, null, false);

        // Проверка результатов
        double expected = 3412.97;
        double epsilon = 0.01;
        assertEquals(expected, vacationPayDto.getVacationPay(), epsilon);
    }

    @Test
    @DisplayName("Отрицательное количеством дней отпуска")
    void testNegativeVacationDays() {
        // Условия
        double averageSalary = 10000;
        int vacationDays = -10;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                null, null, false);

        // Проверка результатов
        assertNull(vacationPayDto);
    }

    @Test
    @DisplayName("Стандартный случай с датами 5 дневная рабочая неделя")
    void testFiveDayWorkWeek() {
        // Условия
        double averageSalary = 10000;
        int vacationDays = 10;
        LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
        LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);
        boolean isSixDayWorkWeek = false;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                vacationStartDate, vacationEndDate, isSixDayWorkWeek);

        // Проверка результатов
        double expected = 2758.62;
        double epsilon = 0.01;
        assertEquals(expected, vacationPayDto.getVacationPay(), epsilon);
    }

    @Test
    @DisplayName("Обратный порядок дат")
    void testWrongDate() {
        // Условия
        double averageSalary = 20000;
        int vacationDays = 10;
        LocalDate vacationStartDate = LocalDate.of(2024, 7, 10);
        LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);
        boolean isSixDayWorkWeek = true;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                vacationStartDate, vacationEndDate, isSixDayWorkWeek);

        // Проверка результатов
        assertNull(vacationPayDto);
    }

    @Test
    @DisplayName("Даты отсутствуют")
    void testNullDate() {
        // Условия
        double averageSalary = 20000;
        int vacationDays = 0;
        LocalDate vacationStartDate = null;
        LocalDate vacationEndDate = null;
        boolean isSixDayWorkWeek = true;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                vacationStartDate, vacationEndDate, isSixDayWorkWeek);

        // Проверка результатов
        assertNull(vacationPayDto);
    }

    @Test
    @DisplayName("Отрицательная средняя зарплата")
    void testNegativeAverageSalary() {
        // Условия
        double averageSalary = -20000;
        int vacationDays = 10;
        LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
        LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);
        boolean isSixDayWorkWeek = true;

        // Проведение теста
        CalculatorService calculatorService = new CalculatorService();
        VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationDays,
                vacationStartDate, vacationEndDate, isSixDayWorkWeek);

        // Проверка результатов
        assertNull(vacationPayDto);
    }

}
