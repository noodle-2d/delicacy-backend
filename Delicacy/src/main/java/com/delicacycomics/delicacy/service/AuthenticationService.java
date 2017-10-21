package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.component.BCryptPasswordEncoder;
import com.delicacycomics.delicacy.component.JwtGenerator;
import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.entity.UserRole;
import com.delicacycomics.delicacy.exception.ForbiddenException;
import com.delicacycomics.delicacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.delicacycomics.delicacy.entity.UserRole.ADMIN;
import static com.delicacycomics.delicacy.entity.UserRole.CUSTOMER;
import static com.delicacycomics.delicacy.entity.UserRole.MODERATOR;

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
        return (UserData)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public User getCurrentUser() {
        UserData userData = getCurrentUserData();
        return userService.getUserById(userData.getId());
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
        if (!passwordEncoder.checkPassword(password, user.getPasswordHash())) {
            throw new ForbiddenException("Incorrect password");
        }
        UserData userData = user.toUserData();
        jwtGenerator.encodeJwt(userData, response);
    }

    @Transactional
    public void register(String login, String password) {
        User user = userRepository.findByLogin(login);
        if (user != null) {
            throw new ForbiddenException("User with the given login already exists");
        }
        String passwordHash = passwordEncoder.encodePassword(password);
        user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        user.setRole(CUSTOMER);
        userRepository.save(user);
    }

}
