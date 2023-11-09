package org.review.calculator;

import java.util.List;

public class ReviewCalculrator {

    private static final List<ReviewNewArithmeticOperator> list = List.of(new ReviewAdditionOperator(), new ReviewSubtractionOperator(), new ReviewMultiplicationOperator(), new ReviewDIvisionOperator());

    public static int calculate(ReviewPositiveNumber num1, String oper, ReviewPositiveNumber num2) {

//        if(oper.equals("+")){
//            return num1 + num2;
//        } else if (oper.equals("-")) {
//            return num1 - num2;
//        } else if (oper.equals("*")) {
//            return num1 * num2;
//        } else if (oper.equals("/")) {
//            return num1 / num2;
//        }
//
//        return 0;

//        return ReviewArithmeicOperator.calculate(num1 , oper, num2);

        return list.stream()
                .filter(a -> a.supports(oper))
                .map(a -> a.calculate(num1, num2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산 아님."));
    }


}
