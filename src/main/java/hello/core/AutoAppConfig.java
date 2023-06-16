package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core", //탐색할 시작 패키지의 시작 위치 지정(이거 설정 안 하면 전체를 뒤지기 때문에 시간 오래 걸림), 이 패키지를 포함하여 하위 패키지 모두 탐색
        //basePackageClasses = AutoAppConfig.class,   //AutoAppConfig의 패키지는 hello.core 이기에 이걸로 지정 (위의 로직과 동일해짐)
        //basePackage 괸련 설정하지 않으면 ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 됨 -> 이걸 권장, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 걸 권장
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)    //자동으로 등록될 때 제외할 내용들(AppConfig,TestAppConfig의 Configuration가 붙은 거는 자동으로 등록되기에 제외하기 위해)
)  //자동으로 스프링빈으로 등록됨
public class AutoAppConfig {
}
