package main.java.model;

public abstract class Person {
    private String firstName ;
    private String lastName ;
    private String nationalId;
    private String email;
    private String phoneNumber;
    private String birthDate;


    public Person(String firstName,String lastName,String nationalId,String email, String phoneNumber,String birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    // getter

    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String getNationalId(){return nationalId;}
    public String getEmail(){return email;}
    public String getPhoneNumber(){return phoneNumber;}
    public String getBirthDate(){return birthDate;}

    //setter
    public void setFirstName(String firstName){this.firstName = firstName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public void setNationalId(String nationalId){this.nationalId= nationalId;}
    public void setEmail(String email){this.email = email;}
    public void setPhoneNumber(String phoneNumber){this.phoneNumber = phoneNumber;}
    public void setBirthDate(String birthDate){this.birthDate = birthDate;}


}
