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

import java.util.Arrays;

@Aspect
@Component
public class SecureAdvice {

    @Autowired
    private AuthenticationService authenticationService;

    @Around("@annotation(Secure)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        UserData userData = authenticationService.getCurrentUserData();

        if (userData != null) {
        UserRole currentUserRole = userData.getRole();
        Secure secure = ((MethodSignature)joinPoint.getSignature())
                .getMethod().getAnnotation(Secure.class);
        System.out.println("Secure = " + secure.toString());
        UserRole[] requiredUserRoles = secure.role();
            if (!Arrays.asList(requiredUserRoles).contains(currentUserRole)) {
                throw new ForbiddenException("Another user role required");
            }
        } else {
            throw new ForbiddenException("Need to authorize for this action");
        }

        //UserRole requiredUserRole = secure.role();     old version before totsamiy edit ;)
        //
        //if (currentUserRole.ordinal() < requiredUserRole.ordinal()) {
        //    throw new ForbiddenException("User role required: " + requiredUserRole);
        //}
        //
        authenticationService.clearCurrentUserData();
        return joinPoint.proceed();
    }
}


