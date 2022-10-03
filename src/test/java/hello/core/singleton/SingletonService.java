package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService(); //자기 자신을 내부의 private으로 static선언 -> 클래스 레벨에 들어가기 때문에 딱 하나만 존재하게 됨

    //조회
    public static SingletonService getInstance() {
        return instance;
    }

    //**private 생성자로 생성 막아버리기
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
