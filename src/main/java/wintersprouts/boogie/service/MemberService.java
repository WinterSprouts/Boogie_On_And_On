package wintersprouts.boogie.service;

import wintersprouts.boogie.auth.TokenForm;
import wintersprouts.boogie.domain.member.Member;
import wintersprouts.boogie.domain.member.MemberLoginRequestForm;

public interface MemberService {
    public TokenForm login(MemberLoginRequestForm memberLoginRequestForm);

    public boolean join(Member member);

    public Member findByEmail(String email);

    public Long updateAccount(String email, Long money);

}
