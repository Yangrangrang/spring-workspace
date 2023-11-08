package org.example.calculator;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperator = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator());
    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        // 일반 조건문 방식
//        if (operator.equals("+")){
//            return operand1 + operand2;
//        } else if (operator.equals("-")) {
//            return operand1 - operand2;
//        } else if (operator.equals("*")) {
//            return operand1 * operand2;
//        } else if (operator.equals("/")) {
//            return operand1 / operand2;
//        }
//        return 0;

        // enum 방식
//        return ArithmeticOperator.calculate(operand1, operator, operand2);

        // interface방식
        return arithmeticOperator.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
