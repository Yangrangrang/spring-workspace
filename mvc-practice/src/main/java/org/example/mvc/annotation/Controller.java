package org.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  @Target 어노테이션은 다른 어노테이션을 어디에 적용할 수 있는지를 지정합니다. ElementType.TYPE은 이 어노테이션이 클래스, 인터페이스, enum 등의 타입에만 적용될 수 있음을 나타냅니다.
 *  @Retention 어노테이션은 어노테이션 정보가 유지되는 기간을 지정합니다. RetentionPolicy.RUNTIME은 런타임까지 어노테이션 정보가 유지되어야 함을 나타냅니다.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

}
