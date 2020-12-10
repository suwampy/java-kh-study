package com.kh.spring.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect // Pointcut + Advice
public class LogAdvice {
	// Pointcut 지정 : advice를 적용할 부분을 지정한다
	
	// 포인트 컷 표현식
	// execution 명시자 : Advice를 적용할 메소드를 명시할 때 사용
	// execution(리턴타입패턴 클래스이름패턴 메소드이름패턴 (파라미터패턴))
	// * : 모든, .. : 0개 이상
	
	//@Pointcut("execution(* com.kh.spring..*Impl.*(..))")
	public void allPointcut() {}
	
	//@Before("CommonPointcut.allPointcut()")
	public void printLog() {
		System.out.println("[공통] : 비즈니스 로직 수행 전 동작" );
	}
	
	//@After("CommonPointcut.allPointcut()")
	public void finallyLog() {
		System.out.println("[공통] : 비즈니스 로직 종료");
	}
	
	
	
	
	

}
