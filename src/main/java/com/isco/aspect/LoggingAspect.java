package com.isco.aspect;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

    private final Log log = LogFactory.getLog(getClass());

    public LoggingAspect(){}


    @Before("execution( * com.isco.repository..*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("***** Starting: " + joinPoint.getSignature().getName() + " *****");
        System.out.println("***** Starting: " + joinPoint.getSignature().getName() + " *****");
    }

    @AfterReturning("execution( * com.isco.repository..*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("***** Completed: " + joinPoint.getSignature().getName() + " *****");
        System.out.println("***** Completed: " + joinPoint.getSignature().getName() + " *****");
    }

}
