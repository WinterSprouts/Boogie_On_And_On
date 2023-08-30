package wintersprouts.boogie.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenForm {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
