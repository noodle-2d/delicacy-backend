package com.delicacycomics.delicacy.component;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoder {

    public String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}
