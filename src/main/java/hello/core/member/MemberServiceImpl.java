package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//memberService에 대한 구현체
//구현체가 하나일 때는 관례적으로 service 뒤에 impl을 붙임
@Component
public class MemberServiceImpl implements MemberService{

    //가입하고 조회하려면 memberRepository가 필요함
    //인터페이스만 가지고 있으면 nullpointexception 발생 -> 구현객체(MemmoryMemberRepository)를 선택해줘야 함
    //private final MemberRepository memberRepository = new MemmoryMemberRepository();
    //ㄴ> = 기준으로 왼쪽은 인터페이스를 의존하지만 오른쪽은 실제 할당하는 부분이 구현체를 의존 -> 추상화, 구체화 둘 다 의존하는 문제 발생(DIP 위반)

    //인터페이스(추상화)만 의존할 수 있게
    private final MemberRepository memberRepository;

    //생성자
    //MemberRepository에 구현체가 뭐가 들어갈지는 생성자를 통해서 감
    @Autowired  //componentscan할 때 자동 의존관계주입.. ac.getBean(MemberRepository.class) 이런 식으로 들어간다고 보면 됨
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //ConfigueationSingletonTest 테스트 용도이기에 인터페이스에는 안 만듦
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
