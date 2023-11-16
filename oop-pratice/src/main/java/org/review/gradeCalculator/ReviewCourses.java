package org.review.gradeCalculator;

import java.util.List;

public class ReviewCourses {
    private final List<ReviewCourse> courses;

    public ReviewCourses(List<ReviewCourse> courses) {
        this.courses = courses;
    }

    // 학점 총 합계
    public double ReviewMultiplyCreditAndCourseGrade() {
        return courses.stream()
//                .mapToDouble(ReviewCourse::reviewMultiplyCreditAndCourseGrade)
                .mapToDouble(course-> course.reviewMultiplyCreditAndCourseGrade())
                .sum();
    }

    // 총 신청 학점 수
    public int ReviewCalculateTotalComplatedCredit() {
        return courses.stream()
                .mapToInt(ReviewCourse::getCredit)
                .sum();
    }
}
