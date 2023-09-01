package wintersprouts.boogie.domain.member;

import lombok.Data;

@Data
public class MemberJoinRequestForm {
    private String joinEmail;
    private String joinPw;
    private String name;
    private String nickname;
    private Role role;
    private Long account;
}
