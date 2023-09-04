package wintersprouts.boogie.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), ORGANIZATION("ROLE_ORGANIZATION");
    private String value;
}
