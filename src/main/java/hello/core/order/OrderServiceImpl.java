package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemmoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemmoryMemberRepository();
    //private final MemberRepository memberRepository = new MemmoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  //할인정책을 변경하려고 보니 추상과 구현 클래스 둘 다 의존하고 있음 -> DIP 위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();  //할인정책 변경
    private DiscountPolicy discountPolicy;  //final은 무조건 값이 할당되어야 하기 때문에 삭제, discountpolicy라는 인터페이스만 의존하게 됨
    private final MemberRepository memberRepository;

    //생성자
    //MemberRepository, DiscountPolicy에 구현체가 뭐가 들어갈지는 생성자를 통해서 감
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원 조회
        Member member = memberRepository.findById(memberId);
        //할인 가격 조회
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //ConfigueationSingletonTest 테스트 용도이기에 인터페이스에는 안 만듦
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
