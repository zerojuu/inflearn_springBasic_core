package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);    //이때 설정정보는 AutoAppConfig

        MemberService memberService = ac.getBean(MemberService.class);
        //검증
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
