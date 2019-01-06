package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import com.cg.employee.exception.EmployeeException;
import com.cg.employee.service.EmployeeServiceImpl;
import com.cg.employee.util.DBConnection;

public class DBConnectionTest {

	
		static EmployeeServiceImpl emptest;
		static Connection dbCon;

		@BeforeAll
		public static void beforeClass() {
			emptest = new EmployeeServiceImpl();
			dbCon = null;
		}

		@BeforeEach
		public void beforeEachTest() {
			System.out.println("----Starting DBConnection Test Case----\n");
		}


		/**
		 * Test case for Establishing Connection
		 * @throws java.sql.SQLException: ORA-01017: invalid username/password; logon denied

		 * @throws at oracle.jdbc.driver.DatabaseError.throwSqlException(DatabaseError.java:112)
					at oracle.jdbc.driver.OracleDriver.connect(OracleDriver.java:801)
					at com.cg.employee.util.DBConnection.getConnection(DBConnection.java:24)
		 *@throws IO Exception
		 *@throws ClassNotFoundException


		 * @throws EmployeeException
		 **/
		
		
		@Test
		public void test() throws EmployeeException, ClassNotFoundException, IOException, SQLException {
			Connection dbCon = DBConnection.getConnection();
			Assert.assertNotNull(dbCon);
		}

		@AfterEach
		public void afterEachTest() {
			System.out.println("----End of DBConnection Test Case----\n");
		}

		@AfterAll
		public static void destroy() {
			System.out.println("\t----End of Tests----");
			emptest = null;
			dbCon = null;
		}

	}


