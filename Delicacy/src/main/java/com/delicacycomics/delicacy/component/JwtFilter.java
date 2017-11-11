package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collection;

import static com.delicacycomics.delicacy.entity.UserStatus.BLOCKED;

@Component
public class JwtFilter implements Filter {

    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    // Метод вызывается при каждом вызове любого из энд-пойнтов REST API
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        // Вызываем этот метод, чтобы попытаться декодировать JWT токен, пришедший в хэдере Authorization
        // DecodeResult должен содержать результат декодирования
        JwtGenerator.DecodeResult decodeResult = jwtGenerator.decodeJwt(httpServletRequest);

        // Условие - если декодирование прошло успешно, и хэдер содержал валидный и ещё не протухший токен
        if (decodeResult != null) {
            // Взять объект UserData, содержащий информацию о текущем пользователе
            UserData userData = decodeResult.getUserData();
            // Положить информацию о текущем пользователе в SecurityContext
            // В дальнейшем UserData будет доступна из любого места в коде, с помощью следующего вызова:
            // authenticationService.getCurrentUserData()
            // Этот метод присутствует в классе AuthenticationService
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(userData));

            // Условие - если токен, полученный в хэдере, пора обновить
            // Instant.now() - возвращает экземпляр Instant, содержащий текущий момент времени
            // Instant - подобен классу Date, но более удобен для использования
            if (decodeResult.getRefresh().isBefore(Instant.now())) {
                // Достаем из базы сущность, содержающую актуальную информацию о пользователе
                User updatedUser = userService.getUserById(userData.getId());
                // Условие - если пользователь не заблокирован
                if (updatedUser.getStatus() != BLOCKED) {
                    // Вызываем этот метод, чтобы записать в хэдер Authorization ответа новый JWT-токен
                    // с обновленной информацией о пользователе
                    jwtGenerator.encodeJwt(updatedUser.toUserData(), httpServletResponse);
                }
            }
        }

        // Этот вызов нужно сделать в Filter-классе, чтобы вызвать дальнейшую цепочку фильтров и соответствующий запросу
        // метод из контроллера. Если не сделать этот вызов, метод контроллера вообще не будет вызван.
        // Данный метод можно не вызывать, например, в том случае, когда JWT-токен не соответствует каким-либо требованиям,
        // и доступ данному пользователю запрещён
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }

    private static class JwtAuthentication implements Authentication {

        private UserData userData;

        public JwtAuthentication(UserData userData) {
            this.userData = userData;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return userData;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException { }

        @Override
        public String getName() {
            return null;
        }

    }

}
