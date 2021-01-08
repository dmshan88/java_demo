package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthTokenAspect {
    /**
     * Spring中使用@Pointcut注解来定义方法切入点
     * @Pointcut 用来定义切点，针对方法  @Aspect 用来定义切面，针对类
     * 后面的增强均是围绕此切入点来完成的
     * 此处仅配置被我们刚才定义的注解：AuthToken修饰的方法即可
     * 
     */
    @Pointcut("@annotation(authToken)")
    public void doAuthToken(AuthToken authToken) {
    }
 
    /**
     * 此处我使用环绕增强，在方法执行之前或者执行之后均会执行。
     */
    @Around("doAuthToken(authToken)")
    public Object deBefore(ProceedingJoinPoint pjp, AuthToken authToken) throws Throwable {
        System.out.println("---------方法执行之前-------------");
        // 执行原方法，并记录返回值。
        Object proceed = pjp.proceed();
        System.out.println("---------方法执行之后-------------");
        return proceed; 
    }
 


}
