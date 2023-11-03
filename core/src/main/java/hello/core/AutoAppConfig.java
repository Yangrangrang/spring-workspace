package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// @Component 붙은 빈을 다 찾아서 자동으로 스프링 빈에 등록
@ComponentScan (
        // Configuration 어노테이션 붙은건 뺄꺼야 (실무에서는 굳이 이렇게 안한다)
//        basePackages = "hello.core.member",
//        basePackageClasses = AppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
