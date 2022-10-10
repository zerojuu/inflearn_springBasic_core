package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
       ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
       
       //시나리오 : A사용자가 주문을 하고 금액을 조회하려는 사이 B사용자가 주문을 중간에 해버림
       
       //조회
       StatefulService statefulService1 = ac.getBean(StatefulService.class);
       StatefulService statefulService2 = ac.getBean(StatefulService.class);
       
       //ThreadA : A사용자 10000원 주문 
        statefulService1.order("userA", 10000);
       //ThreadB : B사용자 20000원 주문
       statefulService2.order("userB", 20000);
        
        //ThreadA : A사용자 주문 금액 조회
        //int price = statefulService1.getPrice();
       // System.out.println("price = " + price);  //service를 1,2로 나누어도 인스턴스는 같기 때문에 마지막인 B사용자의 20000원이 조회됨

        //검증
        //Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    //무상태로 재설계
    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        //조회
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A사용자 10000원 주문 -> int(지역변수) 사용
        int userAprice = statefulService1.order("userA", 10000);
        //ThreadB : B사용자 20000원 주문
        int userBprice = statefulService2.order("userB", 20000);

        //ThreadA : A사용자 주문 금액 조회
        System.out.println("price = " + userAprice);
    }
    
    //테스트용 config
    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}