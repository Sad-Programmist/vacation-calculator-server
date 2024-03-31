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
	@DisplayName("Расчет отпускных за 5 дней со средней зп 10000")
	void testVacationPay1() {
		// Условия
		double averageSalary = 10000;
		LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
		LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);

		// Проведение теста
		CalculatorService calculatorService = new CalculatorService();
		VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationStartDate, vacationEndDate);

		// Проверка результатов
		double expected = 2292.99;
		double epsilon = 0.01;
		assertEquals(expected, vacationPayDto.getVacationPay(), epsilon);
	}

	@Test
	@DisplayName("Расчет отпускных за месяц со средней зп 20000")
	void testVacationPay2() {
		// Условия
		double averageSalary = 20000;
		LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
		LocalDate vacationEndDate = LocalDate.of(2024, 7, 15);

		// Проведение теста
		CalculatorService calculatorService = new CalculatorService();
		VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationStartDate, vacationEndDate);

		// Проверка результатов
		double expected = 27515.92;
		double epsilon = 0.01;
		assertEquals(expected, vacationPayDto.getVacationPay(), epsilon);
	}

	@Test
	@DisplayName("Расчет отпускных при неправильном порядке дат")
	void testWrongDate() {
		// Условия
		double averageSalary = 20000;
		LocalDate vacationStartDate = LocalDate.of(2024, 7, 10);
		LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);

		// Проведение теста
		CalculatorService calculatorService = new CalculatorService();
		VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationStartDate, vacationEndDate);

		// Проверка результатов
		assertNull(vacationPayDto);
	}

	@Test
	@DisplayName("Расчет отпускных при отсутствии дат")
	void testNullDate() {
		// Условия
		double averageSalary = 20000;
		LocalDate vacationStartDate = null;
		LocalDate vacationEndDate = null;

		// Проведение теста
		CalculatorService calculatorService = new CalculatorService();
		VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationStartDate, vacationEndDate);

		// Проверка результатов
		assertNull(vacationPayDto);
	}

	@Test
	@DisplayName("Расчет отпускных с отрицательной средней зарплатой")
	void testNegativeAverageSalary() {
		// Условия
		double averageSalary = -20000;
		LocalDate vacationStartDate = LocalDate.of(2024, 6, 10);
		LocalDate vacationEndDate = LocalDate.of(2024, 6, 15);

		// Проведение теста
		CalculatorService calculatorService = new CalculatorService();
		VacationPayDto vacationPayDto = calculatorService.calculateVacation(averageSalary, vacationStartDate, vacationEndDate);

		// Проверка результатов
		assertNull(vacationPayDto);
	}

}
