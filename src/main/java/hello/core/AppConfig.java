package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemmoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //memberservice 생성 -> memberserviceimpl에서 MemberRepository만 선언 및 생성자 생성 -> 생성자를 통해서 MemmoryMemberRepository 주입
    public MemberService memberService() {
        return new MemberServiceImpl(new MemmoryMemberRepository());
    }

    //orderservice 생성
    public OrderService orderService() {
        return new OrderServiceImpl(new MemmoryMemberRepository(), new FixDiscountPolicy());
    }
}
