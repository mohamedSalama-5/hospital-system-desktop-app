package main.java.model;

import java.sql.Date;

public class Patient extends Person{
    private String gender;
    private String address;
    private String bloodType;
    private java.sql.Date  admissionDate;
    private java.sql.Date  dischargeDate;
    private Integer roomId;

    public Patient(String firstName,String lastName,String nationalId,
            String email, String phoneNumber,java.sql.Date  birthDate,
            String gender ,String address ,String bloodType ,
                   java.sql.Date  admissionDate ,java.sql.Date  dischargeDate ,Integer roomId  ){
        super( firstName, lastName, nationalId, email,  phoneNumber, birthDate);

        this.gender = gender;
        this.address = address;
        this.bloodType = bloodType;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomId = roomId;
    }


    public Patient(int id,String firstName,String lastName,String nationalId,
                   String email, String phoneNumber,java.sql.Date  birthDate,
                   String gender ,String address ,String bloodType ,
                   java.sql.Date  admissionDate ,java.sql.Date  dischargeDate ,Integer roomId  ){
        super(id, firstName, lastName, nationalId, email,  phoneNumber, birthDate);

        this.gender = gender;
        this.address = address;
        this.bloodType = bloodType;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomId = roomId;
    }

    //getter
    public String getGender(){return gender;}
    public String getAddress(){return address;}
    public String getBloodType(){return bloodType;}
    public java.sql.Date  getAdmissionDate(){return admissionDate;}
    public java.sql.Date  getDischargeDate(){return dischargeDate;}
    public Integer getRoomId(){return roomId;}

    //setter
    public void setGender(String gender){this.gender = gender;}
    public void setAddress(String address){this.address = address;}
    public void setBloodType(String bloodType){this.bloodType = bloodType;}
    public void setAdmissionDate(Date admissionDate){this.admissionDate = admissionDate;}
    public void setDischargeDate(Date dischargeDate){this.dischargeDate = dischargeDate;}
    public void setRoomId(int roomId){this.roomId = roomId;}

    @Override
    public String toString(){
        return "Patient{ " +
                super.toString() +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", admissionDate='" + admissionDate + '\'' +
                ", dischargeDate='" + dischargeDate + '\'' +
                ", roomId=" + roomId +
                " }";
    }

}
