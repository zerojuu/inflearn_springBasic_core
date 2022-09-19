package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //memberservice 생성 -> memberserviceimpl에서 MemberRepository만 선언 및 생성자 생성 -> 생성자를 통해서 MemmoryMemberRepository 주입
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    //중복 제거 및 역할 보이게 리팩터링
    private MemberRepository memberRepository() {
        return new MemmoryMemberRepository();  //리턴 타입은 인터페이스로 해야 함
    }

    //orderservice 생성
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //중복 제거 및 역할 보이게 리팩터링
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();  //FixDiscountPolicy에서 RateDiscountPolicy로 변경 원하면 여기만 수정하면 됨
    }
}
