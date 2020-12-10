package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
public class AfterReturningAdvice {
	// loginMember() 메소드가 호출 될 때(로그인 프로세스 동작 시)
	/* execution(* com.kh.spring.member.model.service.MemberServiceImpl.loginMember(*))
	 * execution(* com.kh.spring..MemberServiceImpl.loginMember(*))
	 * execution(* com.kh.spring..*Impl.loginMember(*))
	 * execution(* com.kh.spring..*Impl.login*(*))
	 */
	@AfterReturning(pointcut="execution(* com.kh.spring..*Impl.login*(*))", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		
		String methodName = jp.getSignature().getName();
		System.out.println("[log] : " + methodName + "() 메소드 실행");
		
		// 관리자가 로그인 한 경우
		// [log] : 관리자님 환영합니다
		// 일반회원이 로그인 한 경우
		// [log] : ID XXX 회원 로그인
		if(returnObj instanceof Member) {
			Member m = (Member)returnObj;
			if(m.getId().equals("admin")) {
				System.out.println("[log] : 관리자님 환영합니다.");
			}else {
				System.out.println("[log] : ID : " + m.getId() + " 회원 로그인");
			}
		}
		
	}
	
	
	
	
	
	
	
	
	

}
