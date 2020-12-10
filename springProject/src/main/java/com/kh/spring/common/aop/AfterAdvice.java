package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAdvice {
	@After("CommonPointcut.allPointcut()")
	public void afterLog(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		
		String printLog = "[후처리] : " + methodName + "()";
		
		System.out.println(printLog + " 종료");
	}
	
	
	
	
	
	
	
	
	
	

}
