package com.kh.nxcr.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.nxcr.model.vo.Employee;
import com.kh.nxcr.model.vo.EmployeeList;
import com.nexacro17.xapi.data.DataSet;
import com.nexacro17.xapi.data.DataTypes;
import com.nexacro17.xapi.data.PlatformData;
import com.nexacro17.xapi.data.Variable;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformException;
import com.nexacro17.xapi.tx.PlatformType;

/**
 * Servlet implementation class SaveEmployeeServlet
 */
@WebServlet("/saveEmployee")
public class SaveEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("오니?");
		
		// ---------------- 요청 값 받아오기 ------------------------------
		
		// 1. 넥사크로 플랫폼 요청 양식으로 변경
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		
		// 2. 데이터를 받아오는 작업
		try {
			req.receiveData();
		} catch (PlatformException e) {
			// 데이터가 없을 경우의 컨트롤이 필요함
			e.printStackTrace();
		}
		
		// 3. 넥사크로 데이터를 파싱하여 가져온다
		PlatformData in_data = req.getData();
		
		// 4. DataSet 가져오기
		DataSet in_ds = in_data.getDataSet("in_ds");
		
		// 5-1. delete 된 데이터의 경우
		for(int i = 0; i < in_ds.getRemovedRowCount(); i++) {
			String emplId = in_ds.getRemovedData(i, "EMPL_ID").toString();
			String fullName = in_ds.getRemovedData(i, "FULL_NAME").toString();
			System.out.println("삭제 된 Employee : " + emplId + ", " + fullName);
			
			// 이후 DB로 보내서 deleteEmployee 실행하기
			// int result = new EmployeeService().deleteEmployee(emplId);
			
			// DB 대신 간이로 처리하기
			EmployeeList.getList().remove(emplId);
		}
		
		// 5-2. insert, 5-3. update 된 데이터의 경우
		// RowType : 각 행(Row)의 상태를 나타내는 코드
		// 0 : DataSet.ROW_TYPE_NORMAL (정상 상태)
		// 1 : DataSet.ROW_TYPE_INSERTED (추가 된 데이터)
		// 2 : DataSet.ROW_TYPE_UPDATED (수정 된 데이터)
		for(int i = 0; i < in_ds.getRowCount(); i++) {
			// i번째 row의 상태 코드를 얻어 온다
			int rowType = in_ds.getRowType(i);
			
			Employee e = new Employee();
			e.setEmplId(in_ds.getString(i, "EMPL_ID"));
			e.setFullName(in_ds.getString(i, "FULL_NAME"));
			e.setDeptCd(in_ds.getString(i, "DEPT_CD"));
			e.setPosCd(in_ds.getString(i, "POS_CD"));
			e.setHireDate(new Date(in_ds.getDateTime(i, "HIRE_DATE").getTime()));
			e.setSalary(in_ds.getInt(i, "SALARY"));
			e.setGender(in_ds.getString(i, "GENDER"));
			e.setMarried(in_ds.getString(i, "MARRIED"));
			e.setMemo(in_ds.getString(i, "MEMO"));
			
			if(rowType == DataSet.ROW_TYPE_INSERTED) {
				System.out.println("삽입 된 회원 : " + e);
				// DB로 데이터를 보내서 insert 한다
				// int result = new EmployeeService().insertEmployee(e);
				
			}else if(rowType == DataSet.ROW_TYPE_UPDATED) {
				System.out.println("수정 된 회원 : " + e);
				// DB로 데이터를 보내서 update 한다
				// int result = new EmployeeService().updateEmploye(e);
			}else {
				System.out.println("변경 없는 회원 : " + e);
			}
			
			// 추가 테스트
			// :A - all
			// :U - update
			// :N - 삭제되지 않은 정보
			
			// DB 대신 간이로 처리하기
			EmployeeList.getList().put(e.getEmplId(), e);
			
		}
		
		// 6. 트랜잭션 간 전달할 변수 받아보기
		// PlatformDate in_data 안에 위치
		Variable testVal = in_data.getVariable("testVal");
		String testValue = testVal.getString();
		System.out.println("testVal : " + testValue);
		
		Variable testVal2 = in_data.getVariable("testVal2");
		String testValue2 = testVal2.getString();
		System.out.println("testVal2 : " + testValue2);
		
		// 7. 콜백 함수 동작 시키기
		// -> 응답 있을 때와 없을 때 msg 비교해보기
		
		// ------------- 서버에서 응답 보내기 -----------------------
		// 8. 서버에서 응답 보내기
		HttpPlatformResponse res 
		= new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		
		// WAS에서 응답할 넥사크로 데이터를 만든다
		PlatformData out_data = new PlatformData();
		
		// 이름을 넣어서 하나의 데이터 셋을 생성한다
		DataSet out_ds = new DataSet("out_ds");
		
		// 데이터 셋의 컬럼 지정
		out_ds.addColumn("code", DataTypes.INT);
		out_ds.addColumn("msg", DataTypes.STRING);
		
		// 데이터 행 생성
		int row = out_ds.newRow();
		
		// 행에 메세지 입력하기 -> db 수행 결과에 따라 다른 메세지를 보내줄 수 있음
		out_ds.set(row, "code", 0);
		out_ds.set(row, "msg", "저장이 완료 되었습니다.");
		
		// PlatformData 형식에 out_ds dataset 담기
		out_data.addDataSet(out_ds);
		
		// 응답 객체에 세팅
		res.setData(out_data);
		
		// 응답을 보낸다
		try {
			res.sendData();
		} catch (PlatformException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
