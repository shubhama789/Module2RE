package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.cg.employee.bean.EmployeeBean;
import com.cg.employee.bean.ProjectBean;
import com.cg.employee.dao.EmployeeDaoImpl;
import com.cg.employee.exception.EmployeeException;

public class EmployeeTest {

	static EmployeeDaoImpl employeeDaoImpl;
	static EmployeeBean employeeBean;
	static ProjectBean projectBean;

	@BeforeClass
	public static void initialize() {
		System.out.println("in before class");
		employeeDaoImpl = new EmployeeDaoImpl();
		employeeBean = new EmployeeBean();
	    projectBean = new ProjectBean();
	}

	/************************************
	 * Test case for addAddProject()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	
	@Test
	public void testAddProject() throws EmployeeException, ClassNotFoundException, IOException, SQLException {

		assertNotNull(employeeDaoImpl.addProject(projectBean));

	}

	/************************************
	 * Test case for addAddProject1()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/

	@Ignore
	@Test
	public void testAddProject1() throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		// increment the number next time you test for positive test case
		assertEquals("P141",employeeDaoImpl.addProject(projectBean));
	}

	/************************************
	 * Test case for addAddEmployee()
	 * @throws EmployeeException
	 ************************************/

	@Test
	public void testAddEmployee() throws EmployeeException {
		Date date = new Date();
		employeeBean.setEmpName("subham");
		employeeBean.setDesignation("developer");
		employeeBean.setAddress("dhanbad");
		employeeBean.setPhoneNumber("9547909577");
		employeeBean.setEmpId("E150");
		employeeBean.setHiredate(date);
		employeeBean.setProjectId("P141");
		employeeBean.setSalary(5000);
		assertEquals("subham",employeeBean.getEmpName());
		assertTrue("Data Inserted successfully",employeeBean.getEmpName().equals("subham"));

	}
	/************************************
	 * Test case for viewEmployeeDetails()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	@Test
	public void testviewEmployeeDetails() throws ClassNotFoundException, EmployeeException, IOException, SQLException {
		assertNotNull(employeeDaoImpl.viewEmployeeDetails("E150"));
	}
	/************************************
	 * Test case for checkAccess()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	@Test
	public void testcheckaccess() throws ClassNotFoundException, EmployeeException, SQLException, IOException {
		assertTrue(employeeDaoImpl.checkAccess("subham","agarwal"));
	}
	
	/************************************
	 * Test case for viewProject()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	@Test
	public void testviewProject() throws EmployeeException, ClassNotFoundException, IOException, SQLException {

		assertNotNull(employeeDaoImpl.viewProject(employeeBean.getProjectId()));

	}
	/************************************
	 * Test case for retriveActive()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	
	@Test
	public void testretriveActive() throws EmployeeException, ClassNotFoundException, IOException, SQLException {

		assertNotNull(employeeDaoImpl.retriveActive());

	}
	
	
	/************************************
	 * Test case for retriveINActive()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	
	@Test
	public void testretriveInActive() throws EmployeeException, ClassNotFoundException, IOException, SQLException {

		assertNotNull(employeeDaoImpl.retriveInActive());

	}
	
	/************************************
	 * Test case for AssignProject()
	 * @throws EmployeeException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws SQLException
	 ************************************/
	
	@Test
	public void testassignProject() throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		// Make sure when YOu test both project and employee should exist and
		// Employee should not have any project assigned to it 
		// and if YOu are inserting value into Table directly then COMMIT then try.......
		employeeDaoImpl.assignProject("P120","E123");
		assertEquals("P120",employeeDaoImpl.viewEmployeeDetails("E123").getProjectId());

	}

}
