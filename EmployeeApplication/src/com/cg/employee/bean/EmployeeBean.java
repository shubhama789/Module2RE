package com.cg.employee.bean;

import java.util.Date;

public class EmployeeBean {

	
	private String empId;
	private String empName;
	private String phoneNumber;
	private String designation;
	private Date hiredate;
	private double salary;
	private String address;
	private String projectId;
	//private ProjectBean projectInfo;
	
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	
	/*public ProjectBean getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectBean projectInfo) {
		this.projectInfo = projectInfo;
	}*/
	
	@Override
	public String toString() {
		return "EmployeeId= " + empId + ", empName= " + empName + ", phoneNumber= " + phoneNumber
				+ ", designation= " + designation + ", hiredate= " + hiredate + ", salary= " + salary + ", address= "
				+ address + "\n";
	}
	
	
	
	
	
}
