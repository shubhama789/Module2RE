package com.cg.employee.pl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;



import com.cg.employee.bean.EmployeeBean;
import com.cg.employee.bean.ProjectBean;
import com.cg.employee.exception.EmployeeException;
import com.cg.employee.service.EmployeeServiceImpl;
import com.cg.employee.service.IEmployeeService;





public class EmployeeMain {
	
	static Scanner sc = new Scanner(System.in);
	static IEmployeeService iEmployeeService = null;
	static EmployeeServiceImpl employeeServiceImpl = null;
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, EmployeeException {
		
		int option;
		while(true)
		{
			System.out.println("----------------MENU---------------");
			System.out.println();
			System.out.println("1. PROJECTION/SELECTION OPERATIONS. [View Details]");
			System.out.println("2. DATA MANIPULATION OPERATIONS. [Manipulate Details]");
			System.out.println("3. Exit");
			System.out.println("Select from above options");
			try {
			option = sc.nextInt();
			String username=null;
			String password=null;
			switch(option)
			{
				
				case 1:
					projectionOperation();
					break;
				case 2:
					System.out.println("Enter Your Admin Credentials to use this facility: ");
					System.out.println("Enter your Username: ");
					username=sc.next();
					System.out.println("Enter your Password: ");
					password=sc.next();
					if(checkaccess(username,password)) {
						manipulationOperation();
					}
					else {
						System.out.println("Please contact Admin to do this operations");
					}
					break;
				case 3:
					System.exit(0);
					break;
				default:
					System.out.println("Please Select only from above two options");
					break;
			}//switch case ends of main menu
			}//try block of main menu ends
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Input Type Mismatch Occured!!!!!!!");
			}//catch block of main menu ends
			
		}//while loop of main menu ends
			
	}// main menu ends

	
	
	private static boolean checkaccess(String username, String password) throws ClassNotFoundException, EmployeeException, SQLException, IOException {
		boolean access;
		iEmployeeService=new EmployeeServiceImpl();
		access=iEmployeeService.checkAccess(username,password);
		return access;
	}



	private static void manipulationOperation() throws ClassNotFoundException, IOException, SQLException, EmployeeException {
		
		EmployeeBean employeeBean = null;
		ProjectBean projectBean = null;
		
		String projectId = null;
		String empId = null;
		
		int option;
		while(true)
		{
			System.out.println("----------------MANIPULATION OPERATIONS---------------");
			System.out.println();
			System.out.println("1. Add Employee");
			System.out.println("2. Add Project");
			System.out.println("3. Remove Employee.");
			System.out.println("4. Remove Project. ");
			System.out.println("5. Assign Project. ");
			System.out.println("6. Exit");
			System.out.println("Select from above options");
			
			try {
			option = sc.nextInt();
			switch(option)
			{
				
				case 1:
					while (employeeBean == null) {
						employeeBean = populateEmployeeBean();
					}
					try {
						iEmployeeService = new EmployeeServiceImpl();
						empId = iEmployeeService.addEmployee(employeeBean);

						System.out.println("Employee details  has been successfully registered ");
						System.out.println("Your EmployeeID Is: " + empId);

					} catch (EmployeeException employeeException) {
						
						System.err.println("ERROR : "+ employeeException.getMessage());
					} finally {
						empId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}

					break;
				case 2:
					while (projectBean == null) {
						projectBean = populateProjectBean();
						
					}
					try {
						iEmployeeService = new EmployeeServiceImpl();
						projectId = iEmployeeService.addProject(projectBean);

						System.out.println("Project details  has been successfully registered ");
						System.out.println("ProjectID Is: " + projectId);

					} catch (EmployeeException employeeException) {
						
						System.err.println("ERROR : "+ employeeException.getMessage());
					} finally {
						projectId = null;
						iEmployeeService = null;
						projectBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 3:
					System.out.println("Enter Id of Employee whom u want to Terminate: ");
					empId = sc.next();
					employeeBean = new EmployeeBean();
					iEmployeeService= new EmployeeServiceImpl();
					employeeServiceImpl = new EmployeeServiceImpl();
					try {
					if(employeeServiceImpl.validateEmployeeId(empId))
					{
						try {
								iEmployeeService.removeEmployee(empId);
								System.out.println(" Employee with empID "+empId+" has been Successfully removed");
							}
						catch(Exception e)
						{
							System.err.println(e.getMessage());
						}
						
					}
					}
					finally {
						empId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					
					break;
				case 4:
					System.out.println("Enter Id of Project which u want to delete: ");
					projectId = sc.next();
					projectBean = new ProjectBean();
					iEmployeeService= new EmployeeServiceImpl();
					employeeServiceImpl = new EmployeeServiceImpl();
					try {
					if(employeeServiceImpl.validateProjectId(projectId))
					{
						try {
								iEmployeeService.removeProject(projectId);
								System.out.println(" Project with projectID "+projectId+" has been Successfully removed");
							}
						catch(Exception e)
						{
							System.err.println(e.getMessage());
						}
						
					}
					}
					finally {
						projectId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
          
					break;
				case 5:
					System.out.println("Enter ProjectId which u want to assign to Employee");
					projectId = sc.next();
					System.out.println();
					System.out.println("Enter Id of Employee to whom u want to assign project with projectID: "+projectId);
					empId= sc.next();
					iEmployeeService= new EmployeeServiceImpl();
					employeeServiceImpl = new EmployeeServiceImpl();
					try {
					if(employeeServiceImpl.validateProjectId(projectId)&& employeeServiceImpl.validateEmployeeId(empId))
					{
						try {
								iEmployeeService.assignProject(projectId,empId);
								System.out.println(" Project with projectID "+projectId+" has been Successfully assigned to employee with empID"+empId);
						}
						catch(Exception e)
						{
							System.err.println(e.getMessage());
						}
						
					}
					else {
						throw new EmployeeException("please enter valid empid and pojectID");
					}
					}
					finally {
						empId=null;
						projectId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 6:
					System.exit(0);
					break;
				default:
					System.out.println("Please Select only from above options");
					break;
			}                //switch case of MANIPULATION menu ends
			}                             //try block of Manipulation ends
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Please enter only Numeric Value.");
			}                                //catch block of Manipulation ends
		}                   //while loop of Manipulation menu ends
	}        // Manipulation OPertions Method Ends

	
	
	
	
	private static ProjectBean populateProjectBean() {
		ProjectBean projectBean = new ProjectBean();

		System.out.println("\n Enter Details");

		System.out.println("Enter Project name: ");
		projectBean.setProjectName(sc.next());

		System.out.println("Enter Project Location: ");
		projectBean.setLocation(sc.next());
		

		System.out.println("Enter Project StartDate[dd/MM/yyyy]: ");
		projectBean.setProjectStartDate(changeToDate(sc.next()));
		
		System.out.println("Enter Project EndDate[dd/MM/yyyy]: ");
		projectBean.setProjectEndDate(changeToDate(sc.next()));

		employeeServiceImpl = new EmployeeServiceImpl();

		try {
			employeeServiceImpl.validateProject(projectBean);
			return projectBean;
		} catch(EmployeeException employeeException) {
			
			System.err.println("Invalid data:");
			System.err.println(employeeException.getMessage() + " \n Try again..");
			System.exit(0);

		}
		return null;

	}



	private static String changeToDate(String next) {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		LocalDate inputDate = LocalDate.parse(next,dateTimeFormatter);
		return inputDate.format(dateTimeFormatter1);
	}



	private static EmployeeBean populateEmployeeBean() {
		
		EmployeeBean employeeBean = new EmployeeBean();

		System.out.println("\n Enter Details");

		System.out.println("Enter Employee name: ");
		employeeBean.setEmpName(sc.next());

		System.out.println("Enter Employee  contact No.: ");
		employeeBean.setPhoneNumber(sc.next());

		System.out.println("Enter Employee Designation: ");
		employeeBean.setDesignation(sc.next());
		
		System.out.println("Enter Employee Salary: ");

		try {
			employeeBean.setSalary(sc.nextDouble());
		} catch (InputMismatchException inputMismatchException) {
			sc.nextLine();
			System.err.println("Please enter a numeric value for employee Salary");
			}
		System.out.println("Enter Employee Address");
		employeeBean.setAddress(sc.next());

		employeeServiceImpl = new EmployeeServiceImpl();

		try {
			employeeServiceImpl.validateEmployee(employeeBean);
			return employeeBean;
		} catch (EmployeeException employeeException) {
			
			System.err.println("Invalid data:");
			System.err.println(employeeException.getMessage() + " \n Try again..");
			System.exit(0);

		}
		return null;

	}// populate Employee Method ends

	
	
	
	
	private static void projectionOperation() {
		
		EmployeeBean employeeBean = null;
		ProjectBean projectBean = null;
		
		String projectId = null;
		String empId = null;
		
		int option;
		while(true)
		{
			System.out.println("----------------PROJECTION/SELECTION OPERATIONS---------------");
			System.out.println();
			System.out.println("1. View Employee using EmpID");
			System.out.println("2. View Project using ProjectID");
			System.out.println("3. Show Active Employee");
			System.out.println("4. Show Inactive Employee");
			System.out.println("5. Exit");
			System.out.println("Select from above options");
			
			try {
			option = sc.nextInt();
			switch(option)
			{
				
				case 1:
					System.out.println(" Enter EmpID of an Employee whose details u want to see.... ");
					empId = sc.next();
					try
					{
						employeeBean= new EmployeeBean();
						iEmployeeService = new EmployeeServiceImpl();
						employeeServiceImpl = new EmployeeServiceImpl();
					
						if(employeeServiceImpl.validateEmployeeId(empId))
						{
							
							employeeBean= iEmployeeService.viewEmployeeDetails(empId);
							if(employeeBean.getEmpId()!=null)
								System.out.println(employeeBean);
							else
								System.out.println("Employee ID doesn't Exists");
						}
						else
						{
							System.out.println("EmployeeID format is wrong.");
						}
					}
					catch(Exception exc)
					{
						System.err.println("Error:"+exc.getMessage());
					}
					finally {
						empId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 2:
					System.out.println(" Enter ProjectID to see the details of an employee ");
					projectId = sc.next();
					try
					{
						projectBean= new ProjectBean();
						iEmployeeService = new EmployeeServiceImpl();
						employeeServiceImpl = new EmployeeServiceImpl();
					
						if(employeeServiceImpl.validateProjectId(projectId))
						{
							
							projectBean= iEmployeeService.viewProject(projectId);
							if(projectBean.getProjectId()!=null)
								System.out.println(projectBean);
							else
								System.out.println("Project ID doesn't Exists");
						}
						else
						{
							System.out.println("ProjectId format is wrong.");
						}
					}
					catch(Exception exc)
					{
						System.err.println("Error:"+exc.getMessage());
					}
					finally {
						projectId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 3:
					try
					{
						employeeBean = new EmployeeBean();
						iEmployeeService = new EmployeeServiceImpl();
						List<EmployeeBean> list=null;
						list=iEmployeeService.retriveActive();
						
						
						if(!list.isEmpty()) {
							System.out.println(list);
						}
						else {
							throw new EmployeeException("NO Active Employee");
						}
						
					}
					catch(Exception e)
					{
						System.err.println("Error:"+e.getMessage());
					}
					finally {
						empId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 4:
					try
					{
						employeeBean = new EmployeeBean();
						iEmployeeService = new EmployeeServiceImpl();
						List<EmployeeBean> list=null;
						list=iEmployeeService.retriveInActive();
						
						
						if(!list.isEmpty()) {
							System.out.println(list);
						}
						else {
							throw new EmployeeException("All Employees have Project ....NO INactive members");
						}
						
					}
					catch(Exception e)
					{
						System.err.println("Error:"+e.getMessage());
					}
					finally {
						empId = null;
						iEmployeeService = null;
						employeeBean = null;
						employeeServiceImpl=null;
					}
					break;
				case 5:
					System.exit(0);
					break;
				default:
					System.out.println("Please Select only from above options");
					break;
			}               
			}                            
			catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("Please enter only Numeric Value.");
			}                               
		}                  
		
	}
}
		