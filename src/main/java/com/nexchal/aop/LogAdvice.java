package com.nexchal.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class LogAdvice {

	@Around("execution(* com.nexchal.board.service..*Service.*(..)) || execution(* com.nexchal.sample.service..*Service.*(..))")
	public Object logTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		long start = System.currentTimeMillis();
		
		Object result = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long gap = end - start;
		
		log.info("-------------------------------");
		log.info(proceedingJoinPoint.getTarget());
		log.info(proceedingJoinPoint.getSignature());
		log.info("RUNTIME : " + gap);
		log.info("-------------------------------");
			
		if(gap > 100) {
			log.warn("-------------- WARNING -----------------");
		}
		
		return result;
	}
}
