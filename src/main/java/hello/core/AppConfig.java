package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //설정정보
public class AppConfig {

    //@Bean memberService -> memberRepository를 호출하면서 결과적으로는 new MemmoryMemberRepository를 호출해줌
    //@Bean orderService -> memberRepository를 호출하면서 new MemmoryMemberRepository를 호출해줌 + discountPolicy
    //이러면 MemmoryMemberRepository이 두번 호출되면서 싱글톤이 깨지는 건가..?

    //memberservice 생성 -> memberserviceimpl에서 MemberRepository만 선언 및 생성자 생성 -> 생성자를 통해서 MemmoryMemberRepository 주입
    @Bean  //bean 선언시 스프링 컨테이너에 자동등록이 됨
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());   //memberservice 객체를 만들 때 memberRepository 생성..생성자주입
    }

    //중복 제거 및 역할 보이게 리팩터링
    @Bean
    public MemberRepository memberRepository() {
        return new MemmoryMemberRepository();  //리턴 타입은 인터페이스로 해야 함
    }

    //orderservice 생성
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());  //생성자주입
    }

    //중복 제거 및 역할 보이게 리팩터링
    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();  //FixDiscountPolicy에서 RateDiscountPolicy로 변경 원하면 여기만 수정하면 됨
    }
}
