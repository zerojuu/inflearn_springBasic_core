package hello.core.beanFind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemmoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);   //내부에서 쓰기로 생성한 SameBeanConfig 선언

    //static 장점 : class 안에 class를 썼다(중첩클래스)는 건 class 안에서만 쓰겠다는.. scope
    @Configuration  //ApplicationContextSameBeanFindTest 내부에서만 쓸 config
    static class SameBeanConfig {
        //빈의 이름(memberRepository1,2)이 다르고 객체 타입(MemmoryMemberRepository)은 같을 수 있음 : 파라미터 값을 넣을 수도 있음 return new MemmoryMemberRepository("10"); 이런 식으로
        @Bean
        public MemberRepository memberRepository1() {
            return new MemmoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemmoryMemberRepository();
        }
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다")
    void findBeanByDuplicate() {
        //MemberRepository bean = ac.getBean(MemberRepository.class);  //실행시 memberRepository1,2 두개가 존재하기에 NoUniqueBeanDefinitionException 오류 발생
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));  //람다
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 빈 이름을 지정하면 된다")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1",MemberRepository.class);  //빈 이름(memberRepository1) 지정
        org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key  = " + key + "value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        //검증
        org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);
    }
}
