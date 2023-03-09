package com.example.SpringBoot.JWT.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    Logger log= LoggerFactory.getLogger(LoggingAdvice.class);

    @Pointcut(value = "execution(* com.adg.erp.*.*.*.*(..) )")
    public void logginPointCut() {

    }

    @Around("logginPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper=new ObjectMapper();
        String methodName=proceedingJoinPoint.getSignature().getName();
        String className=proceedingJoinPoint.getTarget().getClass().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("method invoked: "+className+" : "+methodName+" : arguments : "+objectMapper.writeValueAsString(args));
        Object object=proceedingJoinPoint.proceed();
//		log.info("method invoked: "+className+" : "+methodName+" : response : "+objectMapper.writeValueAsString(object));
        return object;
    }
}
