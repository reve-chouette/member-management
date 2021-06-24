package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

//Ctrl+Shift+T 테스트코드클래스 생성
public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*회원가입*/
    public Long join(Member member){
        //같은 이름을 가진 회원은 중복회원으로 처리
/*
        Optional<Member> result = memberRepository.findByName(member.getName());
        //null이 될 수 있는 값을 optional로 감싸주면, 아래와 같이 다양한 걸 사용할 수 있다.
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
*/
        //또는, 그 자체가 이미 Optional이므로, 바로 사용해줘도 된다.
        //Ctrl+Alt+M으로 메서드 추출해낼 수 있다.
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*전체회원조회*/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
