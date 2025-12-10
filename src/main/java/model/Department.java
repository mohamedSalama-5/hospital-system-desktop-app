package main.java.model;

public class Department {
    private  int departmentId; // ID auto-increment in DB
    private String name;
    private String workHours;
    private String createdAt;  // created at auto in DB

    public Department( String name, String workHours) {
        this.name = name;
        this.workHours = workHours;
    }


    public Department(int departmentId, String name, String workHours, String createdAt) {
        this.departmentId = departmentId;
        this.name = name;
        this.workHours = workHours;
        this.createdAt = createdAt;
    }


    // getters
    public int getDepartmentId() { return departmentId; }
    public String getName() { return name; }
    public String getWorkHours() { return workHours; }
    public String getCreatedAt() { return createdAt; }

    //setter
    public void setName(String name) { this.name = name; }
    public void setWorkHours(String workHours) { this.workHours = workHours; }

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
