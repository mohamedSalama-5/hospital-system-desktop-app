package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Patient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PatientDAO {

    public Patient insert(Patient patient) {
        String sql = "INSERT INTO patient (f_name,l_name,national_id,birth_date,gender,email,phone_number,address,blood_type,admission_date,discharge_date,room_id)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,patient.getFirstName());
            statement.setString(2,patient.getLastName());
            statement.setString(3,patient.getNationalId());
            statement.setDate(4, patient.getBirthDate());
            statement.setString(5,patient.getGender());
            statement.setString(6,patient.getEmail());
            statement.setString(7,patient.getPhoneNumber());
            statement.setString(8,patient.getAddress());
            statement.setString(9,patient.getBloodType());
            statement.setDate(10, patient.getAdmissionDate());
            statement.setDate(11, patient.getDischargeDate());
            if (patient.getRoomId() != null) {
                statement.setInt(12, patient.getRoomId());
            } else {
                statement.setNull(12, Types.INTEGER);
            }
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                patient.setId(keys.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return patient;
    }

    public Patient updateByNationalId(String nationalId, Patient newData) {
        String sql = "UPDATE patient "+
                     "SET f_name = ? , l_name = ? , national_id = ? , birth_date = ? , gender = ? , email = ? , phone_number = ? , address = ? , blood_type = ? , admission_date = ? , discharge_date = ? , room_id = ? " +
                "WHERE national_id = ?";
        int effectedRow =0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,newData.getFirstName());
            statement.setString(2,newData.getLastName());
            statement.setString(3,newData.getNationalId());
            statement.setDate(4,newData.getBirthDate());
            statement.setString(5,newData.getGender());
            statement.setString(6,newData.getEmail());
            statement.setString(7,newData.getPhoneNumber());
            statement.setString(8,newData.getAddress());
            statement.setString(9,newData.getBloodType());
            statement.setDate(10,newData.getAdmissionDate());
            statement.setDate(11,newData.getDischargeDate());
            if (newData.getRoomId() == null) {
                statement.setNull(12, Types.INTEGER);
            } else {
                statement.setInt(12, newData.getRoomId());
            }
            statement.setString(13,nationalId);
            effectedRow=statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
            return (effectedRow > 0)? newData : null;
    }
    public Patient findByNationalId(String nationalId) {
        String sql = "SELECT id, f_name , l_name , national_id , birth_date , gender , email , phone_number , address , blood_type , admission_date , discharge_date , room_id FROM patient " +
                "WHERE national_id = ?";
        Patient patient = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nationalId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
             patient= mapResultSet(result);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return patient;

    }
    public boolean delete(String nationalId) {
        String sql = "DELETE FROM patient WHERE national_id = ?";
        int row =0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nationalId);
            row = statement.executeUpdate();
        }catch (SQLException e){e.printStackTrace();}
            return row > 0;
    }


    public List<Patient> getAll() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patient";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                list.add(mapResultSet(resultSet));
            }
        }catch (SQLException e){e.printStackTrace();}
        return list;
    }
    // search by name
    public List<Patient> searchByName(String name){
        List<Patient> list = new ArrayList<>();
        String sql ="SELECT * FROM patient WHERE f_name iLIKE ? OR l_name iLIKE ? ORDER BY admission_date ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            String searchName = "%"+name+"%";
            statement.setString(1,searchName);
            statement.setString(2,searchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                list.add(mapResultSet(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    //return patientId by nationalId
    public Integer getIdByNationalId(String nationalId){
        String sql = " SELECT id from patient WHERE national_id = ? ";
        Integer patientId = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nationalId);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                patientId = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientId;
    }


    public String getPatientNameByNationalId(String patientNationalId){
        String patientName =null;
        String sql = "SELECT f_name , l_name FROM patient WHERE national_id = ? ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1,patientNationalId);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                patientName = result.getString("f_name")+" "+result.getString("l_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patientName;
    }




    private Patient mapResultSet(ResultSet resultSet) throws SQLException{
        return new Patient(
                resultSet.getInt("id"),
                resultSet.getString("f_name"),
                resultSet.getString("l_name"),
                resultSet.getString("national_id"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getDate("birth_date"),
                resultSet.getString("gender"),
                resultSet.getString("address"),
                resultSet.getString("blood_type"),
                resultSet.getDate("admission_date"),
                resultSet.getDate("discharge_date"),
                resultSet.getInt("room_id")
        );
    }

}
