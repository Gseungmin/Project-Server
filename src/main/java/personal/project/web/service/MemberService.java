package personal.project.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.project.domain.entity.Member;
import personal.project.web.repository.MemberRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    /**회원 저장*/
    public void join(Member member) {
        memberRepository.save(member);
    }

    /**회원 조회*/
    public Optional<Member> findMember(String email) {
        return memberRepository.findByEmail(email);
    }
}



