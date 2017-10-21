package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    public UserData getCurrentUserData() {
        return (UserData)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Transactional
    public User getCurrentUser() {
        UserData userData = getCurrentUserData();
        return userService.getUserById(userData.getId());
    }

}
