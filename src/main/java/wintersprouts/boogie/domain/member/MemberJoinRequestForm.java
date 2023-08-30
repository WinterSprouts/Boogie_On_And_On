package wintersprouts.boogie.domain.member;

import lombok.Data;

@Data
public class MemberJoinRequestForm {
    private String joinId;
    private String joinPw;
    private Role role;
}
