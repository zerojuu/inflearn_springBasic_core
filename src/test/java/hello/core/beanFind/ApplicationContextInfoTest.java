package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    //ApplicationContext는 AnnotationConfigApplicationContext의 상위 인터페이스 -> 개발시 가급적 기능을 적게 제공하는 상위 인터페이스를 사용해야 향후 구현 클래스가 변경되어도 클라이언트 코드를 변경하지 않아도 됨
    //실제 스프링 애플리케이션을 개발할 때는 ApplicationContext를 사용 (스프링 코어를 설명하다보니 AnnotationConfigApplicationContext를 통해 제공되는 기능도 필요해서 사용하게 됨)
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();  //getBeanDefinitionNames : 스프링에 등록된 모든 빈 이름 조회
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);  //타입을 모르기 때문에 object가 꺼내짐
            System.out.println("name = "+beanDefinitionName+" object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = "+beanDefinitionName+" object = " + bean);
            }
        }
    }
}
