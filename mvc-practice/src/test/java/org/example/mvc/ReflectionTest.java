package org.example.mvc;

import org.assertj.core.api.Assertions;
import org.example.mvc.annotation.Controller;
import org.example.mvc.annotation.Service;
import org.example.mvc.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Controller 애노테이션이 실행되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {

//        // ord.example 패키지 밑에 있는 모든 클래스 대상으로 reflection을 사용
//        Reflections reflections = new Reflections("org.example");

//        Set<Class<?>> beans = getTypesAnnotatedWith();
        // 해당 패키지 밑에 Controller라는 어노테이션이 붙어져 있는 대상들을 다 찾아서 해당 hashSet에 담는다.
//        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));
//        beans.addAll(reflections.getTypesAnnotatedWith(Service.class));

        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));
        logger.debug("beans: [{}]", beans);
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        // User에 클래스에 선언된 모든 필드
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        // User에 선언되어있는 생성자
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        // User에 선언된 메소드
        logger.debug("User all declared method: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    // hip영영에 로드되어 있는 클래스 타입 객체를 가져오는 세가지 방법
    @Test
    void load() throws ClassNotFoundException {
        // 1.
        Class<User> clazz = User.class;
        
        // 2.
        User user = new User("serverwizard", "랑랑");
        Class<? extends User> clazz2 = user.getClass();
        
        // 3.
        Class<?> clazz3 = Class.forName("org.example.mvc.model.User");

        logger.debug("class [{}]", clazz);
        logger.debug("class [{}]", clazz2);
        logger.debug("class [{}]", clazz3);

        assertThat(clazz == clazz2).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
        assertThat(clazz == clazz3).isTrue();

    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations ) {

        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}
