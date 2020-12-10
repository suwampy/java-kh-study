package com.kh.ajax.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.ajax.test.vo.Sample;
import com.kh.ajax.test.vo.User;

@Controller
public class TestController {
	@Autowired
	Sample samp;
	
	// 1. ServletOutputStream을 이용한 방식으로 출력용 메소드 사용
	@RequestMapping("test1.do")
	public void test1Method(HttpServletResponse response,
			String name, int age) throws IOException {
		PrintWriter out = response.getWriter();
		
		if(name.equals("신사임당") && age == 47) {
			out.write("success");
		}else {
			out.write("fail");
		}
	}
	
	/* 프론트엔드의 AJAX 요청은 대부분 JSON이므로 백엔드에서도 JSON 형태로 응답
	 * 스프링에서는 이와 관련 된 어노테이션을 제공
	 * ==> @RequestBody, @ResponseBody */
	
	/* 2. @ResponseBody를 이용한 방식
	 * @RequestBody : HTTP 요청 => 자바 객체로 전달 
	 *                JSON 타입 => String 자바 타입
	 * @ResponseBody : 자바 객체 => HTTP 응답
	 *                 String 자바 타입 => JSON 타입*/
	// @RequestMapping의 produces 속성을 이용해 Response의 Content-Type을 제어
	@RequestMapping(value="test2.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public String test2Method(HttpServletResponse response) {
		// response.setContentType("application/json; charset=utf-8");
		// 웹 브라우저에서 test2.do 실행 후 Network 확인하면
		// ResponseHeader에 contentType이 이미 지정 되어 있음
		
		JSONObject job = new JSONObject();
		// JSON 사용을 위해 pom.xml에 lib 추가
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", "홍길동");
		job.put("content", "JSON 객체를 뷰로 리턴하는 테스트입니다.");
		
		return job.toJSONString();
	}
	
	// 3. Stream을 이용해서 json 배열 보내기
	@RequestMapping("test3.do")
	public void test3Method(HttpServletResponse response) throws IOException {
		// response.setContentType 혹은 전송 시 ajax에서 dataType : "json"
		// 을 작성해야 json으로 파싱 된다.
		response.setContentType("application/json; charset=utf-8"); 
		
		ArrayList<User> list = new ArrayList<User>();
		// DB에 있는 회원 리스트라고 가정
		list.add(new User("user01", "pass01", "유관순", 15, "user01@kh.or.kr", "010-1234-5678"));
		list.add(new User("user02", "pass02", "홍길동", 20, "user02@kh.or.kr", "010-1234-5678"));
		list.add(new User("user03", "pass03", "신사임당", 25, "user03@kh.or.kr", "010-1234-5678"));
		list.add(new User("user04", "pass04", "강감찬", 30, "user04@kh.or.kr", "010-1234-5678"));
		list.add(new User("user05", "pass05", "선덕여왕", 35, "user05@kh.or.kr", "010-1234-5678"));
		
		// 1. List를 JSON 배열로 담아주기
		JSONArray jarr = new JSONArray();
		for(User u : list) {
			// 1_1. JSON 배열에 담기 위해 user 객체를 JSON 객체에 담기
			JSONObject jUser = new JSONObject();
			
			jUser.put("userId", u.getUserId());
			jUser.put("userPwd", u.getUserPwd());
			jUser.put("userName", u.getUserName());
			jUser.put("age", u.getAge());
			jUser.put("email", u.getEmail());
			jUser.put("phone", u.getPhone());
			
			// 1_2. user 정보를 담은 JSON 객체 JSON 배열에 넣기
			jarr.add(jUser);
		}
		// 2. 전송을 하기 위해 JSON 객체로 담겨잇는 회원 정보 JSON 배열을
		// JSON 객체에 다시 담아줌
		JSONObject sendJson = new JSONObject();
		sendJson.put("list", jarr);
		
		// 3. 전송
		PrintWriter out = response.getWriter();
		out.print(sendJson);
	}
	
