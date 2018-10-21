package com.kookyungmin.waops.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* com.kookyungmin.waops.service.QuestionService*.listPage(..))")
	public void startLog(JoinPoint jp) {
		logger.debug("startLog>>>>> pointcut={}, args={}", jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		
	}
	
	@After("execution(* com.kookyungmin.waops.service.QuestionService*.listPage(..))")
	public void afterLog(JoinPoint jp) {
		logger.debug("afterLog>>>>> pointcut={}, args={}", jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
	}
	
	@Around("execution(* com.kookyungmin.waops.service.QuestionService*.listPage(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.debug("-------------- TimeLog ---------------");
		long stime = System.currentTimeMillis();
		Object result = pjp.proceed();
		logger.debug(pjp.getSignature().getName() + ">>" + (System.currentTimeMillis() - stime));
		logger.debug("-------------- TimeLog ---------------");
		return result;
	}
	
}
