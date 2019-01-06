package com.cg.employee.dao;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.employee.bean.EmployeeBean;
import com.cg.employee.bean.ProjectBean;
import com.cg.employee.exception.EmployeeException;

import com.cg.employee.util.DBConnection;





public class EmployeeDaoImpl implements IEmployeeDao{
	Logger logger=Logger.getRootLogger();
	public EmployeeDaoImpl()
	{
	PropertyConfigurator.configure("resources//log4j.properties");
	
	}
	@Override
	public String addProject(ProjectBean project) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sequence = "P";
		
		try {
			Statement st = connection.createStatement();
			rs=st.executeQuery(QueryMapper.GEN_PID);
			
			while(rs.next()) {
				sequence+=rs.getString(1);
			}
			pst = connection.prepareStatement(QueryMapper.INSERT_PROJECT);
			pst.setString(1,sequence);
			pst.setString(2,project.getProjectName());
			pst.setString(3,project.getLocation());
			pst.setString(4,project.getProjectStartDate());
			pst.setString(5,project.getProjectEndDate());
			
			pst.executeUpdate();
			logger.info("Project Has been Successfully Addded and returned ProjectId: "+sequence);
			return sequence;
		}
		catch(SQLException sqlException)
		{
				logger.error("Insertion failed due to some SQL Exception ");
				throw new EmployeeException("Problem in Inserting Project in database!!");
		}
			
