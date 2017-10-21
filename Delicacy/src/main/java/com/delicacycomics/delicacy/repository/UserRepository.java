package com.delicacycomics.delicacy.repository;

import com.delicacycomics.delicacy.entity.User;
import com.delicacycomics.delicacy.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Дмитрий on 10/2/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByLogin(String login);
    User findByIpAddress(String ipAddress);
    User findByRole(UserRole role);

}
