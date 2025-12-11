package main.java.model;

public class Report {
    private int reportId;
    private java.sql.Date reportDate;
    private String description;
    private int patientId;
    private int doctorId;
    // not in DB
    private String patientName;
    private String doctorName;
    private String patientNationalId;

    public Report(){};
    public Report(java.sql.Date reportDate, String description) {
        this.reportDate = reportDate;
        this.description = description;
    }

    public Report(int reportId , java.sql.Date reportDate, String description, int patientId, int doctorId){
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.description = description;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    // constructor to view the data in GUI
    public Report(int reportId, String patientName , String patientNationalId , java.sql.Date reportDate, String description, String doctorName){
        this.reportId = reportId;
        this.patientName = patientName;
        this.patientNationalId = patientNationalId;
        this.reportDate = reportDate;
        this.description = description;
        this.doctorName = doctorName;

    }

    public int getId(){return reportId;}
    public java.sql.Date getReportDate() {return reportDate;}
    public String getDescription() {return description;}
    public int getPatientId() {return patientId;}
    public int getDoctorId() {return doctorId;}
    public String getPatientName(){return patientName;}
    public String getPatientNationalId(){return patientNationalId;}
    public String getDoctorName(){return doctorName;}

    public void setReportDate(java.sql.Date reportDate) {this.reportDate = reportDate;}
    public void setDescription(String description) {this.description = description;}
    public void setPatientId(int patientId) {this.patientId = patientId;}
    public void setDoctorId(int doctorId) {this.doctorId = doctorId;}
    public void setPatientName(String patientName){this.patientName = patientName;}
    public void setPatientNationalId(String patientNationalId){this.patientNationalId = patientNationalId;}
    public void setDoctorName(String doctorName){this.doctorName = doctorName;}

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
