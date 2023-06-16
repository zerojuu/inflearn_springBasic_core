package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)    //자동으로 등록될 때 제외할 내용들(AppConfig,TestAppConfig의 Configuration가 붙은 거는 자동으로 등록되기에 제외하기 위해)
)  //자동으로 스프링빈으로 등록됨
public class AutoAppConfig {
}
