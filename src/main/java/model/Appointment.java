package main.java.model;

public class Appointment {
    private int appointmentId; // auto in DB
    private java.sql.Date appointmentDate;
    private String status;
    private int patientId;
    private int clinicId;
    // not in DB
    private String clinicName;
    private String patientName;

    public Appointment(java.sql.Date appointmentDate , String status){
        this.appointmentDate = appointmentDate;
        this.status = status;

    }
    // view in GUI
    public Appointment(int appointmentId,java.sql.Date appointmentDate , String status,  String clinicName){
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.clinicName = clinicName;
    }

    public int getId(){return appointmentId;}
    public java.sql.Date getAppointmentDate(){return appointmentDate;}
    public String getStatus(){return status;}
    public int getPatientId(){return patientId;}
    public int getClinicId(){return clinicId;}
    public String getClinicName(){return clinicName;}
    public String getPatientName(){return patientName;}


    public void setAppointmentId(int appointmentId){this.appointmentId = appointmentId;}
    public void setAppointmentDate(java.sql.Date appointmentDate){this.appointmentDate =appointmentDate;}
    public void setStatus(String status){this.status = status;}
    public void setPatientId(int patientId){this.patientId = patientId;}
    public void setClinicId(int clinicId){this.clinicId = clinicId;}
    public void setClinicName(String clinicName){this.clinicName = clinicName;}
    public void setPatientName(String patientName){this.patientName = patientName;}



}
