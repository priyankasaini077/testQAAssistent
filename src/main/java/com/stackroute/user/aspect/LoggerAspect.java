package com.stackroute.user.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggerAspect {
	Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * Write loggers for each of the methods of NewsController, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	
	@Before("execution(* com.stackroute.newz.controller.*.*(..))")
    public void logStartController(JoinPoint point) {
		log.info("Started -- " + point.getSignature().getName());
	}
	
	@After("execution(* com.stackroute.newz.controller.*.*(..))")
    public void logEndController(JoinPoint point) {
		log.info("Ended -- " + point.getSignature().getName());
	}
	
	@AfterThrowing(pointcut = "execution(* com.stackroute.newz.controller.*.*(..))", throwing = "ex")
    public void logThrowController(JoinPoint point, Exception ex) {
		log.error("Throws -- " + point.getSignature().getName() + " Exception :" + ex.getMessage());
	}
	
	@AfterReturning(pointcut = "execution(* com.stackroute.newz.controller.*.*(..))", returning = "value")
    public void logReturnController(JoinPoint point, Object value) {
		log.error("Gives -- " + point.getSignature().getName() + " returing : "+ value);
	}

}