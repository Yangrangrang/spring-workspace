package org.example.di;

import org.assertj.core.api.Assertions;
import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class BeanFactoryTest {
    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    // 테스트 메소드가 호출되기 전에 호출되는 메소드
    void setUp() {
        // org.example 밑에 있는 클래스들을 대상으로 Reflection 기술을 사용한다는 의미
        reflections = new Reflections("org.example");

        // * TOP DOWN 방식 : 메소드를 만들지 않았지만 미리 있다고 가정을 하고 코드를 작성하는 것
        Set<Class<?>> preInstantiatedClazz = getTypeAnnotatedWith(Controller.class, Service.class);
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    // 컨트롤러의 annotation에 붙은 클래스 타입 객체와 서비스의 annotation에 붙은 클래스 타입객체를 조회해서, Beans에 add하고 add된 Set을 리턴
    private Set<Class<?>> getTypeAnnotatedWith(Class<? extends Annotation>... annotations) {   // 몇개가 들어올 지 모르기 때문에 java문법으로 정의
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) {
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        assertThat(userController.getUserService()).isNotNull();
    }

    // gitflowTest
}
