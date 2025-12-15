package main.java.dao;
import main.java.db.DBConnection;
import main.java.model.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public Doctor insert(Doctor doctor) {
        String sql = "INSERT INTO doctor (f_name , l_name , national_id , specialization , salary , email , phone_number , employment_date , birth_date , department_id) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?) ";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            setStatement(statement,doctor);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                doctor.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Doctor updateByNationalId(String nationalId , Doctor newData){
        String sql = "UPDATE doctor " +
                "SET f_name = ? , l_name = ? , national_id = ? , specialization = ? , salary = ? , email = ? , phone_number = ? , employment_date = ? , birth_date = ? , department_id = ? " +
                "WHERE national_id = ? ";
        int row =0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            setStatement(statement,newData);
             row = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return (row>0)?newData:null;
    }

    public Doctor findByNationalId(String national_id){
        String sql = "SELECT d.id , d.f_name , d.l_name , d.national_id , d.specialization , d.salary , d.email , d.phone_number , d.employment_date , d.birth_date , d.department_id ," +
                " department.name AS department_name FROM doctor d " +
                "JOIN department ON d.department_id = department.id " +
                "WHERE d.national_id = ?";
        Doctor doctor = null;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,national_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                doctor = new Doctor(
                    result.getInt("id"),
                    result.getString("f_name"),
                    result.getString("l_name"),
                    result.getString("national_id"),
                    result.getString("email"),
                    result.getString("phone_number"),
                    result.getDate("birth_date"),
                    result.getString("specialization"),
                    result.getDouble("salary"),
                    result.getDate("employment_date"),
                    result.getInt("department_id"),
                    result.getString("department_name")
            );
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return doctor;
    }

    public List<Doctor> searchByName(String name){
        String sql = "SELECT d.id, d.f_name, d.l_name, d.national_id, d.specialization, d.salary, d.email, d.phone_number, d.employment_date, d.birth_date, d.department_id, department.name AS department_name " +
                "FROM doctor d " +
                "JOIN department ON d.department_id = department.id " +
                "WHERE d.f_name iLIKE ? OR d.l_name iLIKE ?" +
                "ORDER BY d.f_name, d.l_name ";
        List<Doctor> list = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            String searchName = "%"+name+"%";
            statement.setString(1,searchName);
            statement.setString(2,searchName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Doctor doctor = new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("f_name"),
                        resultSet.getString("l_name"),
                        resultSet.getString("national_id"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("birth_date"),
                        resultSet.getString("specialization"),
                        resultSet.getDouble("salary"),
                        resultSet.getDate("employment_date"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")
                );
                list.add(doctor);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public boolean delete(String nationalId){
        String sql = "DELETE FROM doctor WHERE national_id = ?";
        int row = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,nationalId);
            row = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return row >0;
    }

    public Integer getIdByName(String fullName){
        Integer doctorId  = null;
        String sql = " SELECT id from doctor WHERE CONCAT(f_name, ' ', l_name)  iLIKE ?";
        String searchName = "%"+fullName+"%";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,searchName);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                doctorId = result.getInt("id");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return doctorId;
    }

    public void setStatement(PreparedStatement statement , Doctor doctor)throws SQLException{
        statement.setString(1, doctor.getFirstName());
        statement.setString(2, doctor.getLastName());
        statement.setString(3, doctor.getNationalId());
        statement.setString(4, doctor.getSpecialization());
        statement.setDouble(5, doctor.getSalary());
        statement.setString(6, doctor.getEmail());
        statement.setString(7, doctor.getPhoneNumber());
        statement.setDate(8, doctor.getEmploymentDate());
        statement.setDate(9, doctor.getBirthDate());
        statement.setInt(10, doctor.getDepartmentId());
    }
    
}
