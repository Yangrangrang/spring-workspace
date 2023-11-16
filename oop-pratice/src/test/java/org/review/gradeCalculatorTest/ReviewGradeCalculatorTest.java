package org.review.gradeCalculatorTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.review.gradeCalculator.ReviewCourse;
import org.review.gradeCalculator.ReviewGradeCalculator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점수x교과목 평점)의 합계/수강신청 총 학점 수
 * 일급 컬렉션 사용
 */
public class ReviewGradeCalculatorTest {
    // 교과목, 과목의 학점, 내 점수

    @DisplayName("평균 학점 계산")
    @Test
    void RCalculateGradeTest() {
        // 내가 어떤 과목을 신청했는지
        List<ReviewCourse> courses = List.of(new ReviewCourse("수학", 3, "A+"), new ReviewCourse("영어", 3, "A+"));

        // 신청한 과목들 학점을 계산하고 평균을 냄
        ReviewGradeCalculator reviewGradeCalculator = new ReviewGradeCalculator(courses);

        // 신청한 과목들 학점 계산 후 평균
        double resultGrade = reviewGradeCalculator.ReviewCalculateGrade();
        assertThat(resultGrade).isEqualTo(4.5);
    }
}
