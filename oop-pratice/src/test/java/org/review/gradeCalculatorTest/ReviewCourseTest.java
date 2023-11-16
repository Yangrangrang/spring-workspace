package org.review.gradeCalculatorTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.review.gradeCalculator.ReviewCourse;

public class ReviewCourseTest {

    @DisplayName("과목 생성")
    @Test
    void createTest() {
        Assertions.assertThatCode(() -> new ReviewCourse("수학", 2, "A+"))
                .doesNotThrowAnyException();
    }
}
