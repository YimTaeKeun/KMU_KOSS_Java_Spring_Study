package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach()
    public void beforeEach(){
        // dependancy Injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // 실제 동작 코드는 영어로 적는게 강추지만, 테스트 코드는 직관을 위해서 한글 사용 추천
    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);


        //then, 검증
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //방법 3
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        // 방법 1
//        try{
//            memberService.join(member2);
//            fail("예외가 발생해야합니다.");
//        }catch (IllegalStateException e){
//
//        }
        // 방법 2
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        //then
    }

    @Test
    void findOne() {
    }
}