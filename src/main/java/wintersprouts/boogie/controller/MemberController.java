package wintersprouts.boogie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wintersprouts.boogie.auth.JwtTools;
import wintersprouts.boogie.auth.TokenForm;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.domain.member.MemberDepositMoneyForm;
import wintersprouts.boogie.domain.member.MemberJoinRequestForm;
import wintersprouts.boogie.domain.member.MemberLoginRequestForm;
import wintersprouts.boogie.service.MemberServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final JwtTools jwtTools;
    private final MemberServiceImpl memberServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public TokenForm login(@RequestBody MemberLoginRequestForm memberLoginRequestForm) {
        TokenForm login = memberServiceImpl.login(memberLoginRequestForm);

        return login;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody MemberJoinRequestForm memberJoinRequestForm) {
        log.info("memberJoinForm={}", memberJoinRequestForm);
        Member joinMember = Member.builder()
                .email(memberJoinRequestForm.getJoinEmail())
                .password(passwordEncoder.encode(memberJoinRequestForm.getJoinPw()))
                .name(memberJoinRequestForm.getName())
                .nickname(memberJoinRequestForm.getNickname())
                .role(memberJoinRequestForm.getRole())
                .account(0L)
                .build();
        return memberServiceImpl.join(joinMember) ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/deposit")
    public String deposit(@RequestBody MemberDepositMoneyForm memberDepositMoneyForm, HttpServletRequest request) {
        String memberEmail = jwtTools.getMemberEmailByRequest(request);
        Long balance = memberServiceImpl.updateAccount(memberEmail, memberDepositMoneyForm.getAddedAmount());

        return "현재 잔액은 "+balance;
    }

}
