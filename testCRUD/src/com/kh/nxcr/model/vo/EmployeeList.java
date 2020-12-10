package com.kh.nxcr.model.vo;

import java.util.HashMap;
import java.util.Map;

public class EmployeeList {
	// DB를 만들지 않았으므로 해당 어플리케이션에서 참조할 수 있는 employeeList를
	// 간이로 만들어 작업하기 위한 용도
	private static Map<String, Employee> list = new HashMap<>();

	public static Map<String, Employee> getList() {
		return list;
	}

	public static void setList(Map<String, Employee> list) {
		EmployeeList.list = list;
	}

	@Override
	public String toString() {
		return "EmployeeList [toString()=" + super.toString() + "]";
	}

}
