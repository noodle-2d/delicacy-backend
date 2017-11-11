package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.component.BCryptPasswordEncoder;
import com.delicacycomics.delicacy.component.JwtGenerator;
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

    // Получение UserData из SecurityContext'a, если она там есть, то есть была положена туда ранее в JwtFilter
    public UserData getCurrentUserData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserData)authentication.getPrincipal();
        } else {
            return null;
        }
    }

    // Получение сущности User, соответствующей текущему аутентифицированному пользователю
    @Transactional
    public User getCurrentUser() {
        UserData userData = getCurrentUserData();
        if (userData != null) {
            return userService.getUserById(userData.getId());
        } else {
            return null;
        }
    }

    // Аутентификация покупателя
    @Transactional
    public void authenticate(String login, String password, HttpServletResponse response) {
        authenticateForRoles(login, password, response, Collections.singletonList(CUSTOMER));
    }

    // Аутентификация модератора или админа
    @Transactional
    public void authenticateAdmin(String login, String password, HttpServletResponse response) {
        authenticateForRoles(login, password, response, Arrays.asList(MODERATOR, ADMIN));
    }

    // Аутентификация по определенной роли. Вызывается выше
    private void authenticateForRoles(String login, String password,
                                      HttpServletResponse response,
                                      List<UserRole> roles) {
        // Ищем в базе пользователя с заданным логином
        User user = userRepository.findByLogin(login);
        // Если пользователь не найден, или найден, но не имеет одной из требуемых ролей, запрещаем доступ
        if (user == null || !roles.contains(user.getRole())) {
            throw new ForbiddenException("User with the given login does not exist");
        }
        // Если пользователь заблокирован, запрещаем доступ
        if (user.getStatus() == BLOCKED) {
            throw new ForbiddenException("User with the given login is blocked");
        }
        // Если хэш пароля неправильный, запрещаем доступ
        if (!passwordEncoder.checkPassword(password, user.getPasswordHash())) {
            throw new ForbiddenException("Incorrect password");
        }
        // Если всё хорошо, формируем для пользователя JWT-токен и кладем его в хэдер ответа
        UserData userData = user.toUserData();
        jwtGenerator.encodeJwt(userData, response);
    }

    // Регистрация нового пользователя-покупателя
    @Transactional
    public void register(String login, String password) {
        // Ищем, есть ли уже пользователь в базе с таким логином
        User user = userRepository.findByLogin(login);
        // Если уже есть, запрещаем регистрацию
        if (user != null) {
            throw new ForbiddenException("User with the given login already exists");
        }
        // Считаем хэш пароля
        String passwordHash = passwordEncoder.encodePassword(password);
        // Создаём новую сущность User, записываем в неё новые значения и сохраняем в базе
        // Это типичный пример того, как вообще делается insert в базу через класс репозиторий
        user = new User();
        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        user.setStatus(ACTIVE);
        user.setRole(CUSTOMER);
        userRepository.save(user);
    }

}
