package org.review.calculatorTest;

import org.assertj.core.api.Assertions;
import org.example.calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.review.calculator.ReviewCalculrator;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 요구사항
 * 간단한 사칙연산을 할 수 있다.
 * 함수로만 계산할 수 있다.
 * 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생시킨다.
 * MVC패턴(Model-View-Controller) 기반으로 구현한다.
 */
public class CalculatorTest {

    @DisplayName("덧셈")
    @Test
    void 사칙연산덧셈() {
        int result = ReviewCalculrator.calculate(1, "+", 2);
        assertThat(result).isEqualTo(3);
    }

    @DisplayName("사칙연산")
    @ParameterizedTest
    @MethodSource("fomulaAndResult")
    void 사칙연산(int num1, String oper, int num2, int result) {
        int calculateResult = ReviewCalculrator.calculate(num1, oper, num2);

        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> fomulaAndResult() {
        return Stream.of(
                arguments(1, "+", 2, 3),
                arguments(1, "-", 2, -1),
                arguments(8, "*", 2, 16),
                arguments(8, "/", 2, 4)
        );
    }

    @DisplayName("나눗셈 예외")
    @Test
    void 나눗셈예외() {
        assertThatCode(() -> ReviewCalculrator.calculate(10 , "/", 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0으로는 나눌 수 없습니다.");
    }
}
