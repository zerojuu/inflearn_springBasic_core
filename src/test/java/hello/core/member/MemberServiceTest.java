package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);  //회원가입
        Member findMember = memberService.findMember(1L);  //조회

        //then
        Assertions.assertThat(member).isEqualTo(findMember);  //검증
    }
}
