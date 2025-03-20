package com.HospitalManagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	
	private Connection connection;
	
	private Scanner scanner;
	
	public Patient(Connection connection, Scanner scanner) {
		this.connection = connection;
		this.scanner = scanner;
	}
	
//	PATIENT DETAILS ADDING
	public void addPatient() {
		System.out.print("Enter the Patient Name: ");
		String name = scanner.next();
		
		System.out.print("Enter the Patient Age: ");
		int age = scanner.nextInt();
		
		System.out.print("Enter the Patient Gender: ");
		String gender  = scanner.next();
		
		
		try {
			String insert = "INSERT INTO patients(name, age, gender) VALUES(?,?,?)";
			PreparedStatement pstmt = connection.prepareStatement(insert);
			
			pstmt.setString(1, name);
			pstmt.setInt(2, age);
			pstmt.setString(3, gender);
			
			int affectedRows = pstmt.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Patient Details Added Successfully.");
			}
			else {
				System.out.println("Patient Details Failed To Add!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	PATIENT DETAILS VIEWING
	public void viewPatients() {
		
		String selectQuery = "SELECT * FROM patients";
		
		
		try {
			PreparedStatement pstmt = connection.prepareCall(selectQuery);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            
            while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("Name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
				System.out.println("+------------+--------------------+----------+------------+");
			}
            
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	PATIENTS VIWEING BY ID
	public boolean getPatientById(int id) {
		
		String query = "SELECT * FROM patients WHERE id = ?";
		
		try {
			PreparedStatement pStmt = connection.prepareStatement(query);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}