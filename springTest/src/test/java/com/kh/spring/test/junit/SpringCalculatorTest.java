package com.kh.spring.test.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 2_3. 테스트 클래스 작성

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations=
//{"file:src/main/webapp/WEB-INF/spring/appServlet/test-context.xml"})
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/test-context.xml")
public class SpringCalculatorTest {
	/* @RunWith(SpringJUnit4ClassRunner.class)
	 * 스프링이 제공하는 JUnitRunner를 이용해서 테스트 실행
	 * @ContextConfiguration의 설정 정보 이용하여 스프링 컨텍스트 생성
	 * 테스트 클래스의 @Autowired 등의 필드에 자동 주입 처리
	 * 테스트 메소드마다 컨텍스트를 생성하지 않도록 컨텍스트를 캐싱
	 * 
	 * @ContextConfiguration 
	 * 자동으로 만들어 줄 애플리케이션 컨텍스트의 설정 파일 경로를 지정
	 */
	@Autowired
	private Calculator calc;

	@Test // 테스트용 메소드임을 명시하는 어노테이션
	public void sumTest() {
		int result = calc.sum(10, 20);
		assertEquals(30, result);
		assertEquals(30, result, 0);
		
		/* assertEquals(a, b) : A와 B가 일치함을 확인
		 * assertEquals(expected, actual, delta) : 예상값, 실제값, 허용오차 
		 * assertArrayEquals(a, b) : 배열 A와 B가 일치함을 확인
		 * assertSame(a, b) : 객체 A와 B가 같은 객체임을 확인 <-> assertNotSame(a, b)
		 * => assertEquals 메서드는 두 객체의 값이 같은지 검사하는데 반해
		 *    assertSame 메서드는 두 객체가 동일한지 확인(== 연산자)
		 * assertTrue(a) : 조건 A가 참인지 확인 <-> assertFalse(a)
		 * assertNotNull(a) : 해당 값이 null이 아닌가를 확인 <-> assertNull(a)   
		 */
		
		System.out.println("calc@sumTest=" + calc.hashCode());
		System.out.println("CalculatorTest@sumTest=" + this.hashCode());
		
	}

	@Test
	public void multiplyTest() {
		int result = calc.multiply(10, 20);
		assertEquals(200, result);
		assertEquals(200, result, 0);
		
		System.out.println("calc@multiplyTest=" + calc.hashCode());
		System.out.println("CalculatorTest@multiplyTest=" + this.hashCode());
	}
	
	// 결과 : 테스트 성공. 같은 calc 객체, 같은 context, 다른 CalculatorTest 객체

}
