package org.review.calculator;

// 숫자가 0 또는 음수인지 확인 하는 클래스
public class ReviewPositiveNumber {

    private final int value;

    public ReviewPositiveNumber(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (isNegativeNumber(value)) {
            throw new IllegalArgumentException("0또는 음수를 전달할 수 없음");
        }
    }

    private boolean isNegativeNumber(int value) {
        return value <= 0;
    }

    public int toInt() {
        return value;
    }

//    public int getValue() {
//        return value;
//    }
}

