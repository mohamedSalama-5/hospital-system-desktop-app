package main.java.model;

public class Room {
    private  int roomId;// ID auto-increment in DB
    private int capacity;
    private int departmentId;
    // not in DB
    private String departmentName;


    public Room( int capacity, int departmentId) {
        this.capacity = capacity;
        this.departmentId = departmentId;
    }

    //constructor to edit
    public Room(int roomId, int capacity, int departmentId) {
        this.roomId = roomId;
        this.capacity = capacity;
        this.departmentId = departmentId;
    }

    // constructor to view the data in GUI
    public Room(int roomId, int capacity, int departmentId,String departmentName){
        this.roomId = roomId;
        this.capacity = capacity;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
    //getter
    public int getRoomId() { return roomId; }
    public int getCapacity() { return capacity; }
    public int getDepartmentId() { return departmentId; }

    //setter
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    @Override
    public String toString(){
        return "Room{ "+
                ", roomId="+roomId+
                ", capacity="+capacity+
                ", departmentId="+departmentId+
                ", departmentName='"+departmentName+'\''+
                " }";
    }
}
