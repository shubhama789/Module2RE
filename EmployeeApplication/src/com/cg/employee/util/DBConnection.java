package com.cg.employee.util;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		
		FileInputStream fis = new FileInputStream("resources/DBCredentials.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String u= properties.getProperty("url");
		String user =properties.getProperty("username");
		String pass=properties.getProperty("password");
		String driver = properties.getProperty("driver");
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(u,user,pass);
		return connection;
		
		
	}

	
	
	
}
