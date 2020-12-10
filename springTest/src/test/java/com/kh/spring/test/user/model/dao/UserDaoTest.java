package com.kh.spring.test.user.model.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.spring.test.user.model.vo.User;

// 3_6. UserDaoTest 클래스 작성
/* test 진행 시 was를 이용한 서버 구동 없이 진행 해야 함
 * 서버 구동 == 서버 배포
 * 배포는 test가 완료 되고 진행
 * was를 이용하지 않으면 서버 구동이 되지 않아 web.xml과 내부에서 호출되는 설정 관련 xml 파일을
 * 읽을 수 없어 각종 bean들이 생성되지 않음
 * 
 * 이를 해결하기 위해 @WebAppConfiguration 어노테이션을 사용함
 * ==> servlet-api 웹 모듈 버전을 3.XX로 올려야 함
 * */
@WebAppConfiguration // 프로젝트의 web.xml이 아닌 가상의 web.xml을 사용
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class UserDaoTest {
	@Autowired
	UserDao userDao;
	
	// 테스트 회원 삭제하고 test 시작하기 위한 setup
	@Before
	public void setUp() {
		// 부모 테이블의 데이터 삭제
		userDao.deleteUser("user01");
	}
	
	@Test
	public void selectOneUserTest() {
		// 1. 존재하는 특정 회원 조회
		User u = userDao.selectOneUser("qwerty");
		// assertThat : 해당 객체가 특정 상황을 만족하는지 확인
		// assertThat(actual, 비교식)
		// is("")
		// is(nullValue()), is(notNullValue())
		// is(equalsTo())
		assertThat(u.getName(), is("고길동"));

		// 2. 존재하지 않는 회원 조회
		u = userDao.selectOneUser("notExist");
		assertThat(u, is(nullValue()));

	}

	// (실습) : insertUser() 메소드에 대한
	// 테스트 메소드 만들기
	// 1. user 정보(id:user01, password:pass01, name:둘리)
	// 2. 실행 결과로 리턴 된 정수가 0보다 큰지 확인함
	// 3. selectOneUser("새로 등록한 회원 아이디")로 조회한 후
	// 리턴 된 객체의 사용자 아이디와 등록 된 사용자 아이디가 일치하는지 확인
	@Test
	public void insertUserTest() {
		// user 정보
		// id : user01
		// password : pass01
		// name : 둘리
		User u = new User();
		u.setId("user01");
		u.setPassword("pass01");
		u.setName("둘리");

		// 실행 결과로 리턴된 정수가 0보다 큰지 확인함
		int result = userDao.insertUser(u);
		assertTrue(result > 0);

		// selectOneUser("새로 등록한 회원아이디") 로 조회한 다음,
		// 리턴된 객체의 사용자아이디와 등록된 사용자 아이디가 일치하는지 확인
		User returnUser = userDao.selectOneUser("user01");
		assertThat(u.getId(), is(returnUser.getId()));
		
		// 한번 더 시행하면 pk의 unique 조건에 걸리므로
		// @Before 어노테이션을 통해 테스트 환경을 setup하는 메소드를 작성
	}

}



