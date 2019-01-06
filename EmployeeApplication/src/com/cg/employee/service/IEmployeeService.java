package com.cg.employee.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cg.employee.bean.EmployeeBean;
import com.cg.employee.bean.ProjectBean;
import com.cg.employee.exception.EmployeeException;


public interface IEmployeeService {

	public String addEmployee(EmployeeBean employee) throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	public void removeEmployee(String empID) throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	public EmployeeBean viewEmployeeDetails(String empId) throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	
	public boolean checkAccess(String username,String password) throws EmployeeException, SQLException, ClassNotFoundException, IOException;
	
	public String addProject(ProjectBean project) throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	public void removeProject(String projectID) throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	public ProjectBean viewProject(String projectId)throws EmployeeException, ClassNotFoundException, IOException, SQLException;
	//public List retrieveAll() throws EmployeeException;
	public List<EmployeeBean> retriveActive() throws ClassNotFoundException, EmployeeException, IOException, SQLException;
	public List<EmployeeBean> retriveInActive() throws ClassNotFoundException, EmployeeException, SQLException, IOException;
	public void assignProject(String projectId, String empId) throws ClassNotFoundException, EmployeeException, IOException, SQLException;
	
}
