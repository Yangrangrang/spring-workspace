package org.review.calculator;

import java.util.Arrays;

public enum ReviewArithmeicOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 + num2;
        }
    },SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 - num2;
        }
    },MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 * num2;
        }
    }, DIVISION("/") {
        @Override
        public int arithmeticCalculate(int num1, int num2) {
            return num1 / num2;
        }
    };

    private final String operator;

    ReviewArithmeicOperator(String operator) {
        this.operator = operator;
    }

    public abstract int arithmeticCalculate(final int num1, final int num2);

    public static int calculate(int num1, String oper, int num2) {
        ReviewArithmeicOperator reviewArithmeicOperator = Arrays.stream(values())
                .filter(v -> v.operator.equals(oper))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산 아님"));
        return reviewArithmeicOperator.arithmeticCalculate(num1, num2);
    }
}
