package main.java.model;

import java.sql.Date;

public class Employee extends Person{

    private String specialization;
    private double salary;
    private java.sql.Date  employmentDate;
    private int departmentId;
    // not in DB
    private transient String departmentName;

    public Employee(String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date  birthDate, String specialization
            , double salary, java.sql.Date  employmentDate, int departmentId) {
        this(0, firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId, null);
    }

    //overloading constructor to view the data in GUI
    public Employee(int id,String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date  birthDate,String specialization
            , double salary,java.sql.Date  employmentDate,int departmentId,String departmentName) {
        super(id,firstName, lastName, nationalId, email, phoneNumber, birthDate);
        this.specialization = specialization;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }


    // to update data
    public Employee(String firstName, String lastName, String nationalId, String email, String phoneNumber, java.sql.Date  birthDate,String specialization
            , double salary,java.sql.Date  employmentDate,int departmentId,String departmentName) {
        this(0,firstName, lastName, nationalId, email, phoneNumber, birthDate, specialization, salary, employmentDate, departmentId, departmentName);

    }
    // getter
    public String getSpecialization() { return specialization; }
    public double getSalary() { return salary; }
    public java.sql.Date  getEmploymentDate() { return employmentDate; }
    public int getDepartmentId() { return departmentId; }
    public String getDepartmentName(){return departmentName;}
    //setter
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setSalary(double salary) { this.salary = salary; }
    public void setEmploymentDate(java.sql.Date  employmentDate) { this.employmentDate = employmentDate; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName){this.departmentName = departmentName;}
    @Override
    public String toString(){
        return "Employee{ " +
                super.toString() +
                ", specialization='" + specialization + '\'' +
                ", salary=" + salary +
                ", employmentDate=" + employmentDate  +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                " }";
    }

}
