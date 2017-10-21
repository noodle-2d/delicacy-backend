package com.delicacycomics.delicacy.service;

import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserRole;
import com.delicacycomics.delicacy.exception.NotFoundException;
import com.delicacycomics.delicacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User getUserById(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Transactional
    public User getUserByIpAddressOrGetDefault(String ipAddress) {
        User user = userRepository.findByIpAddress(ipAddress);
        if (user != null) {
            return user;
        } else {
            return userRepository.findByRole(UserRole.UNAUTHORIZED);
        }
    }

}
