package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.entity.UserRole;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Value;
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

    // Значения, автоматически подставляемые Spring'ом и приходящие из конфигурационного класса (application.properties)
    @Value("${jwt.expireDays}")
    private int expireDays;
    @Value("${jwt.refreshDays}")
    private int refreshDays;
    @Value("${jwt.secret}")
    private String secret;

    // Формирование JWT-токена по UserData и запись его в хэдер Authorization ответа
    public void encodeJwt(UserData userData, HttpServletResponse response) {
        // Высчитываем даты протухания (expire) и обновления (refresh) JWT-токена
        Long expire = Date.from(Instant.now().plusSeconds(DAY_SECONDS * expireDays)).getTime();
        Long refresh = Date.from(Instant.now().plusSeconds(DAY_SECONDS * refreshDays)).getTime();

        // Формируем мапу со всеми значениями, которые собираемся хранить в токене
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

        // Делаем вызов специального библиотечного метода, формирующего для нас сам JWT-токен
        // Передаем сюда, что хотим использовать алгоритм хэширования HS256, и наш secret
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS256, secret).compact();
        // Записываем JWT-токен в хэдер
        response.setHeader(AUTHORIZATION, BEARER_PREFIX + token);
    }

    // Метод для попытки декодировать JWT-токен из хэдера Authorization
    // Возвращает null, если декодировать токен не удалось, или он оказался протухшим
    public DecodeResult decodeJwt(HttpServletRequest request) {
        // Пытаемся достать из хэдера запроса сам JWT-токен
        String token = extractToken(request);
        if (token == null) {
            return null;
        }

        // Пытаемся декодировать JWT-токен
        DefaultClaims claims = extractClaims(token);
        if (claims == null) {
            return null;
        }

        // Получаем экземпляры Instant, содержащие даты протухания и обновления токена
        // Instant - подобен классу Date, но более удобен для использования
        Instant expire = new Date(claims.get(EXPIRE, Long.class)).toInstant();
        Instant refresh = new Date(claims.get(REFRESH, Long.class)).toInstant();
        // Если токен уже протух
        if (expire.isBefore(Instant.now())) {
            return null;
        }

        // Достаем из клэймов всю полезную информацию, чтобы сформировать UserData
        Long id = claims.get(ID, Long.class);
        String login = claims.get(LOGIN, String.class);
        String name = claims.get(NAME, String.class);
        String surname = claims.get(SURNAME, String.class);
        UserRole role = UserRole.valueOf(claims.get(ROLE, String.class));
        String ipAddress = claims.get(IP_ADDRESS, String.class);
        UserData userData = new UserData(id, login, name, surname, role, ipAddress);
        return new DecodeResult(userData, expire, refresh);
    }

    // Этот метод пытается достать из запроса значение JWT-токена, если оно там есть
    // Возвращает null, если токен отсутствует
    private String extractToken(HttpServletRequest request) {
        // Взять значение хэдера Authorization
        // Это значение ожидается в виде: Bearer jwt.token
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        // Если хэдера нет совсем или в нём нет ожидаемого префикса
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
            return null;
        }

        // Берем кусок хэдера после префикса, то есть сам JWT-токен
        String token = authorizationHeader.substring(BEARER_PREFIX.length());
        if (token.isEmpty()) {
            return null;
        }
        return token;
    }

    // Этот метод пытается декодировать JWT-токен и получить содержащиеся в нём клэймы
    // Внутри класса DefaultClaims содержится та мапа, которую мы формировали, когда формировали JWT-токен
    // Если декодировать токен не удалось, метод возвращает null
    private DefaultClaims extractClaims(String token) {
        try {
            return (DefaultClaims)Jwts.parser()
                    .setSigningKey(secret)
                    .parse(token).getBody();
        } catch (Exception ex) {
            return null;
        }
    }

    // Вспомогательный класс, возвращаемый после декодирования JWT-токена
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
