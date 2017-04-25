package pl.com.tokarzewski.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminAspect {
    private Logger logger = LoggerFactory.getLogger(AdminAspect.class);

    @Before("execution(* pl.com.tokarzewski.controllers.AdminController.*(..))")
    public void logMethodExecution(JoinPoint joinPoint){
        logger.info("Admin access to: " + joinPoint.getSignature().getName());
    }
}
