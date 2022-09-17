package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//discountpolicy의 구현체
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;  //1000원 할인

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {  //enum은 =이 아니라 == 쓰는 게 맞음
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
