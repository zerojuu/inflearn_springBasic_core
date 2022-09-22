package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {  //public 없어도 됨

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        //검증
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //memberservice에 memberserviceimpl을 등록했으니
    }

    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        //검증
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //인터페이스(memberservice)이면 구현체(memberserviceimpl)이 대상
    }

    @Test
    @DisplayName("구체 타입으로 조회")  //구체적으로 조회하는 건 지양
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        //검증
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //memberservice에 memberserviceimpl을 등록했으니
    }

    @Test
    @DisplayName("빈 이름으로 조회X")  //실패 테스트
    void findBeanByNameX() {
        //MemberService memberService = ac.getBean("xxxx", MemberServiceImpl.class);  --> 이거 실행해보면 NoSuchBeanDefinitionException 에러 발생

        //검증(이때는 junit의 Assertions 사용)
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class));
    }
}
