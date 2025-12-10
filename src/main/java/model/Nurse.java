package main.java.model;

import java.sql.Date;

public class Nurse extends Employee{


    public Nurse(String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date birthDate, String specialization, double salary, java.sql.Date employmentDate, int departmentId) {
        super(firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId);
    }

    public Nurse(int id,String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date birthDate, String specialization, double salary, java.sql.Date employmentDate, int departmentId,String departmentName) {
        super(id,firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId,departmentName);
    }


}
