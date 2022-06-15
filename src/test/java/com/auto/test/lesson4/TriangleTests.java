package com.auto.test.lesson4;

import com.auto.main.lesson4.Triangle;
import com.auto.main.lesson4.WrongInputException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class TriangleTests {
    private static Logger logger = LoggerFactory.getLogger(TriangleTests.class);

    @BeforeAll
    static void beforeAll() {
        logger.error("error");
        logger.info("info");
        logger.trace("trace");
    }

    @Test
    @DisplayName("Smoke. Тест проверяет существование треугольника с заданными сторонами")
    void checkTriangleExist() {
        double area = Triangle.triangleArea(1, 1, 1);
        assertThat(area).isGreaterThan(0);
    }

    @ParameterizedTest
    @DisplayName("Тест проверяет равенство ожидаемой и фактической площади ")
    @CsvSource({"1,1,1, 0.4330127018922193", "7,3,5, 6.49519052838329"})
    void checkTriangleArea(double a, double b, double c, double expectedArea) {
        double resultArea = Triangle.triangleArea(a, b, c);
        assertThat(resultArea).isEqualTo(expectedArea);

    }

    @Test
    @DisplayName("Тест проверяет вывод исключения если по введенным сторонам невозможно построить треугольник ")
    void exceptionWrongInput() {
        Assertions.assertThrows(WrongInputException.class, () -> Triangle.triangleArea(2, 2, 7));
        Assertions.assertThrows(WrongInputException.class, () -> Triangle.triangleArea(0, 1, 0));
        Assertions.assertThrows(WrongInputException.class, () -> Triangle.triangleArea(-7, 3, 5));

    }

    @ParameterizedTest
    @DisplayName("Позитивные тесты построения треугольника")
    @MethodSource("triangleDataProvider")
    void positiveTests(double a, double b, double c, double expectedArea) {
        double resultArea = Triangle.triangleArea(a, b, c);
        assertThat(resultArea).isEqualTo(expectedArea);
    }

    private static Stream<Arguments> triangleDataProvider() {
        return Stream.of(
                Arguments.of(5, 5, 5, 10.825317547305483),
                Arguments.of(4, 5, 6, 9.921567416492215),
                Arguments.of(7, 5, 7, 16.345871038277526),
                Arguments.of(75464, 55464, 74645, 1.9339075885807748E9)
        );
    }


}
