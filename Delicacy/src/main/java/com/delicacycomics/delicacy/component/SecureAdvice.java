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

    // Этот метод будет вызван при обращении к любому из методов контроллера, помеченную аннотацией Secure
    // Secure - пользовательская аннотация, и мы могли использовать здесь любую другую аннотацию, какую бы захотели
    // В аннотации @Around можно указать много различных значений, чтобы настроить, в каких именно случаях будет
    // вызываться данный метод. В данном случае он вызывается при вызове метода, помеченного аннотацией Secure
    // Метод принимает на вход объект класса ProceedingJoinPoint, который подобен FilterChain в Filter-классе
    // Этот объект формирует для нас Spring, когда вызывает данный метод
    @Around("@annotation(Secure)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // Берём информацию о текущем аутентифицированном пользователе
        // Она уже лежит в SecurityContext, потому что была положена туда ранее в JwtFilter
        UserData userData = authenticationService.getCurrentUserData();
        // Получаем роль текущего аутентифицированного пользователя
        UserRole currentUserRole = userData.getRole();

        // Получаем аннотацию, которой был помечен метод контроллера
        Secure secure = ((MethodSignature)joinPoint.getSignature())
                .getMethod().getAnnotation(Secure.class);
        // Получаем роль, указанную в аннотации, который помечен метод контроллера
        // Эта роль - минимальная, которой должен обладать пользователя для получения доступа к вызыванному энд-пойнту
        UserRole requiredUserRole = secure.role();

        // Выясняем, обладает ли текущий пользователь требуемой ролью
        if (currentUserRole.ordinal() < requiredUserRole.ordinal()) {
            // В данном случае у пользователя нет доступа к энд-пойнту, и метод контроллера вообще не будет вызван,
            // так мы не делаем вызов joinPoint.proceed()
            throw new ForbiddenException("User role required: " + requiredUserRole);
        }

        // Вызываем этот метод, чтобы Spring вызвал метод контроллера
        return joinPoint.proceed();
    }

}
