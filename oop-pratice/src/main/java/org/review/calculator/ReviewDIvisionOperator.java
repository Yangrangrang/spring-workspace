package org.review.calculator;

public class ReviewDIvisionOperator implements ReviewNewArithmeticOperator{
    @Override
    public boolean supports(String oper) {
        return oper.equals("/");
    }

    @Override
    public int calculate(ReviewPositiveNumber num1, ReviewPositiveNumber num2) {
//        if (num1.toInt() == 0 || num2.toInt() == 0) {
//            throw new IllegalArgumentException("0으로는 나눌 수 없습니다.");
//        }
        return num1.toInt() / num2.toInt();
    }
}
