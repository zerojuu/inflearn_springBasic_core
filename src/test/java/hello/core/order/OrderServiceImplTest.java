package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemmoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void  createOrder() {

        MemmoryMemberRepository memberRepository = new MemmoryMemberRepository();
        memberRepository.save(new Member(1L, "nameA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());  //OrderServiceImpl은 memberRepository, discountPolicy 두 개가 필요
        Order order = orderService.createOrder(1L, "itemA", 10000);

        //검증
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}