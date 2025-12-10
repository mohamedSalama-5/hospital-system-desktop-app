package main.java.model;

public class Clinic {
    private int clinicId;
    private String name;
    private int departmentId;
    // not in DB
    private String departmentName;

    public Clinic(String name,int departmentId){
        this.name = name;
        this.departmentId = departmentId;
    }
    // edit Clinic in DB
    public Clinic(int clinicId, String name, int departmentId ){
        this.clinicId = clinicId;
        this.name = name;
        this.departmentId = departmentId;
    }
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

    @Override
    public String toString(){
        return "Clinic{ "+
                "clinicId="+clinicId+
                ", name='"+name+'\''+
                ", departmentId="+departmentId+
                ", departmentName='"+departmentName+'\''+
                " }";
    }
}
