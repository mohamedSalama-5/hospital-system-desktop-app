package main.java.model;

import java.util.Date;

public class Department {
    private  int departmentId; // ID auto-increment in DB
    private String name;
    private int workHours;
    private Date createdAt;  // created at auto in DB

    public Department( String name, int workHours) {
        this.name = name;
        this.workHours = workHours;
    }


    public Department(int departmentId, String name, int workHours, Date createdAt) {
        this.departmentId = departmentId;
        this.name = name;
        this.workHours = workHours;
        this.createdAt = createdAt;
    }


    // getters
    public int getDepartmentId() { return departmentId; }
    public String getName() { return name; }
    public int getWorkHours() { return workHours; }
    public Date getCreatedAt() { return createdAt; }

    //setter
    public void setDepartmentId(int departmentId){this.departmentId = departmentId;}
    public void setName(String name) { this.name = name; }
    public void setWorkHours(int workHours) { this.workHours = workHours; }
    public void setCreatedAt(Date createdAt){this.createdAt = createdAt;}
    @Override
    public String toString(){
        return "Department{ "+
                ", departmentId= "+departmentId+
                ", name='"+name+'\''+
                ", workHours="+workHours+
                ", createdAt='"+createdAt+'\''+
                " }";
    }

}
