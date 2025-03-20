package com.HospitalManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HosptialDBConnection {
	
	private static final String url = "jdbc:mysql://localhost:3306/hospital";
	private static final String uName = "root";
	private static final String passWd = "Asif@287568";
	
	public static void main(String[] args) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			Connection connection = DriverManager.getConnection(url, uName, passWd);
			Patient patient = new Patient(connection, scanner);
			Doctors doctors = new Doctors(connection);
			
			while(true) {
				System.out.println("HOSPITAL MANAGMENT SYSTEM.");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                
                int choice = scanner.nextInt();
                
                switch (choice) {
				case 1:
					// ADD PATIENT
					patient.addPatient();
					System.out.println();
					break;
				case 2:
					//VIEW PATIENT
					patient.viewPatients();
					System.out.println();
					break;
				case 3:
					//VIEW DOCTORS
					doctors.viewDoctors();
					System.out.println();
					break;
				case 4:
					//BOOK APPOINTMENT
					bookAppointment(patient, doctors, connection, scanner);
					System.out.println();
					break;
				case 5:
					System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
					return;
				default:
					System.out.println("Enter a Valid Choice!!");
					break;
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void bookAppointment(Patient patient, Doctors doctors, Connection connection, Scanner scanner) {
		System.out.print("Enter Patient Id: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int doctorId = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.next();
        
        if(patient.getPatientById(patientId) && doctors.getDoctorById(doctorId)) {
        	if(checkDoctorAvailability(doctorId, appointmentDate, connection)) {
        		String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES(?, ?, ?)";
        		
        		try {
					PreparedStatement pStmt = connection.prepareStatement(appointmentQuery);
					pStmt.setInt(1, patientId);
					pStmt.setInt(2, doctorId);
					pStmt.setString(3, appointmentDate);
					
					int rowsAffected = pStmt.executeUpdate();
					
					if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
        		
        	} else {
        		System.out.println();
        		System.out.println("Doctor not available on this date!!");
        	}
        } else {
        	System.out.println();
        	System.out.println("Either doctor or patient doesn't exist!!/n Please book in another date");
        }
        
        
	}

	public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection connection) {
		
		 String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
		 
		 try {
			PreparedStatement pStmt = connection.prepareStatement(query);
			
			pStmt.setInt(1, doctorId);
			pStmt.setString(2, appointmentDate);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if(resultSet.next()) {
				int count = resultSet.getInt(1);
				if(count == 0) {
					return true;
				}
				else {
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
