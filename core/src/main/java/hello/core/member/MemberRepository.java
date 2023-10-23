package hello.core.member;

// 회원 저장소 (역할)
public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);
}
