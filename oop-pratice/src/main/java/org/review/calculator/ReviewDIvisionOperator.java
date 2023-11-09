package org.review.calculator;

public class ReviewDIvisionOperator implements ReviewNewArithmeticOperator{
    @Override
    public boolean supports(String oper) {
        return oper.equals("/");
    }

    @Override
    public int calculate(int num1, int num2) {
        if (num1 == 0 || num2 == 0) {
            throw new IllegalArgumentException("0으로는 나눌 수 없습니다.");
        }
        return num1 / num2;
    }
}
