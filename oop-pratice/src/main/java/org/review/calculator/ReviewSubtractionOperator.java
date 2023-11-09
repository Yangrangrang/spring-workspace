package org.review.calculator;

public class ReviewSubtractionOperator implements ReviewNewArithmeticOperator{
    @Override
    public boolean supports(String oper) {
        return oper.equals("-");
    }

    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}
