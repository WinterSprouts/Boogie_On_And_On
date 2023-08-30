package wintersprouts.boogie.domain.member;

import lombok.Data;

@Data
public class MemberLoginRequestForm {
    private String loginId;
    private String loginPw;
}
