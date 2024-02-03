package com.iablonski.planner.utils.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log
public class LoggingAspect {

    @Around("execution(* com.iablonski.planner.todo.controller..*(..))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("..... Executing: " + className + " -----> " + methodName + " .....");

        StopWatch watch = new StopWatch();

        watch.start();
        Object result = proceedingJoinPoint.proceed();
        watch.stop();

        log.info("..... Executing time of: " + className + " -----> " + methodName + " : " + watch.getTotalTimeMillis() + " .....");

        return result;
    }
}
