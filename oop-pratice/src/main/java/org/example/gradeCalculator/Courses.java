package org.example.gradeCalculator;

import java.util.List;

// 일급 컬렉션 : 리스트 형태로 된 정보만 인스턴스 변수로 가지는 클래스 (변수가 하나 더 있으면 안됨)
public class Courses {

    private final List<Course> courses;

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public double multiplyCreditAndCourseGrade() {
        return courses.stream()
                .mapToDouble(Course::multiplyCreditAndCourseGrade)
                .sum();

        // 위 코드로 간단하게 작성 할 수 있음.
//        double multipliedCreditAndCourseGrade = 0;
//        for (Course course : courses) {
//            multipliedCreditAndCourseGrade += course.multiplyCreditAndCourseGrade();
//        }
//        return multipliedCreditAndCourseGrade;
    }

    public int calculateTotalComplatedCredit() {
        return courses.stream()
//                .mapToInt(Course::getCredit)
//                .sum();
                .mapToInt(Course -> Course.getCredit())
                .sum();

//        int totalCompletedCredit = courses.stream()
//                .mapToInt(Course::getCredit)
//                .sum();
//
//        System.out.println("totalCompletedCredit = " + totalCompletedCredit);
//
//        return totalCompletedCredit;
    }
}
