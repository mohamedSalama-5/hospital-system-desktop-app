package main.java.dao;

import main.java.db.DBConnection;
import main.java.model.Clinic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicDAO {

    // add
    public Clinic insert(Clinic clinic ){
        DepartmentDAO departmentDAO = new DepartmentDAO();
        String sql = "INSERT INTO clinic (name , department_id )" +
                "VALUES(?,?)";
        int affectedRows = 0;
        int departmentId =  departmentDAO.getIdByName(clinic.getDepartmentName());
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,clinic.getName());
            statement.setInt(2,departmentId);
            affectedRows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (affectedRows > 0)?clinic:null;
    }

    // get all
    public List<Clinic> getAll(){
        List<Clinic> list = new ArrayList<>();
        String sql ="SELECT c.* , d.name AS department_name  FROM clinic c " +
            "JOIN department d ON c.department_id = d.id ";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Clinic clinic = new Clinic(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("department_name")
                );
                list.add(clinic);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }

    // delete
    public boolean delete(String clinicName){
        int clinicId = getClinicId(clinicName);
        String sql = " DELETE FROM clinic WHERE id = ?";
        int affectedRows = 0;
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,clinicId);
            affectedRows = statement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return affectedRows>0;
    }

    public Integer getClinicId(String clinicName){
        Integer clinicId = null;
        String sql = " SELECT id FROM clinic WHERE name = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,clinicName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                clinicId = resultSet.getInt("id");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return clinicId;
    }




}
