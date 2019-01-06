package com.cg.employee.dao;

public class QueryMapper {

	public final static String GEN_PID="select projectid_sequence.nextval from dual";  
	public final static String INSERT_PROJECT="insert into projectbean values(?,?,?,?,?,0)";
	
	public final static String VIEW_PROJECT_USING_ID="select * from projectbean where projectID=?";
	public final static String GEN_EMPID="select empID_sequence.nextval from dual";
	public final static String ADD_EMP="insert into employeebean values(?,?,?,?,SYSDATE,?,?,null)";
	public final static String VIEW_EMP_USING_EMPID="select * from employeebean where empID=?";					
	public final static String CHECK_ACCESS= "select * from admin where username =?";
	public final static String DELETE_EMP="delete from employeebean where empid=?";
	public final static String UPDATE_PROJECTBEAN="update projectbean set no_of_emp=(select count(*) from employeebean where projectID=?) where projectID=?";
	public final static String DELETE_PROJECT_USING_PID="delete from projectbean where projectid=?";
	
	public final static String UPDATE_EMPBEAN_REMOVE="update employeebean set projectid=null where projectID=?";
	public final static String UPDATE_EMPBEAN_ASSIGN="update employeebean set projectID=? where empid=?";
	
	public final static String RETRIEVE_ACTIVE="select * from employeebean where projectid IS NOT NULL order by empID";
	public final static String RETRIEVE_INACTIVE="select * from employeebean where projectid IS NULL order by empID";
	
	public final static String SELECT_PID_USING_EID="select projectid from employeebean where empid=?";
	public final static String SELECT_PID="select projectid from projectbean";
	
}