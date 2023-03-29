package personal.project.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import personal.project.domain.dto.MemberJoin;
import personal.project.domain.dto.ReturnMemberJoin;
import personal.project.domain.entity.Member;
import personal.project.web.service.MemberService;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    /**회원 가입*/
    @PostMapping("/auth/join")
    public ReturnMemberJoin join(@RequestBody MemberJoin memberJoin) {

        //회원이 이미 존재할 경우
        Optional<Member> findMember = memberService.findMember(memberJoin.getEmail());
        if (findMember.isPresent()) {
            return new ReturnMemberJoin(false, findMember.get().getEmail());
        }

        memberService.join(new Member(memberJoin.getNickname(), memberJoin.getEmail(), memberJoin.getPassword()));
        return new ReturnMemberJoin(true, findMember.get().getEmail(), "accessToken");
    }
}