		finally
		{
				try 
				{
					rs.close();
					pst.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					
					logger.error("Error in closing DB connection ");
					throw new EmployeeException("Error in closing db connection");

				}
		}
		
	}

	@Override
	public ProjectBean viewProject(String projectId) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		
		
		ProjectBean projectBean = new ProjectBean();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
		
		pst = connection.prepareStatement(QueryMapper.VIEW_PROJECT_USING_ID);
		pst.setString(1, projectId);
		rs = pst.executeQuery();
		while(rs.next())
		{
		projectBean.setProjectId(rs.getString(1));
		projectBean.setProjectName(rs.getString(2));
		projectBean.setLocation(rs.getString(3));
		projectBean.setProjectStartDate(rs.getString(4));
		projectBean.setProjectEndDate(rs.getString(5));
		projectBean.setNo_of_emp(rs.getInt(6));
		}
		logger.info("Project Successfully Viewed ");
		return projectBean;
		}
		catch (SQLException e) {
			logger.error("Problem in Viewing Project from database");
			throw new EmployeeException("Problem in Viewing Project from database!!");
		}
		finally
		{
				try 
				{
					
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					
					logger.error("Error in closing db connection");
					throw new EmployeeException("Error in closing db connection");

				}
		}
		
	}

	@Override
	public String addEmployee(EmployeeBean employee) throws EmployeeException, ClassNotFoundException, IOException, SQLException{
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String sequence = "E";
		
		try {
			Statement st = connection.createStatement();
			rs=st.executeQuery(QueryMapper.GEN_EMPID);
			
			while(rs.next()) {
				sequence+=rs.getString(1);
			}
			pst = connection.prepareStatement(QueryMapper.ADD_EMP);
			pst.setString(1,sequence);
			pst.setString(2,employee.getEmpName());
			pst.setString(3,employee.getPhoneNumber());
			pst.setString(4,employee.getDesignation());
			pst.setDouble(5,employee.getSalary());
			pst.setString(6,employee.getAddress());
			
			pst.executeUpdate();
			logger.info("Employee Details successfully Inserted");
			return sequence;
		}
		catch(SQLException sqlException)
		{
				logger.error("Problem in Inserting EMployee in database!!");
				throw new EmployeeException("Problem in Inserting EMployee in database!!");
		}

		finally
		{
				try 
				{
					
					rs.close();
					pst.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					
					logger.error("Error in closing db connection!");
					throw new EmployeeException("Error in closing db connection");

				}
		}
		
		
	}

	

	@Override
	public EmployeeBean viewEmployeeDetails(String empId) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst =null;
		Statement st = connection.createStatement();
		EmployeeBean employeeBean = new EmployeeBean();
		ResultSet rs = null;
		try {
		
		pst = connection.prepareStatement(QueryMapper.VIEW_EMP_USING_EMPID);
		pst.setString(1, empId);
		rs = pst.executeQuery();
		while(rs.next())
		{
		employeeBean.setEmpId(rs.getString(1));
		employeeBean.setEmpName(rs.getString(2));
		employeeBean.setPhoneNumber(rs.getString(3));
		employeeBean.setDesignation(rs.getString(4));
		employeeBean.setHiredate(rs.getDate(5));
		employeeBean.setSalary(rs.getDouble(6));
		employeeBean.setAddress(rs.getString(7));
		employeeBean.setProjectId(rs.getString(8));
		}
		logger.info("Successfully viewed Employee Details");
		return employeeBean;
		}
		catch (SQLException e) {
			logger.error("Problem in Viewing Employee from database!!");
			throw new EmployeeException("Problem in Viewing Employee from database!!");
		}
		finally
		{
				try 
				{
					st.close();
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					logger.error("Error in closing DB connection");
					throw new EmployeeException("Error in closing db connection");

				}
		}
		
	}

	@Override
	public boolean checkAccess(String username, String password) throws EmployeeException, SQLException, ClassNotFoundException, IOException {
		
		Connection connection = DBConnection.getConnection();
		ResultSet rs = null;
		PreparedStatement pst1=null;
		
		String user=null;
		String pass = null;
		boolean access=false;
		try {
			
			connection.setAutoCommit(false);
			//rs=st.executeQuery("select * from admin where username ='"+username+"'");
			pst1 = connection.prepareStatement(QueryMapper.CHECK_ACCESS);
			pst1.setString(1,username);
			rs=pst1.executeQuery();
			while(rs.next()) {
				user = rs.getString(1);
				pass = rs.getString(2);
			}
			
			if(user!=null) {
				if(pass.equals(password)) {
					access= true;
				}
				else {
					logger.error("Wrong Password!!!!!!");
					System.out.println("Wrong Password");
					//throw new EmployeeException("wrong paasword");
				}
			}
			else {
				logger.error("Invalid User!!!!!!");
				System.out.println("NO such User Exists!!");
			}
			connection.commit();
			return access;
			
		}
		catch (NullPointerException e) {
			logger.error("No Records in Database about Admin Detail");
			throw new EmployeeException("There are no records of Admin");
		}
		
		finally
		{
				try 
				{
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					logger.error("Error in closing DB connection");
					throw new EmployeeException("Error in closing db connection");

				}
		}
			
		
	}

	@Override
	public void removeEmployee(String empID) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		//Statement st = connection.createStatement();
		PreparedStatement pst1=null;
		PreparedStatement pst2=null;
		PreparedStatement pst3=null;
		String dummy=null;
		String projectId=null;
		ResultSet rs = null;
		try {
		//rs=st.executeQuery(QueryMapper.VIEW_EMP_USING_EMPID);
		pst2=connection.prepareStatement(QueryMapper.VIEW_EMP_USING_EMPID);
		pst2.setString(1,empID);
		rs=pst2.executeQuery();
		while(rs.next()) {
			dummy=rs.getString(1);
			projectId=rs.getString(8);
		}
		
		if(dummy!=null) {
			//rs = st.executeQuery("delete from employeebean where empid='" + empID + "'");
			pst3=connection.prepareStatement(QueryMapper.DELETE_EMP);
			pst3.setString(1,empID);
			pst3.executeQuery();
			pst1 = connection.prepareStatement(QueryMapper.UPDATE_PROJECTBEAN);
			pst1.setString(1,projectId);
			pst1.setString(2,projectId);
			pst1.executeUpdate();
			logger.info("Employee Successfully Removed");
		}else {
			logger.error("Employee doesn't exist!");
			throw new EmployeeException("Employee doesn't exist ");
		}
		}
		catch (SQLException e) {
			logger.error("Problem in removing employee from database!!");
			throw new EmployeeException("Problem in removing employee from database!!");
		}
		finally
		{
				try 
				{
					rs.close();
					pst1.close();
					pst2.close();
					pst3.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					logger.error("Error in closing db connection");
					throw new EmployeeException("Error in closing db connection");

				}
		}
	}

	@Override
	public void removeProject(String projectID) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		//Statement st = connection.createStatement();
		PreparedStatement pst =null;
		PreparedStatement pst1 =null;
		String dummy=null;
		ResultSet rs = null;
		try {
		
		pst = connection.prepareStatement(QueryMapper.VIEW_PROJECT_USING_ID);
		pst.setString(1,projectID);
		rs = pst.executeQuery();
		while(rs.next()) {
			dummy=rs.getString(1);
		}
		
		if(dummy!=null) {
			//rs = st.executeQuery("delete from projectbean where projectid='" + projectID + "'");
			pst = connection.prepareStatement(QueryMapper.DELETE_PROJECT_USING_PID);
			pst.setString(1,projectID);
			pst.executeQuery();
			pst1 = connection.prepareStatement(QueryMapper.UPDATE_EMPBEAN_REMOVE);
			pst1.setString(1,projectID);
			pst1.executeUpdate();
			logger.info("Project Successfully Removed");
		}else {
			logger.error("Project doesn't exists!");
			throw new EmployeeException("Project doesn't exists!!!! ");
		}
		}
		catch (SQLException e) {
			logger.error("Problem in Viewing Project from database");
			throw new EmployeeException("Problem in Viewing Project from database!!");
		}
		finally
		{
				try 
				{
					//pst.close();
					pst1.close();
					rs.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					
					throw new EmployeeException("Error in closing db connection");

				}
		}
	}

	@Override
	public List<EmployeeBean> retriveActive() throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		
		List<EmployeeBean> list=null;
		ResultSet rs=null;
		try {
		rs=st.executeQuery(QueryMapper.RETRIEVE_ACTIVE);
		list=new ArrayList<>();
		while(rs.next())
		{
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmpId(rs.getString(1));
			employeeBean.setEmpName(rs.getString(2));
			employeeBean.setPhoneNumber(rs.getString(3));
			employeeBean.setDesignation(rs.getString(4));
			employeeBean.setHiredate(rs.getDate(5));
			employeeBean.setSalary(rs.getDouble(6));
			employeeBean.setAddress(rs.getString(7));
			employeeBean.setProjectId(rs.getString(8));
			
			//donorId=rs.getString(1);
			list.add(employeeBean);
			
		}
		return list;
		}
		catch (SQLException e) {
			throw new EmployeeException("Problem in Viewing Employee from database!!");
		}
		finally
		{
				try 
				{
					rs.close();
					con.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					
					throw new EmployeeException("Error in closing db connection");

				}
		}
	}

	@Override
	public List<EmployeeBean> retriveInActive() throws EmployeeException, SQLException, ClassNotFoundException, IOException {
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		
		List<EmployeeBean> list=null;
		ResultSet rs=null;
		try {
		rs=st.executeQuery(QueryMapper.RETRIEVE_INACTIVE);
		list=new ArrayList<>();
		while(rs.next())
		{
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmpId(rs.getString(1));
			employeeBean.setEmpName(rs.getString(2));
			employeeBean.setPhoneNumber(rs.getString(3));
			employeeBean.setDesignation(rs.getString(4));
			employeeBean.setHiredate(rs.getDate(5));
			employeeBean.setSalary(rs.getDouble(6));
			employeeBean.setAddress(rs.getString(7));
			employeeBean.setProjectId(rs.getString(8));
			
			
			list.add(employeeBean);
			
		}
		return list;
		}
		catch (SQLException e) {
			throw new EmployeeException("Problem in Viewing Employee from database!!");
		}
		finally
		{
				try 
				{
					rs.close();
					con.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					
					throw new EmployeeException("Error in closing db connection");

				}
		}
	}

	@Override
	public void assignProject(String projectId, String empId) throws EmployeeException, ClassNotFoundException, IOException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		//PreparedStatement pst3 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String dummy="";
		boolean dummy2=false;
		try {
			Statement st = connection.createStatement();
			//rs=st.executeQuery("select projectid from employeebean where empid='"+empId+"'");
			pst2=connection.prepareStatement(QueryMapper.SELECT_PID_USING_EID);
			pst2.setString(1,empId);
			rs=pst2.executeQuery();
			
			while(rs.next()) {
				dummy=rs.getString(1);
			}
			rs1=st.executeQuery(QueryMapper.SELECT_PID);
			
			while(rs1.next()) {
				if(projectId.equals(rs1.getString(1))) {
					dummy2=true;
				}
			}
			if(dummy2) {
				if(dummy==null) {
				pst = connection.prepareStatement(QueryMapper.UPDATE_EMPBEAN_ASSIGN);
				pst.setString(1,projectId);
				pst.setString(2,empId);
				pst.executeUpdate();
				//pst1 = connection.prepareStatement("update projectbean set no_of_emp=(select count(*) from employeebean where projectID='"+projectId+"') where projectID='"+projectId+"'");
				pst1 = connection.prepareStatement(QueryMapper.UPDATE_PROJECTBEAN);
				pst1.setString(1,projectId);
				pst1.setString(2,projectId);
				pst1.executeUpdate();
				logger.info("Project with projectId"+projectId+" has been assigned to Employee with empID: "+empId);
				}
				else {
					logger.error("Problem in assigning Project");
					throw new EmployeeException("Problem in assigning Project.");
				}
			
			}
			else {
				logger.error("Project with projectID: "+projectId+" doesn't exist");
				throw new EmployeeException("Project doesn't exist");
				
			}
			
		}
		catch(SQLException sqlException)
		{
				//sqlException.printStackTrace();
				throw new EmployeeException("Problem in Assigning Project to EMployeee in database!!");
		}

		finally
		{
				try 
				{
					pst2.close();
					rs.close();
					pst.close();
					pst1.close();
					
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					//sqlException.printStackTrace();
					
					throw new EmployeeException("Error in closing db connection");

				}
		}
		
		
	}

}
