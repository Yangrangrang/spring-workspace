package org.example.gradeCalculatorTest;

import org.assertj.core.api.Assertions;
import org.example.gradeCalculator.Course;
import org.example.gradeCalculator.GradeCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 요구사항
 * 평균학점 계산 방법 = (학점수x교과목 평점)의 합계/수강신청 총 학점 수
 * 일급 컬렉션 사용
 */
public class GradeCalculatorTest {
    // 학점계산기 도메인 : 이수한 과목(객체지향프로그래밍, 자료구조, 중국어회화), 학점 계산기      >> 이부분에서 모든 객체를 도출하진 않는다
    // 학점 계산기가 이수한 과목들을 인스턴스 변수로 가지면서 평균 학점을 계산할 수 있을 거 같다
    // 이수한 과목 : 객체지향프로그래밍, 자료구조, 중국어회화 > 객체3가지 ---> 과목(코스) 클래스
    // 평균학점을 계산하기 위해서는 이수한 과목을 전달하여 평균학점 계산을 요청한다. >> 학점계산기에서 요청 >> (학점수x교과목 평점)의 합계    >> 과목(코스)에게 요청
    //                                                                                      >>  수강신청 총 학점 수          >> 과목(코스)에게 요청

    @DisplayName("평균 학점을 계산한다.")
    @Test
    void CalculateGradeTest() {
        List<Course> courses = List.of(new Course("OOP", 3, "A+"), new Course("자료구조", 3, "A+"));

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double gredeResult = gradeCalculator.calculateGrade();

        assertThat(gredeResult).isEqualTo(4.5);
    }

}
