package org.example.calculratorTest;

import org.assertj.core.api.Assertions;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositiveNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0 , -1})
    void createTest(int value){
        Assertions.assertThatCode(() -> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0또는 음수를 전달할 수 없습니다.");
    }
}
