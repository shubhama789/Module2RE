package com.cg.employee.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.cg.employee.bean.EmployeeBean;
import com.cg.employee.bean.ProjectBean;
import com.cg.employee.dao.EmployeeDaoImpl;
import com.cg.employee.dao.IEmployeeDao;
import com.cg.employee.exception.EmployeeException;



public class EmployeeServiceImpl implements IEmployeeService{

	IEmployeeDao iEmployeeDao = null;
	
	
	@Override
	public String addProject(ProjectBean project) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		String projectID;
		projectID = iEmployeeDao.addProject(project);
		return projectID;
	}

	
	@Override
	public ProjectBean viewProject(String projectId) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		ProjectBean projectBean;
		
		projectBean = iEmployeeDao.viewProject(projectId);	
		
		return projectBean;
	}

	@Override
	public String addEmployee(EmployeeBean employee) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		String empId;
		empId = iEmployeeDao.addEmployee(employee);
		return empId;
	}

	

	@Override
	public EmployeeBean viewEmployeeDetails(String empId) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		EmployeeBean employeeBean;
		
		employeeBean = iEmployeeDao.viewEmployeeDetails(empId);	
		
		return employeeBean;
		
		
	}
	
	

	public void validateEmployee(EmployeeBean employeeBean) throws EmployeeException {
		
		List<String> validationErrors = new ArrayList<String>();

		//Validating Employee name
		if(!(isValidName(employeeBean.getEmpName()))) {
			validationErrors.add("\n Employee Name Should Be In Alphabets and minimum 3  and maximum 20 characters long ! \n");
		}
		//Validating Phone Number
		if(!(isValidPhoneNumber(employeeBean.getPhoneNumber()))){
			validationErrors.add("\n Phone Number Should be in 10 digit \n");
		}
		//validating designation
		if(!(isValidDesignation(employeeBean.getDesignation()))){
			validationErrors.add("\n Designation should be minimum 5 characters long \n");
		}
		//Validating Donation Amount
		if(!(isValidSalary(employeeBean.getSalary()))){
			validationErrors.add("\n Salary Should be a positive Number \n" );
		}
		//Validating address
		if(!(isValidAddress(employeeBean.getAddress()))){
			validationErrors.add("\n Address Should Be Greater Than 5 Characters \n");
		}
		
		
		if(!validationErrors.isEmpty())
			throw new EmployeeException(validationErrors +"");
		
		
	}
	

	private boolean isValidAddress(String address) {
		return (address.length() > 5);
	}

	private boolean isValidSalary(double salary) {
		return (salary>0);
	}

	private boolean isValidDesignation(String designation) {
		Pattern designPattern=Pattern.compile("^[A-Za-z]{5,}$");
		Matcher designMatcher=designPattern.matcher(designation);
		return designMatcher.matches();
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		Pattern phonePattern=Pattern.compile("^[6-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(phoneNumber);
		return phoneMatcher.matches();
	}

	private boolean isValidName(String empName) {
		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,20}$");
		Matcher nameMatcher=namePattern.matcher(empName);
		return nameMatcher.matches();
	}

	@Override
	public boolean checkAccess(String username, String password) throws EmployeeException, SQLException, ClassNotFoundException, IOException {
		iEmployeeDao = new EmployeeDaoImpl();
		boolean access;
		access= iEmployeeDao.checkAccess(username,password);
	
		return access;
	}

	public void validateProject(ProjectBean projectBean) throws EmployeeException {
		
		List<String> validationErrors = new ArrayList<String>();

		//Validating Project name
		if(!(isValidName(projectBean.getProjectName()))) {
			validationErrors.add("\n Project Name Should Be In Alphabets and minimum 3  and maximum 20 characters long ! \n");
		}
		//Validating Phone Number
		if(!(isValidStartDate(projectBean.getProjectStartDate()))){
			validationErrors.add("\n Start date should be in dd/MM/yyyy format to be able to convert to dd-MMM-yyyy format\n");
		}
		//validating designation
		if(!(isValidEndDate(projectBean.getProjectEndDate()))){
			validationErrors.add("\n end date should be in dd/MM/yyyy format to be able to convert to dd-MMM-yyyy format \n");
		}
		//Validating address
		if(!(isValidLocation(projectBean.getLocation()))){
			validationErrors.add("\n Location Should Be Greater Than 5 Characters \n");
		}
		
		
		if(!validationErrors.isEmpty())
			throw new EmployeeException(validationErrors +"");
		
		
	}

	private boolean isValidEndDate(String projectEndDate) {
		Pattern phonePattern=Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-([A-Za-z]{3})-((19|2[0-9])[0-9]{2})$");
		Matcher phoneMatcher=phonePattern.matcher(projectEndDate);
		return phoneMatcher.matches();
	}

	private boolean isValidStartDate(String projectStartDate) {
		Pattern phonePattern=Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-([A-Za-z]{3})-((19|2[0-9])[0-9]{2})$");
		Matcher phoneMatcher=phonePattern.matcher(projectStartDate);
		return phoneMatcher.matches();
	}

	private boolean isValidLocation(String location) {
		return location.length()>5;
	}

	public boolean validateEmployeeId(String empId) {
		Pattern idPattern = Pattern.compile("[E][1-9][0-9]{2,3}");
		Matcher idMatcher = idPattern.matcher(empId);
		return idMatcher.matches();
	}

	public boolean validateProjectId(String projectId) {
		Pattern idPattern = Pattern.compile("[P][1-9][0-9]{2,3}");
		Matcher idMatcher = idPattern.matcher(projectId);
		return idMatcher.matches();
	}


	@Override
	public void removeEmployee(String empID) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		iEmployeeDao.removeEmployee(empID);
		
	}


	@Override
	public void removeProject(String projectID) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		iEmployeeDao.removeProject(projectID);
		
	}


	@Override
	public List<EmployeeBean> retriveActive() throws ClassNotFoundException, EmployeeException, IOException, SQLException {
		List<EmployeeBean> list = new ArrayList<>();
		iEmployeeDao = new EmployeeDaoImpl();
		list = iEmployeeDao.retriveActive();
		return list;
	}


	@Override
	public List<EmployeeBean> retriveInActive() throws ClassNotFoundException, EmployeeException, SQLException, IOException {
		List<EmployeeBean> list = new ArrayList<>();
		iEmployeeDao = new EmployeeDaoImpl();
		list = iEmployeeDao.retriveInActive();
		return list;
	}


	@Override
	public void assignProject(String projectId, String empId) throws ClassNotFoundException, EmployeeException, IOException, SQLException {
		iEmployeeDao = new EmployeeDaoImpl();
		
		iEmployeeDao.assignProject(projectId,empId);
		
	}


	

	
	
	
}
