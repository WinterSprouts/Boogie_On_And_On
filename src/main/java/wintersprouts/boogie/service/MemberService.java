package wintersprouts.boogie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import wintersprouts.boogie.auth.JwtTokenProvider;
import wintersprouts.boogie.auth.TokenForm;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.domain.member.MemberJoinRequestForm;
import wintersprouts.boogie.domain.member.MemberLoginRequestForm;
import wintersprouts.boogie.domain.member.Role;
import wintersprouts.boogie.repository.MemberRepository;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenForm login(MemberLoginRequestForm memberLoginRequestForm) {
        // 1. LoginDto 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberLoginRequestForm.getLoginId(), memberLoginRequestForm.getLoginPw());

        log.info("authToken = {}", authenticationToken.toString());
        // 2. 검증
        Authentication authentication = null;
        try {
            authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenForm dto = jwtTokenProvider.generateToken(authentication);

        return dto;
    }

    @Transactional
    public boolean join(MemberJoinRequestForm memberJoinRequestForm) {

        Member member = Member.builder()
                .email(memberJoinRequestForm.getJoinEmail())
                .password(passwordEncoder.encode(memberJoinRequestForm.getJoinPw()))
                .name(memberJoinRequestForm.getName())
                .nickname(memberJoinRequestForm.getNickname())
                .role(memberJoinRequestForm.getRole())
                .build();

        Member save = memberRepository.save(member);

        return StringUtils.hasText(save.getEmail());
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).get();
    }

}
