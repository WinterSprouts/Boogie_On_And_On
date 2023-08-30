package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wintersprouts.boogie.auth.TokenForm;
import wintersprouts.boogie.domain.member.MemberJoinRequestForm;
import wintersprouts.boogie.domain.member.MemberLoginRequestForm;
import wintersprouts.boogie.service.MemberService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public TokenForm login(@RequestBody MemberLoginRequestForm memberLoginRequestForm) {
        TokenForm login = memberService.login(memberLoginRequestForm);

        return login;
    }

    @GetMapping("/join")
    public String join(@RequestBody MemberJoinRequestForm memberJoinRequestForm) {
        boolean result = memberService.join(memberJoinRequestForm);
        return result ? "success" : "fail";
    }

    @PostMapping("/test")
    public String test() {
        return "이거 보면 성공한거임";
    }
}
