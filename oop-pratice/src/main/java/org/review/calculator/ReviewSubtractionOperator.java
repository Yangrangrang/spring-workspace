package org.review.calculator;

public class ReviewSubtractionOperator implements ReviewNewArithmeticOperator{
    @Override
    public boolean supports(String oper) {
        return oper.equals("-");
    }

    @Override
    public int calculate(ReviewPositiveNumber num1, ReviewPositiveNumber num2) {
        return num1.toInt() - num2.toInt();
    }
}
