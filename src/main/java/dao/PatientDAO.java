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

    public Patient updateById(int id, Patient newData) {
        String sql = "UPDATE patient SET " +
                "f_name = ?, l_name = ?, national_id = ?, birth_date = ?, gender = ?, " +
                "email = ?, phone_number = ?, address = ?, blood_type = ?, admission_date = ?, " +
                "discharge_date = ?, room_id = ? " +
                "WHERE id = ?";

        int affectedRows = 0;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newData.getFirstName());
            statement.setString(2, newData.getLastName());
            statement.setString(3, newData.getNationalId());

            // Nullable columns
            if (newData.getBirthDate() != null)
                statement.setDate(4, newData.getBirthDate());
            else
                statement.setNull(4, Types.DATE);

            if (newData.getGender() != null)
                statement.setString(5, newData.getGender());
            else
                statement.setNull(5, Types.VARCHAR);

            if (newData.getEmail() != null)
                statement.setString(6, newData.getEmail());
            else
                statement.setNull(6, Types.VARCHAR);

            if (newData.getPhoneNumber() != null)
                statement.setString(7, newData.getPhoneNumber());
            else
                statement.setNull(7, Types.VARCHAR);

            if (newData.getAddress() != null)
                statement.setString(8, newData.getAddress());
            else
                statement.setNull(8, Types.VARCHAR);

            if (newData.getBloodType() != null)
                statement.setString(9, newData.getBloodType());
            else
                statement.setNull(9, Types.VARCHAR);

            if (newData.getAdmissionDate() != null)
                statement.setDate(10, newData.getAdmissionDate());
            else
                statement.setNull(10, Types.DATE);

            if (newData.getDischargeDate() != null)
                statement.setDate(11, newData.getDischargeDate());
            else
                statement.setNull(11, Types.DATE);

            if (newData.getRoomId() != null)
                statement.setInt(12, newData.getRoomId());
            else
                statement.setNull(12, Types.INTEGER);

            // WHERE
            statement.setInt(13, id);
            affectedRows = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRows > 0 ? newData : null;
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
