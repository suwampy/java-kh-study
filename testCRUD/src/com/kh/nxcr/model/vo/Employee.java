package com.kh.nxcr.model.vo;

import java.sql.Date;

public class Employee {
	private String emplId;
	private String fullName;
	private String deptCd;
	private String posCd;
	private java.sql.Date hireDate;
	private int salary;
	private String gender;
	private String married;
	private String memo;
	
	public Employee() {}

	public Employee(String emplId, String fullName, String deptCd, String posCd, Date hireDate, int salary,
			String gender, String married, String memo) {
		super();
		this.emplId = emplId;
		this.fullName = fullName;
		this.deptCd = deptCd;
		this.posCd = posCd;
		this.hireDate = hireDate;
		this.salary = salary;
		this.gender = gender;
		this.married = married;
		this.memo = memo;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDeptCd() {
		return deptCd;
	}

	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	public String getPosCd() {
		return posCd;
	}

	public void setPosCd(String posCd) {
		this.posCd = posCd;
	}

	public java.sql.Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "Employee [emplId=" + emplId + ", fullName=" + fullName + ", deptCd=" + deptCd + ", posCd=" + posCd
				+ ", hireDate=" + hireDate + ", salary=" + salary + ", gender=" + gender + ", married=" + married
				+ ", memo=" + memo + "]";
	}
	
	

}
