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

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        JwtGenerator.DecodeResult decodeResult = jwtGenerator.decodeJwt(httpServletRequest);

        if (decodeResult != null) {
            UserData userData = decodeResult.getUserData();
            SecurityContextHolder.getContext().setAuthentication(new JwtAuthentication(userData));

            if (decodeResult.getRefresh().isBefore(Instant.now())) {
                User updatedUser = userService.getUserById(userData.getId());
                if (updatedUser.getStatus() != BLOCKED) {
                    jwtGenerator.encodeJwt(updatedUser.toUserData(), httpServletResponse);
                }
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() { }

    public static class JwtAuthentication implements Authentication {

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
