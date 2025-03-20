package com.HospitalManagment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctors {
	
	private Connection connection;
	
	public Doctors(Connection connection) {
		this.connection = connection;
	}
	

	
//	PATIENT DETAILS VIEWING
	public void viewDoctors() {
		
		String selectQuery = "SELECT * FROM doctors";
		
		try {
			PreparedStatement pstmt = connection.prepareCall(selectQuery);
			ResultSet resultSet = pstmt.executeQuery();
			System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");
            
            while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("Name");
				String specialization = resultSet.getString("specialization");
				System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
			}
            
            
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	PATIENTS VIWEING BY ID
	public boolean getDoctorById(int id) {
		
		String query = "SELECT * FROM doctors WHERE id = ?";
		
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
