package com.cg.employee.bean;

public class ProjectBean {

	private String projectId;
	private String projectName;
	private String location;
	private String projectStartDate;
	private String projectEndDate;
	private int no_of_emp; 
	
	
	public int getNo_of_emp() {
		return no_of_emp;
	}
	public void setNo_of_emp(int no_of_emp) {
		this.no_of_emp = no_of_emp;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(String projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public String getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(String projectEndDate) {
		this.projectEndDate = projectEndDate;
	}
	
	@Override
	public String toString() {
		return " projectId= " + projectId + "\n projectName= " + projectName + "\n location= " + location
				+ "\n projectStartDate= " + projectStartDate + "\n projectEndDate= " + projectEndDate + "\n No_of_EMP = " + no_of_emp;
	}
	
	
	
	
}
