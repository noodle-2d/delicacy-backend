package com.delicacycomics.delicacy.component;

import com.delicacycomics.delicacy.entity.UserData;
import com.delicacycomics.delicacy.entity.UserRole;
import com.delicacycomics.delicacy.exception.ForbiddenException;
import com.delicacycomics.delicacy.service.AuthenticationService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecureAdvice {

    @Autowired
    private AuthenticationService authenticationService;

    @Around("@annotation(Secure)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        UserData userData = authenticationService.getCurrentUserData();
        UserRole currentUserRole = userData.getRole();

        Secure secure = ((MethodSignature)joinPoint.getSignature())
                .getMethod().getAnnotation(Secure.class);
        UserRole requiredUserRole = secure.role();

        if (currentUserRole.ordinal() < requiredUserRole.ordinal()) {
            throw new ForbiddenException("User role required: " + requiredUserRole);
        }

        return joinPoint.proceed();
    }

}
