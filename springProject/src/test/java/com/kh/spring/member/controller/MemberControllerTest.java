package com.kh.spring.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml",
		"classpath:spring/appServlet/servlet-context.xml",
		"classpath:spring/spring-security.xml"})
public class MemberControllerTest {
	@Autowired
	private WebApplicationContext wac; // 현재 실행 중인 애플리케이션의 구성을 제공하는 인터페이스
	private MockMvc mockMvc; // client 요청 내용을 controller에서 받아 처리하는 것과 같은 테스트를
							 // 진행할 수 있는 클래스
	
	@Before
	public void setup() {
		//  매번 테스트를 진행할때마다 테스트 하기 전 가상의 요청과 응답을 처리하기 위한
		// MockMvc mockMvc 객체를 만들어 주기 위해
		// @Before 어노테이션으로 setup() 메소드를 생성
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testMemberInsert() throws Exception {
		// perform() : mockMvc 객체를 이용하여 매핑될 url과 필요한 데이터가 담긴 가상의 요청을 작성
		// andDo(print()) : 처리되어진 내용을 출력 
		// andExpect() : 예상 결과와 일치하는지 확인
		mockMvc.perform(post("/minsert.do")
				.param("id", "user100")
				.param("pwd", "pass100")
				.param("name", "테스트")
				.param("email", "test@gmail.com")
				.param("gender", "F")
				.param("phone", "01012345678")
				.param("post", "주소1")
				.param("address1", "주소2")
				.param("address2", "주소3"))
				.andDo(print())
				.andExpect(redirectedUrl("home.do"));
	}
	
	
	
	
	
	
	
	
	
	
	


}
