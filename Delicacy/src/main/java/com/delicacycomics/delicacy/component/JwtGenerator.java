package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.entity.UserRole;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String ROLE = "role";
    private static final String IP_ADDRESS = "ip_address";
    private static final String EXPIRE = "expire";
    private static final String REFRESH = "refresh";
    private static final long DAY_SECONDS = 60 * 60 * 24;

    public void encodeJwt(UserData userData, HttpServletResponse response) {
        Long expire = Date.from(Instant.now().plusSeconds(DAY_SECONDS * 14)).getTime();
        Long refresh = Date.from(Instant.now().plusSeconds(DAY_SECONDS * 3)).getTime();

        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put(ID, userData.getId());
        tokenData.put(LOGIN, userData.getLogin());
        tokenData.put(NAME, userData.getName());
        tokenData.put(SURNAME, userData.getSurname());
        tokenData.put(ROLE, userData.getRole());
        tokenData.put(IP_ADDRESS, userData.getIpAddress());
        tokenData.put(EXPIRE, expire);
        tokenData.put(REFRESH, refresh);

        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(tokenData);

        String key = "secret";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS256, key).compact();
        response.setHeader(AUTHORIZATION, BEARER_PREFIX + token);
    }

    public DecodeResult decodeJwt(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) {
            return null;
        }

        DefaultClaims claims = extractClaims(token);
        if (claims == null) {
            return null;
        }

        Instant expire = new Date(claims.get(EXPIRE, Long.class)).toInstant();
        Instant refresh = new Date(claims.get(REFRESH, Long.class)).toInstant();
        if (expire.isBefore(Instant.now())) {
            return null;
        }

        Long id = claims.get(ID, Long.class);
        String login = claims.get(LOGIN, String.class);
        String name = claims.get(NAME, String.class);
        String surname = claims.get(SURNAME, String.class);
        UserRole role = UserRole.valueOf(claims.get(ROLE, String.class));
        String ipAddress = claims.get(IP_ADDRESS, String.class);
        UserData userData = new UserData(id, login, name, surname, role, ipAddress);
        return new DecodeResult(userData, expire, refresh);
    }

    private String extractToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        String token = authorizationHeader.substring(BEARER_PREFIX.length());
        if (token.isEmpty()) {
            return null;
        }
        return token;
    }

    private DefaultClaims extractClaims(String token) {
        try {
            String key = "secret";
            return (DefaultClaims)Jwts.parser()
                    .setSigningKey(key)
                    .parse(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    public static class DecodeResult {
        private UserData userData;
        private Instant expire;
        private Instant refresh;

        public DecodeResult(UserData userData, Instant expire, Instant refresh) {
            this.userData = userData;
            this.expire = expire;
            this.refresh = refresh;
        }

        public UserData getUserData() {
            return userData;
        }

        public Instant getExpire() {
            return expire;
        }

        public Instant getRefresh() {
            return refresh;
        }
    }

}
