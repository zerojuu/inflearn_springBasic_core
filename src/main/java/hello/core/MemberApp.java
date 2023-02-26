package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //AppConfig appConfig.xml = new AppConfig();
        //MemberService memberService = appConfig.xml.memberService();  //memberServiceImpl이 들어가있는 memberService
        //MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);  //ApplicationContext:스프링 컨테이너, AppConfig에 있는 환경설정으로 적용
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);  //AppConfig에서 꺼낼 객체 이름, 타입

        Member member = new Member(1L, "memberA", Grade.VIP);   //id는 long 타입이라서 1뒤에 L 붙임 (없으면 컴파일 오류)
        memberService.join(member);  //회원가입

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = "+findMember.getName());
    }
}
