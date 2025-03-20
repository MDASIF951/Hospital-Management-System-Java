# Hospital Management System

This is a simple Hospital Management System implemented in Java, using JDBC for database connectivity with MySQL. It allows users to manage patient and doctor information, as well as book appointments.

## Features

-   **Add Patient:** Allows users to add new patient records to the database.
-   **View Patients:** Displays a list of all patients in the database.
-   **View Doctors:** Displays a list of all doctors in the database.
-   **Book Appointment:** Allows users to book appointments for patients with doctors, checking for doctor availability.
-   **Database Integration:** Uses MySQL database to store and retrieve patient, doctor, and appointment data.

## Prerequisites

-   Java Development Kit (JDK) installed.
-   MySQL database server installed and running.
-   MySQL Connector/J JDBC driver.
-   Basic understanding of SQL and JDBC.

## Getting Started

1.  **Clone the Repository:**

    ```bash
    git clone <repository_url>
    ```

2.  **Set up MySQL Database:**

    -   Create a database named `hospital`.
    -   Create the following tables:

        ```sql
        CREATE TABLE patients (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255),
            age INT,
            gender VARCHAR(10)
        );

        CREATE TABLE doctors (
            id INT AUTO_INCREMENT PRIMARY KEY,
            name VARCHAR(255),
            specialization VARCHAR(255)
        );

        CREATE TABLE appointments (
            id INT AUTO_INCREMENT PRIMARY KEY,
            patient_id INT,
            doctor_id INT,
            appointment_date DATE,
            FOREIGN KEY (patient_id) REFERENCES patients(id),
            FOREIGN KEY (doctor_id) REFERENCES doctors(id)
        );
        ```

3.  **Add MySQL Connector/J:**

    -   Download the MySQL Connector/J JAR file.
    -   Add the JAR file to your project's classpath.

4.  **Update Database Credentials:**

    -   Open `HosptialDBConnection.java` and update the `url`, `uName`, and `passWd` variables with your MySQL database credentials.

    ```java
    private static final String url = "jdbc:mysql://localhost:3306/hospital";
    private static final String uName = "root";
    private static final String passWd = "YourPassword";
    ```

5.  **Compile and Run:**

    -   Compile the Java files:

        ```bash
        javac com/HospitalManagment/*.java
        ```

    -   Run the `HosptialDBConnection` class:

        ```bash
        java com.HospitalManagment.HosptialDBConnection
        ```

6.  **Use the Application:**

    -   Follow the on-screen prompts to interact with the system.

## Project Structure
HospitalManagementSystem/
├── com/
│   └── HospitalManagment/
│       ├── Doctors.java
│       ├── HosptialDBConnection.java
│       └── Patient.java
└── README.md

-   `Doctors.java`: Manages doctor-related operations.
-   `HosptialDBConnection.java`: Handles database connection and main application logic.
-   `Patient.java`: Manages patient-related operations.
-   `README.md`: Project documentation.

## Usage Example

1.  Run the application.
2.  Select option `1` to add a patient.
3.  Enter the patient details.
4.  Select option `3` to view doctors.
5.  Select option `4` to book an appointment.
6.  Enter the patient ID, doctor ID, and appointment date.
7.  Verify the appointment booking.

## Future Enhancements

-   Implement user authentication and authorization.
-   Add functionality to update and delete patient and doctor records.
-   Implement appointment scheduling with time slots.
-   Add a graphical user interface (GUI).
-   Add better error handling and input validation.
-   Add the ability to search for patients and doctors.
-   Generate appointment reports.
