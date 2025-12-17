package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Bill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class BillDAO {

    // add
    public Bill insert(Bill bill , String patientNationalId){
        int effectedRow = 0;
        LocalDate date = LocalDate.now();
        PatientDAO patientDAO = new PatientDAO();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        bill.setPatientId(patientId);
        String sql = "INSERT INTO bill ( total_amount , status , date , patient_id )" +
                "VALUES(?,?,?,?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setDouble(1,bill.getTotalAmount());
            statement.setString(2,bill.getStatus());
            statement.setDate(3, Date.valueOf(date));
            statement.setInt(4,bill.getPatientId());
            effectedRow = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return (effectedRow > 0)?bill:null;
    }

    // get bills by patient national id

    public List<Bill> getBillByNationalId(String patientNationalId){
        List<Bill> list = new ArrayList<>();
        PatientDAO patientDAO = new PatientDAO();
        Integer patientId = patientDAO.getIdByNationalId(patientNationalId);
        String sql = "SELECT b.* , p.f_name AS f_name , p.l_name AS l_name " +
                "  FROM bill b " +
                "JOIN patient p ON p.patient_id = b.patient_id  WHERE b.patient_id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,patientId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Bill bill = new Bill(resultSet.getInt("id"),
                                resultSet.getString("f_name")+" "+resultSet.getString("l_name"),
                                patientNationalId,
                                resultSet.getDouble("total_amount"),
                                resultSet.getString("status")
                );
                list.add(bill);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    // get all bills
    public List<Bill> getAll(){
        List<Bill> list = new ArrayList<>();
        PatientDAO patientDAO = new PatientDAO();
        String sql = "SELECT b.* , p.f_name AS f_name , p.l_name AS l_name , p.national_id AS national_id " +
                "  FROM bill b " +
                "JOIN patient p ON p.patient_id = b.patient_id ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill(resultSet.getInt("id"),
                        resultSet.getString("f_name") + " " + resultSet.getString("l_name"),
                        resultSet.getString("national_id"),
                        resultSet.getDouble("total_amount"),
                        resultSet.getString("status")
                );
                list.add(bill);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    // update status
    public boolean updateStatus(int billId , String newStatus){
        String sql ="UPDATE bill SET status = ? WHERE id = ? ";
        int effectedRows = 0;

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,newStatus);
            statement.setInt(2,billId);
            effectedRows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return effectedRows >0;
    }

    // delete bill
    public boolean deleteBill(int billId){
        String sql = "DELETE FROM bill WHERE id = ? ";
        int effectedRows = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,billId);
            effectedRows = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return effectedRows > 0 ;
    }


}
