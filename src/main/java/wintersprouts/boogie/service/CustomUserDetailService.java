package wintersprouts.boogie.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = memberRepository.findByMemberId(username).map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다. "));
        return userDetails;
    }

    private UserDetails createUserDetails(Member member) {
        Member build = member.builder()
                .memberId(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .build();
        build.setMemberIndex(member.getMemberIndex());

        return build;
    }
}
