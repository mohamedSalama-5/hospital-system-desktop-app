package main.java.model;

public class Reports {
    private int reportId;
    private String reportDate;
    private String description;
    private int patientId;
    private int doctorId;
    // not in DB
    private String patientName;
    private String patientNationalId;


    public Reports(String reportDate, String description, int patientId, int doctorId) {
        this.reportDate = reportDate;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Reports(int reportId , String reportDate, String description, int patientId, int doctorId){
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    // constructor to view the data in GUI
    public Reports(int reportId,String reportDate, String description, int patientId, int doctorId,String patientName,String patientNationalId){
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.patientNationalId = patientNationalId;
    }

    public int getId(){return reportId;}
    public String getReportDate() {return reportDate;}
    public String getDescription() {return description;}
    public int getPatientId() {return patientId;}
    public int getDoctorId() {return doctorId;}
    public String getPatientName(){return patientName;}
    public String getPatientNationalId(){return patientNationalId;}

    public void setReportDate(String reportDate) {this.reportDate = reportDate;}
    public void setDescription(String description) {this.description = description;}
    public void setPatientId(int patientId) {this.patientId = patientId;}
    public void setDoctorId(int doctorId) {this.doctorId = doctorId;}
    public void setPatientName(String patientName){this.patientName = patientName;}
    public void setPatientNationalId(String patientNationalId){this.patientNationalId = patientNationalId;}

    @Override
    public String toString() {
        return "Reports{" +
                "reportId="+reportId+
                ", reportDate='" + reportDate + '\'' +
                ", description='" + description + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", patientName='"+patientName+'\''+
                ", patientNationalI='"+patientNationalId+'\''+
                '}';
    }

}
