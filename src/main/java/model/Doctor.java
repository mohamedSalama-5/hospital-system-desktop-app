package main.java.model;

import java.util.Date;

public class Doctor extends Employee{

    // insert and update
    public Doctor(String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date birthDate, String specialization, double salary, java.sql.Date employmentDate, int departmentId) {
        super(firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId);
    }

    //constructor overloading to view the data
    public Doctor(int id ,String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date birthDate, String specialization, double salary, java.sql.Date employmentDate, int departmentId,String departmentName) {
        super(id,firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId,departmentName);
    }


}
