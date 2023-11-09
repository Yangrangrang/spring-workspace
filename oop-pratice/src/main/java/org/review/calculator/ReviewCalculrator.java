package org.review.calculator;

public class ReviewCalculrator {

    public static int calculate(int num1, String oper, int num2) {
        if(oper.equals("+")){
            return num1 + num2;
        } else if (oper.equals("-")) {
            return num1 - num2;
        } else if (oper.equals("*")) {
            return num1 * num2;
        } else if (oper.equals("/")) {
            return num1 / num2;
        }

        return 0;
    }
}
