package org.review.calculator;

public interface ReviewNewArithmeticOperator {

    // 연산자 확인
    public boolean supports(String oper);

    // 연산할 숫자
    public int calculate(ReviewPositiveNumber num1, ReviewPositiveNumber num2);
}
