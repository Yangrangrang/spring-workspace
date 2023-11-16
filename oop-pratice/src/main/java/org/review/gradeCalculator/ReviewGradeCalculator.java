package org.review.gradeCalculator;

import java.util.List;

// 학점 계산 하기
public class ReviewGradeCalculator {

    private final ReviewCourses courses;

    public ReviewGradeCalculator(List<ReviewCourse> courses) {
        this.courses = new ReviewCourses(courses);
    }

    public double ReviewCalculateGrade() {
        double totalMultipliedCreditAndCourseGrade = courses.ReviewMultiplyCreditAndCourseGrade();

        int totalComletedCredit = courses.ReviewCalculateTotalComplatedCredit();

        return totalMultipliedCreditAndCourseGrade / totalComletedCredit;
    }
}
