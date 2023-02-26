package hello.core.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemmoryMemberRepository implements MemberRepository{

    //데이터베이스가 미확정이라 memorymemberrepository 생성 (메모리에서만 하기 때문에 개발용으로는 상관없음)
    //회원저장소니까 map같은 게 필요
    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 이슈로 인해 HashMap이 아닌 ConcurrentHashMap을 써야 함

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
