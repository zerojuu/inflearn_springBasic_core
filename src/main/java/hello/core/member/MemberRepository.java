package hello.core.member;

public interface MemberRepository {

    //์ ์ฅ
    void save(Member member);

    //์กฐํ
    Member findById(Long memberId);
}
