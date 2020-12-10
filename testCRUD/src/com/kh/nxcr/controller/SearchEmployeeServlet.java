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
import com.nexacro17.xapi.data.Variable;
import com.nexacro17.xapi.tx.HttpPlatformRequest;
import com.nexacro17.xapi.tx.HttpPlatformResponse;
import com.nexacro17.xapi.tx.PlatformException;
import com.nexacro17.xapi.tx.PlatformType;

/**
 * Servlet implementation class SearchEmployeeServlet
 */
@WebServlet("/searchEmployee")
public class SearchEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpPlatformRequest req = new HttpPlatformRequest(request);
		
		try {
			req.receiveData();
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PlatformData in_data = req.getData();
		
		// ver 1 - variable
		/*Variable emplId = in_data.getVariable("emplId");
		String id = emplId.getString();
		System.out.println("id : " + id);
		
		Variable fullName = in_data.getVariable("fullName");
		String name = fullName.getString();
		System.out.println("name : " + name);*/
		
		// ver2 - Dataset Binding
		DataSet in_ds = in_data.getDataSet("in_ds");
		
		String id = in_ds.getString(0, "EMPL_ID");
		String name = in_ds.getString(0, "FULL_NAME");
		
		int code = -1;
		String msg = "존재하지 않는 사원입니다.";
		for(Entry<String, Employee> entry : EmployeeList.getList().entrySet()) {
			Employee e = entry.getValue();
			if(id.equals(e.getEmplId()) && name.equals(e.getFullName())) {
				code = 0;
				msg = "존재하는 사원입니다.";
				break;
			}
		}
		
		HttpPlatformResponse res 
		= new HttpPlatformResponse(response, PlatformType.CONTENT_TYPE_XML, "UTF-8");
		
		PlatformData out_data = new PlatformData();
		
		DataSet out_ds = new DataSet("out_ds");
		
		out_ds.addColumn("code", DataTypes.INT);
		out_ds.addColumn("msg", DataTypes.STRING);
		
		int row = out_ds.newRow();
		
		out_ds.set(row, "code", code);
		out_ds.set(row, "msg", msg);
		
		out_data.addDataSet(out_ds);
	
		res.setData(out_data);
		
		try {
			res.sendData();
		} catch (PlatformException e) {
			// TODO Auto-generated catch block
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
