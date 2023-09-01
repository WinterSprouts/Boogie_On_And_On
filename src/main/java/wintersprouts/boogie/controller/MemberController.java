package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import wintersprouts.boogie.auth.TokenForm;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.domain.member.MemberDepositMoneyForm;
import wintersprouts.boogie.domain.member.MemberJoinRequestForm;
import wintersprouts.boogie.domain.member.MemberLoginRequestForm;
import wintersprouts.boogie.service.MemberService;

import java.security.Principal;

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

    @PostMapping("/join")
    public String join(@RequestBody MemberJoinRequestForm memberJoinRequestForm) {
        log.info("memberJoinForm={}", memberJoinRequestForm);
        boolean result = memberService.join(memberJoinRequestForm);
        return result ? "success" : "fail";
    }

    @PostMapping("/test")
    public String test() {
        return "이거 보면 성공한거임";
    }

    @PatchMapping("/deposit")
    public String deposit(Principal principal, @RequestBody MemberDepositMoneyForm memberDepositMoneyForm) {
        String email = principal.getName();
        log.info("name={}", email);

        Member member = memberService.findByEmail(email);
        Long account = member.getAccount();

        //업데이트하는 로직 필요

        Long balance = account + memberDepositMoneyForm.getAddedAmount();

        return "현재 잔액은 "+balance;
    }

}
