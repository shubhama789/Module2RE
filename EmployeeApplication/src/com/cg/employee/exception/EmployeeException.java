package com.cg.employee.exception;

public class EmployeeException extends Exception{

	String message;
	
	public EmployeeException(String message) {
		this.message=message;
		System.out.println("  "+message);
	}

	@Override
	public String toString() {
		return "EmployeeException []"+message;
	}

	

	
}