	/* 4. JsonView를 이용한 방식
	 * 컨트롤러에서 ModelAndView를 리턴하고, 
	 * jsonView 빈에서 해당 map 객체를 json 형식으로 리턴
	 * jsonView 관련 의존 => json-lib-ext-spring */
	@RequestMapping("test4.do")
	public ModelAndView test4Method(ModelAndView mv, 
			HttpServletResponse response) {
		// 1. Sample VO 작성
		// 2. servlet-context.xml에서 Sample 객체 bean 등록(생성자 주입)
		// 3. 필드 영역에 Sample 객체 의존성 주입
		
		Map<String, Sample> map = new HashMap<String, Sample>();
		map.put("samp", samp);
		
		// addAllObjects 메소드를 이용하여 map 객체에 저장 된
		// 모든 속성(key, value)를 모델에 저장함
		mv.addAllObjects(map);
		
		// 뷰 지정 : JsonView를 빈으로 등록하고 JsonView id를 뷰 이름으로 지정
		// viewName과 실제 view 단을 연결해주는 역할의 viewResolver도 빈으로 등록
		mv.setViewName("jsonView");
		
		response.setContentType("application/json; charset=utf-8");
		
		return mv;
	}
	
	/* 5. @RequestBody를 이용한 방법
	 * 뷰에서 전송한 json 데이터를 컨트롤러에서 자바 객체로 변환 */
	@RequestMapping("test5.do")
	@ResponseBody
	public String test5Method(@RequestBody String param) throws ParseException {
		// 뷰에서 stringify 메소드를 통해 객체를 json String 형태로 보냄
		// 전송 온 문자열을 다시 json 객체로 변환 처리
		JSONParser parser = new JSONParser();
		JSONObject jObj = (JSONObject)parser.parse(param);
		
		String name = (String)jObj.get("name");
		// int age = (Integer)jObj.get("age");
		// 뷰에서 보낸 값이 long 자료형
		int age = ((Long)jObj.get("age")).intValue();
		
		System.out.println(name + ", " + age);
		
		return "success";
	}
	/* 6. JSON Array @RequestBody로 받고 콘솔에 모든 객체 출력 후
	 * @ResponseBody에 "success" 메세지 보내기 */
	@RequestMapping("test6.do")
	@ResponseBody
	public String test6Method(@RequestBody String param) throws ParseException {
		
		JSONParser parser = new JSONParser();	
		JSONArray jArr = (JSONArray)parser.parse(param);
		
		for(int i=0; i< jArr.size(); i++) {
			JSONObject jObj = (JSONObject)jArr.get(i);
			
			String name = (String)jObj.get("name");
			int age = ((Long)jObj.get("age")).intValue();
			
			System.out.println(i + "번째 객체 : " + name + ", " + age);
		}
		
		return "success";
		
	}
	
	/* 보다 간단하게 @RequestBody, @ResponseBody를 이용하려면
	 * JSON 형태의 정보를 Map과 커맨드 객체로 변환하기 위해 jackson 라이브러리 사용 */
	/*@ResponseBody
	@RequestMapping("test7.do")
	public HashMap<String, Object> test7Method(
			@RequestBody HashMap<String, Object> map){
		
		System.out.println(map);
		return map;
		
	}*/
	@ResponseBody
	@RequestMapping("/test7.do")
	public HashMap<String, Object> test7Method(
			@RequestBody Sample sampleVO){
		System.out.println(sampleVO.getName());
		System.out.println(sampleVO.getAge());
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sampleVO", sampleVO);
		
		return map;
	}
	
	/* 8. Json 배열 전송 List<VO>로 받기 */
	@ResponseBody
	@RequestMapping("/test8.do")
	public List<Sample> test7Method(@RequestBody List<Sample> sampleList){
		for(Sample s : sampleList) {
			System.out.println(s.getName());
			System.out.println(s.getAge());
		}
		return sampleList;
	}

}
