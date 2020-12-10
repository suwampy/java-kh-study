package com.kh.nxcr.controller;

import java.io.IOException;
import java.util.Map.Entry;

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
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformException;
import com.nexacro17.xapi.tx.PlatformType;

/**
 * Servlet implementation class SelectEmployeeServlet
 */
@WebServlet("/selectEmployee")
public class SelectEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		// 보관 중인 EmployeeList를 응답으로 보내서 ds_emp를 변경하자!
		HttpPlatformResponse res =
				new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		
		PlatformData out_data = new PlatformData();
		
		DataSet out_ds = new DataSet("out_ds");
		
		out_ds.addColumn("EMPL_ID", DataTypes.STRING);
		out_ds.addColumn("FULL_NAME", DataTypes.STRING);
		out_ds.addColumn("DEPT_CD", DataTypes.STRING);
		out_ds.addColumn("POS_CD", DataTypes.STRING);
		out_ds.addColumn("HIRE_DATE", DataTypes.DATE);
		out_ds.addColumn("SALARY", DataTypes.INT);
		out_ds.addColumn("GENDER", DataTypes.STRING);
		out_ds.addColumn("MARRIED", DataTypes.STRING);
		out_ds.addColumn("MEMO", DataTypes.STRING);
		
		for(Entry<String, Employee> e : EmployeeList.getList().entrySet()) {
			int row = out_ds.newRow();
			
			out_ds.set(row, "EMPL_ID", e.getValue().getEmplId());
			out_ds.set(row, "FULL_NAME", e.getValue().getFullName());
			out_ds.set(row, "DEPT_CD", e.getValue().getDeptCd());
			out_ds.set(row, "POS_CD", e.getValue().getPosCd());
			out_ds.set(row, "HIRE_DATE", e.getValue().getHireDate());
			out_ds.set(row, "SALARY", e.getValue().getSalary());
			out_ds.set(row, "GENDER", e.getValue().getGender());
			out_ds.set(row, "MARRIED", e.getValue().getMarried());
			out_ds.set(row, "MEMO", e.getValue().getMemo());
		}
		
		DataSet out_ds2 = new DataSet("out_ds2");
		
		out_ds2.addColumn("code", DataTypes.INT);
		out_ds2.addColumn("msg", DataTypes.STRING);
		
		int row = out_ds2.newRow();
		
		out_ds2.set(row, "code", 0);
		out_ds2.set(row, "msg", "저장 된 데이터를 불러 왔습니다.");
		
		// platformData 형식에 dataset 담기
		out_data.addDataSet(out_ds);
		out_data.addDataSet(out_ds2);
		
		res.setData(out_data);
		
		try {
			res.sendData();
		} catch (PlatformException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
