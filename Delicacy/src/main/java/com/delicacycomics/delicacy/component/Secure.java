package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.UserRole;

public @interface Secure {
    UserRole role();
}
