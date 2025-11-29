package main.java.model;

public class Patient extends Person{
    private String gender;
    private String address;
    private String bloodType;
    private String admissionDate;
    private String dischargeDate;
    private int roomId;

    public Patient(String firstName,String lastName,String nationalId,
            String email, String phoneNumber,String birthDate,
            String gender ,String address ,String bloodType ,
            String admissionDate ,String dischargeDate ,int roomId  ){
        super( firstName, lastName, nationalId, email,  phoneNumber, birthDate);

        this.gender = gender;
        this.address = address;
        this.bloodType = bloodType;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomId = roomId;
    }

    public String getGender(){return gender;}


}
