package com.kh.spring.test.junit;

// 1_2. Calculator 클래스 작성

// * 단위 테스트(unit test)
// 개발 과정에서 진행되는 테스트로 구현된 모듈의 기능 수행 여부를 판정하고,
// 내부에 존재하는 논리적 오류를 검출하는 테스트

// 해당 클래스의 메소드를 대상으로 단위 테스트를 진행하기 위해
// 해당 클래스 우클릭 - New - JUnit Test Case
// 기본 값은 해당 클래스와 동일한 경로에 '클래스명 + Test' 이름으로 테스트 클래스를 생성
public class Calculator {

	public int sum(int a, int b) {
		return a+b;
	}
	
	public int multiply(int a, int b) {
		return a*b;
	}
}




