package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.component.BCryptPasswordEncoder;
import com.delicacycomics.delicacy.component.JwtFilter;
import com.delicacycomics.delicacy.component.JwtGenerator;
import com.delicacycomics.delicacy.dto.request.UserRegisterDto;
import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.entity.UserRole;
import com.delicacycomics.delicacy.exception.ForbiddenException;
import com.delicacycomics.delicacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.delicacycomics.delicacy.entity.UserRole.*;
import static com.delicacycomics.delicacy.entity.UserStatus.ACTIVE;
import static com.delicacycomics.delicacy.entity.UserStatus.BLOCKED;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;

    public UserData getCurrentUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof JwtFilter.JwtAuthentication) {
            return (UserData)authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public void clearCurrentUserData() {
        SecurityContextHolder.clearContext();
    }

    @Transactional
    public User getCurrentUser() {
        UserData userData = getCurrentUserData();
        if (userData != null) {
            return userService.getUserById(userData.getId());
        } else {
            return null;
        }
    }

    @Transactional
    public void authenticate(String login, String password, HttpServletResponse response) {
        authenticateForRoles(login, password, response, Collections.singletonList(CUSTOMER));
    }

    @Transactional
    public void authenticateAdmin(String login, String password, HttpServletResponse response) {
        authenticateForRoles(login, password, response, Arrays.asList(MODERATOR, ADMIN));
    }

    private void authenticateForRoles(String login, String password,
                                      HttpServletResponse response,
                                      List<UserRole> roles) {
        User user = userRepository.findByLogin(login);
        if (user == null || !roles.contains(user.getRole())) {
            throw new ForbiddenException("User with the given login does not exist");
        }
        if (user.getStatus() == BLOCKED) {
            throw new ForbiddenException("User with the given login is blocked");
        }
        if (!passwordEncoder.checkPassword(password, user.getPasswordHash())) {
            throw new ForbiddenException("Incorrect password");
        }
        UserData userData = user.toUserData();
        jwtGenerator.encodeJwt(userData, response);
    }

    @Transactional
    public void register(UserRegisterDto userRegisterDto) {
        User user = userRepository.findByLogin(userRegisterDto.getLogin());
        if (user != null) {
            throw new ForbiddenException("User with the given login already exists");
        }
        String passwordHash = passwordEncoder.encodePassword(userRegisterDto.getPassword());
        user = new User();
        user.setLogin(userRegisterDto.getLogin());
        user.setPasswordHash(passwordHash);
        user.setStatus(ACTIVE);
        user.setRole(CUSTOMER);
        user.setRegistrationDate(new Date());
        user.setName(userRegisterDto.getName());
        user.setSurname(userRegisterDto.getSurname());
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        user.setEmail(userRegisterDto.getEmail());
        user.setLink(userRegisterDto.getLink());
        userRepository.save(user);
    }

}
