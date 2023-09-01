package wintersprouts.boogie.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;

@Slf4j
@Component
public class JwtTools {

    private final Key key;

    public JwtTools(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * HttpServletRequest 에 있는 JWT Token 을 파싱하여, MemberEmail 을 리턴합니다.
     */
    public String getMemberEmailByRequest(HttpServletRequest request) {

        String token = parseRequestToToken(request);

        Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return body.get("sub", String.class);
    }

    private String parseRequestToToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // "Bearer " 부분을 제외한 토큰 값을 반환
            return authorizationHeader.substring(7);
        }

        return null; // JWT 토큰이 없는 경우
    }

}
