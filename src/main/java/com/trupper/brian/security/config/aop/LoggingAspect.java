package com.trupper.brian.security.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.logging.Logger;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    //AOP expression for which methods shall be intercepted
    @Around("execution(* com.trupper.brian.service..*(..)))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //Get intercepted method details
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();

        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        //Log method execution time
        log.info("Execution time of " +
                "\u001B[31m{}\u001B[34m" +
                "\u001B[37m.\u001B[34m{}\u001B[0m :: \u001B[33m" +
                "{} ms\u001B[0m", className, methodName, stopWatch.getTotalTimeMillis());


        return result;
    }
}
