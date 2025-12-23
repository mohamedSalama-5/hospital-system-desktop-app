package main.java.model;

public class Clinic {
    private int clinicId;
    private String name;
    private int departmentId;
    // not in DB
    private String departmentName;

    // view the Clinic in GUI
    public Clinic(int clinicId , String name,int departmentId, String departmentName){
        this.clinicId = clinicId;
        this.name = name;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public int getId(){return clinicId;}
    public String getName(){return name;}
    public int getDepartmentId(){return departmentId;}
    public String getDepartmentName(){return departmentName;}

    public void setName(String name){this.name = name;}
    public void setDepartmentId(int departmentId){this.departmentId = departmentId;}
    public void setDepartmentName(String departmentName){this.departmentName = departmentName;}

}
