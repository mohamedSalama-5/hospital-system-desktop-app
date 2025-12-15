package main.java.model;

public class Bill {
    private final int billId;
    private double totalAmount;
    private String status;
    private int patientId;
    // not in DB
    private transient String patientName;
    private transient String patientNationalId;

    // for add Bill
    public Bill(double totalAmount,String status){
            this(0,totalAmount,status,0);
    }

    // for get the bill from DB
    public Bill(int billId, double totalAmount, String status, int patientId){
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.patientId = patientId;
    }

    // for View the Bill in GUI
    public Bill(int billId,String patientName,String patientNationalId,double totalAmount,String status ){
        this(billId,totalAmount,status,0);
        this.patientName = patientName;
        this.patientNationalId = patientNationalId;
    }

    public int getId(){return billId;}
    public double getTotalAmount(){return totalAmount;}
    public String getStatus(){return status;}
    public int getPatientId(){return patientId;}
    public String getPatientName(){return patientName;}
    public String getPatientNationalId(){return patientNationalId;}

    public void setTotalAmount(double totalAmount){this.totalAmount= totalAmount;}
    public void setStatus(String status){this.status = status;}
    public void setPatientId(int patientId){this.patientId = patientId;}
    public void setPatientName(String patientName){this.patientName = patientName;}
    public void setPatientNationalId(String patientNationalId){this.patientNationalId = patientNationalId;}


    @Override
    public String toString(){
        return "Bill{ "+
                "billId= "+billId+
                ", Total Amount= "+totalAmount+
                ", Status='"+status+'\''+
                ", Patient Id="+patientId+
                ", Patient Name='"+patientName+'\''+
                ", Patient National Id="+patientNationalId+
                " }";
    }
}

