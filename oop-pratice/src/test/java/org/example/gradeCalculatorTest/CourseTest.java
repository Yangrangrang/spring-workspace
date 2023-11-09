package org.example.gradeCalculatorTest;

import org.assertj.core.api.Assertions;
import org.example.gradeCalculator.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    @DisplayName("과목을 생성")
    @Test
    void createTest() {
        Assertions.assertThatCode(() -> new Course("OOP", 3, "A"))
                .doesNotThrowAnyException();    // 해당 코스가 정상적으로 생성이 됐다며 어떠한 Exception도 발생하지 않는다.
    }
}
