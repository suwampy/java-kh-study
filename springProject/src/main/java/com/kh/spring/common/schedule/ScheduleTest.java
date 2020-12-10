package com.kh.spring.common.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTest {
	/* @Scheduled
	 * 
	 * spring에서 제공하는 스케줄러
	 * - 스케줄러 : 시간에 따른 특정 작업(Job)의 순서를 지정하는 방법
	 * 
	 * * 설정 방법
	 * 1) servlet-context.xml -> namespace 탭 -> task 체크
	 * 2) servlet-context.xml -> source 탭 -> <task:annotation-driven/> 추가
	 * 
	 * * @Scheduled 속성
	 * - fixedDelay : 이전 작업이 끝난 시점으로부터 고정된 시간(ms)을 설정 ex) fixedDelay = 5000
	 * - fixedDelayString : fixedDelay에서 value만 문자열 ex) fixedDelayString = "5000"
	 * - fixedRate : 이전 작업이 수행되기 시작한 시점으로부터 고정된 시간(ms)을 설정 ex) fixedRate = 3000
	 * - fixedRateString : fixedRate에서 value만 문자열 ex) fixedRateString = "3000"
	 * 
	 * * cron 속성 : UNIX 계열 잡 스케쥴러 표현식으로 작성
	 * - cron = "초 분 시 일 월 요일 [년도]"
	 * - 요일 : 1(SUN) ~ 7(SAT)
	 * ex) 2020년 1월 22일 수요일 15시 53분 50초
	 * cron = "50 53 15 22 1 4" // 년도 생략
	 * 
	 * - 특수 문자
	 *  * : 모든 수
	 *  - : 두 수 사이의 값 ex) 10-15 -> 10 이상 15이하
	 *  , : 특정 값 지정  ex) 3,4,7 -> 3,4,7 지정
	 *  / : 값의 증가 ex) 0/5 -> 0부터 시작하여 5마다
	 *  ? : 특별한 값이 없음. (월, 요일만 해당)
	 *  L : 마지막 (월, 요일만 해당) 
	 */
	
	//@Scheduled(fixedDelay=5000)
	@Scheduled(cron="0 59 15 * * *")
	public void test() {
		System.out.println("@Scheduled 테스트");
	}

}


