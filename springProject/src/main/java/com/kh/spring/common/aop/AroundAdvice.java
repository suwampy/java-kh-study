package com.kh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class AroundAdvice {
	@Around("CommonPointcut.allPointcut()")
	public Object aroundLogs(ProceedingJoinPoint pp) throws Throwable {
		// 전처리, 후처리를 모두 해결하고자 할 때 사용하는 어드바이스
		String methodName = pp.getSignature().getName();
		
		// 해당 메소드를 수행하는데 걸린 시간을 계산하여 출력
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object obj = pp.proceed(); // 전처리 종료, 메소드 실행 완료 후 후처리 시작
		
		stopWatch.stop();
		
		System.out.println(methodName + "() 매소드 수행에 걸린 시간 : "
				+ stopWatch.getTotalTimeMillis() + "(ms)");
		
		return obj;
	}
	
	
	
	
	
	
	
	
	
	
}
