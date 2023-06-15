package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigueationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //각 service의 impl에만 넣어놔서 impl로 꺼내야 getMemberRepository 사용 가능
        //원래는 이렇게 구체타입(impl)으로 꺼내면 안 됨
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberService --> memberRepository1 = " + memberRepository1);
        System.out.println("orderService --> memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        //셋 다 같은 인스턴스 사용됨
        //호출 순서 : AppConfig.memberService -> AppConfig.memberRepository -> AppConfig.orderService

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}
