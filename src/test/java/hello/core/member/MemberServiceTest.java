package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach  //beforeEach : 각테스트 실행하기 전에 무조건 실행되는 거
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

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
