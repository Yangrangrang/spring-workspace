package org.review.gradeCalculator;

public class ReviewCourse {

    private final String subject;
    private final int credit;
    private final String grade;

    public ReviewCourse(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    // 신청 학적을 사용하려면 getter가 필요
    public int getCredit() {
        return credit;
    }

    // 신청 학점 * 성적
    public double reviewMultiplyCreditAndCourseGrade() {
        return credit * reviewGradeToNumber();
    }

    // 성적이 String 이고 각 성적 별 점수가 있음
    public double reviewGradeToNumber() {
        double grade = 0;
        switch (this.grade) {
            case "A+" :
                grade = 4.5;
                break;
            case "A":
                grade = 4;
                break;
            case "B+":
                grade = 3.5;
                break;
            case "B":
                grade = 3;
                break;
            case "C+":
                grade = 2.5;
                break;
            case "C":
                grade = 2;
                break;
        }
        return grade;
    }
}
