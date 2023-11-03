package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    // 임의 테스트 클래스   (Member는 spring 이 아니다)
    static class TestBean {

        @Autowired(required = false)    // 의존관계가 없으면 메서드 자체가 호출이 안됨
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired  // 호출은 되지만 값은 null
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); // null
        }

        @Autowired  // 값이 없으면 Optional.empty
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
