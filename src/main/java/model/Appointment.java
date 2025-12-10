package main.java.model;

public class Appointment {
    private int appointmentId; // auto in DB
    private String appointmentDate;
    private String status;
    private int patientId;
    private int clinicId;
    // not in DB
    private String clinicName;

    public Appointment(String appointmentDate , String status, int patientId , int clinicId){
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.patientId = patientId;
        this.clinicId = clinicId;
    }
    // edit appointment in DB
    public Appointment(int appointmentId,String appointmentDate , String status, int patientId , int clinicId){
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.patientId = patientId;
        this.clinicId = clinicId;
    }
    // view in GUI
    public Appointment(int appointmentId,String appointmentDate , String status, int patientId , int clinicId, String clinicName){
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.patientId = patientId;
        this.clinicId = clinicId;
        this.clinicName = clinicName;
    }

    public int getId(){return appointmentId;}
    public String getAppointmentDate(){return appointmentDate;}
    public String getStatus(){return status;}
    public int getPatientId(){return patientId;}
    public int getClinicId(){return clinicId;}
    public String getClinicName(){return clinicName;}

    public void setAppointmentDate(String appointmentDate){this.appointmentDate =appointmentDate;}
    public void setStatus(String status){this.status = status;}
    public void setPatientId(int patientId){this.patientId = patientId;}
    public void setClinicId(int clinicId){this.clinicId = clinicId;}
    public void setClinicName(String clinicName){this.clinicName = clinicName;}

    @Override
    public String toString(){
        return "Appointment{ "+
                "appointmentId="+appointmentId+
                ", appointmentDate='"+appointmentDate+'\''+
                ", status='"+status+'\''+
                ", patientId="+patientId+
                ", clinicId="+clinicId+
                ", clinicName='"+clinicName+'\''+
                " }";
    }

}
